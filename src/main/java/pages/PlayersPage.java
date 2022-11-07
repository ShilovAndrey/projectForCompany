package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс PageObject главной страницы Players
 */
public class PlayersPage {

    protected WebDriver chromeDriver;
    protected WebDriverWait wait;
    private WebElement tableWithPlayers;


    public PlayersPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        tableWithPlayers = chromeDriver.findElement(By.xpath("//table[@class='table table-hover table-striped table-bordered table-condensed']"));
    }

    /**
     * Проверяет загрузку таблицы
     *
     * @return boolean
     * @author A.Shilov
     */
    public boolean checkIfPlayersTableDisplayed() {
        return tableWithPlayers.isDisplayed();
    }

    /**
     * Сортирует таблицу по колонке Username
     *
     * @author A.Shilov
     */
    public void sortTableByUsername() {
        WebElement userNameColumnTitleElement = chromeDriver.findElement(By.xpath("//a[contains(.,'Username')]"));
        userNameColumnTitleElement.click();
        WebElement disElement = chromeDriver.findElement(By.xpath("//tbody//td/a[@href][1]"));
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(disElement));
    }

    /**
     * Проверяет корректность сортировки таблицы по столбцу Username. Считывает в список значения столбца Username, потом
     * сортирует и сравнивает отсортированную коллекцию со списком, загруженным со страницы сайта
     *
     * @return boolean
     * @author A.Shilov
     */
    public boolean isTableCorrectlySorted() {
        List<String> headerList = new ArrayList<>();
        List<String> listForCollectionsSort = new ArrayList<>();
        chromeDriver.findElements(By.xpath("//table/thead//th/a")).forEach(x -> headerList.add(x.getText()));
        int userNameIndex = headerList.indexOf("Username") + 2;
        chromeDriver.findElements(By.xpath("//table//tbody//td[" + userNameIndex + "]")).forEach(x -> listForCollectionsSort.add(x.getText()));
        List<String> sortedListByCite = new ArrayList<>(listForCollectionsSort);
        Collections.sort(listForCollectionsSort);
        return listForCollectionsSort.equals(sortedListByCite);
    }
}

