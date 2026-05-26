# BFHL API

A Spring Boot 3 REST API for the Bajaj Finserv Health campus hiring challenge.

## Endpoint

### `POST /bfhl`

**Request:**
```json
{ "data": ["a", "1", "334", "4", "R", "$"] }
```

**Response:**
```json
{
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

## Logic

| Token type | Rule |
|---|---|
| Pure numeric string | → odd_numbers / even_numbers based on value |
| Single alphabet char | → alphabets (uppercased) |
| Anything else | → special_characters |
| sum | Arithmetic sum of all numeric tokens (as string) |
| concat_string | Alphabets in order → reverse → alternating UPPER/lower |

## Running Locally

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080/bfhl`.

## Running with Docker

```bash
docker build -t bfhl-api .
docker run -p 8080:8080 bfhl-api
```

## Running Tests

```bash
./mvnw test
```

## Deployment (Render.com)

1. Push this repository to GitHub.
2. Go to [render.com](https://render.com) → New → Web Service.
3. Connect your GitHub repo.
4. Render will auto-detect `render.yaml` and deploy using Docker.
5. Free tier spins down after inactivity — first request may take ~30 s.

## Tech Stack

- Java 17
- Spring Boot 3.2.5
- Maven
- Docker (multi-stage build)
