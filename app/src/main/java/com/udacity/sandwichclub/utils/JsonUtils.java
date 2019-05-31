package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich msandwich = new Sandwich();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject name = root.getJSONObject("name");
            String mainname = name.getString("mainName");
            msandwich.setMainName(mainname);


            JSONArray jsonArray = name.getJSONArray("alsoKnownAs");
            List<String> malsoKnownas = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                malsoKnownas.add(jsonArray.getString(i));
                }
            msandwich.setAlsoKnownAs(malsoKnownas);
            String poo = root.getString("placeOfOrigin");
            msandwich.setPlaceOfOrigin(poo);
            String desc = root.getString("description");
            msandwich.setDescription(desc);
            String img = root.getString("image");
            msandwich.setImage(img);
            JSONArray ingrediants = root.getJSONArray("ingredients");
            List<String> mingredientslist = new ArrayList<>();
            for (int i = 0; i < ingrediants.length(); i++) {
                mingredientslist.add(ingrediants.getString(i));
            }
            msandwich.setIngredients(mingredientslist);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return msandwich;
    }
}
