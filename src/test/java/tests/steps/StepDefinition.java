package tests.steps;

import com.slotegrator.ui.Steps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;

public class StepDefinition extends Steps {

    @Given("войти на страницу регистрации {string}")
    public void openLoginPage(String url) {
        openLoginPageStep(url);
    }

    @When("авторизоваться, введя в поле login : {string}, в поле password: {string}")
    public void logIn(String login, String password) {
        loginStep(login, password);
    }

    @Then("пользователь успешно авторизован под логином {string}")
    public void checkAuthorization(String login) {
        Assertions.assertTrue(checkAuthorizationStep(login), "Ошибка, авторизация не выполнена");
    }

    @And("админ-панель загрузилась")
    public void dashboardDownloadedCheck() {
        Assertions.assertTrue(checkDashboardDownloadedStep(), "Ошибка, админ-панель не загружена");
    }

    @And("закрыть браузер")
    public void closeBrowser(){
        closeBrowserStep();
    }

    @Given("войти на страницу регистрации {string}, авторизоваться, введя в поле login : {string}, в поле password: {string}")
    public void goMainMenu(String url, String login, String password) {
        openLoginPageStep(url);
        loginStep(login, password);
    }

    @When("открыть список игроков")
    public void openPlayersList() {
        openPlayersListStep();
    }

    @Then("таблица с игроками загрузилась")
    public void isPlayerTableDisplayed() {
        Assertions.assertTrue(checkIfPlayersTableDisplayedStep(), "Ошибка, таблица с игрока не была загружена");
    }

    @Given("войти на страницу регистрации {string}, авторизоваться, введя в поле login : {string}, в поле password: {string}, открыть список игроков")
    public void goMainMenuAndOpenPlayersPage(String url, String login, String password) {
        openLoginPageStep(url);
        loginStep(login, password);
        openPlayersListStep();
    }

    @When("выполнить сортировку по любому столбцу таблицы")
    public void sortPlayersTable() {
        sortTableStep();
    }

    @Then("таблица верно отсортирована по выбранному столбцу")
    public void isTableSortedCorrectly() {
        Assertions.assertTrue(isTableSortedCorrectlyStep(), "Ошибка, сортировка произведена неправильно");
    }
}
