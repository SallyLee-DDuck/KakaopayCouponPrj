# KakaopayCouponPrj
SallyLee KakaopayCouponPrePrj(no.2)

## 목차
* [과제](#과제)
* [개발환경](#개발환경)
* [테스트(실행)방법](#테스트(실행)방법)

#### 과제구현 및 Readme작성 참고사항
1. 테스트결과 제출을 위해 response부분은 테스트데이터로 작성
2. 쿠폰만료일자는 발급 후 30일로 임의지정함
3. 만료일자 안내 구현 시, 알림톡 기능 감안하여 휴대폰번호로 DTO구성

## 과제
Rest API 기반 쿠폰시스템:  
사용자에게 할인, 선물등 쿠폰을 제공하는 서비스를 개발하려 합니다.  
아래 기능명세에 대한 쿠폰시스템 API를 개발하고 Test 코드를 작성하세요.

#### 필수 문제 (필수문제 및 선택문제 구현완료)
조건 : 각각의 쿠폰은 만료기간이 존재하며, 쿠폰형식은 번호, 코드, 자릿수등 자유롭게 선택
1. 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API를 구현하세요.
input : N
2. 생성된 쿠폰중 하나를 사용자에게 지급하는 API를 구현하세요.
output : 쿠폰번호(XXXXX-XXXXXX-XXXXXXXX)
3. 사용자에게 지급된 쿠폰을 조회하는 API를 구현하세요.
4. 지급된 쿠폰중 하나를 사용하는 API를 구현하세요. (쿠폰 재사용은 불가)
input : 쿠폰번호
5. 지급된 쿠폰중 하나를 사용 취소하는 API를 구현하세요. (취소된 쿠폰 재사용 가능)
input : 쿠폰번호
6. 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현하세요.
#### 선택 문제
7. 발급된 쿠폰중 만료 3일전 사용자에게 메세지(“쿠폰이 3일 후 만료됩니다.”)를 발송하는 기능을 구현하
세요. (실제 메세지를 발송하는것이 아닌 stdout 등으로 출력하시면 됩니다.)

### 제약사항(필수: 제약사항 준수 완료)
* API 기능명세에서 제시한 기능을 개발하세요. 
* 단위 테스트 (Unit Test) 코드를 개발하여 각 기능을 검증하세요.
* 프로그램 언어는 평가에 반영되지 않으니 자유롭게 선택하세요. 
* 각 API의 HTTP Method들( GET | POST | PUT | DEL )은 자유롭게 선택하세요. 
* README.md 파일을 추가하여, 개발 프레임워크, 문제해결 전략, 빌드 및 실행 방법을 기술하세요.

### 제약사항(선택)
* API 인증을 위해 JWT(Json Web Token)를 이용해서 Token 기반 API 인증 기능을 개발하고 각 API 호출 시에 HTTP Header에 발급받은 토큰을 가지고 호출하세요. 
  * signup 계정생성 API: ID, PW를 입력 받아 내부 DB에 계정을 저장하고 토큰을 생성하여 출력한다. 
    * 단, 패스워드는 안전한 방법으로 저장한다. 
  * signin 로그인 API: 입력으로 생성된 계정 (ID, PW)으로 로그인 요청하면 토큰을 발급한다. 
* 100억개 이상 쿠폰 관리 저장 관리 가능하도록 구현할것 
* 10만개 이상 벌크 csv Import 기능 대용량 트랙픽(TPS 10K 이상)을 고려한 시스템 구현 
* 성능테스트 결과 / 피드백

## 개발환경
* Java11
* Spring Boot 2.3.2 RELEASE
* Maven
* JPA
* H2
## 테스트(실행)방법
* clone package 복사 후, swagger-ui로 접속하여 테스트 가능 
http://localhost:8080/swagger-ui.html

### 1. 랜덤한 코드(16자리) N개 생성 후 데이터베이스에 보관: 만료일은 생성일 로부터 30일이후로 임의 설정(makeCoupon)
* URL
```
POST - /couponApi/couponList
```
* Parameters(couponDTO)
```
{
  "cpNum": "string",
  "custmHp": "string",
  "expireDt": "2020-08-11T07:13:22.277Z",
  "issueYn": true,
  "totCnt": 100, // 생성할 갯수만 입력(나머지는 null)
  "useYn": true
}
```
* Response
```
SUCCESS - 200 
key는 고유의 키로 numbering되어 생성되며, expireDt는 발급한 날짜 기준 +30일로 셋팅됨

[
  {
    "key": 1,
    "cpNum": "Q2LKTDZME6A5VFH5",
    "useYn": false,
    "issueYn": false,
    "custmHp": null,
    "expireDt": "2020-09-10"
  },
  {
    "key": 2,
    "cpNum": "LBNRYP1WNK44HARH",
    "useYn": false,
    "issueYn": false,
    "custmHp": null,
    "expireDt": "2020-09-10"
  },
  {
    "key": 3,
    "cpNum": "D46JQHQSTRQG5E2M",
    "useYn": false,
    "issueYn": false,
    "custmHp": null,
    "expireDt": "2020-09-10"
  },
  {
    "key": 4,
    "cpNum": "2M8XWMLTDS5RIM0Q",
    "useYn": false,
    "issueYn": false,
    "custmHp": null,
    "expireDt": "2020-09-10"
  }, ...
  {
    "key": 99,
    "cpNum": "7KLOT9UFCIH4RZ67",
    "useYn": false,
    "issueYn": false,
    "custmHp": null,
    "expireDt": "2020-09-10"
  },
  {
    "key": 100,
    "cpNum": "WDTTQPG4BV0QSCXZ",
    "useYn": false,
    "issueYn": false,
    "custmHp": null,
    "expireDt": "2020-09-10"
  }
]
```

### 2.생성된 쿠폰 중 하나를 사용자에게 지급(issueCoupon)
* URL
```
PUT - /couponApi/couponList
```
* Parameters(couponDTO)
```
{
  "cpNum": "string",
  "custmHp": "string", //휴대폰번호만 입력
  "expireDt": "2020-08-11T07:21:45.031Z",
  "issueYn": true,
  "totCnt": 0,
  "useYn": true
}
```
* Response
```
SUCCESS - 200 

Q2LKTDZME6A5VFH5 //해당 휴대폰번호에게 발급된 쿠폰코드
```

### 3.사용자에게 지급된 쿠폰을 조회(issueCouponList)
* URL
```
GET - /couponApi/couponList
```

* Response
```
SUCCESS - 200 

[
  {
    "key": 1,
    "cpNum": "Q2LKTDZME6A5VFH5", //위에서 발급한 쿠폰 
    "useYn": false,
    "issueYn": true,
    "custmHp": "010-0000-0000",
    "expireDt": "2020-09-10"
  }
]
```

### 4. 사용자가 지급된 쿠폰 중 하나를 사용함(useCoupon)
* URL
```
PUT - /couponApi/couponList/{cpNum}/usetrue
```
* PathVariable
```
{cpNum} : String  // 쿠폰번호
```
* Response
```
SUCCESS - 200 

{
  "key": 1,
  "cpNum": "Q2LKTDZME6A5VFH5",
  "useYn": true, //쿠폰사용처리됨
  "issueYn": true,
  "custmHp": "010-0000-0000",
  "expireDt": "2020-09-10"
}
```

### 5. 사용자가 지급된 쿠폰 중 하나를 사용함(useCancelCoupon)
* URL
```
PUT - /couponApi/couponList/{cpNum}/usefalse
```
* PathVariable
```
{cpNum} : String  // 쿠폰번호
```
* Response
```
SUCCESS - 200 

{
  "key": 1,
  "cpNum": "Q2LKTDZME6A5VFH5",
  "useYn": false, // 사용취소됨
  "issueYn": true,
  "custmHp": "010-0000-0000",
  "expireDt": "2020-09-10"
}
```

### 6.발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회
* URL
```
GET - /couponApi/couponList/expiredCouponList
```
* Response
```
SUCCESS - 200 
[] //현 시점 기준으로 만료예정데이터없음(retrun DTO는 CouponDTO)
```

### 7.발급된쿠폰 중 3일 전 만료 쿠폰을 오전 10시에 전송(배치작업으로 실행)
* ExpireCouponNoticeJob 에서 구현완료
```
작업시작시간: 10시
해당 작업은 현재 10시에 당일날짜 기준으로 만료일 3일 후 확인한 다음 Sysout으로 찍게 되어있으나,
실제로는 알림톡 api연결을 활용하여 구현가능함
```

#### 계정생성(패스워드 안전한 방법으로 구현)
* URL
```
POST - /sign-up
```

* Parameters(customerDTO)
```
{
  "custmID": "string", //ID
  "custmpw": "string"  //PW
}
```
* Response (테스트결과 파일로 표현)
```
SUCCESS - 200 
{
  "key": 1,
  "custmId": "sally",
  "custmPw": "$2a$10$gleG0eti17Bw9hhImalvHOdxV91sebrBH508Wbf0sMqDBkwooYT9W"
}
```
#### 로그인
* URL
```
GET - /sign-in
```
* Parameters
```
id, pw
```
* Response
```
SUCCESS - 200 
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWxseSIsImlhdCI6MTU5NzEyOTg1NSwiZXhwIjoxNTk3MTMwNDU1fQ.HppafrcwGlw6lp3FrOFbTRpDb_7bumHKnqxvM1A-6Uw
```
