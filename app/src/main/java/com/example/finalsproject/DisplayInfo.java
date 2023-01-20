package com.example.finalsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.jar.Attributes;

public class DisplayInfo extends AppCompatActivity {

    private TextView name;
    private TextView description;
    private TextView ingredients_list;
    private TextView steps_list;
    private String ingredients="";
    private String steps="";
    //placeholder
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);
        recipe=getIntent().getParcelableExtra("recipees"); // lahat ng info nandito na
        setupUI();
        setValues();
    }
    private void setupUI(){
        name = findViewById(R.id.display_name_view);
        description = findViewById(R.id.display_description_view);
        ingredients_list = findViewById(R.id.display_ingredients_view);
        steps_list = findViewById(R.id.display_steps_view);
    }
    private void setValues(){
        int i=1;
        name.setText(recipe.getName());
        try{
            description.setText(recipe.getDescription());
        }catch(NullPointerException ex){}
        try{
            for(String ingredient:recipe.getIngredientsList()){
                ingredients+=ingredient+"\n";
            }
            ingredients_list.setText(ingredients);
        }catch(NullPointerException ex){ }
        try{
            for(String step:recipe.getStepList()){
                steps+="Step "+i+": "+step+"\n";
                i++;
            }
            steps_list.setText(steps);
        }catch(NullPointerException ex){ }

    }
}