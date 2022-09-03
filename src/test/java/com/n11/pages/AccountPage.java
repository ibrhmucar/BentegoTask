package com.n11.pages;

import com.n11.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    @FindBy(id="searchData")
    public WebElement searchbar;

    @FindBy(xpath = "//a[@data-page='2']")
    public WebElement pageNumber;

    @FindBy(xpath = "(//span[@class='btnBasket'])[3]")
    public WebElement addBasket;

    @FindBy(xpath = "//a[@title='Hesabım']")
    public WebElement hesabimLink;

    @FindBy(xpath = "(//span[@class='btnBasket'])[3]")
    public WebElement warningText;

    @FindBy(xpath = "(//a[@class='plink'])[3]")
    public WebElement clickTheItem;

    public String urun = "(//h3[@class='proDescription'])[3]";

}

