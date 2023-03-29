# 청과물 가격을 조회 API 중계 웹 어플리케이션 서버

#### Swagger 
> http://localhost:8080/swagger-ui.html
#### 브라우저
> http://localhost:8080/index.html

### 1. [GET] 과일 토큰 발급
```
/fruits/token
```
API 호출을 위한 access token을 발급합니다.

**Request**
- curl
```
curl -X GET "http://localhost:8080/fruits/token" -H "accept: */*"
```

**Response**
- Header
```
connection: keep-alive 
content-type: application/json;charset=UTF-8 
date: Wed, 29 Mar 2023 13:40:02 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 
```
- Body
```
{
  "status": "OK",
  "message": "성공",
  "data": {
    "accessToken": "RjeFylZNjbeDw5XGvI-hdctXALr9RDs7mCjH3AMXTlE43ReAgdYeKYFRNySt7qhD"
  }
}
```

### 2. [GET] 과일 목록 조회
```
/fruits/list
```
과일 목록을 제공합니다.
header 내 token이 요구됩니다.

**Request**
- Header
```
token: RjeFylZNjbeDw5XGvI-hdctXALr9RDs7mCjH3AMXTlE43ReAgdYeKYFRNySt7qhD
```
- curl
```
curl -X GET "http://localhost:8080/fruits/list" -H "accept: */*" -H "token: RjeFylZNjbeDw5XGvI-hdctXALr9RDs7mCjH3AMXTlE43ReAgdYeKYFRNySt7qhD"
```

**Response**
- Header
```
connection: keep-alive 
content-type: application/json;charset=UTF-8 
date: Wed, 29 Mar 2023 13:51:45 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 
```
- Body
```
{
  "status": "OK",
  "message": "성공",
  "data": [
    "배",
    "토마토",
    "사과",
    "바나나"
  ]
}
```
### 3. [GET] 과일 가격 조회
```
/fruits/price?name=사과
```
이름이 지정된 과일의 가격 정보를 조회합니다.
header 내 access token이 요구됩니다.

토큰이 없거나 지정된 이름에 해당하는 정보가 없을 경우 400 응답을 반환합니다.

**Request**
- Header
```
token: RjeFylZNjbeDw5XGvI-hdctXALr9RDs7mCjH3AMXTlE43ReAgdYeKYFRNySt7qhD
```
- Param
```
name : 사과 // 조회할 과일의 이름
```
- curl
```
curl -X GET "http://localhost:8080/fruits/price?name=%EC%82%AC%EA%B3%BC" -H "accept: */*" -H "token: RjeFylZNjbeDw5XGvI-hdctXALr9RDs7mCjH3AMXTlE43ReAgdYeKYFRNySt7qhD"
```

**Response**
- Header
```
connection: keep-alive 
content-type: application/json;charset=UTF-8 
date: Wed, 29 Mar 2023 14:03:37 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 
```
- Body
```
{
  "status": "OK",
  "message": "성공",
  "data": {
    "name": "사과",
    "price": 1500
  }
}
```
### 4. [GET] 채소 토큰 발급
```
/vegetables/token
```
API 호출을 위한 access token을 발급합니다.


**Request**
- curl
```
curl -X GET "http://localhost:8080/vegetables/token" -H "accept: */*"
```

**Response**
- Header
```
connection: keep-alive 
content-type: application/json;charset=UTF-8 
date: Wed, 29 Mar 2023 14:07:14 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 
```
- Body
```
{
  "status": "OK",
  "message": "성공",
  "data": {
    "accessToken": "lilF0MnV3IkOlZQ6UhwXntU88FCERkvVRbZxRtiqEsCYzVDY_I2uW9m4Tk5qqzLG"
  }
}
```
### 5. [GET] 채소 목록 조회
```
/vegetables/list
```
채소 목록을 조회합니다.
header 내 access token이 요구됩니다.

**Request**
- Header
```
token: lilF0MnV3IkOlZQ6UhwXntU88FCERkvVRbZxRtiqEsCYzVDY_I2uW9m4Tk5qqzLG
```
- curl
```
curl -X GET "http://localhost:8080/vegetables/list" -H "accept: */*" -H "token: lilF0MnV3IkOlZQ6UhwXntU88FCERkvVRbZxRtiqEsCYzVDY_I2uW9m4Tk5qqzLG"
```

**Response**
- Header
```
connection: keep-alive 
content-type: application/json;charset=UTF-8 
date: Wed, 29 Mar 2023 14:07:26 GMT 
keep-alive: timeout=60 
transfer-encoding: chunked 
```
- Body
```
{
  "status": "OK",
  "message": "성공",
  "data": [
    "치커리",
    "토마토",
    "깻잎",
    "상추"
  ]
}
```
### 4. [GET] 채소 토큰 발금
```
/vegetables/price?name=치커리
```
이름이 지정된 과일의 가격 정보를 조회합니다.
header 내 access token이 요구됩니다.

토큰이 없거나 지정된 이름에 해당하는 정보가 없을 경우 400 응답을 반환합니다.

**Request**
- Header
```
token: lilF0MnV3IkOlZQ6UhwXntU88FCERkvVRbZxRtiqEsCYzVDY_I2uW9m4Tk5qqzLG
```
- Param
```
name : 치커리 // 조회할 채소의 이름
```
- curl
```
curl -X GET "http://localhost:8080/vegetables/price?name=치커리" -H "accept: */*" -H "token: lilF0MnV3IkOlZQ6UhwXntU88FCERkvVRbZxRtiqEsCYzVDY_I2uW9m4Tk5qqzLG"
```

**Response**
- Header
``` 
connection: keep-alive 
 content-type: application/json;charset=UTF-8 
 date: Wed, 29 Mar 2023 14:07:31 GMT 
 keep-alive: timeout=60 
 transfer-encoding: chunked 
```
- Body
```
{
  "status": "OK",
  "message": "성공",
  "data": {
    "name": "치커리",
    "price": 1600
  }
}
```