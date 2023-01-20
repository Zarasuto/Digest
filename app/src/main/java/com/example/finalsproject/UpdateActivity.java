package com.example.finalsproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {

    private static Recipe recipe;
    private String id;
    private EditText name;
    private Spinner imageKey;
    private EditText description;
    private EditText ing1;
    private EditText ing2;
    private EditText ing3;
    private EditText ing4;
    private EditText ing5;
    private EditText ing6;
    private EditText ing7;
    private EditText ing8;
    private EditText ing9;
    private EditText ing10;
    private EditText ing11;
    private EditText ing12;
    private EditText ing13;
    private EditText step1;
    private EditText step2;
    private EditText step3;
    private EditText step4;
    private EditText step5;
    private EditText step6;
    private EditText step7;
    private EditText step8;
    private EditText step9;
    private EditText step10;
    private EditText step11;
    private EditText step12;
    private EditText step13;
    private Button update;
    private DatabaseReference updateReference;// new reference points to the specific node at the database (see setupValues())

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setupValues();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRecipe();
            }
        });
    }

    private void setupValues() {
        recipe = getIntent().getParcelableExtra("recipeId");//gets the put extra
        id = recipe.getId();
        updateReference = FirebaseDatabase.getInstance().getReference("Recipes").child(id);// now points to the specific recipe to be updated
        name = findViewById(R.id.txtNameU);
        imageKey = findViewById(R.id.spnrImageU);
        description = findViewById(R.id.txtDescriptionU);
        ing1 = findViewById(R.id.txtIngredient1U);
        ing2 = findViewById(R.id.txtIngredient2U);
        ing3 = findViewById(R.id.txtIngredient3U);
        ing4 = findViewById(R.id.txtIngredient4U);
        ing5 = findViewById(R.id.txtIngredient5U);
        ing6 = findViewById(R.id.txtIngredient6U);
        ing7 = findViewById(R.id.txtIngredient7U);
        ing8 = findViewById(R.id.txtIngredient8U);
        ing9 = findViewById(R.id.txtIngredient9U);
        ing10 = findViewById(R.id.txtIngredient10U);
        ing11 = findViewById(R.id.txtIngredient11U);
        ing12 = findViewById(R.id.txtIngredient12U);
        ing13 = findViewById(R.id.txtIngredient13U);
        step1 = findViewById(R.id.txtStep1U);
        step2 = findViewById(R.id.txtStep2U);
        step3 = findViewById(R.id.txtStep3U);
        step4 = findViewById(R.id.txtStep4U);
        step5 = findViewById(R.id.txtStep5U);
        step6 = findViewById(R.id.txtStep6U);
        step7 = findViewById(R.id.txtStep7U);
        step8 = findViewById(R.id.txtStep8U);
        step9 = findViewById(R.id.txtStep9U);
        step10 = findViewById(R.id.txtStep10U);
        step11 = findViewById(R.id.txtStep11U);
        step12 = findViewById(R.id.txtStep12U);
        step13 = findViewById(R.id.txtStep13U);
        update = findViewById(R.id.btnUpdate);

        System.out.println(recipe.getId());
        name.setText(recipe.getName());
        try{description.setText(recipe.getDescription());}catch(Exception ex){};
        try{ing1.setText((recipe.getIngredientsList().get(0)));}catch(Exception ex){};
        try{ing2.setText((recipe.getIngredientsList().get(1)));}catch(Exception ex){};
        try{ing3.setText((recipe.getIngredientsList().get(2)));}catch(Exception ex){};
        try{ing4.setText((recipe.getIngredientsList().get(3)));}catch(Exception ex){};
        try{ing5.setText((recipe.getIngredientsList().get(4)));}catch(Exception ex){};
        try{ing6.setText((recipe.getIngredientsList().get(5)));}catch(Exception ex){};
        try{ing7.setText((recipe.getIngredientsList().get(6)));}catch(Exception ex){};
        try{ing8.setText((recipe.getIngredientsList().get(7)));}catch(Exception ex){};
        try{ing9.setText((recipe.getIngredientsList().get(8)));}catch(Exception ex){};
        try{ing10.setText((recipe.getIngredientsList().get(9)));}catch(Exception ex){};
        try{ing11.setText((recipe.getIngredientsList().get(10)));}catch(Exception ex){};
        try{ing12.setText((recipe.getIngredientsList().get(11)));}catch(Exception ex){};
        try{ing13.setText((recipe.getIngredientsList().get(12)));}catch(Exception ex){};
        try{step1.setText((recipe.getStepList().get(0)));}catch(Exception ex){};
        try{step2.setText((recipe.getStepList().get(1)));}catch(Exception ex){};
        try{step3.setText((recipe.getStepList().get(2)));}catch(Exception ex){};
        try{step4.setText((recipe.getStepList().get(3)));}catch(Exception ex){};
        try{step5.setText((recipe.getStepList().get(4)));}catch(Exception ex){};
        try{step6.setText((recipe.getStepList().get(5)));}catch(Exception ex){};
        try{step7.setText((recipe.getStepList().get(6)));}catch(Exception ex){};
        try{step8.setText((recipe.getStepList().get(7)));}catch(Exception ex){};
        try{step9.setText((recipe.getStepList().get(8)));}catch(Exception ex){};
        try{step10.setText((recipe.getStepList().get(9)));}catch(Exception ex){};
        try{step11.setText((recipe.getStepList().get(10)));}catch(Exception ex){};
        try{step12.setText((recipe.getStepList().get(11)));}catch(Exception ex){};
        try{step13.setText((recipe.getStepList().get(12)));}catch(Exception ex){};
    }

    private void updateRecipe(){

        //almost the same process
        ArrayList<String> ingredientsList = packIngredients();
        ArrayList<String> stepList = packSteps();
        String txtName = name.getText().toString().trim();
        String txtImageKey = convertImageKeyToImageID();
        String txtDescription = description.getText().toString().trim();
        if(!TextUtils.isEmpty(txtName) && !TextUtils.isEmpty(txtDescription)){
            Recipe recipe = new Recipe(id, txtName, txtImageKey, txtDescription, ingredientsList, stepList);
            updateRecipe(id, txtName, txtImageKey, txtDescription, ingredientsList, stepList);
            updateReference.child("description").setValue(recipe.getDescription());
            updateReference.child("id").setValue(recipe.getId());
            updateReference.child("imageKey").setValue(recipe.getImageKey());
            updateReference.child("name").setValue(recipe.getName());
            updateReference.child("ingredientsList").setValue(recipe.getIngredientsList());
            updateReference.child("stepList").setValue(recipe.getStepList());
            //updates the recipe (since the we initialized the reference while already pointing to the
            //branch, no .child() is needed
            // Insert Toast Notification Here
            Toast.makeText(this,"Updated Item Sucessfully",Toast.LENGTH_SHORT).show();
            goToMainMenu();
        }else{
            Toast.makeText(this, "Please Fill in missing name and description fields", Toast.LENGTH_SHORT).show();
        }

    }
    private String convertImageKeyToImageID(){
        switch(imageKey.getSelectedItem().toString()){
            case "Main Course":
                return Integer.toString(R.drawable.main_course);
            case "Appetizer":
                return Integer.toString(R.drawable.appetizer);
            case "Dessert":
                return Integer.toString(R.drawable.dessert);
        }
        return "";
    }
    private void updateRecipe(String id, String txtName, String txtImageKey, String txtDescription, ArrayList<String> ingredientsList, ArrayList<String> stepList){
        recipe.setId(id);
        recipe.setName(txtName);
        recipe.setImageKey(txtImageKey);
        recipe.setDescription(txtDescription);
        recipe.setIngredientsList(ingredientsList);
        recipe.setStepList(stepList);
    }
    private ArrayList<String> packIngredients(){
        ArrayList<String> arr_ingredients = new ArrayList<>();
        ArrayList<String> arr_ingredientsTemp = new ArrayList<>();

        String txtIng1 = ing1.getText().toString().trim();
        String txtIng2 = ing2.getText().toString().trim();
        String txtIng3 = ing3.getText().toString().trim();
        String txtIng4 = ing4.getText().toString().trim();
        String txtIng5 = ing5.getText().toString().trim();
        String txtIng6 = ing6.getText().toString().trim();
        String txtIng7 = ing7.getText().toString().trim();
        String txtIng8 = ing8.getText().toString().trim();
        String txtIng9 = ing9.getText().toString().trim();
        String txtIng10 = ing10.getText().toString().trim();
        String txtIng11 = ing11.getText().toString().trim();
        String txtIng12 = ing12.getText().toString().trim();
        String txtIng13 = ing13.getText().toString().trim();

        arr_ingredientsTemp.add(txtIng1);
        arr_ingredientsTemp.add(txtIng2);
        arr_ingredientsTemp.add(txtIng3);
        arr_ingredientsTemp.add(txtIng4);
        arr_ingredientsTemp.add(txtIng5);
        arr_ingredientsTemp.add(txtIng6);
        arr_ingredientsTemp.add(txtIng7);
        arr_ingredientsTemp.add(txtIng8);
        arr_ingredientsTemp.add(txtIng9);
        arr_ingredientsTemp.add(txtIng10);
        arr_ingredientsTemp.add(txtIng11);
        arr_ingredientsTemp.add(txtIng12);
        arr_ingredientsTemp.add(txtIng13);

        for(String ing : arr_ingredientsTemp){
            if(!TextUtils.isEmpty(ing)){
                arr_ingredients.add(ing);
            }
        }

        return arr_ingredients;

    }

    private ArrayList<String> packSteps() {
        ArrayList<String> arr_steps = new ArrayList<>();
        ArrayList<String> arr_stepsTemp = new ArrayList<>();

        String txtStep1 = step1.getText().toString().trim();
        String txtStep2 = step2.getText().toString().trim();
        String txtStep3 = step3.getText().toString().trim();
        String txtStep4 = step4.getText().toString().trim();
        String txtStep5 = step5.getText().toString().trim();
        String txtStep6 = step6.getText().toString().trim();
        String txtStep7 = step7.getText().toString().trim();
        String txtStep8 = step8.getText().toString().trim();
        String txtStep9 = step9.getText().toString().trim();
        String txtStep10 = step10.getText().toString().trim();
        String txtStep11 = step11.getText().toString().trim();
        String txtStep12 = step12.getText().toString().trim();
        String txtStep13 = step13.getText().toString().trim();

        arr_stepsTemp.add(txtStep1);
        arr_stepsTemp.add(txtStep2);
        arr_stepsTemp.add(txtStep3);
        arr_stepsTemp.add(txtStep4);
        arr_stepsTemp.add(txtStep5);
        arr_stepsTemp.add(txtStep6);
        arr_stepsTemp.add(txtStep7);
        arr_stepsTemp.add(txtStep8);
        arr_stepsTemp.add(txtStep9);
        arr_stepsTemp.add(txtStep10);
        arr_stepsTemp.add(txtStep11);
        arr_stepsTemp.add(txtStep12);
        arr_stepsTemp.add(txtStep13);

        for(String step : arr_stepsTemp){
            if(!TextUtils.isEmpty(step)){
                arr_steps.add(step);
            }
        }

        return arr_steps;
    }
    private void goToMainMenu(){
        Intent i= new Intent(UpdateActivity.this,MainMenu.class);
        startActivity(i);
    }
}