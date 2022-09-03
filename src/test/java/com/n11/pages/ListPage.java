package com.n11.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPage extends BasePage {


    @FindBy (xpath = "(//h4[@class='listItemTitle'])[1]")
    public WebElement listItem;

    @FindBy (xpath = "//span[@class='deleteProFromFavorites']")
    public WebElement deleteItem;

    @FindBy (xpath = "//div[@class='btnHolder']")
    public WebElement tamamButton;




}
