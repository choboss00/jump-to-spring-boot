## 점프 투 스프링부트 게시판 클론코딩

1. 타임리프가 아닌 html, css, javascript 로 구성해보기
2. 기능 추가해서 만들어보기

## 최종 화면

![1](https://github.com/choboss00/jump-to-springBoot/assets/111727212/63ba7712-a74f-4ace-9ba5-6f51f7b870ac)

## 테이블 설계 ( ERD )

### 필수기능만 구현할 때

![2](https://github.com/choboss00/jump-to-springBoot/assets/111727212/bf4aee33-29ba-4897-a72a-9b5acdcd6a94)
---

## 구현해야하는 기능

### 필수

1. 게시물 등록
2. 게시물 삭제
3. 게시물 수정
4. 로그인, 로그아웃
5. 회원가입
6. 게시글 페이징 처리
7. 게시글 검색

### 추가 : 필수 기능을 다 구현한 뒤 추가로 진행할 예정

SBB 추가 기능 탭 참고 ( + 이미지 삽입 기능 )

---

## 시퀀스 다이어그램

### 게시글 조회

![3](https://github.com/choboss00/jump-to-springBoot/assets/111727212/c5f6dedb-e896-4176-93dc-97101f8f5bf4)
### 게시글 상세 조회

![4](https://github.com/choboss00/jump-to-springBoot/assets/111727212/3717aeec-96b5-40d4-9b00-4003ec959aa9)
### 게시글 검색

![5](https://github.com/choboss00/jump-to-springBoot/assets/111727212/71521276-5854-4632-aea3-56b935b920ff)
### 게시글 등록

![6](https://github.com/choboss00/jump-to-springBoot/assets/111727212/a34a22f9-4604-478a-b9c0-ec6134c93729)
### 게시글 삭제

![7](https://github.com/choboss00/jump-to-springBoot/assets/111727212/9f64b19e-cf15-49d7-8c47-2d00afa3b16f)
### 게시글 수정

![8](https://github.com/choboss00/jump-to-springBoot/assets/111727212/b97a0f95-1eb2-41ad-b95e-bbd96b022327)
### 회원가입

![9](https://github.com/choboss00/jump-to-springBoot/assets/111727212/1298f799-d4cb-479e-9f11-69610bf2586a)
### 로그인

![10](https://github.com/choboss00/jump-to-springBoot/assets/111727212/98c1027c-ae24-4b19-b67b-7256ec816d89)
---

## 코드 컨밴션

### 변수 ( 함수 ) 명에 대한 네이밍 컨밴션

1. 카멜 케이스 사용
2. 함수명 작성 시 동사 + 명사 형태로 구성
3. class, constructor 를 작성할 때는 Pascal Case 사용 ( 첫글자만 Upper )
4. 글자 길이는 20자 이내 설정
5. 명확하게 설정

### 데이터베이스 네이밍 컨밴션

1. 테이블 명은 영어 대문자로 구성
2. 이름 작성시 snake case 사용

### 함수 관련 컨밴션

1. magic number 지양
2. 주석 작성시, 다음과 같은 규칙을 통해 작성
    1. 함수의 전체 기능
    2. 함수의 파라미터
    3. 예시 코드 ( 스웨거를 통해 작성 )
3. 되도록 else if 줄이기

### 게시글 삭제는 soft delete 채택

- 추후에 데이터들을 활용할 일이 있을 수 있기 때문에, soft delete 채택