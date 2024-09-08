package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import com.example.Pages.LoginPage;
import com.example.Pages.MyAccountPage;

import java.io.IOException;

public class MyAccountTest {
    private LoginPage loginPage;
    private MyAccountPage myAccountPage;
    private WebDriver driver;
    private WebDriverWait wait;

    String platformName = "&@¨(@*)";
    String operationalSystemName = "Windows";
    String oSVersionName = "2837-03%4";
    String descrition = "test add pr0fil&";


    @BeforeEach
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    // Can create a new Profile
    public void canCreateNewProfile() throws IOException{
        myAccountPage.loginMyAccount();
        myAccountPage.selectProfile();
        myAccountPage.AddProfile(platformName, operationalSystemName, oSVersionName, descrition);
        String expectedText = platformName+" "+operationalSystemName+" "+oSVersionName;
        myAccountPage.verifyProfileWasCreated(expectedText);
        myAccountPage.deleteProfile(expectedText);
    }

    @Test
    //Can delete a Profile Created
    public void canDeleteAllProfilesCreated() throws IOException{
        myAccountPage.loginMyAccount();
        myAccountPage.selectProfile();
        for(int i = 0; i <4; i++){
            myAccountPage.AddProfile(platformName+i, operationalSystemName+i, oSVersionName+i, descrition+i);
        }

        myAccountPage.deleteAllProfilesCreated();
        myAccountPage.verifyAllProfilesDeleted("Alterar ou Apagar Perfís");

    }

    @Test
    //Can not delete (Select)
    public void cantDeleteSelect() throws IOException{
        myAccountPage.loginMyAccount();
        myAccountPage.selectProfile();
        myAccountPage.deleteProfile("(selecione)");
        myAccountPage.verifyCantDeleteSelect();
    }
}
