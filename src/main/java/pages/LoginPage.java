package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс PageObject осуществляет аутентификацию пользователя в системе
 */
public class LoginPage {

    protected WebDriver chromeDriver;
    private WebElement loginField;
    private WebElement passwordField;
    private WebElement signInButton;

    public LoginPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        loginField = chromeDriver.findElement(By.xpath("//input[@id='UserLogin_username']"));
        passwordField = chromeDriver.findElement(By.xpath("//input[@id='UserLogin_password']"));
        signInButton = chromeDriver.findElement(By.xpath("//input[@type='submit']"));
    }

    /**
     * Осуществляет вход в систему под пользователем
     *
     * @param username учетное имя пользователя для входа в систему
     * @param password учетный пароль пользователя для входа в систему
     * @author A.Shilov
     */
    @Step("Войти в систему под пользователем: {username}")
    public void login(String username, String password) {
        loginField.click();
        loginField.sendKeys(username);
        passwordField.click();
        passwordField.sendKeys(password);
        signInButton.click();
    }
}
