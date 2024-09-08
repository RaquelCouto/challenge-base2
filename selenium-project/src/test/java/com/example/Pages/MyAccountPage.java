package com.example.Pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;
import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class MyAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    public MyAccountPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.loginPage = new LoginPage(driver, wait);
    }

    public void navigateToProfilePage() throws IOException {
        loginMyAccount();
        selectProfile();
    }


    public void loginMyAccount() throws IOException {
        loginPage.waitForPageLoad();
        WebElement profileMenuDropdown = driver.findElement(By.cssSelector(".dropdown-toggle"));
        profileMenuDropdown.click();
        WebElement myAccountItem = driver.findElement(By.cssSelector(".user-menu a"));
        myAccountItem.click();

        try{
            WebElement alertText = driver.findElement(By.cssSelector(".alert p"));
            String warningAuth = alertText.getText();
            String expectedString = "Você está visitando uma página segura, e sua sessão expirou. Por favor autentique-se novamente.";
            if(warningAuth == expectedString){
                loginPage = new LoginPage(driver, wait);
                loginPage.loginWithPassword();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No login was required since you are alredy authenticated.");
        }

    }

    public void selectProfile(){

        List<WebElement> navItems = driver.findElements(By.cssSelector(".nav.nav-tabs li"));

        if (navItems.size() >= 4) {
            WebElement profile = navItems.get(3);
            profile.click(); 
        } else {
            System.out.println("Less than four elements found!");
        }
    }

    public void AddProfile(String platform, String OS, String OSVersion, String description){
        WebElement platformField = driver.findElement(By.cssSelector("#platform"));
        platformField.sendKeys(platform);

        WebElement OSField = driver.findElement(By.cssSelector("#os"));
        OSField.sendKeys(OS);

        WebElement OSVersionField = driver.findElement(By.cssSelector("#os-version"));
        OSVersionField.sendKeys(OSVersion);

        WebElement descriptionField = driver.findElement(By.cssSelector("#description"));
        descriptionField.sendKeys(description);

        
        WebElement addProfileButton = driver.findElement(By.cssSelector(".btn[type='submit'][value='Adicionar Perfil']"));
        addProfileButton.click();
    }

    public void findProfileCreated(String expectedString){

        scrollToElementByCss("#account-profile-update-div");
        WebElement selecProfileDropdown = driver.findElement(By.cssSelector("#select-profile"));
        Select dropdown = new Select(selecProfileDropdown);
        dropdown.selectByVisibleText(expectedString);
        
    }

    public void verifyProfileWasCreated(String expectedString){
        WebElement selecProfileDropdown = driver.findElement(By.cssSelector("#select-profile"));
        String actualText = selecProfileDropdown.getText();
        Assertions.assertTrue(actualText.contains(expectedString));
        
    }
    public void deleteProfile(String expectedString){
        findProfileCreated(expectedString);

        List <WebElement> radioButtons = driver.findElements(By.cssSelector("span.lbl"));
        WebElement deleteProfileRadioButton = radioButtons.get(radioButtons.size() - 1);
        deleteProfileRadioButton.click();

        WebElement sendButton = driver.findElement(By.cssSelector(".btn[type='submit'][value='Enviar']"));
        sendButton.click();
    }

    public void deleteAllProfilesCreated(){
        boolean moreProfilesToDelete = true;

        while (moreProfilesToDelete) {
            try {
                WebElement allProfilesDropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#select-profile")));
                Select dropdown = new Select(allProfilesDropDown);
                List<WebElement> profiles = dropdown.getOptions();

                moreProfilesToDelete = false;

                for (WebElement profile : profiles) {
                    if (!profile.getText().equals("(selecione)")) { // Ignora "(selecione)" nas opções da lista
                        deleteProfile(profile.getText());
                        moreProfilesToDelete = true;
                        break;
                    }
                }

                if (profiles.size() == 1 && profiles.get(0).getText().equals("(selecione)")) {
                    break;
                }

            } catch (NoSuchElementException e) {
                moreProfilesToDelete = false;
            } catch (Exception e) {
                moreProfilesToDelete = false;
                e.printStackTrace();
            }
        }
            
    }

    public void scrollToElementByCss(String elementId) {
        
        WebElement element = driver.findElement(By.cssSelector(elementId));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void verifyAllProfilesDeleted(String text) {
        boolean isTextPresent = driver.getPageSource().contains(text);
        assertFalse(isTextPresent, "O texto '" + text + "' foi encontrado na página, mas não deveria estar.");
    }


    public void verifyCantDeleteSelect() {

        WebElement selectAlertText = driver.findElement(By.cssSelector(".alert p"));
        String actualText = selectAlertText.getText();
        String expectedText = "APPLICATION ERROR #11";
        assertEquals(actualText, expectedText);
    }

    
}
