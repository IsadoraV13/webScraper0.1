package com.webcrawler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.webcrawler.models.ConsumerJumbo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ClickerJumbo {

    public static void main(String[] args) {
        // Selenium WebDriver is a tool to automate web applications
        // HtmlUnitDriver is one of the drivers of Selenium WebDriver; Htmlunit is a headless browser

        // Create a new instance of htmlunit driver
        WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
//        ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        java.util.logging.Logger.getLogger("net.sourceforge.htmlunit").setLevel(java.util.logging.Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);

        // Silence CSS warnings [for webclient: webclient.setCssErrorHandler(new SilentCssErrorHandler())]
        Logger.getLogger ("").setLevel (Level.OFF);

        // use driver to visit the target web page
        driver.get("https://www.jumbo.com/service/online-bestellen/reserveer-een-moment");
        System.out.println("initial age title: " + driver.getTitle());
        System.out.println("initial url: " + driver.getCurrentUrl());

        // Find and click on the delivery button (opens a new window)
        System.out.println("Click on: " + driver.findElement(By.className("jum-button.mr.mb.primary")).getText());
        // click on delivery button
        driver.findElement(By.className("jum-button.mr.mb.primary")).click();

        // switch to new window
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        System.out.println("New window opens with new page title: " + driver.getTitle());
        System.out.println("New Page url: " + driver.getCurrentUrl());
        System.out.println("num of windows:" + driver.getWindowHandles().size());
        System.out.println();

        // Click on 'Log in to deliver' (instead of pick up); requires login. Button name is 'Log in/register'
        System.out.println("Click on: " + driver.findElement(By.className("jum-hd-link.jum-btn")).getText());
        driver.findElement(By.className("jum-hd-link.jum-btn")).click();

        System.out.println("Page title changes to: " + driver.getTitle());
        System.out.println("Page url changes to: " + driver.getCurrentUrl());


        // login details
//        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("ShopLoginForm_Login")));
        WebDriver newDriver = new HtmlUnitDriver(BrowserVersion.CHROME, true);
        newDriver.get("https://www.jumbo.com/INTERSHOP/web/WFS/Jumbo-Grocery-Site/nl_NL/-/EUR/ViewDeliveryOptions-Start");
        System.out.println("Enter login details");
        WebElement email = newDriver.findElement(By.name("ShopLoginForm_Login"));
        email.sendKeys("xxxxx");
        WebElement pwd = newDriver.findElement(By.name("ShopLoginForm_Password"));
        pwd.sendKeys("xxxx");

        newDriver.findElement(By.className("jum-btn-yellow.jum-ga-b2b-login-login")).click();

        System.out.println("Page title after attempting to log in: " + driver.getTitle());
        System.out.println("url (seems to be the login error page?): " + driver.getCurrentUrl());
        System.out.println();

        // we should be taken to this page after logging in - but we are being sent to a different page
        driver.get("https://www.jumbo.com/INTERSHOP/web/WFS/Jumbo-Grocery-Site/nl_NL/-/EUR/ViewDeliveryOptions-Start?login=true");
        System.out.println("we therefore go to this url as logging in doesn't take us here automatically:\n" + driver.getCurrentUrl());

        // Find and click on 'choose this delivery address' but this doesn't seem to work
        // url would be: https://www.jumbo.com/INTERSHOP/web/WFS/Jumbo-Grocery-Site/nl_NL/-/EUR/ViewDeliveryOptions-Start?login=true
        System.out.println("Text on button:" + driver.findElement(By.className("jum-hd-link.jum-btn")).getText());
        driver.findElement(By.className("jum-hd-link.jum-btn")).click();
        System.out.println("If login works, button should say 'choose this delivery address' and url would be:\n" +
                "https://www.jumbo.com/INTERSHOP/web/WFS/Jumbo-Grocery-Site/nl_NL/-/EUR/ViewDeliveryOptions-Start?login=true\n" +
                "instead, the url we are on suggests we still need to log in:\n" + driver.getCurrentUrl());
        System.out.println();

        // final page to see delivery slots
        driver.get("https://www.jumbo.com/INTERSHOP/web/WFS/Jumbo-Grocery-Site/nl_NL/-/EUR/ViewPUPSelection-ViewSlotBooking?ft=hd&HDAddressChanged=true");
        System.out.println("If we try to fetch this final page to see delivery slots: " + driver.getTitle());
        System.out.println("button reads: " + driver.findElement(By.className("jum-checkout-info-address-title")).getText()
                + ", which means 'select delivery address'");

        new ConsumerJumbo().Start();
//        WebElement table = driver.findElement(By.xpath("//div[@class='slotbooking-calendar']"));

//        Actions actions = new Actions(driver);
//        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOfElementLocated(By.className("slotbooking-calendar")));

        driver.quit();
    }
}
