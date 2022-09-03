package com.n11.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    @FindBy(id="searchData")
    public WebElement searchbar;

    @FindBy(xpath = "//a[@data-page='2']")
    public WebElement pageNumber;

    @FindBy(xpath = "(//span[@title='Favorilere ekle'])[3]")
    public WebElement addFav3;

    @FindBy(xpath = "//a[@title='Hesabım']")
    public WebElement hesabimLink;

}

