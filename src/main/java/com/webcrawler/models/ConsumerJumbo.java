package com.webcrawler.models;

import com.webcrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerJumbo {
    public void Start() {
    crawl();
    }

        private void crawl () {
            String crawlUrl = this.getCrawlUrl();

            Document doc = null;
            try {
                //Jsoup fetches and parses an HTML document from the web into a new Document
                //The connect(String url) method creates a new Connection (throws an IOException)
                //get() fetches and parses an HTML file
                doc = Jsoup.connect(crawlUrl).get();
                //jum-page-content clearfix
                Elements slots = doc.select("div#slotbooking-app");
                for (Element slot : slots) {
                    System.out.println("\n\t** " +
                            slot.attr("font"));
                }
            } catch (IOException e) {
                Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, e);
            }
            System.out.println("page title where we are 'scraping': " + doc.title());
        }

        private String getCrawlUrl () {
            String searchUrl = "https://www.jumbo.com/INTERSHOP/web/WFS/Jumbo-Grocery-Site/nl_NL/-/EUR/ViewPUPSelection-ViewSlotBooking?ft=hd&HDAddressChanged=true";

            return searchUrl;
        }
}
