# Board

처음 Vue와 SpringBoot를 접하는 입문자를 위한 게시판 예제 파일

## 기술 스택

### Backend

- Spring Boot 3.4.0
- MariaDB
- Gradle
- spring-boot-starter-data-jpa (Backend)
- mybatis-spring-boot-starter:3.0.4 (Backend_mybatis)

### Frontend

- TypeScript
- Vite 5.4.1
- Vue 3.5.13
- Axios 1.7.9

## 데이터베이스 설정

### MariaDB 테이블 생성

```sql
CREATE TABLE board (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    author VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## API 엔드포인트

### 1. 게시글 목록 조회

```
GET http://localhost:8080/api/boards
```

- 엔드포인트: `/api/boards`
- 메서드: `GET`
- 설명: 모든 게시글을 조회합니다.

### 2. 게시글 검색

```
GET http://localhost:8080/api/boards/search?keyword=검색어
```

- 엔드포인트: `/api/boards/search`
- 메서드: `GET`
- 설명: 검색어에 해당하는 게시글을 조회합니다.

### 3. 특정 게시글 조회

```
GET http://localhost:8080/api/boards/{id}
```

- 엔드포인트: `/api/boards/{id}`
- 메서드: `GET`
- 설명: 특정 게시글을 조회합니다.

### 4. 게시글 생성

```
POST http://localhost:8080/api/boards
```

- 엔드포인트: `/api/boards`
- 메서드: `POST`
- 설명: 새로운 게시글을 생성합니다.

### 5. 게시글 수정

```
PUT http://localhost:8080/api/boards/{id}
```

- 엔드포인트: `/api/boards/{id}`
- 메서드: `PUT`
- 설명: 특정 게시글을 수정합니다.

### 6. 게시글 삭제

```
DELETE http://localhost:8080/api/boards/{id}
```

- 엔드포인트: `/api/boards/{id}`
- 메서드: `DELETE`
- 설명: 특정 게시글을 삭제합니다.

※ WebConfig.java 파일에서 현재 vite의 기본 개발 서버 포트 5173번만 접근 허용하고 있습니다.
