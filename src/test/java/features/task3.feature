Feature: 3 тест по заданию Slotegrator

  @Run
  Scenario: Задание 3 - отсортировать по любому столбцу и проверить сортировку
    Given войти на страницу регистрации "http://test-app.d6.dev.devcaz.com/admin/login", авторизоваться, введя в поле login : "admin1", в поле password: "[9k<k8^z!+$$GkuP", открыть список игроков
    When выполнить сортировку по любому столбцу таблицы
    Then таблица верно отсортирована по выбранному столбцу
    And закрыть браузер