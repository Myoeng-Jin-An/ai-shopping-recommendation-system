# AI Behavior-Based Recommendation System

## 📌 Overview

사용자의 행동 데이터를 기반으로 개인화 추천을 제공하고, 동시에 이상 행동을 탐지하는 AI 시스템입니다.

## 🧠 Key Features

* 사용자 행동 로그 수집 (클릭, 체류시간, 구매)
* 협업 필터링 기반 추천 시스템
* 이상 행동 탐지 (Rule + ML)
* OAuth2 + JWT 인증

## 🧱 Architecture

Frontend → Spring Boot → MySQL → FastAPI

## ⚙️ Tech Stack

* Backend: Spring Boot, MyBatis
* AI: FastAPI, scikit-learn
* DB: MySQL
* Infra: Docker, AWS

## 🗄️ Database

* users
* products
* user_logs
* orders
* order_items

## 🔥 Highlights

* 추천 + 이상 탐지 결합 시스템
* 실시간 로그 기반 추천
* 비정상 행동 탐지

## 🚀 Deployment

* Docker Compose
* AWS EC2 / RDS / S3
