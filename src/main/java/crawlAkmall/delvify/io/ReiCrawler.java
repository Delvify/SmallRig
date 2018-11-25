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

    private static void crawler(String urllink) throws Exception {

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
//                System.out.println(header.text().split(":")[0]);
//                System.out.println(header.text().split(":")[1]);
            }
            if (header.text().split(":").length > 2){
                for (Element sub_desc : header.select("span")){
                    obj.put(sub_desc.text().split(":")[0], sub_desc.text().split(":")[1]);
//                  System.out.println(sub_desc.text().split(":")[0]);
//                  System.out.println(sub_desc.text().split(":")[1]);
                }
            }
            if (header.text().split(":").length < 2){
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

        String saveFileName = "newproduct.json";
        Utilities.writeToFile(obj, saveFileName );
    }


//    private static ArrayList<String> SmallRigUrlReader() {
//        ArrayList<String> urlList = new ArrayList<String>();
//        String filePath = "SmallRigUrlList";
//        File file = new File(filePath);
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(file));
//            String line;
//            while ((line = br.readLine()) != null){
//                urlList.add(line);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return urlList;
//    }
}
