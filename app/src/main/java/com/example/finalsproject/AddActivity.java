package com.example.finalsproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {


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
    private Button add;
    private DatabaseReference reference;
    private List<Recipe> recipeList; //Main Arraylist the contains objects of the recipe


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        setupValues();//setup the xml values and other instances
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecipe();
            }
        });
    }

    private void addRecipe() {
        ArrayList<String> ingredientsList = packIngredients();
        ArrayList<String> stepList = packSteps();
        String txtName = name.getText().toString().trim();
        String txtImageKey = convertImageKeyToImageID();
        String txtDescription = description.getText().toString().trim();

        if(!TextUtils.isEmpty(txtName) && !TextUtils.isEmpty(txtDescription)){//checks if the editTexts are empty
            String id = reference.push().getKey();//generates a random id

            Recipe recipe = new Recipe(id, txtName, txtImageKey, txtDescription, ingredientsList, stepList);

            reference.child(id).setValue(recipe);//puts the recipe object into the database
            Intent intent = new Intent(this, MainMenu.class);
            intent.putExtra("recipe", recipe);
            Toast.makeText(this,"Add Item Sucessfully",Toast.LENGTH_SHORT).show();
            startActivity(intent);
            // Insert Toast Notification Here
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
    //setup xml values
    private void setupValues(){
        name = findViewById(R.id.txtName);
        imageKey = findViewById(R.id.spnrImage);
        description = findViewById(R.id.txtDescription);
        ing1 = findViewById(R.id.txtIngredient1);
        ing2 = findViewById(R.id.txtIngredient2);
        ing3 = findViewById(R.id.txtIngredient3);
        ing4 = findViewById(R.id.txtIngredient4);
        ing5 = findViewById(R.id.txtIngredient5);
        ing6 = findViewById(R.id.txtIngredient6);
        ing7 = findViewById(R.id.txtIngredient7);
        ing8 = findViewById(R.id.txtIngredient8);
        ing9 = findViewById(R.id.txtIngredient9);
        ing10 = findViewById(R.id.txtIngredient10);
        ing11 = findViewById(R.id.txtIngredient11);
        ing12 = findViewById(R.id.txtIngredient12);
        ing13 = findViewById(R.id.txtIngredient13);
        step1 = findViewById(R.id.txtStep1);
        step2 = findViewById(R.id.txtStep2);
        step3 = findViewById(R.id.txtStep3);
        step4 = findViewById(R.id.txtStep4);
        step5 = findViewById(R.id.txtStep5);
        step6 = findViewById(R.id.txtStep6);
        step7 = findViewById(R.id.txtStep7);
        step8 = findViewById(R.id.txtStep8);
        step9 = findViewById(R.id.txtStep9);
        step10 = findViewById(R.id.txtStep10);
        step11 = findViewById(R.id.txtStep11);
        step12 = findViewById(R.id.txtStep12);
        step13 = findViewById(R.id.txtStep13);
        add = findViewById(R.id.btnAdd);
        reference = FirebaseDatabase.getInstance().getReference("Recipes");//Will create a branch named recipes
        recipeList = new ArrayList<>();
    }

    //method that checks if the edit text is empty and puts all populated ones into a new arraylist
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
    //same thing as above
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




}