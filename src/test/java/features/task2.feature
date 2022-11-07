Feature: 2 тест по заданию Slotegrator

  @Run
  Scenario: Задание 2 - открыть список игроков
    Given войти на страницу регистрации "http://test-app.d6.dev.devcaz.com/admin/login", авторизоваться, введя в поле login : "admin1", в поле password: "[9k<k8^z!+$$GkuP"
    When открыть список игроков
    Then таблица с игроками загрузилась
    And закрыть браузер