# Деплой

## Что поднимает docker compose

| Сервис  | Что делает                                              | Наружу                         |
|---------|---------------------------------------------------------|--------------------------------|
| nginx   | отдаёт собранный React, проксирует `/api/...` в Spring  | `${NGINX_BIND}:${NGINX_PORT}`  |
| backend | Spring Boot                                             | только `127.0.0.1:8080`        |
| db      | PostgreSQL                                              | только `127.0.0.1:5432`        |

Фронт собирается внутри образа nginx (`deploy/nginx/Dockerfile`), отдельно собирать его не нужно.

## Запуск

```bash
git clone <repo> && cd CommunicationMatrixRusMAI
cp .env.example .env                  # адрес/порт nginx
cp backend/.env.example backend/.env  # БД, SMTP и т.д.
docker compose up -d --build
```

## Что должно стоять перед приложением

nginx приложения работает по обычному HTTP, без TLS. Перед ним нужен TLS-прокси, который:

- проксирует домен на `http://<сервер>:${NGINX_PORT}`;
- передаёт `Host` и `X-Forwarded-Proto: https` (и желательно `X-Forwarded-For`).

**dev:** Nginx Proxy Manager, proxy host `matrix.<домен>` → `<IP виртуалки>:${NGINX_PORT}`, scheme `http`, SSL с Force SSL.
В `.env` на виртуалке `NGINX_BIND` = IP интерфейса, до которого достаёт NPM.

**Сервер заказчика со своим прокси:** настроить на нём то же самое.

**Голый сервер без прокси:** поставить на хост Caddy (сам получит сертификат):

```
matrix.example.ru {
    reverse_proxy 127.0.0.1:8081
}
```

`NGINX_BIND=127.0.0.1`. Caddy сам передаёт `X-Forwarded-Proto`.

## Проверка

- `https://<домен>/` открывает фронт, `https://<домен>/results/matrix` после F5 не даёт 404.
- `https://<домен>/api/...` отвечает бэк.
- В Spring `request.isSecure()` за прокси возвращает `true`
  (нужен `server.forward-headers-strategy: native` в application.yml).
