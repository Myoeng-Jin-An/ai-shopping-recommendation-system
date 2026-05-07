# 🛒 AI 기반 맞춤형 쇼핑 추천 + 이상 행동 탐지 시스템

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)

사용자의 행동 데이터를 기반으로 **맞춤형 상품을 추천**하고, **비정상적인 구매 패턴을 실시간으로 탐지**하는 AI 통합 시스템입니다.

</div>

---

## 📌 프로젝트 개요

단순한 추천 시스템을 넘어, **이상 행동 탐지(Anomaly Detection) + 개인화 추천(Recommendation)** 을 결합한 서비스형 프로젝트입니다.

| 구분 | 내용 |
|------|------|
| 추천 방식 | 협업 필터링 + 행동 가중치 기반 점수 |
| 탐지 방식 | 패턴 분석 + 이상 점수 임계값 초과 시 알림 |
| 서버 구성 | Spring Boot (API) + FastAPI (AI) 이중 서버 |

---

## 🎯 핵심 기능

### 1. 사용자 행동 로그 수집
- 상품 클릭, 체류시간, 장바구니 추가, 구매 이벤트 수집
- Spring Boot REST API를 통해 MySQL에 실시간 저장

### 2. AI 추천 시스템
- 행동 가중치 기반 점수 계산 → 개인화 추천
- 인기 상품 / 카테고리 / 협업 필터링(User-Item Matrix) 3단계 추천

### 3. 이상 행동 탐지
- 비정상 구매 패턴 실시간 분석
- 이상 점수 초과 시 → 관리자 알림 + 추천 시스템 제외 처리

---

## 🧱 시스템 아키텍처

```

<img width="1672" height="941" alt="image" src="https://github.com/user-attachments/assets/91d75f56-f66a-4311-8553-b70563838eb5" />


```

### 데이터 흐름

```
사용자 행동 발생
  → Spring Boot (로그 수집 및 MySQL 저장)
  → FastAPI AI Server (추천 점수 계산 + 이상 점수 계산)
  → 이상 점수 임계값 초과 시: 관리자 알림 발송
  → 추천 결과 Spring Boot 반환
  → 사용자에게 추천 상품 노출
```

---

## 🧠 추천 로직

### 행동 가중치

| 행동 유형 | 점수 |
|-----------|------|
| 클릭 | +1점 |
| 장바구니 추가 | +3점 |
| 구매 완료 | +5점 |
| 체류시간 (초당) | +0.1점 |

### 추천 단계

```
1단계: 인기 상품 추천       - 전체 사용자 행동 집계 기반
2단계: 카테고리 기반 추천   - 사용자의 주요 관심 카테고리
3단계: 협업 필터링 추천     - User-Item Matrix 유사도 계산
```

---

## 🚨 이상 행동 탐지 로직

### 탐지 대상 패턴

| 패턴 | 설명 |
|------|------|
| 고가 상품 반복 클릭 | 짧은 시간 내 고가 상품 다수 클릭 |
| 다계정 구매 시도 | 동일 IP에서 다수 계정 구매 반복 |
| 결제 반복 실패 | 장바구니 → 구매 반복 실패 패턴 |

### 탐지 흐름

```
행동 패턴 분석
  → 이상 점수 계산
  → 임계값(threshold) 초과 여부 판단
  → 초과 시: 관리자 알림 + anomaly_logs 기록 + 추천 제외
```

---

## 🗄️ ERD


<img width="1109" height="716" alt="image" src="https://github.com/user-attachments/assets/2bcb231e-3ec2-447e-b0c6-e8faa4c09c4a" />


---

## ⚙️ 기술 스택

| 분류 | 기술 |
|------|------|
| Backend | Spring Boot, MyBatis |
| AI Server | FastAPI (Python) |
| Database | MySQL |
| Infra | Docker, AWS EC2 / RDS / S3 |

---

## 📁 프로젝트 구조

```
├── spring-backend/
│   ├── src/main/java/
│   │   ├── controller/       # REST API 엔드포인트
│   │   ├── service/          # 비즈니스 로직
│   │   ├── mapper/           # MyBatis Mapper 인터페이스 (Repository 역할)
│   │   ├── domain/           # DB 테이블과 매핑되는 POJO 객체 (VO/Model)
│   │   │   ├── User.java
│   │   │   ├── Product.java
│   │   │   ├── UserBehaviorLog.java
│   │   │   ├── Recommendation.java
│   │   │   └── AnomalyLog.java
│   │   └── dto/              # 요청/응답 객체 (Request / Response)
│   └── src/main/resources/
│       └── mapper/           # MyBatis SQL XML
│
├── ai-server/
│   ├── main.py               # FastAPI 진입점
│   ├── recommender.py        # 추천 로직
│   ├── anomaly_detector.py   # 이상 탐지 로직
│   └── requirements.txt
│
├── docker-compose.yml
└── README.md
```

---

## 🔌 주요 API 명세

### Spring Boot API

| Method | Endpoint | 설명 |
|--------|----------|------|
| `POST` | `/api/logs/behavior` | 사용자 행동 로그 저장 |
| `GET` | `/api/recommendations/{userId}` | 사용자 맞춤 추천 조회 |
| `GET` | `/api/anomaly/{userId}` | 이상 행동 로그 조회 |

### FastAPI AI Server

| Method | Endpoint | 설명 |
|--------|----------|------|
| `POST` | `/recommend` | 추천 결과 생성 |
| `POST` | `/anomaly/detect` | 이상 행동 점수 계산 |

---

## 🚀 실행 방법

### 사전 요구사항

- Docker & Docker Compose 설치
- Java 17+
- Python 3.10+

### 환경 변수 설정

`.env` 파일을 프로젝트 루트에 생성 후 아래 내용을 입력합니다.

```env
# MySQL
MYSQL_ROOT_PASSWORD=your_password
MYSQL_DATABASE=shopping_db
MYSQL_USER=user
MYSQL_PASSWORD=your_password

# Spring Boot
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/shopping_db
SPRING_DATASOURCE_USERNAME=user
SPRING_DATASOURCE_PASSWORD=your_password

# FastAPI
AI_SERVER_URL=http://ai-server:8000
ANOMALY_THRESHOLD=80
```

### 실행

```bash
# 전체 서비스 빌드 및 실행
docker-compose up --build

# 백그라운드 실행
docker-compose up -d --build
```

### 포트 정보

| 서비스 | 포트 |
|--------|------|
| Spring Boot API | 8080 |
| FastAPI AI Server | 8000 |
| MySQL | 3306 |

---

## 🌿 브랜치 전략

Git Flow를 기반으로 한 브랜치 전략을 사용합니다.

```
main
├── develop
│   ├── feature/user-behavior-log      # 기능 개발
│   ├── feature/recommendation-engine
│   └── feature/anomaly-detection
├── release/1.0.0                      # 배포 전 QA
└── hotfix/fix-anomaly-threshold       # 긴급 버그 수정
```

### 브랜치 규칙

| 브랜치 | 용도 | 병합 대상 |
|--------|------|-----------|
| `main` | 실제 배포 브랜치 | - |
| `develop` | 개발 통합 브랜치 | `main` |
| `feature/*` | 기능 단위 개발 | `develop` |
| `release/*` | 배포 전 QA 및 버전 관리 | `main`, `develop` |
| `hotfix/*` | 운영 긴급 버그 수정 | `main`, `develop` |

### 커밋 메시지 컨벤션

```
feat:     새로운 기능 추가
fix:      버그 수정
docs:     문서 수정 (README 등)
style:    코드 포맷, 세미콜론 누락 등 (로직 변경 없음)
refactor: 코드 리팩토링
test:     테스트 코드 추가 및 수정
chore:    빌드 설정, 패키지 수정 등
```

**예시**
```bash
feat: 사용자 행동 로그 저장 API 구현
fix: 이상 점수 임계값 계산 오류 수정
docs: API 명세 README 업데이트
```

### PR 규칙

- `feature/*` → `develop` PR 시 **최소 1명 이상 코드 리뷰** 후 머지
- PR 제목은 커밋 컨벤션을 따름
- 머지 방식은 **Squash and Merge** 사용 (커밋 히스토리 정리)

---

## 📮 기여 방법

1. 이 저장소를 Fork합니다.
2. 새 브랜치를 생성합니다. (`git checkout -b feature/기능명`)
3. 변경사항을 커밋합니다. (`git commit -m 'feat: 기능 추가'`)
4. 브랜치에 Push합니다. (`git push origin feature/기능명`)
5. Pull Request를 생성합니다.

---

## 📄 라이선스

This project is licensed under the MIT License.
