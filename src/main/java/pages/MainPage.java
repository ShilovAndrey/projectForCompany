package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Класс PageObject главной страницы системы
 */
public class MainPage {

    protected WebDriver chromeDriver;

    public MainPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    /**
     * Осуществляет проверку корректности авторизации
     *
     * @param expectedUserName имя пользователя, под которым должны были авторизоваться
     * @return boolean
     * @author A.Shilov
     */
    @Step("Проверить выполнение авторизации под пользователем: {expectedUserName}")
    public boolean isUserAuthorizedCorrectly(String expectedUserName) {
        List<WebElement> userNameElements = chromeDriver.findElements(By.xpath("//span[contains(.,'" + expectedUserName + "')]"));
        return userNameElements.size() > 0;
    }

    /**
     * Осуществляет проверку загрузку админ-панели
     *
     * @return boolean
     * @author A.Shilov
     */
    @Step("Проверить загрузку админ-панели")
    public boolean isDashboardDownloaded() {
        List<WebElement> dashboardElements = chromeDriver.findElements(By.xpath("//span[contains(.,'Dashboard')]"));
        return dashboardElements.size() > 0;
    }

    /**
     * Переходит на пейдж Players
     *
     * @author A.Shilov
     */
    @Step("Проверить загрузку админ-панели")
    public void openPlayersTable() {
        WebElement playersTableLinkElement = chromeDriver.findElement(By.xpath("//p[contains(.,'Players online / total')]"));
        playersTableLinkElement.click();
    }

}
