# Собирается из docker-compose.yml:
#   контекст  = ./frontend         (исходники React)
#   nginx-config = ./deploy/nginx  (дополнительный контекст с конфигом)

# --- Стадия 1: сборка фронта ---
FROM node:24-alpine AS build
WORKDIR /app

# pnpm нужной версии берётся из packageManager в package.json
RUN corepack enable

# Сначала только манифесты: слой с зависимостями кэшируется,
# пока не поменялся lock-файл
COPY package.json pnpm-lock.yaml ./
RUN pnpm install --frozen-lockfile

COPY . .
RUN pnpm build


# --- Стадия 2: nginx со статикой ---
FROM nginx:stable-alpine

COPY --from=nginx-config templates/ /etc/nginx/templates/
COPY --from=build /app/dist/ /usr/share/nginx/html/

EXPOSE 80
