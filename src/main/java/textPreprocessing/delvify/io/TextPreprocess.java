package textPreprocessing.delvify.io;

import crawlAkmall.delvify.io.Utilities;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class TextPreprocess {

    public static void main(String[] args)  throws Exception{
        String filePath = "C:\\Users\\jugs\\IdeaProjects\\akmallCrawler\\product.json";
        ArrayList<String> jsonData = Utilities.dataReader(filePath);

        TextPreprocess textPreprocess = new TextPreprocess();
        textPreprocess.preprocess(jsonData);

    }

    private void preprocess(ArrayList<String> jsonData) throws Exception{

        for (String data : jsonData){

            JSONObject new_jsonObject = new JSONObject();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(data);

            parseDescription(new_jsonObject, jsonObject);
            parseItemTitle(new_jsonObject, jsonObject);
            parseKeyFeatures(new_jsonObject, jsonObject);
            parseNetWeight(new_jsonObject, jsonObject);

            new_jsonObject.put("Designer", jsonObject.get("Designer").toString().trim());
            new_jsonObject.put("imageUrl", jsonObject.get("imageUrl").toString().trim());
            new_jsonObject.put("Compatibility", jsonObject.get("Compatibility").toString().trim());
            new_jsonObject.put("Material(s)", jsonObject.get("Material(s)").toString().trim());
            new_jsonObject.put("item_price", jsonObject.get("item_price").toString().trim());
            new_jsonObject.put("Product Dimensions", jsonObject.get("Product Dimensions").toString().trim());
            new_jsonObject.put("Package Weight", jsonObject.get("Package Weight").toString().trim());
            new_jsonObject.put("Package Includes", jsonObject.get("Package Includes").toString().trim());



            String newFileName = "new_smallRigProduct.json";
            Utilities.writeToFile(new_jsonObject, newFileName);
        }
    }

    private void parseNetWeight(JSONObject new_jsonObject, JSONObject jsonObject) {
        if (jsonObject.get("Net Weight") != (null))
            new_jsonObject.put("Net Weight", jsonObject.get("Net Weight"));
        else
            new_jsonObject.put("Net Weight", jsonObject.get("Package Weight"));
    }

    private void parseKeyFeatures(JSONObject new_jsonObject, JSONObject jsonObject) {
        try {
            if (jsonObject.get("Key Features") != null){
                String new_keyFeatures = jsonObject.get("Key Features").toString().replace("[^a-zA-Z0-9]", "");
                new_jsonObject.put("Key Features",new_keyFeatures);
            }
            else if (jsonObject.get("Key Features") == null){
                new_jsonObject.put("Key Features", jsonObject.get("item_title").toString().trim());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseItemTitle(JSONObject new_jsonObject, JSONObject jsonObject) {
        if (jsonObject.get("item_title").toString().length() >= 5){
            String new_title = jsonObject.get("item_title").toString().replace("[^a-zA-Z0-9]", "");
            new_jsonObject.put("item_title", new_title);
        }
        else if (jsonObject.get("item_title").toString().length() <= 5){
            new_jsonObject.put("item_title", "Small Rigs");     //to be change with category name
        }
    }

    private void parseDescription(JSONObject new_jsonObject, JSONObject jsonObject) {
        if (jsonObject.get("description").toString().length() > 5 ){
            String new_desc = jsonObject.get("description").toString().replace("[^a-zA-Z0-9]", "");
            new_jsonObject.put("description", new_desc);
        }
        else if (jsonObject.get("description").toString().length() < 5){
            new_jsonObject.put("description", jsonObject.get("item_title").toString().trim());
        }
    }

    private boolean isCompleteData(JSONObject jsonData) {
        if (jsonData.get("Designer").toString().length() > 5){
            if (jsonData.get("description").toString().length() > 10){
                if (jsonData.get("item_title").toString().length() > 10){
                    if (jsonData.get("item_price").toString().length() > 5){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
