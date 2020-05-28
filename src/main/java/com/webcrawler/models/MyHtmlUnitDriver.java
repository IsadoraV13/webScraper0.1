package com.webcrawler.models;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyHtmlUnitDriver extends HtmlUnitDriver {

    public MyHtmlUnitDriver(BrowserVersion version, boolean enableJavascript) {
        super(version, enableJavascript);
    }

    protected WebClient modifyWebClient(WebClient client) {
        WebClient modifiedClient = super.modifyWebClient(client);
        Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        modifiedClient.getOptions().setThrowExceptionOnScriptError(false);
        modifiedClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        modifiedClient.getOptions().setPrintContentOnFailingStatusCode(false);
        return modifiedClient;
    }

}
