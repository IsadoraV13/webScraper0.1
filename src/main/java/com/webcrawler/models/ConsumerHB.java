package com.webcrawler.models;

import com.webcrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerHB {
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
                //.l-wrap.product-list-flex.js-product-list-container li article form div a
                Elements products = doc.select(".page > div#ajaxLoaded");
                for (Element product : products) {
                    System.out.println("\n\t** " +
                            product.attr(".prod-title") + ", price: " + product.attr(".prod-price")
                            + ": " + product.absUrl("href"));
                }
            } catch (IOException e) {
                Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, e);
            }
            System.out.println(doc.title());
        }

        private String getCrawlUrl () {
            String baseUrl = "https://www.hollandandbarrett.nl/search?query=";
            String searchQuery = "naturya";
            String searchUrl = "";
            try {
                searchUrl = baseUrl + URLEncoder.encode(searchQuery, "UTF-8") + "&isSearch=true";
            } catch (UnsupportedEncodingException e) {

            }
            return searchUrl;
        }
}
