# BFHL API

Spring Boot REST API built for the Bajaj Finserv Health hiring challenge.

---

# Live API Links

## POST API

```text
https://bfhl-api-a8g3.onrender.com/bfhl
```

### Sample Request

```bash
curl -X POST https://bfhl-api-a8g3.onrender.com/bfhl \
-H "Content-Type: application/json" \
-d '{"data":["a","1","334","4","R","$"]}'
```

### Sample Response

```json
{
  "success": true,
  "is_success": true,
  "user_id": "atharvya_sharma_01062006",
  "email": "atharvyasharma230072@acropolis.in",
  "roll_number": "0827AL231037",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

<img width="960" height="512" alt="image" src="https://github.com/user-attachments/assets/39bb9b70-3c50-46e3-95c3-619f7a094c45" />

---

## GET API

```text
https://bfhl-api-a8g3.onrender.com/actuator/health
```

### Run

```bash
curl https://bfhl-api-a8g3.onrender.com/actuator/health
```

### Response

```json
{
  "status": "UP",
  "components": {
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 414921494528,
        "free": 61853470720,
        "threshold": 10485760,
        "path": "/app/.",
        "exists": true
      }
    },
    "livenessState": {
      "status": "UP"
    },
    "ping": {
      "status": "UP"
    },
    "readinessState": {
      "status": "UP"
    }
  },
  "groups": [
    "liveness",
    "readiness"
  ]
}
```

<img width="956" height="506" alt="image" src="https://github.com/user-attachments/assets/0ee119e7-7958-4ca4-810c-906577d8cdf7" />

---

# Run Locally

## Start Application

```bash
./mvnw spring-boot:run
```

Application runs on:

```text
http://localhost:8080
```

---

## Test Local POST API

```bash
curl -X POST http://localhost:8080/bfhl \
-H "Content-Type: application/json" \
-d '{"data":["a","1","334","4","R","$"]}'
```

---

## Test Local GET API

```bash
curl http://localhost:8080/actuator/health
```

---

# Run with Docker

## Build

```bash
docker build -t bfhl-api .
```

## Run

```bash
docker run -p 8080:8080 bfhl-api
```

---

# Run Tests

```bash
./mvnw test
```

---

# Tech Stack

- Java 17
- Spring Boot 3
- Maven
- Docker
- Render

---

# Author

**Atharvya Sharma**

- Email: atharvyasharma230072@acropolis.in
- Roll Number: 0827AL231037
