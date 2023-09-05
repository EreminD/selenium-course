## Напишите фабрику WebDriver'ов

#### Шаги:
1. Изучите паттерн Фабрика (можно [тут](https://vertex-academy.com/tutorials/ru/pattern-factory-java/) или [тут](https://progery.ru/pattern-fabrika-java))
2. Напишите класс WebDriverFactory:
   1. Фабрика предоставляет два метода `getDriver(String name)` и `getDriver(String name, String... args)`
   2. Первый метод создает новый веб-драйвер (какой именно - определяется из `name`)
   3. Второй метод также создает драйвер, но запускает его с параметрами, которые были переданы в `args`)
3. Доработайте ваш тест на Лабиринт (или любой другой):
   1. Тест должен стать параметризированным
   2. Параметром является имя браузера ("chrome", "ff", "edge", ...)
   3. Перед началом работ, тест получает драйвер по имени из фабрики

#### Примеры вызовов:
- Открыть Хром `WebDriverFactory.getDriver(Drivers.CHROME)`
- Открыть Хром с опциями `WebDriverFactory.getDriver(Drivers.CHROME, "--window-size=800,800", "--window-position=50,50")`

#### Список опций по браузерам:
https://www.selenium.dev/documentation/webdriver/browsers/