package crawlAkmall.delvify.io;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Utilities {

    public static ArrayList<String> dataReader(String filePath) {
        ArrayList<String> dataList = new ArrayList<String>();

        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                dataList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    public static void writeToFile(JSONObject obj, String saveFileName) throws Exception {
        FileWriter fileWriter = new FileWriter(saveFileName, true);
        fileWriter.write(obj.toJSONString() + "\n");
        fileWriter.close();
    }
}
