package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.Pages.CreateTaskPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CreateTaskTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private CreateTaskPage createTaskPage;
    private String username;
    private String password;

    @BeforeEach
    public void setUp() throws IOException {
        loadCredentials();
        initializeDriver();
        login();
        createTaskPage = new CreateTaskPage(driver, wait);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    //Can create a new Task with non-ASCII digits
    public void createNewTask() {
        createTaskPage.navigateToCreateTaskPage();
        createTaskPage.fillTaskDetails("Summary Test *&", "Description test 1098773&%$$", 1);
        createTaskPage.submitTask();
        createTaskPage.verifyTaskCreated("Summary Test *&");
    }

    @Test
    //Can create a private task
    public void createNewPrivateTask() {
        createTaskPage.navigateToCreateTaskPage();
        createTaskPage.fillTaskDetails("Summary Test privado *&", "Description test 1098773&%$$", 1);
        createTaskPage.selectPrivateTask();
        createTaskPage.submitTask();
        createTaskPage.verifyTaskCreated("Summary Test privado *&");
    }

    @Test
    //Can not create a task without required field
    public void createTaskWithoutRequiredField() {
        createTaskPage.navigateToCreateTaskPage();
        createTaskPage.fillPartialTaskDetails("Summary Test privado *&", "Description test 1098773&%$$");
        createTaskPage.submitTask();
        createTaskPage.verifyCategoryFieldError();
    }

    private void loadCredentials() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src\\config.properties")) {
            properties.load(fis);
        }
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    private void initializeDriver() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void login() {
        driver.get("https://mantis-prova.base2.com.br");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.className("width-40")).click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys(password);
        driver.findElement(By.className("width-40")).click();
    }
}