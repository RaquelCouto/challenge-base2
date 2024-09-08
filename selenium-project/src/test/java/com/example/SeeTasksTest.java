package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import com.example.Pages.*;


public class SeeTasksTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private SeeTasksPage seeTasksPage;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
        seeTasksPage = new SeeTasksPage(driver, wait);
        loginPage = new LoginPage(driver, wait);
    
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
    // Verify it is possible to add markers to a Task
    public void addMarkersToTask() {
        seeTasksPage.navigateToAllTasks();
        WebElement firstBugId = driver.findElement(By.cssSelector("#buglist tbody .column-id"));
        firstBugId.click();
        
        seeTasksPage.applyMarkers("newMark%", 4);
        seeTasksPage.verifyTaskMarkerText("Nenhum marcador aplicado.");
    }

    @Test
    //Search for a task that exists
    public void searchTaskThatExists() {
        seeTasksPage.navigateToAllTasks();
        WebElement firstBugId = driver.findElement(By.cssSelector("#buglist tbody .column-id"));
        String existingProject = firstBugId.getText();

        seeTasksPage.searchForTask(existingProject);
        seeTasksPage.verifyTaskText(existingProject);
    }

    @Test
    // Search for a task that not exists
    public void searchTaskThatNotExists() {
        seeTasksPage.navigateToAllTasks();
        seeTasksPage.searchForTask("*@&@4");
        seeTasksPage.verifyErrorText("Um número era esperado para bug_id.");
    }

    @Test
    // Can download a file
    public void canDownloadFile() {
        seeTasksPage.navigateToAllTasks();

        WebElement applyButton = driver.findElement(By.xpath("//*[@id=\"bug_action\"]/div/div[2]/div[1]/div/div[1]/a[2]"));
        applyButton.click();

        String downloadPath = "C:\\Users\\raque\\Downloads"; //if running in another machine, you have to change this path
        boolean fileDownloaded = seeTasksPage.isFileDownloaded(downloadPath);

        assertTrue(fileDownloaded, "File downloaded with success!");
    }

    @Test
    // Verify if it is possible to filter tasks
    public void canApplyFilter() {
        seeTasksPage.navigateToAllTasks();
        seeTasksPage.applyFilter(5, "atribuído");
    }

}

