package com.example.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;

public class WrongPasswordLoginPage extends LoginPage {

    public WrongPasswordLoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait); 
    }

    @Override
    public void login() {
        try {
            loadCredentials();  
        } catch (IOException e) {
            e.printStackTrace();
        }
        String wrongPassword = "KSjdje%7T63";
        loginWithCredentials(wrongPassword);
    }
}
