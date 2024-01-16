# Book Review - BE

## 기능
- 읽은 책에 대한 리뷰(평점, 리뷰, 좋은 글귀)
- 읽고 싶은 책에 대한 리스트

## 화면 정의서
- https://tinyurl.com/ys85q4wq

## 사용 기술
- java : 17
- spring boot : 3.2.0
- jpa
- mysql + querydsl

## 프로젝트 구조
### 멀티 모듈
- book-common
  - 공통 설정
  - 유틸 클래스
- book-domain
  - entity
  - repository
- book-app-api
  - api(endpoint)
