# URL Shortener — Frontend (React + Vite)

## Run (local)
```bash
# Node 20+ recommended
npm install
npm run dev
# open http://localhost:5173
```
Create a `.env` file to override API base if needed:
```
VITE_API_BASE=http://localhost:8080
```

## Docker
```bash
docker build -t url-shortener-frontend .
docker run -p 8081:80 url-shortener-frontend
```
