package com.n11.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {


    @FindBy(xpath = "//a[@class='btnSignIn']")
    public WebElement signInButton;

    @FindBy(xpath = "(//span[@class='setCookieBtn pickAll'])[2]")
    public WebElement acceptAllCookies;

}
