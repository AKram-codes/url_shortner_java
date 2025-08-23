# URL Shortener — Backend (Spring Boot)

## Run (local)
```bash
# Java 17 + Maven required
mvn spring-boot:run
```

## REST API
- `POST /api/shorten` body:
```json
{ "longUrl": "https://example.com/very/long/url", "customLength": 7 }
```
- Response `201`:
```json
{ "code": "a1B2c3d", "shortUrl": "http://localhost:8080/r/a1B2c3d", "longUrl": "https://example.com/very/long/url" }
```
- `GET /r/{code}` → 301 redirect to original URL.

H2 console at `/h2` (JDBC URL: `jdbc:h2:file:./data/urlshortener`).

## Docker
```bash
docker build -t url-shortener-backend .
docker run -p 8080:8080 url-shortener-backend
```
