# Система регистрации и обработки пользовательских заявок

[Техническое задание](https://docs.google.com/document/d/19RBkDRhoSKJzJhRzIiy9G8rmBjuHJtyOycC-G3pw9GE/edit)

## Содержание

1. [Запуск приложения](#guide)
2. [Стэк](#stack)
2. [Пароли пользователей](#creds)

## Запуск приложения <a id="guide"></a>

```shell
git clone https://github.com/kirumaqq/light.digital-task

cd ./light.digital-task

./gradlew bootRun

# предпологается, что у вас локально установлен постгрес, если нет то:
docker run -p 5432:5432 \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_DB=support-service \
  --rm --name some-postgres postgres:15
```

## Стэк <a id="stack"></a>

- Java, Spring Boot, Security, Cloud Open Feign
- PostgreSQL, Gradle, Github Actions

## Пароли пользователей <a id="creds"></a>

| name      | password | roles                 |
|:----------|----------|-----------------------|
| user1     | pass     | user                  |
| operator1 | pass     | operator              |
| admin1    | pass     | admin                 |
| su        | pass     | user, operator, admin |

