package crawlAkmall.delvify.io;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReiCrawler {

    public static void main(String[] args) throws Exception {

        String filePath = "SmallRigUrlList";
        ArrayList<String> smallrigUrlList = Utilities.dataReader(filePath);
            //SmallRigUrlReader();
        for (String urllink : smallrigUrlList)
            crawler(urllink);
    }

    public static void crawler(String urllink) throws Exception {

        JSONObject obj = new JSONObject();
        Document document = Jsoup.connect(urllink)
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "tokens")
                .timeout(30000)
                .post();

        StringBuilder description = new StringBuilder();
        Elements desc_element = document.select("div.product-description-container.rte").select("p");
        for (Element header : desc_element){
            if (header.text().split(":").length == 2 ){
                obj.put(header.text().split(":")[0], header.text().split(":")[1]);
            }
            else if (header.text().split(":").length > 2){
                for (Element sub_desc : header.select("span")){
                    try {
                        obj.put(sub_desc.text().split(":")[0], sub_desc.text().split(":")[1]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else if (header.text().length() > 5){
                description.append(" ").append(header.text().trim());
            }
            obj.put("description",description.toString());
        }

        Elements item_element = document.select("div.product-info-column");
        String item_title = item_element.select("h1.product-title").text();
        obj.put("item_title",item_title);
        String item_price = item_element.select("div.product-price").select("div.product-price-line").text();
        obj.put("item_price", item_price);

        // Images url list
        JSONArray jsonArray = new JSONArray();
        List<String> imageURL = new ArrayList<String>();
        Elements item_image_url = document.select("div.product-slides-wrap.has-multiple-images").select("a");
        for (Element url : item_image_url){
            jsonArray.add(url.attr("href"));
            imageURL.add(url.attr("href"));
        }
        obj.put("imageUrl", jsonArray);

        String saveFileName = "smallrignewproduct.json";
        Utilities.writeToFile(obj, saveFileName );
    }

}
