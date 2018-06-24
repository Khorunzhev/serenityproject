# О проекте

Тестовый проект на основе технологического стека: Java + Maven + Junit + Serenity-BDD.
Проект выполняет тест отправки письма на mail.ru
```
Отладка проекта велась в окружении chromediver 2.40, Chrome 67.0.3396.87, MAC OS.
```

## Чеклист по необходимому окружению

* У вас установлена Java начиная от 8 версии
* У вас установлен maven
* В файле serenity.properties полю webdriver.chrome.driver установлен путь до chromedriver-a совместимого с вашей ОС.
Изначально в поле webdriver.chrome.driver установлен driver для запуска на MAC OS.
Версия chromedriver для windows лежит в папке webdrivers.

## Запуск тестов

С помощью maven из корня проекта:
```
mvn clean verify
```
Также возможен запуск по тегу:
```
mvn clean verify -Dtags="sendMailStory"
```
Где sendMailStory - тег теста или класса тестов.


### Просмотр результатов тестов

Результаты прохождения можно посмотреть в консоле или открыть отчет.
Отчет имеет формат *.html, расположен в папке target/site/serenity и доступен после запуска тестов.


### Тестовые данные
Тестовые данные хранятся в файле maildata.csv и лежат в папке resources/testdata. Разделителем является ";"
Каждая новая строчка в файле maildata.csv - это новый набор тестовых данных.
Описание структуры данных:
* Login - логин с которым пользователь авторизуется в mail.ru
* Password - пароль с которым пользователь авторизуется в mail.ru
* ToWhom - адрес на который отправляется письмо
* Title - Заголовок письма
* MailText - Текcт тела письма
* FileLocation - Путь до файла, который прикрепляется вложением к письму.
Путь строится относительно папки resources. Пример: "mailtestfiles/test.txt".

### Особенности работы
getDriver().navigate().refresh() в методе loginToMail добавлен из-за проблем с загрузкой Js и многочисленных ошибок в консоле при старте драйвера.
Без refresh не нажималась кнопка Войти.
Различные wait и javascriptExecutor при этом не спасают.
Различные версии chromeWebDriver тоже не помогают.
Возможно что проблема специфична для mac os.
