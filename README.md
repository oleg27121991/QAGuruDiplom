# Проект по автоматизации тестирования для сайта  [BezKassira.by](https://bezkassira.by/)

<img alt="main page" src="media/screenshots/mainPage.png">

##  Содержание:

- <a href="#tools"> Используемые инструменты</a>
- <a href="#cases"> Тест-кейсы</a>
- <a href="#autotests"> Запуск автотестов</a>
- <a href="#jenkins"> Сборка в Jenkins</a>
- <a href="#allureReport"> Пример Allure-отчета</a>
- <a href="#allure"> Интеграция с Allure TestOps</a>
- <a href="#jira"> Интеграция с Jira</a>
- <a href="#tg"> Уведомления в Telegram с использованием бота</a>
- <a href="#video"> Видео примера запуска тестов в Selenoid</a>

____
<a id="tools"></a>
## Используемые инструменты

<p align="center">
<a href="https://www.java.com/"><img width="6%" title="Java" src="media/logo/Java.svg"></a>
<a href="https://selenide.org/"><img width="6%" title="Selenide" src="media/logo/Selenide.svg"></a>
<a href="https://aerokube.com/selenoid/"><img width="6%" title="Selenoid" src="media/logo/Selenoid.svg"></a>
<a href="https://github.com/allure-framework/allure2"><img width="6%" title="Allure Report" src="media/logo/Allure_Report.svg"></a>
<a href="https://qameta.io/"><img width="5%" title="Allure TestOps" src="media/logo/AllureTestOps.svg"></a>
<a href="https://gradle.org/"><img width="6%" title="Gradle" src="media/logo/Gradle.svg"></a>
<a href="https://junit.org/junit5/"><img width="6%" title="JUnit5" src="media/logo/JUnit5.svg"></a>
<a href="https://github.com/"><img width="6%" title="GitHub" src="media/logo/GitHub.svg"></a>
<a href="https://www.jenkins.io/"><img width="6%" title="Jenkins" src="media/logo/Jenkins.svg"></a>
<a href="https://web.telegram.org/a/"><img width="6%" title="Telegram" src="media/logo/Telegram.svg"></a>
<a href="https://www.atlassian.com/ru/software/jira/"><img width="5%" title="Jira" src="media/logo/Jira.svg"></a>
</p>


____
Тесты написаны на языке <code>Java</code> с использованием фреймворка для автоматизации тестирования [Selenide](https://selenide.org/), сборщик - <code>Gradle</code>.

<code>JUnit 5</code> задействован в качестве фреймворка модульного тестирования.
При прогоне тестов для удаленного запуска используется [Selenoid](https://aerokube.com/selenoid/).

Для удаленного запуска реализована джоба в <code>Jenkins</code> с формированием Allure-отчета и отправкой результатов в <code>Telegram</code> при помощи бота.
Также реализована интеграция с <code>Allure TestOps</code> и <code>Jira</code>.


Содержание Allure-отчета для каждого кейса:
* Шаги теста и результат их выполнения
* Скриншот страницы на последнем шаге (возможность визуально проанализировать, почему упал тест)
* Page Source (возможность открыть source страницы в новой вкладке и посмотреть причину падения теста)
* Логи консоли браузера
* Видео выполнения автотеста.
____
<a id="cases"></a>
## Тест-кейсы

Auto:
#### Авторизация покупателя:
- ✓ Успешная авторизация зарегистрированного покупателя
- ✓ Авторизация покупателя с неверным паролем
- ✓ Авторизация покупателя с неверным логином
#### Покупка билетов
- ✓ Проверка отображения общей стоимости выбранных билетов
#### Мероприятия
- ✓ Проверка отображения возрастного ограничения на странице мероприятия
- ✓ Проверка отображения стоимости билета на странице мероприятия
#### Главная страница
- ✓ Проверка отображения формы для авторизации покупателя
- ✓ Проверка отображения названий типов мероприятия в навигационном меню

Manual:
- ✓ Открытие страниц сайтов при клике на ссылку в header
  <a id="autotests"></a>
____
## Запуск автотестов

### Запуск тестов из терминала

Локальный запуск.
Из корневой директории проекта выполнить:
- Запуск всех тестов после команды:
```
gradle clean All_test
```
- Запуск тестов для главной страницы:
```
gradle clean MainPage_test
```
- Запуск тестов для проверки авторизации пользователя:
```
gradle clean Auth_test
```
- Запуск тестов для мероприятия:
```
gradle clean Events_test
```
- Запуск тестов для проверки покупки билетов на мероприятие:
```
gradle clean Tickets_test 
```
____
<a id="jenkins"></a>
## <img width="4%" style="vertical-align:middle" title="Jenkins" src="media/logo/Jenkins.svg"> </a> Сборка в <a target="_blank" href="https://jenkins.autotests.cloud/job/BirdsAutotests/"> Jenkins </a>
Для доступа в Jenkins необходима регистрация на ресурсе [Jenkins](https://jenkins.autotests.cloud/)

Для запуска сборки необходимо перейти в раздел <code>Build with parameters</code>, выбрать необходимые параметры и нажать кнопку <code>Build</code>.
###  Параметры сборки в Jenkins:
```
- TASK (набор тестов для запуска)
- URL (адрес основной страницы тестируемого сайта)
- SELENOID (адрес удаленного сервера, на котором будут выполняться тесты)
- SIZE (размер окна браузера, по умолчанию 1920x1080)
- VERSION (версия браузера, по умолчанию 100.0. Реализован запуск в Firefox на версиях 98.0 и 97.0, а также Chrome 99.0 и 100.0 )
- BROWSER (браузер, по умолчанию chrome)
```
<p align="center">
<img title="Jenkins Build" src="media/screenshots/jenkins.png">
</p>
После выполнения сборки, в блоке <code>Build History</code> напротив номера сборки появятся значки <code>Allure Report</code> и <code>Allure TestOps</code>, при клике на которые откроется страница с сформированным html-отчетом и тестовой документацией соответственно.

____
<a id="allureReport"></a>
## <img width="4%" style="vertical-align:middle" title="Allure Report" src="media/logo/Allure_Report.svg"> </a> Пример <a target="_blank" href="https://jenkins.autotests.cloud/job/BirdsAutotests/6/allure/"> Allure-отчета </a>


<p align="center">
<img title="Allure Overview" src="media/screenshots/allureReport.png">
</p>

____
<a id="allure"></a>
## <img width="4%" style="vertical-align:middle" title="Allure TestOps" src="media/logo/AllureTestOps.svg"> </a> Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/3872/dashboards"> Allure TestOps </a>

На *Dashboard* в <code>Allure TestOps</code> видна статистика количества тестов: сколько из них добавлены и проходятся вручную, сколько автоматизированы. Новые тесты, а так же результаты прогона приходят по интеграции при каждом запуске сборки.

<p align="center">
<img title="Allure TestOps DashBoard" src="media/screenshots/allureTestOps.png">
</p>

____
<a id="jira"></a>
## <img width="4%" style="vertical-align:middle" title="Jira" src="media/logo/Jira.svg"> </a> Интеграция с <a target="_blank" href="https://jira.autotests.cloud/browse/HOMEWORK-1000"> Jira </a>

Реализована интеграция <code>Allure TestOps</code> с <code>Jira</code>, в задаче отображается, какие тест-кейсы были написаны в рамках задачи и результат их прогона.

<p align="center">
<img title="Jira Task" src="media/screenshots/jira.png">
</p>

____
<a id="tg"></a>
## <img width="4%" style="vertical-align:middle" title="Telegram" src="media/logo/Telegram.svg"> Уведомления в Telegram с использованием бота

После завершения сборки, бот, созданный в <code>Telegram</code>, автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов в специально настроенный чат.

<p align="center">
<img width="70%" title="Telegram Notifications" src="media/screenshots/telegram.png">
</p>

____
<a id="video"></a>
## <img width="4%" style="vertical-align:middle" title="Selenoid" src="media/logo/Selenoid.svg"> Видео примера запуска тестов в Selenoid

В отчетах Allure для каждого теста прикреплен не только скриншот, но и видео прохождения теста
<p align="center">
  <img title="Selenoid Video" src="media/screenshots/test.gif">
</p>