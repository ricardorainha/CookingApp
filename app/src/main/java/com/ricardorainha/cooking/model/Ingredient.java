package com.ricardorainha.cooking.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient implements Parcelable
{

    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("measure")
    @Expose
    private String measure;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    public final static Parcelable.Creator<Ingredient> CREATOR = new Creator<Ingredient>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        public Ingredient[] newArray(int size) {
            return (new Ingredient[size]);
        }

    }
            ;

    protected Ingredient(Parcel in) {
        this.quantity = ((int) in.readValue((int.class.getClassLoader())));
        this.measure = ((String) in.readValue((String.class.getClassLoader())));
        this.ingredient = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Ingredient() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Ingredient withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Ingredient withMeasure(String measure) {
        this.measure = measure;
        return this;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient withIngredient(String ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(quantity);
        dest.writeValue(measure);
        dest.writeValue(ingredient);
    }

    public int describeContents() {
        return 0;
    }

}