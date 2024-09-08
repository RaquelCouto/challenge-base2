package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.Pages.CreateTaskPage;
import com.example.Pages.LoginPage;
import com.example.Pages.MyAccountPage;

import java.io.IOException;
import java.time.Duration;

public class CreateTaskTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private CreateTaskPage createTaskPage;
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;


    @BeforeEach
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        createTaskPage = new CreateTaskPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
        myAccountPage = new MyAccountPage(driver, wait);
    
        loginPage.loadCredentials();
        loginPage.login();
        
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
    // Can not create a task without required field
    public void createTaskWithoutRequiredField() {
        createTaskPage.navigateToCreateTaskPage();
        createTaskPage.fillPartialTaskDetails("Summary Test privado *&", "Description test 1098773&%$$");
        createTaskPage.submitTask();
        createTaskPage.verifyCategoryFieldError();
    }

    @Test
    // Can create a task using a profile created
    public void canCreateTaskUsingProfileCreated() throws IOException{
        myAccountPage.loginMyAccount();
        myAccountPage.selectProfile();
        myAccountPage.AddProfile("Android", "Android", "Android14", "Android-Test");
        createTaskPage.navigateToCreateTaskPage();
        createTaskPage.fillTaskDetails("Summary Test with Profile", "Description test 1098773&%$$", 1);
        createTaskPage.selectProfile(1);
        createTaskPage.submitTask();
        createTaskPage.verifyTaskCreated("Summary Test with Profile");
        myAccountPage.navigateToProfilePage();
        myAccountPage.deleteAllProfilesCreated();
    }

}