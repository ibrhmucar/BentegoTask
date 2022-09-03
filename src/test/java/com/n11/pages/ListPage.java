package com.n11.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPage extends BasePage {


    @FindBy (xpath = "(//h4[@class='listItemTitle'])[1]")
    public WebElement listItem;


    @FindBy (xpath = "//span[@title='Sil']")
    public WebElement deleteItem;

    @FindBy (xpath = "//span[@id='deleteBtnDFLB']")
    public WebElement acceptDelete;



}
