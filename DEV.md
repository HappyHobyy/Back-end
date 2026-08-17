# HappyHobby Back-end Development Guide

HappyHobby Back-end의 **local development, service configuration, infrastructure, messaging, testing, Docker 및 troubleshooting**을 정리한 개발자용 문서입니다.

> [!NOTE]
> 프로젝트의 기능, architecture 및 service overview는 [`README.md`](./README.md)를 참고하세요.  
> 이 문서에서는 repository의 현재 구현을 기준으로 개발에 필요한 세부사항을 다룹니다.

## Table of Contents

- [Repository Structure](#repository-structure)
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Service Configuration](#service-configuration)
- [Gateway & Service Discovery](#gateway--service-discovery)
- [Event Messaging](#event-messaging)
- [Database](#database)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Docker](#docker)
- [Deployment](#deployment)
- [Troubleshooting](#troubleshooting)
- [Known Issues & Security](#known-issues--security)

## Repository Structure

HappyHobby Back-end는 Gateway와 Eureka를 포함한 여러 Spring Boot application과 domain별 multi-module project로 구성됩니다.

| Module | Default port | Responsibility |
| --- | ---: | --- |
| `hobbyhobby-eureka` | `8761` | Service registry |
| `hobbyhobby-gateway` | `8000` | API routing, CORS, JWT validation, Swagger aggregation |
| `hobbyhobby-user:hobbyhobby-api` | `8080` | Authentication, user, mail, profile |
| `hobbyhobby-textcontent:hobbyhobby-api` | `8080` | H-Board, text content, equipment review |
| `hobbyhobby-photocontent:hobbyhobby-api` | `8080` | H-Log, gathering, comment, like |
| `hobbyhobby-community:hobbyhobby-api` | `8080` | Community, favorite, recommendation, popular content |
| `hobbyhobby-common` | — | Shared response DTO and error types |

Domain service는 공통적으로 다음과 같은 module boundary를 사용합니다.

```text
domain-service/
├── hobbyhobby-api/        # Spring Boot entry point / controller
├── hobbyhobby-domain/     # Domain model / service / repository contract
├── hobbyhobby-storage/    # JPA entity / persistence adapter
└── hobbyhobby-external/   # RabbitMQ / S3 / mail integration
```

## Prerequisites

개발 환경에서는 다음 구성 요소가 필요합니다.

- **JDK 17**
- **Gradle Wrapper** — repository에 포함
- **MySQL**
- **RabbitMQ**
- **Amazon S3 credentials** — image upload/delete 기능 사용 시
- **SMTP configuration** — User service의 이메일 인증 및 비밀번호 재설정 사용 시
- **Docker / Docker Compose** — container 환경을 사용하는 경우

Unix 계열 환경에서 Gradle Wrapper의 실행 권한이 없다면 다음과 같이 설정합니다.

```bash
chmod +x gradlew
chmod +x hobbyhobby-eureka/gradlew hobbyhobby-gateway/gradlew
chmod +x hobbyhobby-user/gradlew hobbyhobby-textcontent/gradlew
chmod +x hobbyhobby-photocontent/gradlew hobbyhobby-community/gradlew
```

## Quick Start

### 1. Infrastructure

먼저 다음 infrastructure가 실행 중이어야 합니다.

```text
MySQL
RabbitMQ
   │
   ▼
Eureka
   │
   ▼
Domain Services
   │
   ▼
Gateway
```

권장 실행 순서는 다음과 같습니다.

1. MySQL / RabbitMQ
2. Eureka
3. User / TextContent / PhotoContent / Community
4. Gateway

### 2. Run Services

Repository root에서 각 application을 실행할 수 있습니다.

```bash
./gradlew :hobbyhobby-eureka:bootRun

./gradlew :hobbyhobby-user:hobbyhobby-api:bootRun \
  --args='--server.port=8080'

./gradlew :hobbyhobby-textcontent:hobbyhobby-api:bootRun \
  --args='--server.port=8081'

./gradlew :hobbyhobby-photocontent:hobbyhobby-api:bootRun \
  --args='--server.port=8082'

./gradlew :hobbyhobby-community:hobbyhobby-api:bootRun \
  --args='--server.port=8083'

./gradlew :hobbyhobby-gateway:bootRun
```

각 domain service의 기본 `server.port`는 `8080`입니다. 여러 service를 host에서 동시에 직접 실행할 경우 위 예시처럼 port를 서로 다르게 지정해야 합니다.

### 3. Verify

기본적으로 다음 endpoint를 확인할 수 있습니다.

| Component | URL |
| --- | --- |
| Gateway | `http://localhost:8000` |
| Eureka | `http://localhost:8761` |
| Gateway Swagger UI | `http://localhost:8000/swagger-ui.html` |

Eureka dashboard에서 각 domain service와 Gateway가 정상적으로 등록되었는지 먼저 확인하는 것이 좋습니다.

## Service Configuration

### Environment Variables

현재 application configuration에서 직접 참조하는 AWS 환경 변수는 다음과 같습니다.

```dotenv
AWS_S3_ACCESS_KEY=
AWS_S3_SECRET_KEY=
```

S3를 사용하는 기능을 실행할 경우 유효한 credential과 bucket/region 접근 권한이 필요합니다.

> [!IMPORTANT]
> 현재 repository의 일부 configuration에는 infrastructure address와 credential이 직접 포함되어 있습니다. 새로운 환경에서는 기존 값을 재사용하지 말고 DB, RabbitMQ, JWT, S3, SMTP 설정을 runtime environment variable 또는 secret manager로 분리하는 것을 권장합니다.

### Infrastructure Dependencies

| Dependency | Used by | Purpose |
| --- | --- | --- |
| Eureka | Gateway, all domain services | Service discovery |
| MySQL | All domain services | Domain persistence |
| RabbitMQ | User, TextContent, PhotoContent, Community | Domain event propagation |
| Amazon S3 | Image-related services | Image upload/delete |
| SMTP | User | Email verification / password reset |

각 service의 configuration에서 사용하는 Eureka, MySQL, RabbitMQ endpoint는 실제 local infrastructure와 일치하도록 설정해야 합니다.

## Gateway & Service Discovery

### Gateway Routes

Gateway는 `8000` port에서 동작하며 Eureka의 service name을 기반으로 요청을 전달합니다.

| Public prefix | Discovery target | Main responsibility |
| --- | --- | --- |
| `/user-service/**` | `lb://USER-SERVICE` | User / authentication API |
| `/textcontent-service/**` | `lb://TEXTCONTENT-SERVICE` | H-Board / review API |
| `/photocontent-service/**` | `lb://PHOTOCONTENT-SERVICE` | H-Log / gathering API |
| `/community-service/**` | `lb://COMMUNITY-SERVICE` | Community API |

Gateway는 service prefix를 제거한 뒤 target service로 request를 forwarding합니다.

인증이 적용된 route에서는 `AuthorizationHeaderFilter`가 access token을 검증합니다.

### Eureka Registration

현재 등록되는 application name은 다음과 같습니다.

| Application | `spring.application.name` |
| --- | --- |
| Gateway | `gateway-service` |
| User | `user-service` |
| TextContent | `textcontent-service` |
| PhotoContent | `photocontent-service` |
| Community | `community-service` |

모든 Eureka client의 `eureka.client.service-url.defaultZone`이 동일한 Eureka instance를 가리키는지 확인해야 합니다.

## Event Messaging

Domain 간 직접 HTTP client 대신 RabbitMQ 기반 asynchronous event propagation을 사용합니다.

### User Events

User의 생성·수정·삭제 결과는 필요한 domain service에 전달됩니다.

| Producer | Consumer | Destination | Routing key |
| --- | --- | --- | --- |
| User | TextContent | `textContent` | `User` |
| User | PhotoContent | `photoContent` | `User` |
| User | Community | `community` | `User` |

각 consumer는 필요한 사용자 정보를 자체 storage에 반영합니다.

### Content Events

PhotoContent에서 발생한 content 변경은 Community의 조회 데이터 갱신에 사용됩니다.

| Producer | Consumer | Destination | Routing key |
| --- | --- | --- | --- |
| PhotoContent | Community | `community` | `PhotoContent` |
| Community | Community | `allCommunity` | `AllCommunity` |

`allCommunity` event는 event header에 따라 Community의 in-memory popular-content cache를 초기화하는 데 사용됩니다.

RabbitMQ broker의 host와 `5672` port 설정은 관련 `rabbitmq.yml`에서 동일한 broker를 바라보도록 맞춰야 합니다.

## Database

각 domain service는 별도의 MySQL schema를 사용하도록 구성되어 있습니다.

| Service | Schema | `ddl-auto` |
| --- | --- | --- |
| User | `hobby_user` | `none` |
| TextContent | `hobby_textServer` | `create` |
| PhotoContent | `hobby_imageServer` | `update` |
| Community | `hobby_community` | `none` |

PhotoContent는 `schema.sql` initialization도 사용합니다.

> [!NOTE]
> Root `docker-compose.yml`의 MySQL 설정은 `hobbyhobby` database를 생성하지만, 각 application은 위의 개별 schema를 사용하도록 설정되어 있습니다. Compose를 그대로 사용하는 경우 필요한 schema를 별도로 생성하거나 datasource URL을 환경에 맞게 수정해야 합니다.

User와 Community처럼 `ddl-auto=none`인 service는 필요한 schema/table이 이미 준비되어 있어야 합니다.

## API Documentation

각 domain service는 springdoc-openapi를 사용합니다.

```text
/v3/api-docs
/swagger-ui.html
```

Gateway는 각 service의 OpenAPI document를 집계하며 다음 주소에서 Swagger UI를 제공합니다.

```text
http://localhost:8000/swagger-ui.html
```

주요 API group은 다음과 같습니다.

| Service | Base path | Main operations |
| --- | --- | --- |
| User | `/api/auth`, `/api/user` | Sign-up/login, verification, password reset, profile |
| TextContent | `/api/article/text`, `/api/article/review` | H-Board/review CRUD, search, comment, like |
| PhotoContent | `/api/hlog`, `/api/gathering` | H-Log, gathering, member, comment, like |
| Community | `/api/community` | Popular, favorite, recommendation, popular content |

정확한 request/response schema는 실행 중인 Swagger/OpenAPI document를 기준으로 확인하세요.

## Testing

전체 test task는 repository root에서 실행할 수 있습니다.

```bash
./gradlew test
```

특정 module만 실행하는 예시는 다음과 같습니다.

```bash
./gradlew :hobbyhobby-user:hobbyhobby-domain:test
./gradlew :hobbyhobby-user:hobbyhobby-api:test
./gradlew :hobbyhobby-eureka:test
```

현재 repository에는 User API/domain과 Eureka의 test source가 포함되어 있습니다.

TextContent, PhotoContent, Community subproject에는 JaCoCo report configuration이 있으며 test 실행 후 HTML, CSV, XML report를 생성하도록 구성되어 있습니다. 다만 현재 repository에는 해당 service의 `src/test` source가 포함되어 있지 않습니다.

## Docker

Root `docker-compose.yml`은 Gateway, Eureka, 네 개의 domain service와 MySQL을 정의합니다.

| Service | Host | Container |
| --- | ---: | ---: |
| Gateway | `8000` | `8000` |
| Eureka | `8761` | `8761` |
| User | `8080` | `8080` |
| TextContent | `8081` | `8080` |
| PhotoContent | `8082` | `8080` |
| Community | `8083` | `8080` |
| MySQL | `3306` | `3306` |

### Before Running Compose

현재 Compose definition을 사용할 때는 다음 사항을 먼저 확인해야 합니다.

1. `dockernet`이 external Docker network로 선언되어 있으므로 network를 준비합니다.
2. `/path/to/local/...` 형태의 volume placeholder를 실제 host path로 변경합니다.
3. RabbitMQ는 Compose에 포함되어 있지 않으므로 별도로 실행합니다.
4. 각 application이 사용하는 MySQL schema를 준비합니다.
5. 필요한 runtime secret과 infrastructure endpoint를 설정합니다.

그 후 실행합니다.

```bash
docker compose up -d
```

### Manual Image Build

Domain service Dockerfile은 각 service directory를 기준으로 다음 artifact를 사용합니다.

```text
hobbyhobby-api/build/libs/hobbyhobby-api-0.0.1-SNAPSHOT.jar
```

예를 들어 User image를 직접 build하려면:

```bash
cd hobbyhobby-user

./gradlew build
docker build -t hobbyhobby/user:local .
```

## Deployment

Repository의 GitHub Actions workflow는 service별 Docker image를 build/push하고 EC2 host의 해당 service를 교체하는 deployment flow를 포함합니다.

현재 workflow는 다음 feature branch의 push를 기준으로 동작하도록 구성되어 있습니다.

```text
feature/gateway
feature/user
feature/text-content
feature/photo-content
feature/community
feature/db
```

즉, 현재 workflow는 모든 branch 또는 pull request에 적용되는 일반적인 CI gate라기보다 **service별 deployment automation**에 가깝습니다.

## Troubleshooting

| Symptom | What to check |
| --- | --- |
| Gateway에서 `503` 발생 | Eureka 실행 여부, service registration name, Gateway와 service의 Eureka URL |
| Local service 실행 시 `8080` bind error | 각 domain service의 `server.port` override |
| MySQL connection / missing table error | datasource host/schema 및 `ddl-auto` 설정 |
| RabbitMQ connection failure | broker 실행 여부와 각 `rabbitmq.yml`의 host/port |
| Configuration import error | API module의 `db.yml`, `rabbitmq.yml`, User의 `mail.yml` |
| S3 upload failure | AWS credential, bucket 및 region 접근 권한 |
| 인증 후 downstream에서 `userId`를 받지 못함 | 아래 Gateway request mutation known issue 확인 |

## Known Issues & Security

### Gateway Request Mutation

현재 `AuthorizationHeaderFilter`는 token validation 후 request에 `userId`, `nickname`, `userRole` header를 추가하도록 mutation하지만, 생성된 request를 forwarding exchange에 다시 적용하지 않습니다.

따라서 현재 구현에서는 downstream service에 해당 header가 전달된다고 보장할 수 없습니다.

수정 시에는 mutated request를 포함한 exchange를 filter chain으로 전달하도록 구성해야 합니다.

### Compose / Infrastructure

- Root Compose는 RabbitMQ container를 포함하지 않습니다.
- `dockernet`은 external network를 전제로 합니다.
- Compose의 MySQL database와 service별 datasource schema가 일치하지 않습니다.
- 일부 volume path는 placeholder 상태입니다.
- 명시적인 service startup ordering이 구성되어 있지 않습니다.

### Secret Management

현재 source configuration에는 일부 credential, JWT signing key, mail configuration 및 고정 infrastructure address가 포함되어 있습니다.

운영 또는 새로운 개발 환경에서는 다음과 같이 관리하는 것을 권장합니다.

- committed credential은 rotate
- runtime environment variable 또는 secret manager 사용
- long-lived secret을 Docker build argument로 전달하지 않기
- repository에 실제 credential을 추가하지 않기

### Gateway Security

현재 Gateway의 JWT filter는 configuration에 지정된 route에만 적용됩니다. 또한 global CORS configuration은 모든 origin, method, header를 허용합니다.

Production 환경에서는 실제 client origin과 공개해야 하는 API 범위를 기준으로 CORS 및 authentication policy를 제한해야 합니다.
