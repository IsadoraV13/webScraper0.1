package com.webcrawler.models;

import com.webcrawler.WebCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumerIngles {
    public void Start() {
    crawl();
    }

        private void crawl () {
            String crawlUrl = this.getCrawlUrl();

//            HashSet<String> anchors = new HashSet<String>();

            Document doc = null;
            try {
                //Jsoup fetches and parses a HTML document from the web into a new Document
                //The connect(String url) method creates a new Connection and throws an IOException
                //get() fetches and parses an HTML file
                doc = Jsoup.connect(crawlUrl).get();
                Elements newsHeadlines = doc.select("#mp-itn b a");
                for (Element headline : newsHeadlines) {
//                    String anchor = headline.attr("href");
                    System.out.println("\n\t** " +
                            headline.attr("title") + ": " + headline.absUrl("href"));

                }
            } catch (IOException e) {
                Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, e);
            }
            System.out.println(doc.title());
        }

        private String getCrawlUrl () {
            String searchUrl = "https://www.elcorteingles.es/supermercado/";

            return searchUrl;
        }
}
