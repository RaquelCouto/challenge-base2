package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.Pages.LoginPage;
import com.example.Pages.WrongPasswordLoginPage;



import java.io.IOException;
import java.time.Duration;

public class LoginTest {

    private LoginPage loginPage;
    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    // Can't login with wrong password
    public void cantLogInWithWrongPassword() throws IOException {
        loginPage = new WrongPasswordLoginPage(driver, wait);
        loginPage.loadCredentials();
        loginPage.login();
        
        loginPage.verifyWrongCredentialText("Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.");
    }

    @Test
    //Login successfull
    public void LoginSuccessfull() throws IOException{
        loginPage = new LoginPage(driver, wait);
        loginPage.loadCredentials();
        loginPage.login();
        String expectedURL = "https://mantis-prova.base2.com.br/my_view_page.php";
        loginPage.verifySuccefullLogin(expectedURL);
    }
    
}
