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
            if (!jsonObject.get("item_title").equals(" ")){
                if (jsonObject.get("description").equals(" ")){
                    new_jsonObject = jsonObject;
                    new_jsonObject.put("description", "");
                    new_jsonObject.put("description", jsonObject.get("item_title"));
                }
                if ((jsonObject.get("Key Features") != null) && (jsonObject.get("Key Features").equals(" "))){
                    new_jsonObject = jsonObject;
                    new_jsonObject.put("Key Features", "");
                    new_jsonObject.put("Key Features", jsonObject.get("item_title"));
                }
                else {
                    new_jsonObject = jsonObject;
                }
                System.out.println();
            }
            String newFileName = "new_smallRigProduct.json";
            Utilities.writeToFile(new_jsonObject, newFileName);
        }
    }
}
