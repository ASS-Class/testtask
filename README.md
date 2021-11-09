# Тестовое задание
Для запуска теста в Windows достаточно выполнить команду `mvn clean test allure:serve`

Если версия Chrome <> 95, или ОС <> Windows, то необходимо указать путь до chromedriver в параметре `-Ddriver_path`

Например:
`mvn clean test allure:serve -Ddriver_path=C://selenium//chromedriver.exe`

Лог пишется в `target\tests.log`

Аллюр собирается по @Step'ам (не по аннотациям Cucumber). 
