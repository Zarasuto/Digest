package com.example.finalsproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Recipe implements Parcelable {

    private String id;
    private String name;
    private String imageKey;
    private String description;
    private ArrayList<String> ingredientsList;
    private ArrayList<String> stepList;

    public Recipe() {
    }

    public Recipe(String id, String name, String imageKey, String description, ArrayList<String> ingredientsList, ArrayList<String> stepList) {
        this.id = id;
        this.name = name;
        this.imageKey = imageKey;
        this.description = description;
        this.ingredientsList = ingredientsList;
        this.stepList = stepList;
    }

    protected Recipe(Parcel in) {
        id = in.readString();
        name = in.readString();
        imageKey = in.readString();
        description = in.readString();
        ingredientsList = in.createStringArrayList();
        stepList = in.createStringArrayList();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(ArrayList<String> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public ArrayList<String> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<String> stepList) {
        this.stepList = stepList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(imageKey);
        dest.writeString(description);
        dest.writeStringList(ingredientsList);
        dest.writeStringList(stepList);
    }
}
