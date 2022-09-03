package com.n11.test;

import com.n11.pages.AccountPage;
import com.n11.pages.ListPage;
import com.n11.pages.LoginPage;
import com.n11.pages.MainPage;
import com.n11.utilities.ConfigurationReader;
import com.n11.utilities.Driver;
import com.n11.utilities.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;


@Test

public class HomePageTest extends TestBase {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    AccountPage accountPage = new AccountPage();
    ListPage listPage = new ListPage();



    /** www.n11.com sitesine gidilir ve sayfanın açıldığı kontrol edilir */

    public void microGrupTest() throws Exception {

        String anasayfa = Utils.captureScreenShot();
        Utils.verification("https://www.n11.com/");
        report.createTest("Anasayfa Giriş").pass("Ana sayfa başarı ile açıldı.").addScreenCaptureFromBase64String(anasayfa);



        /** Siteye login olunur ve kontrol edilir */

        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        loginPage.login(username, password);
        Utils.navigateToHesabim();
        Utils.verification("https://www.n11.com/hesabim/siparislerim");

        String login = Utils.captureScreenShot();
        report.createTest("Login").pass("Başarı şekilde login olundu.").addScreenCaptureFromBase64String(login);


        /** IPhone kelimesi aratılır ve aratıldığı kontrol edilir */

        accountPage.searchbar.click();
        accountPage.searchbar.sendKeys("Iphone");
        accountPage.searchbar.sendKeys(Keys.ENTER);
        Utils.waitFor(2);
        String searchWords = Driver.get().findElement(By.xpath("//*[@class=\"resultText \"]/h1")).getAttribute("innerHTML");
        Assert.assertTrue(searchWords.contains("Iphone"));

        String iphone = Utils.captureScreenShot();
        report.createTest("IPhone Kelimesinin Aratılması").pass("IPhone kelimesi başarı şekilde aratıldı.").addScreenCaptureFromBase64String(iphone);

        /** Sayfa 2 seçilir ve gidilir ve kontrol edilir */

        Utils.scrollToElement(accountPage.pageNumber);
        Utils.clickWithJS(accountPage.pageNumber);
        Utils.waitFor(5);

        String pageNumber = Driver.get().findElement(By.cssSelector(".active")).getAttribute("data-page");
        System.out.println(pageNumber);
        Assert.assertTrue(pageNumber.contains("2"));

        String yeniSayfa = Utils.captureScreenShot();
        report.createTest("2'inci Sayfanın Seçilmesi").pass("2'inci sayfa başarı ile açıldı.").addScreenCaptureFromBase64String(yeniSayfa);


        /** 3'üncü ürün favori listesine eklenir */

        accountPage.addFav3.click();

        String fav = Utils.captureScreenShot();
        report.createTest("Ürünün Favorilere Eklenmesi").pass("3'üncü ürün başarı ile favorilere eklendi").addScreenCaptureFromBase64String(fav);

        /** Favori ürünlerinin eklendiği sayfaya gidilir ve kontrol edilir*/


        Utils.waitFor(1);
        WebElement hesabim = Driver.get().findElement(By.xpath("//a[@title='Hesabım']"));
        WebElement favPage = Driver.get().findElement(By.xpath("//a[@title='Favorilerim / Listelerim']"));
        Utils.hover(hesabim);
        Utils.hover(favPage);
        Utils.clickWithJS(favPage);

        Utils.verfication2("/istek-listelerim");

        String favList = Utils.captureScreenShot();
        report.createTest("Favori Listesine Ürün Eklenmesi").pass("Ürünün favorilere eklendiği kontrol edildi.").addScreenCaptureFromBase64String(favList);


        /** Favorilere eklenmiş olan ürün silinir */

        listPage.listItem.click();
        Utils.waitFor(1);

        listPage.deleteItem.click();
        Utils.waitFor(2);

        String message = Driver.get().findElement(By.xpath("//span[@class='message']")).getText();
        Assert.assertTrue(message.contains("Ürününüz listeden silindi."));

        Utils.waitForVisibility(listPage.tamamButton, 10);
        listPage.tamamButton.click();
        Utils.waitFor(3);

        String deleteFav = Utils.captureScreenShot();
        report.createTest("Favorilerden Ürünün Silinmesi").pass("Ürün başarı şekilde favorilerden silindi.").addScreenCaptureFromBase64String(deleteFav);


        /** Sitemden başarılı şekilde çıkış yapılır */

        WebElement logOut = Driver.get().findElement(By.xpath("//a[@class='logoutBtn']"));
        actions.moveToElement(hesabim);
        actions.moveToElement(logOut);
        Utils.clickWithJS(logOut);

        Utils.waitFor(2);
        String logout = Utils.captureScreenShot();
        report.createTest("Siteden Logout Olunması").pass("Siteden başarılı bir şekilde logout olundu.").addScreenCaptureFromBase64String(logout);


    }


}



