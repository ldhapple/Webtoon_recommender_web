#!/usr/bin/env python
# coding: utf-8

import pymysql
import pandas as pd
import numpy as np
from flask import Flask, request, redirect
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import train_test_split
from sklearn.metrics.pairwise import cosine_similarity
import json
import os

app = Flask(__name__)

conn = None
cur = None
sql = ""

conn = pymysql.connect(host='127.0.0.1', user='root', password='1234', db='mydb', charset='utf8' )
# conn = pymysql.connect(user='db01', password='db01', host='mariadb', port=3000, db='webtoonRecommender_db', charset='utf8')
cur = conn.cursor()

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
    df = pd.DataFrame(results, columns=['id', 'user', 'webtoonid', 'rank'])
    return df

user = search_user_data(conn)
webtoons = search_webtoon_data(conn)
ratings = search_rating_data(conn)
recommendation = search_recommendation(conn)

#단순 평점 높은순
def recom_webtoon(n_items):
    webtoon_mean = ratings.groupby(['webtoon_id'])['rating'].mean()
    webtoon_sort = webtoon_mean.sort_values(ascending=False)[:n_items]
    recom_webtoons = webtoons.loc[webtoon_sort.index]
    recommendations = recom_webtoons['title_name']
    return recommendations

users = user[['user_id', 'MBTI', 'AGE', 'SEX']]
ratings = ratings[['user_id','webtoon_id', 'rating']]


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

def setting(): 
    conn = pymysql.connect(host='127.0.0.1', user='root', password='1234', db='mydb', charset='utf8' )
    # conn = pymysql.connect(user='db01', password='db01', host='mariadb', port=3000, db='webtoonRecommender_db', charset='utf8')
    cur = conn.cursor()
    ratings = search_rating_data(conn)
    
    ratings = ratings[['user_id','webtoon_id', 'rating']]

    x = ratings.copy()
    y = ratings['user_id']

    x_train, x_test, y_train, y_test = train_test_split(x,y,
                                                    test_size = 0.25)

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


def RMSE(y_true, y_pred):
    return np.sqrt(np.mean((np.array(y_true) - np.array(y_pred))**2))

def score(model):
    id_pairs = zip(x_test['user_id'], x_test['webtoon_id']) # 테스트 데이터 간의 user_id와 webtoon_id 페어를 맞춰 튜플화
    y_pred = np.array([model(user,webtoon) for (user,webtoon) in id_pairs]) # 예측값
    print(y_pred)
    y_true = np.array(x_test['rating']) # 테스트데이터의 실제 값
    print(y_true)
    return RMSE(y_true, y_pred)

merged_ratings = pd.merge(x_train, users)
users.set_index('user_id')

MBTI_mean = merged_ratings[['webtoon_id', 'MBTI', 'rating']].groupby(['webtoon_id', 'MBTI'])['rating'].mean()

def cf_mbti(user_id, webtoon_id):
    if webtoon_id in rating_matrix.columns:
        MBTI = users.loc[user_id]['MBTI']
        if MBTI in MBTI_mean[webtoon_id].index:
            MBTI_rating = MBTI_mean[webtoon_id][MBTI]
        else:
            MBTI_rating = 3.0
    else:
        MBTI_rating = 3.0
    return MBTI_rating

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


@app.route('/recom_webtoon', methods=['GET', 'POST'])
def recom_webtoon():
    setting()
    params = request.get_json()
    user_id = params['user_id'][0]
    n_items = 20
    neighbor_size = 30
    user_webtoon = rating_matrix.loc[user_id].copy()

    for webtoon in rating_matrix.columns:
        if pd.notnull(user_webtoon.loc[webtoon]):
            user_webtoon.loc[webtoon] = 0

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

    return redirect("http://localhost:8080/login")

def score(model, neighbor_size = 0):
    id_pairs = zip(x_test['user_id'], x_test['webtoon_id'])
    y_pred = np.array([model(user, webtoon, neighbor_size) for (user, webtoon) in id_pairs])
    y_true = np.array(x_test['rating'])
    return RMSE(y_true, y_pred)

# def main():
#     recom_webtoon(user_id = 5, n_items = 20, neighbor_size= 30)

if __name__ == "__main__":
    app.run('0.0.0.0', port="5000", debug=True)
