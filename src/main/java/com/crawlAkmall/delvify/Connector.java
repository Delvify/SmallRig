package com.crawlAkmall.delvify;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Connector {

    public static void main(String[] args) throws IOException {

        String data = "https://www.choies.com/clothing-c-615"; //http://www.akmall.com/bestseller/BestShopCtg.do?ctgId=42";
//        Document doc = Jsoup.parse(data);
        Document doc = Jsoup.connect(data).get();
        System.out.println(doc.title());
        System.out.println(doc.body().text());

        Element elements = doc.getElementsByClass("container").select("div.pro-list").first(); // ("wrap").getElementsByClass("container").first().
//                select("div.content.bestshop").first();
        System.out.println(elements);

    }

}
