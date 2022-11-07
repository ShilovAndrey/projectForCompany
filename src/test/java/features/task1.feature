Feature: 1 тест по заданию Slotegrator

  @Run
  Scenario: Задание 1 - авторизоваться в админке
    Given войти на страницу регистрации "http://test-app.d6.dev.devcaz.com/admin/login"
    When авторизоваться, введя в поле login : "admin1", в поле password: "[9k<k8^z!+$$GkuP"
    Then пользователь успешно авторизован под логином "admin1"
    And админ-панель загрузилась
    And закрыть браузер

