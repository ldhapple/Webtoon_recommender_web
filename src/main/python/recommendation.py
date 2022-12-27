import pymysql
import pandas as pd
import numpy as np

conn = None
cur = None
sql = ""

def search_user_data(conn):
    sql = 'SELECT * FROM user'
    cur.execute(sql)
    results = cur.fetchall()
    df = pd.DataFrame(results, columns=['user_id', 'username', 'password', 'enabled', 'MBTI', 'SEX', 'AGE'])
    return df

def search_webtoon_data(conn):
    sql = 'SELECT * FROM webtoon_data'
    cur.execute(sql)
    results = cur.fetchall()
    df = pd.DataFrame(results, columns=['title_id', 'title_name', 'author', 'day', 'genre', 'story', 'link', 'img_src'])
    webtoons = df.set_index('title_id')
    return webtoons

def search_rating_data(conn):
    sql = 'SELECT * FROM rating'
    cur.execute(sql)
    results = cur.fetchall()
    df = pd.DataFrame(results, columns=['id', 'user_id', 'webtoon_id', 'rating'])
    return df

def search_recommendation(conn):
    sql = 'SELECT * FROM recommendation'
    cur.execute(sql)
    results = cur.fetchall()
    df = pd.DataFrame(results, columns=['user', 'webtoonid', 'rank'])
    return df

user = search_user_data(conn)
webtoons = search_webtoon_data(conn)
ratings = search_rating_data(conn)
recommendation = search_recommendation(conn)

users = user[['user_id', 'MBTI', 'AGE', 'SEX']]
ratings = ratings[['user_id','webtoon_id', 'rating']]

from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
x = ratings.copy()
y = ratings['user_id']

user = users.copy()

for i in range(len(user)):
    if 'TJ' in user.loc[i, 'MBTI']:
        user.loc[i, 'MBTI'] = 0.1
    elif 'FJ' in user.loc[i, 'MBTI']:
        user.loc[i, 'MBTI'] = 0.2
    elif 'FP' in user.loc[i, 'MBTI']:
        user.loc[i, 'MBTI'] = 0.3
    elif 'TP' in user.loc[i, 'MBTI']:
        user.loc[i, 'MBTI'] = 0.4    
    
#     if user.loc[i, 'SEX'] == 'Male':
#         user.loc[i, 'SEX'] = 0.5
#     else:
#         user.loc[i, 'SEX'] = 1
        
    if user.loc[i, 'AGE'] / 10 < 2:
        user.loc[i, 'AGE'] = 0.1
    elif user.loc[i, 'AGE'] / 20 < 2:
        user.loc[i, 'AGE'] = 0.2
    elif user.loc[i, 'AGE'] / 30 < 2:
        user.loc[i, 'AGE'] = 0.3
    elif user.loc[i, 'AGE'] / 40 < 2:
        user.loc[i, 'AGE'] = 0.4
    elif user.loc[i, 'AGE'] / 50 < 2:
        user.loc[i, 'AGE'] = 0.5
        
user_matrix = user.pivot(index = 'user_id',
                        columns = 'SEX',
                        values = 'MBTI')


  
x_train, x_test, y_train, y_test = train_test_split(x,y,
                                                    test_size = 0.25)
                                                    
from sklearn.metrics.pairwise import cosine_similarity
rating_matrix = x_train.pivot(index = 'user_id',
                              columns = 'webtoon_id',
                              values = 'rating')

matrix_dummy = rating_matrix.copy().fillna(0) # 원본 손상 방지
user_matrix_dummy = user_matrix.copy().fillna(0)

user_rating_similarity = cosine_similarity(matrix_dummy, matrix_dummy)

user_info_similarity = cosine_similarity(user_matrix_dummy, user_matrix_dummy)

user_rating_similarity = pd.DataFrame(user_rating_similarity,
                               index = rating_matrix.index,
                               columns = rating_matrix.index)
user_info_similarity = pd.DataFrame(user_info_similarity,
                               index = user_matrix.index,
                               columns = user_matrix.index)


user_similarity = user_rating_similarity.add(user_info_similarity)
user_similarity = user_similarity.dropna(how='all')
user_similarity = user_similarity.dropna(how='all', axis=1)

def CF_knn(user_id, webtoon_id, neighbor_size = 0):
    
    if webtoon_id in rating_matrix.columns:
        sim_scores = user_similarity[user_id].copy()
        webtoon_ratings = rating_matrix[webtoon_id].copy()
        none_rating_idx = webtoon_ratings[webtoon_ratings.isnull()].index
        webtoon_ratings = webtoon_ratings.dropna()
        sim_scores = sim_scores.drop(none_rating_idx)

        if neighbor_size == 0:
            mean_rating = np.dot(sim_scores, webtoon_ratings) / sim_scores.sum()

        else:
            if len(sim_scores) > 1:
                neighbor_size = min(neighbor_size, len(sim_scores)) #sim_score보다 neighbor_size가 클 수 있기 때문에 크기를 맞춰줌.
                sim_scores = np.array(sim_scores)
                webtoon_ratings = np.array(webtoon_ratings)
                user_idx = np.argsort(sim_scores) #sim_scores를 오름차순으로 index값을 추출
                sim_scores = sim_scores[user_idx][-neighbor_size:]
                webtoon_ratings = webtoon_ratings[user_idx][-neighbor_size:] #neighbor_size 만큼 추출
                score_sum = sim_scores.sum()
                if score_sum == 0.0:
                    score_sum = 0.1
                mean_rating = np.dot(sim_scores, webtoon_ratings) / sim_scores.sum() # 행렬 곱

            else:
                mean_rating = 3.0
    else:
        mean_rating = 3.0
        
    return mean_rating

def recom_webtoon(user_id, n_items, neighbor_size = 30):
    user_webtoon = rating_matrix.loc[user_id].copy()

    for webtoon in rating_matrix.columns:
        if pd.notnull(user_webtoon.loc[webtoon]):
            user_webtoon.loc[webtoon] = 0 # 해당 웹툰을 이미 평가한 경우는 제외.
    
        else:
            user_webtoon.loc[webtoon] = CF_knn(user_id, webtoon, neighbor_size)

    webtoon_sort = user_webtoon.sort_values(ascending=False)[:n_items]
    recom_webtoon = webtoons.loc[webtoon_sort.index]
    recommendations = recom_webtoon
    
    for i in range(n_items):
        if ((recommendation['user']==user_id).any()) == False :
            SQL = "INSERT INTO recommendation SET user=%s, webtoonid=%s, rank=%s"
            SQL_data = (user_id, recommendations.index[i], i+1)
            cur.execute(SQL, SQL_data)
            conn.commit()
        else:
            SQL = "UPDATE recommendation SET user=%s, webtoonid=%s, rank=%s WHERE user=%s and rank=%s"
            SQL_data = (user_id, recommendations.index[i], i+1, user_id, i+1)
            cur.execute(SQL, SQL_data)
            conn.commit()

    return recommendations