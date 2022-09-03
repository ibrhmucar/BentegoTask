package com.n11.pages;

import com.n11.utilities.Driver;
import com.n11.utilities.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

    MainPage mainPage = new MainPage();

    @FindBy(id = "email")
    public WebElement emailSection;

    @FindBy(id = "password")
    public WebElement passwordSection;

    @FindBy(xpath = "//label[@class='rememberMe']")
    public WebElement rememberMeButton;

    @FindBy(id = "loginButton")
    public WebElement loginButton;

    @FindBy(xpath = "//a[@title='Hesabım']")
    public WebElement hesabimButton;




    public void login(String username, String password){
        mainPage.signInButton.click();
        emailSection.sendKeys(username);
        passwordSection.sendKeys(password);

        WebElement loginButton = Driver.get().findElement(By.id("loginButton"));
        loginButton.click();
    }



}

