package com.webcrawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClickerIngles {

    public static void main(String[] args) {
        // Selenium WebDriver is a tool to automate web applications
        // HtmlUnitDriver is one of the drivers of Selenium WebDriver; Htmlunit is a headless browser

        // Create a new instance of the html unit driver
        WebDriver driver = new HtmlUnitDriver();

//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
        // enable or disable JS
        //        ((HtmlUnitDriver) driver).setJavascriptEnabled(true); //false by default with new hmtlunitdriver()
        // To silence CSS warnings [for webclient: webclient.setCssErrorHandler(new SilentCssErrorHandler())]
        Logger.getLogger ("").setLevel (Level.OFF);

        // use the driver to visit the target web page
        driver.get("https://www.elcorteingles.es/supermarket/");
        System.out.println("Page title is: " + driver.getTitle());

        // Find the delivery button on the top right by class name //
        System.out.println(driver.findElement(By.className("link.user_bar-item.js-show-time-table.js-custom-gtm-event")).getText());
        driver.findElement(By.className("link.user_bar-item.js-show-time-table.js-custom-gtm-event")).click();
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.id("sess"))));
        System.out.println("num of windows:" + driver.getWindowHandles().size());
        System.out.println("Page title after clicking on 'Delivery times' is: " + driver.getTitle());
        System.out.println("url after clicking on 'Delivery times' is: " + driver.getCurrentUrl());
//        driver.switchTo().defaultContent();

//        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));
        //        System.out.println(driver.getWindowHandle());
        Actions actions = new Actions(driver);
        driver.get(driver.getCurrentUrl());

//        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(popupLocator));

        List<WebElement> deliverySlots = driver.findElements(By.className("sliding_flex_table-wrapper.flex_table._infinite._bordered.js-arrow-navigation-wrapper"));
        for (WebElement slot : deliverySlots) {
            System.out.println(slot.getText());
        }
//        WebElement deliveryButton = driver.findElement(By.className("button.deliver_button.js-select-delivery"));
//        System.out.println(deliveryButton.getText());
//        actions.moveToElement(deliveryButton);
//        deliveryButton.click();
        System.out.println("New Page title is: " + driver.getTitle());
        System.out.println("url of new page is: " + driver.getCurrentUrl());

//        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
//        Actions action = new Actions(driver);
//        action.click((driver.findElement(By.xpath("//button[@class='button deliver_button js-select-delivery']"))));
//        System.out.println(driver.getPageSource());

        //        actions.moveToElement(driver.findElement(By.className("button.deliver_button.js-select-deliver")));
        //        driver.findElement(By.xpath("//button[@class='button deliver_button js-select-delivery']")).click();

        //        WebElement postcode = driver.findElement(By.name("zipcode"));
        //        postcode.sendKeys("38612");
        //        postcode.submit();
        //
        //        WebElement time = driver.findElement(By.className("flex_table-cell._slot._OPEN._vcenter._hcenter." +
        //                        "_bordered._bordered2"));
        //        System.out.println(time.getText());
        //
        //        // Check the title of the page
        //        System.out.println("Page title is: " + driver.getTitle());

        //closing the driver
        driver.quit();
    }
}
