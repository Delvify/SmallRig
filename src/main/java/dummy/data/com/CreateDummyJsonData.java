package dummy.data.com;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class CreateDummyJsonData {
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.put("ItemCode", "ITEM0001");
        obj.put("Name","Olay");
        obj.put("SubCategory", "foundation");
        obj.put("Title","Olay night mask");
        obj.put("Description","Apply after shower evey alternate day." +
                "keep in cool and dry place. keep away from sunlight" +
                "and moisture");
        obj.put("ImageURL","olay.com/image/mask001");

        try {
            FileWriter fileWriter = new FileWriter("product.json", true);
            fileWriter.write(obj.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
