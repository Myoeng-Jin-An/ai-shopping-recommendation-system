# AI 행동 기반 추천 시스템

사용자의 상품 행동 데이터를 기반으로 개인화 상품 추천과 이상 행동 탐지를 제공하는 쇼핑몰 백엔드 프로젝트입니다.

현재는 Spring Boot 기반 백엔드 도메인 구조와 기본 API를 구현한 상태이며, 추후 FastAPI 기반 AI 서버와 Docker Compose 통합 실행 환경을 추가할 예정입니다.

---

## 프로젝트 목표

이 프로젝트는 단순 상품 추천 기능을 넘어, 사용자의 행동 로그와 구매 데이터를 함께 활용하는 추천/탐지 시스템을 목표로 합니다.

- 사용자, 상품, 행동 로그, 주문 데이터를 저장하는 백엔드 API 구현
- AI 추천 결과와 이상 행동 탐지 결과를 저장/조회하는 구조 구현
- 추후 FastAPI AI 서버와 연동하여 추천 점수와 이상 행동 점수 계산
- Swagger UI를 통한 API 테스트 환경 제공
- Docker Compose 기반 통합 실행 및 AWS 배포 확장 예정

---

## 현재 구현 상태

### 구현 완료

- Spring Boot 백엔드 기본 구조
- MVC 기반 도메인 패키지 구조
- User 도메인
- Product 도메인
- Behavior Log 도메인
- Order 도메인
- Recommendation 도메인
- Anomaly Log 도메인
- Global Error Handling
- 도메인별 Business Exception
- Swagger UI 설정
- Spring Security 기본 허용 경로 설정

### 추후 구현 예정

- FastAPI 기반 AI 서버
- 추천 알고리즘
- 이상 행동 탐지 알고리즘
- Spring Boot와 FastAPI 연동
- Docker Compose 기반 통합 실행
- Frontend
- AWS 배포

---

## 기술 스택

| 분류 | 기술 |
|---|---|
| Backend | Java 17, Spring Boot 4.0.6 |
| Persistence | Spring Data JPA, MyBatis |
| Database | MySQL |
| Cache/Session 예정 | Redis |
| API Docs | Springdoc OpenAPI, Swagger UI |
| Security | Spring Security, OAuth2 Client |
| Build | Gradle |
| AI Server 예정 | FastAPI, Python |
| Infra 예정 | Docker, Docker Compose, AWS |

---

## 프로젝트 구조

```text
AI 행동 기반 추천 시스템/
├── .github/
│   ├── ISSUE_TEMPLATE/
│   └── pull_request_template.md
├── ai-shopping-backend/
│   ├── build.gradle
│   ├── settings.gradle
│   ├── gradlew
│   ├── gradlew.bat
│   └── src/
│       └── main/
│           ├── java/com/aishop/backend/
│           │   ├── domain/
│           │   │   ├── user/
│           │   │   ├── product/
│           │   │   ├── behavior/
│           │   │   ├── order/
│           │   │   ├── recommendation/
│           │   │   └── anomaly/
│           │   └── global/
│           │       ├── config/
│           │       ├── error/
│           │       └── security/
│           └── resources/
│               ├── application.yaml
│               ├── application-local.yml
│               ├── application-dev.yml
│               └── application-prod.yml
├── README.md
└── .gitignore
```

도메인 내부는 다음 MVC 구조를 기준으로 구성합니다.

```text
domain/{domain-name}/
├── controller/
├── service/
├── repository/
├── entity/
├── dto/
│   ├── requestDTO/
│   └── responseDTO/
└── converter/
```

---

## 도메인 역할

| 도메인 | 역할 |
|---|---|
| User | 사용자 계정 정보 관리 |
| Product | 브랜드, 카테고리, 상품 정보 관리 |
| Behavior | 사용자 행동 로그 및 세션 데이터 관리 |
| Order | 주문과 주문 상품 데이터 관리 |
| Recommendation | AI 추천 결과 저장 및 조회 |
| Anomaly | 이상 행동 탐지 결과 저장 및 조회 |
| Global | 공통 설정, 에러 처리, 보안 설정 관리 |

---

## 주요 데이터 흐름

```text
사용자 행동 발생
  -> Behavior Log API로 행동 데이터 저장
  -> 주문 발생 시 Order API로 구매 결과 저장
  -> 추후 FastAPI AI 서버가 행동/주문 데이터를 분석
  -> 추천 결과를 Recommendation API로 저장
  -> 이상 행동 결과를 Anomaly Log API로 저장
  -> 사용자 또는 관리자 화면에서 결과 조회
```

현재는 AI 서버가 직접 계산하는 단계가 아니라, AI 결과를 저장하고 조회할 수 있는 백엔드 기반 구조까지 구현되어 있습니다.

---

## ERD 기준 테이블

현재 설계 기준 테이블은 다음과 같습니다.

```text
users
brands
categories
products
orders
order_items
sessions
user_behavior_logs
recommendations
anomaly_logs
```

주요 관계:

```text
User 1 ─ N Order
Order 1 ─ N OrderItem
Product 1 ─ N OrderItem

User 1 ─ N UserBehaviorLog
Product 1 ─ N UserBehaviorLog
Session 1 ─ N UserBehaviorLog

User 1 ─ N Recommendation
Product 1 ─ N Recommendation

User 1 ─ N AnomalyLog
Product 1 ─ N AnomalyLog
```

---

## 주요 API 명세

### User

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/users` | 사용자 생성 |
| GET | `/api/users/{userId}` | 사용자 단건 조회 |

### Brand

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/brands` | 브랜드 생성 |
| GET | `/api/brands` | 브랜드 목록 조회 |

### Category

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/categories` | 카테고리 생성 |
| GET | `/api/categories` | 카테고리 목록 조회 |

### Product

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/products` | 상품 생성 |
| GET | `/api/products` | 상품 목록 조회 |
| GET | `/api/products/{productId}` | 상품 단건 조회 |

### Behavior Log

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/behavior-logs` | 행동 로그 생성 |
| GET | `/api/behavior-logs/users/{userId}` | 사용자 기준 행동 로그 조회 |
| GET | `/api/behavior-logs/sessions/{sessionId}` | 세션 기준 행동 로그 조회 |

### Order

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/orders` | 주문 생성 |
| GET | `/api/orders/{orderId}` | 주문 단건 조회 |
| GET | `/api/orders/users/{userId}` | 사용자 기준 주문 목록 조회 |

### Recommendation

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/recommendations` | 추천 결과 생성 |
| GET | `/api/recommendations/users/{userId}` | 사용자 기준 추천 결과 조회 |

### Anomaly Log

| Method | Endpoint | 설명 |
|---|---|---|
| POST | `/api/anomaly-logs` | 이상 행동 로그 생성 |
| GET | `/api/anomaly-logs/users/{userId}` | 사용자 기준 이상 행동 로그 조회 |

---

## 실행 방법

### 1. 백엔드 실행

```bash
cd ai-shopping-backend
./gradlew.bat bootRun
```

Git Bash 또는 macOS/Linux 환경에서는 다음 명령어를 사용할 수 있습니다.

```bash
cd ai-shopping-backend
./gradlew bootRun
```

### 2. Swagger UI 접속

```text
http://localhost:8080/swagger-ui.html
```

---

## 환경 변수

현재 설정 파일은 `application-local.yml`, `application-dev.yml`, `application-prod.yml`로 분리되어 있습니다.

대표적으로 사용하는 환경 변수는 다음과 같습니다.

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=aishop_db
DB_USER=root
DB_PASSWORD=your_password

REDIS_HOST=localhost
REDIS_PORT=6379

JWT_ACCSECRET=local-access-secret
JWT_REFSECRET=local-refresh-secret
```

실제 값은 로컬 환경에 맞게 설정합니다.

---

## 테스트 순서

Swagger UI에서 API를 확인할 때는 아래 순서를 추천합니다.

```text
1. User 생성
2. Brand 생성
3. Category 생성
4. Product 생성
5. Behavior Log 생성
6. Order 생성
7. Recommendation 생성
8. Anomaly Log 생성
```

예시 요청:

```json
{
  "userId": 1,
  "productId": 1,
  "score": 0.95
}
```

```json
{
  "userId": 1,
  "productId": null,
  "reason": "짧은 시간 동안 비정상적으로 많은 요청이 발생했습니다.",
  "score": 0.88
}
```

---

## 컴파일 확인

```bash
cd ai-shopping-backend
./gradlew.bat compileJava
```

현재 전체 테스트는 테스트용 DB 설정이 없으면 실패할 수 있습니다.

```text
Unable to determine Dialect without JDBC metadata
```

이는 테스트 실행 시 사용할 DB URL 또는 테스트 전용 DB 설정이 없어서 발생하는 문제입니다. 추후 H2 또는 테스트 전용 MySQL 설정을 추가할 예정입니다.

---

## 추후 FastAPI / Docker Compose 계획

현재 FastAPI 서버와 Docker Compose 설정은 아직 구현 전입니다. 추후 다음 구조로 확장할 예정입니다.

```text
AI 행동 기반 추천 시스템/
├── ai-shopping-backend/
├── ai-server/
│   ├── main.py
│   ├── recommender.py
│   ├── anomaly_detector.py
│   └── requirements.txt
└── docker-compose.yml
```

추후 통합 실행 목표:

```bash
docker-compose up --build
```

예정 서비스:

| 서비스 | 역할 | 포트 |
|---|---|---|
| Spring Boot API | 백엔드 API 서버 | 8080 |
| FastAPI AI Server | 추천/이상탐지 계산 서버 | 8000 |
| MySQL | 관계형 데이터베이스 | 3306 |
| Redis | 캐시/세션 저장소 | 6379 |

---

## 브랜치 전략

Git Flow 기반의 브랜치 전략을 사용합니다.

```text
main
├── develop
│   ├── feat/user
│   ├── feat/product
│   ├── feat/behavior-log
│   ├── feat/order
│   ├── feat/recommendation
│   └── feat/anomaly
├── release/1.0.0
└── hotfix/fix-anomaly-threshold
```

| 브랜치 | 용도 | 병합 대상 |
|---|---|---|
| `main` | 배포 브랜치 | - |
| `develop` | 개발 통합 브랜치 | `main` |
| `feat/*` | 기능 단위 개발 | `develop` |
| `release/*` | 배포 전 QA | `main`, `develop` |
| `hotfix/*` | 긴급 수정 | `main`, `develop` |

---

## 커밋 컨벤션

```text
feat: 새로운 기능 추가
fix: 버그 수정
docs: 문서 수정
style: 코드 포맷 수정
refactor: 리팩토링
test: 테스트 코드 추가/수정
chore: 빌드, 설정, 기타 작업
```

예시:

```bash
feat: 추천 결과 도메인 기본 구조 구현
docs: README API 명세 업데이트
chore: gitignore 추가 및 IDE 설정 추적 해제
```

---

## PR 규칙

- `feat/*` 브랜치에서 `develop` 브랜치로 Pull Request를 생성합니다.
- PR 제목은 커밋 컨벤션을 따릅니다.
- PR 본문에는 구현 내용, 테스트 여부, 리뷰 요청 포인트를 작성합니다.
- 병합 방식은 Squash and Merge를 기본으로 사용합니다.

---

## 라이선스

This project is licensed under the MIT License.
