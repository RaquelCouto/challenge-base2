package com.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTaskPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    public CreateTaskPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.loginPage = new LoginPage(driver, wait);
    }

    public void navigateToCreateTaskPage() {
        loginPage.waitForPageLoad();
        WebElement criarTarefa = driver.findElement(By.cssSelector("#sidebar .nav li:nth-of-type(3)"));
        wait.until(ExpectedConditions.visibilityOf(criarTarefa));
        criarTarefa.click();
    }

    public void fillTaskDetails(String summaryText, String descriptionText, int categoryIndex) {
        Select categoryTest = new Select(driver.findElement(By.id("category_id")));
        categoryTest.selectByIndex(categoryIndex);

        driver.findElement(By.id("summary")).sendKeys(summaryText);
        driver.findElement(By.id("description")).sendKeys(descriptionText);
    }

    public void fillPartialTaskDetails(String summaryText, String descriptionText) {
        driver.findElement(By.id("summary")).sendKeys(summaryText);
        driver.findElement(By.id("description")).sendKeys(descriptionText);
    }

    public void selectPrivateTask() {
        WebElement privateRadioButton = driver.findElement(By.xpath("//*[@id='report_bug_form']/div/div[2]/div[1]/div/table/tbody/tr[12]/td/label[2]/span"));
        privateRadioButton.click();
    }

    public void submitTask() {
        WebElement createNewTaskButton = driver.findElement(By.className("btn"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", createNewTaskButton);
        createNewTaskButton.click();
        loginPage.waitForPageLoad();
    }

    public void verifyTaskCreated(String expectedSummaryText) {
        loginPage.waitForPageLoad();
        WebElement verTarefas = driver.findElement(By.xpath("//*[@id='sidebar']/ul/li[2]/a/i"));
        wait.until(ExpectedConditions.visibilityOf(verTarefas));
        verTarefas.click();

        WebElement tbody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#buglist tbody")));
        loginPage.waitForPageLoad();
        WebElement cell = tbody.findElement(By.cssSelector(".column-summary"));
        String actualText = cell.getText();

        if (!actualText.contains(expectedSummaryText)) {
            throw new AssertionError("Expected text is not present!");
        }
    }

    public void verifyCategoryFieldError() {
        String expectedText = "Um campo necessário 'category' estava vazio. Por favor, verifique novamente suas entradas.";
        WebElement categoryEmptyError = driver.findElement(By.cssSelector(".alert p:nth-of-type(2)"));
        String actualText = categoryEmptyError.getText();
        if (!actualText.contains(expectedText)) {
            throw new AssertionError("Expected error message is not present!");
        }
    }

    public void selectProfile(int index) {
        Select profileID = new Select(driver.findElement(By.id("profile_id")));
        profileID.selectByIndex(index);
    }

}
