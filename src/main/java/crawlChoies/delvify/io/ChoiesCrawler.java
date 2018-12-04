package crawlChoies.delvify.io;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.lang.model.util.Elements;

public class ChoiesCrawler {

    public static void main(String[] args) throws Exception{
        ChoiesCrawler choiesCrawler = new ChoiesCrawler();
        choiesCrawler.parseHTML();
    }

    private void parseHTML() throws Exception{
        String choiseUrl = "https://www.choies.com/dresses-c-92";
        Document document = Jsoup.connect(choiseUrl)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "tokens")
                .timeout(30000)
                .post();

        System.out.println();
//        Elements elements = document.select("div.pro-list");

    }
}
