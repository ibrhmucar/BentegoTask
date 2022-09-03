package com.n11.pages;

import com.n11.utilities.Driver;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {


    public BasePage() {

        PageFactory.initElements(Driver.get(), this);
    }

}




