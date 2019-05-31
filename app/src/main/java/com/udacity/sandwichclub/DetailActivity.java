package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {
    Sandwich sandwich;
    ImageView ingredientsIv;
    TextView alsoknownas;
    TextView ingredients_tv;
    TextView placeoforigin;


    TextView description;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        alsoknownas = findViewById(R.id.also_known_tv);
        ingredients_tv = findViewById(R.id.ingredients_tv);
        placeoforigin = findViewById(R.id.placeoforigin_tv);
        description = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);



        if (position == DEFAULT_POSITION) {

            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);

        if (sandwich == null) {

            closeOnError();
            return;
        }

        populateUI();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {


        Picasso.with(this).load(sandwich.getImage()).placeholder(R.mipmap.img).into(ingredientsIv);
        setTitle(sandwich.getMainName());
        String place = (sandwich.getPlaceOfOrigin());
        if (!place.isEmpty()) {
            placeoforigin.setText(sandwich.getPlaceOfOrigin());
        } else {
            placeoforigin.setText("NOT AVAILABLE");
        }
        String alsoknown = (TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        if (!alsoknown.isEmpty()) {
            alsoknownas.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        } else {
            alsoknownas.setText("NOT AVAILABLE");
        }

        ingredients_tv.setText(TextUtils.join(", ", sandwich.getIngredients()));
        description.setText(sandwich.getDescription());

    }
}
