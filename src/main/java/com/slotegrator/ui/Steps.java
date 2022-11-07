package com.slotegrator.ui;

import pages.LoginPage;
import pages.MainPage;
import pages.PlayersPage;

import java.util.concurrent.TimeUnit;

public class Steps {

    private DriverManager driverManager = new DriverManager();
    private PlayersPage playersPage;

    public void openBrowserStep() {
        driverManager.getDriver();
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driverManager.getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    public void openLoginPageStep(String url) {
        driverManager.getDriver().get(url);
    }

    public void loginStep(String user, String password) {
        LoginPage loginPage = new LoginPage(driverManager.getDriver());
        loginPage.login(user, password);
    }

    public boolean checkAuthorizationStep(String login) {
        MainPage mainPage = new MainPage(driverManager.getDriver());
        return mainPage.isUserAuthorizedCorrectly(login);
    }

    public boolean checkDashboardDownloadedStep() {
        MainPage mainPage = new MainPage(driverManager.getDriver());
        return mainPage.isDashboardDownloaded();
    }

    public void openPlayersListStep() {
        MainPage mainPage = new MainPage(driverManager.getDriver());
        mainPage.openPlayersTable();
    }

    public boolean checkIfPlayersTableDisplayedStep() {
        playersPage = new PlayersPage(driverManager.getDriver());
        return playersPage.checkIfPlayersTableDisplayed();
    }

    public void sortTableStep() {
        playersPage = new PlayersPage(driverManager.getDriver());
        playersPage.sortTableByUsername();
    }

    public boolean isTableSortedCorrectlyStep() {
        return playersPage.isTableCorrectlySorted();
    }

    public void closeBrowserStep() {
        driverManager.getDriver().close();
    }
}
