package crawlAkmall.delvify.io;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AllScraper {

    public static void main(String[] args) throws Exception{

        for (int i=1; i<=7; i++)
        {
            String mainUrl = "https://www.smallrig.com/cages/?sort=featured&page=" + i;
            JSONObject obj = new JSONObject();
            Document document = Jsoup.connect(mainUrl)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "tokens")
                    .timeout(30000)
                    .post();


            Elements elements = document.select("div.collection-product-index-column").select("article").select("div.product-item-details").select("a");
            for (Element url : elements){
                ReiCrawler.crawler(url.attr("href").toString());
                System.out.println(url.attr("href").toString());
            }
        }
    }
}
