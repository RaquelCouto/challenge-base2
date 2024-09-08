package com.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

public class LoginPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String username;
    protected String password;

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void loginWithCredentials(String passwordToUse) {
        driver.get("https://mantis-prova.base2.com.br");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.className("width-40")).click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys(passwordToUse);
        driver.findElement(By.className("width-40")).click();
    }

    public void loginWithPassword() throws IOException{
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\config.properties")) {
            properties.load(fis);
        }
        password = properties.getProperty("password");
        WebElement passwordField = driver.findElement(By.cssSelector("#password"));
        passwordField.sendKeys(password);
        driver.findElement(By.className("width-40")).click();
    }

    public void login() {
        try {
            loadCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginWithCredentials(password);
    }

    public void loadCredentials() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\config.properties")) {
            properties.load(fis);
        }
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    public void verifyWrongCredentialText( String expectedText) {
        WebElement errorWrongCredentials = driver.findElement(By.cssSelector(".alert p"));
        String actualText = errorWrongCredentials.getText();
        assertEquals(expectedText, actualText);
    }
    public void waitForPageLoad() {
        try {
            Thread.sleep(4000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void verifySuccefullLogin(String expectedUrl) {
        waitForPageLoad();
        assertTrue(wait.until(driver -> driver.getCurrentUrl().equals(expectedUrl)), 
               "The current URL did not match the expected one.");
    }
}
