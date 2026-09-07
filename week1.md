# Week 1 - Lab 1

## 1. GET Request

### Command

```bash
curl.exe -i https://api.github.com/users/torvalds
```


## 2. Verbose GET Request

### Command

```bash
curl.exe -v https://httpbin.org/get
```

## 3. POST Request

### Command

```bash
curl.exe -i -X POST https://httpbin.org/post -H "Content-Type: application/json" -d "{\"name\":\"Arul\",\"week\":1}"
```

## 4. Query Parameters

### Command

```bash
curl.exe -i "https://httpbin.org/get?role=intern&track=python"
```
