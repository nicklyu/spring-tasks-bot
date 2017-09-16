# spring-tasks-bot 
Этот бот для telegram, который сообщает о поступлении новых работ для проверки и напоминает о непроверенных работах.

## Команды бота:
  * /start - инициализирует бота и регистрирует пользователя в системе
  * /addgroup - возвращает меню добавления новых групп
  * /removegroup - возвращает меню удаления потслеживаемых групп
  * /getgroups - возвращает список отслеживаемых групп
  
  
## План выполнения:
  * [✓] Интеграция с telegram
  * [✓] Интеграция с базой данных
  * [–] Интеграция с OneNote


### Содержание application.properties:
````
#DB properties:
db.driver=драйвер
db.url=адрес бд
db.username=имя пользователя
db.password=пароль

#Hibernate Configuration:
hibernate.dialect=диалект бд
hibernate.show_sql=false/true
entitymanager.packages.to.scan=пакет с сущностями

#App token
app.token=токен приложения telegram
````
