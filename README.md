 <div align=center>
 
 <h3>볼만한웹툰없나</h3>
 </div>
 

## 📚목차
1. [📃 Description](#📃-description)
2. [🌍 Environment](#🌍-environment)
3. [🔉 Data](#🔉-Data)

## 📃 Description

>### 각자 평가한 웹툰의 점수, MBTI, 성별, 나이 데이터를 바탕으로 개인의 취향에 맞는 웹툰을 추천해주는 서비스입니다.<br>
Link: https://recommtoon.com/ 


![image](https://user-images.githubusercontent.com/90108877/202189942-4e234ed1-f140-4892-9223-673c49fd958c.png)

### 서비스 목록

* [웹툰 평가](#웹툰-평가)
* [친구의 웹툰](#친구의-웹툰)
* [각 웹툰별 의견 공유](#각-웹툰별-의견-공유)
* [웹툰 검색](#웹툰-검색)
* [웹툰 추천](#웹툰-추천)


### 웹툰 평가
![image](https://user-images.githubusercontent.com/90108877/202190281-747546af-0d0d-443c-a3fd-9dd3fb27e586.png)


자신이 봤던 웹툰에 대한 평가를 할 수 있는 메뉴입니다. 이 평가를 바탕으로 성별, MBTI, 나이와 함께 웹툰을 추천받게 됩니다.
### 친구의 웹툰
![image](https://user-images.githubusercontent.com/90108877/202190743-5895765f-e737-4e7c-bfa6-c13cf963d880.png)


지인이나 친구의 아이디를 입력해 친구가 평가한 웹툰을 공유받고 서로 의견을 나눌 수 있는 메뉴입니다.<br>
웹툰에 관련한 흥미를 높이고 친구와의 소통을 더 활발하게 할 수 있습니다.

### 각 웹툰별 의견 공유
![image](https://user-images.githubusercontent.com/90108877/202191088-bf6397e4-adc5-458a-8f8f-985be3ec5e64.png)

현재 웹툰들에도 댓글기능이 있지만 해당 회차에 대한 댓글이라 맘에 들지 않았을 때 <br>
요리 레시피같은 웹툰과 관계없는 댓글을 다는 등의 댓글 테러를 하는 경우도 있어 웹툰에 대한 의견 나눔이 아쉽기 때문에 웹툰 전체에 대한 <br>
생각, 느낌을 공유할 수 있는 공간입니다.

### 웹툰 검색
![image](https://user-images.githubusercontent.com/90108877/202191185-c33266ce-1c30-42e3-8a55-736643b1bd5a.png)

특정 웹툰에 대한 의견을 보고 싶거나 의견을 나타내고 싶을 때 검색할 수 있습니다.

### 웹툰 추천
![image](https://user-images.githubusercontent.com/90108877/202191340-a34307b7-847d-4965-b420-3dcec6a1443d.png)

![image](https://user-images.githubusercontent.com/90108877/202191416-dd289013-1a1f-411b-a434-4b13f46035f9.png)

개인 취향에 맞는 웹툰을 MBTI, 나이, 성별, 평가데이터를 바탕으로 content based filtering을 통해 추천받고,<br>
MBTI별 선호하는 웹툰을 파악할 수 있습니다. 

Python 라이브러리를 이용했으며, 선호장르를 파악할 수 있다고 판단되는 MBTI 기능유형 4유형으로 분류해 코사인 유사도를 구한다. <br>
웹툰 평가데이터의 코사인유사도와 유저 정보의 코사인유사도를 따로 구해 전처리하여 합산한다. <br>

최종 코사인유사도 값을 통해 KNN 알고리즘을 이용하여 Collaborative Filtering을 진행하여 점수가 높은 <br>
상위 20개의 웹툰을 추천한다.




## 🌍 Environment

* Language : ![파이썬](https://img.shields.io/badge/python-blue) ![자바](https://img.shields.io/badge/-java-orange) 
![자스](https://img.shields.io/badge/-javascript-red) ![html](https://img.shields.io/badge/html-9cf) ![html](https://img.shields.io/badge/css-yellow)
* Framework : ![SpringBoot](https://img.shields.io/badge/-SpringBoot-brightgreen) ![BootStrap](https://img.shields.io/badge/-BootStrap-blueviolet) ![Flask](https://img.shields.io/badge/-Flask-blue)
* Database:  ![mariaDB](https://img.shields.io/badge/-MariaDB-yellow)

## 🔉 Data & Server
  ![image](https://user-images.githubusercontent.com/90108877/201485868-e7097add-096b-494f-8de8-b13e2d7cb94d.png)
  
  Flask로 "user_id" 정보를 json 데이터로 보내 작동시킨 후 DB의 데이터를 스프링부트 서버에서 클라이언트에게 보여준다.
  
  ![image](https://user-images.githubusercontent.com/90108877/209707307-c56bd5de-bb65-4ea0-958b-5d91054a4ad2.png)

  
```python
    {
       "Webtoon_data":[
           {
                "title_id": 737628,
                "title_name": "별이삼샵",
                "author": "혀노",
                "day": "일"
                "genre": "스토리, 드라마"
                "story": ㄱ나니? 발신자 제한번호로 그녀에게 마음을 전했던 이야기. 
                         '남과여' 혀노 작가가 담아낸
                          촌스럽지만 풋풋했던 2000년대 그 시절.
                "link" : "https://comic.naver.com/webtoon/list?titleId=737628",
                "img_src": "https://shared-comic.pstatic.net/thumb/webtoon/737628/thumbnail/thumbnail_IMAG06_64a2157c-49e6-422a-9628-9181f62e405c.jpg"
           }   
       ]
    }
    
  

