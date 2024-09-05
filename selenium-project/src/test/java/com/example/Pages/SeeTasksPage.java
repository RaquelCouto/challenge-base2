package com.example.Pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;

public class SeeTasksPage {

    private static final String FILE_PATTERN = "^.*_Project\\\\.(xml|csv|temp)$";
    private WebDriver driver;
    private WebDriverWait wait;

    public SeeTasksPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void login(String username, String password) {
        driver.get("https://mantis-prova.base2.com.br");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.className("width-40")).click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys(password);
        driver.findElement(By.className("width-40")).click();

        waitForPageLoad();
    }

    public void navigateToAllTasks() {
        waitForPageLoad();
        WebElement seeAllTasks = driver.findElement(By.cssSelector("#sidebar .nav li:nth-of-type(2)"));
        wait.until(ExpectedConditions.visibilityOf(seeAllTasks)).click();
    }

    public void fillTaskDetails(String summaryText, String descriptionText, int categoryIndex) {
        Select categorySelect = new Select(driver.findElement(By.id("category_id")));
        categorySelect.selectByIndex(categoryIndex);

        driver.findElement(By.id("summary")).sendKeys(summaryText);
        driver.findElement(By.id("description")).sendKeys(descriptionText);
    }

    public void submitTask() {
        WebElement createNewTaskButton = driver.findElement(By.className("btn"));
        createNewTaskButton.click();
        waitForPageLoad();
    }

    public void applyMarkers(String markerText, int markerIndex) {
        WebElement markers = driver.findElement(By.cssSelector("#tag_string"));
        markers.sendKeys(markerText);

        WebElement knowMarkersList = driver.findElement(By.cssSelector("#tag_select"));
        Select knowMarkers = new Select(knowMarkersList);
        knowMarkers.selectByIndex(markerIndex);

        WebElement applyButton = driver.findElement(By.cssSelector("input[type='submit'][value='Aplicar']"));
        applyButton.click();
    }

    public void searchForTask(String searchText) {
        WebElement searchField = driver.findElement(By.cssSelector(".nav-search-input"));
        searchField.sendKeys(searchText);
        searchField.sendKeys(Keys.ENTER);
    }

    public void verifyTaskText(String expectedText) {
        WebElement bugIdFromTable = driver.findElement(By.cssSelector(".table tbody .bug-header-data .bug-id"));
        String actualText = bugIdFromTable.getText();
        assertEquals(expectedText, actualText);
    }

    public void verifyErrorText(String expectedText) {
        WebElement errorElement = driver.findElement(By.cssSelector(".alert p:nth-of-type(2)"));
        String actualText = errorElement.getText();
        assertTrue(actualText.contains(expectedText), "Expected text is not present!");
    }

    public void verifyTaskMarkerText(String expectedText) {
        WebElement markerAddedButton = driver.findElement(By.cssSelector(".btn.btn-xs"));
        String actualText = markerAddedButton.getText();
        assertNotEquals(expectedText, actualText, "The actual text is equal to the expected, but it should be different!");
    }

    public void applyFilter(int statusIndex, String expectedText) {
        waitForPageLoad();
        WebElement statusFilter = driver.findElement(By.cssSelector(".small-caption #show_status_filter"));
        wait.until(ExpectedConditions.visibilityOf(statusFilter)).click();

        waitForPageLoad();
        WebElement statusSelector = driver.findElement(By.cssSelector(".input-xs[name='status[]']"));
        Select statusSelect = new Select(statusSelector);
        statusSelect.selectByIndex(statusIndex);

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit'][value='Aplicar Filtro']"));
        submitButton.click();

        WebElement tableStatusField = driver.findElement(By.cssSelector("#buglist tbody .column-status"));
        String actualText = tableStatusField.getText();
        assertTrue(actualText.contains(expectedText), "Expected text is not present!");
    }

    public void waitForPageLoad() {
        try {
            Thread.sleep(4000); // Use explicit waits instead for more robust solutions
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public boolean isFileDownloaded(String downloadPath) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        if (dirContents != null) {
            Pattern pattern = Pattern.compile(FILE_PATTERN);

            for (File file : dirContents) {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.matches()) {
                    // Arquivo encontrado e corresponde ao padrão regex
                    return true;
                }
            }
        }

        // Nenhum arquivo encontrado que corresponda ao padrão regex
        return false;
    }


}
