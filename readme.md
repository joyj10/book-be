# Book Review - BE

## 기능
- 읽은 책 리뷰
  - 읽은 책 등록(다회독 가능)
  - 읽은 책 수정
  - 리뷰 등록/수정/삭제
  - 좋은 글귀 등록/수정/삭제 
- 읽고 싶은 책
  - 읽고 싶은 책 등록
  - 읽고 싶은 이유 추가
- 월별 읽은 책 통계
- 월단위 읽은 책 리스트 (책장형 디자인)

## 화면 정의서
- https://tinyurl.com/ys85q4wq

## 사용 기술
- java : 17
- spring boot : 3.2.0
- JPA + QueryDSL
- MySQL, Redis
- JWT

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
