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
import org.openqa.selenium.interactions.SourceType;
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

    public void bentegoTask() throws Exception {


        /** Siteye login olunur ve kullanıcı ismi kontrol edilir */

        String username = ConfigurationReader.get("username");
        String password = ConfigurationReader.get("password");
        loginPage.login(username, password);
        extentLogger.pass("Başarı ile giriş yapıldı.");

        //Hesabım sayfasının açıldığı kontrol edilir.
        Utils.navigateToHesabim();
        Utils.verification("https://www.n11.com/hesabim/siparislerim");
        Utils.waitFor(1);

        //Kullanıcı adı ile doğrulama yapılır.
        String userNameActual = Driver.get().findElement(By.xpath(loginPage.accountName)).getText();
        System.out.println(userNameActual);
        String expectedName = "Bentego Task";
        Assert.assertTrue(expectedName.equals(userNameActual));
        System.out.println("Kullanıcı adı " + expectedName + " olarak doğrulandı.");
        extentLogger.pass("Kullanıcı adı doğrulandı.");


        /** telefon kelimesi aratılır ve aratıldığı kontrol edilir */

        accountPage.searchbar.click();
        accountPage.searchbar.sendKeys("telefon");
        accountPage.searchbar.sendKeys(Keys.ENTER);
        Utils.waitFor(2);
        String searchWords = Driver.get().findElement(By.xpath("//*[@class=\"resultText \"]/h1")).getAttribute("innerHTML");
        Assert.assertTrue(searchWords.contains("telefon"));
        System.out.println("Telefon kelimesi aratıldı.");
        extentLogger.pass("Telefon kelimesi başarı ile aratıldı.");


        /** Sayfa 2 seçilir, gidilir ve kontrol edilir */

      //  Utils.scrollToElement(accountPage.pageNumber);
        Utils.clickWithJS(accountPage.pageNumber);
        Utils.waitFor(5);

        String pageNumber = Driver.get().findElement(By.cssSelector(".active")).getAttribute("data-page");
        Assert.assertTrue(pageNumber.contains("2"));
        System.out.println("Sayfanın " + pageNumber+ "'icni sayfa olduğu doğrulandı.");
        extentLogger.pass("Sayfanın 2'inci sayfa olduğu doğrulandı.");


        /** Ürün sepete eklenir */

        int rastGeleSayi =Utils.randomNumber();
        System.out.println(rastGeleSayi);
        Utils.waitFor(1);
        String expectedUrun = Driver.get().findElement(By.xpath("(//h3[@class='productName'])["+rastGeleSayi+"]")).getText();
        WebElement urun = Driver.get().findElement(By.xpath("(//h3[@class='productName'])["+rastGeleSayi+"]"));
        Utils.clickWithJS(urun);
        Utils.waitFor(2);
        WebElement sepeteEkle = Driver.get().findElement(By.xpath("//button[@type='submit']"));
        Utils.waitFor(1);
        sepeteEkle.click();
        Utils.waitFor(2);
        String warningTextExpected = "Ürün sepetinize eklendi";
        String warningTextAcutal = Driver.get().findElement(By.xpath("//div[@class='text']")).getText();
        Assert.assertTrue(warningTextAcutal.contains(warningTextExpected));
        extentLogger.pass(rastGeleSayi+"'inci Ürün sepete başarı ile eklendi.");


        /** Sepetim sayfasına gidilir ve ürünün sepete eklendiği kontrol edilir*/


        Utils.waitFor(2);
        WebElement sepetim = Driver.get().findElement(By.xpath("(//a[@href='//www.n11.com/sepetim'])[1]"));
        sepetim.click();
        Utils.waitFor(2);
        String sepetimUrun= Driver.get().findElement(By.xpath("//a[@class='prodDescription']")).getText();
        Assert.assertTrue(sepetimUrun.equals(expectedUrun));

        System.out.println("Seçilen " + expectedUrun + "ürününün sepete eklendiği onaylandı.");
        extentLogger.pass("Ürünün sepette olduğu doğrulandı.");


        /** Sepete eklenmiş olan ürün silinir ve kontrol edilir */

        Utils.waitFor(2);
        listPage.deleteItem.click();
        Utils.waitFor(2);
        listPage.acceptDelete.click();

        String message = Driver.get().findElement(By.xpath("//h2[@class='title']")).getText();
        Assert.assertTrue(message.contains("Sepetin Boş Görünüyor"));

        System.out.println("Ürünün silindi ve sepetin boş olduğu onaylandı.");
        extentLogger.pass("Sepetin boş olduğu doğrulandı.");


        /** Sitemden başarılı şekilde çıkış yapılır */

        WebElement logOut = Driver.get().findElement(By.xpath("//a[@class='logoutBtn']"));
        WebElement hesabim= Driver.get().findElement(By.xpath("//a[@href='//www.n11.com/hesabim']"));
        actions.moveToElement(hesabim);
        actions.moveToElement(logOut);
        Utils.clickWithJS(logOut);

        Utils.waitFor(2);

        System.out.println("Sistemden çıkış yapıldı.");
        extentLogger.pass("Sistemden başarılı şekilde çıkış yapıldı.");

    }


}



