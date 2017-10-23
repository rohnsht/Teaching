package np.com.rohanshrestha.firstproject;

import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import np.com.rohanshrestha.firstproject.models.Flower;

/**
 * Created by legend on 15/10/17.
 */

public class JsonParser {

    public static ArrayList<Flower> flowerParser(String jsonString) {

        ArrayList<Flower> flowerList = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(jsonString);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Flower flower = new Flower();

                flower.setCategory(obj.getString("category"));
                flower.setName(obj.getString("name"));
                flower.setProductId(obj.getInt("productId"));
                flower.setPrice(obj.getDouble("price"));
                flower.setInstructions(obj.getString("instructions"));

                flowerList.add(flower);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return flowerList;
    }

}
