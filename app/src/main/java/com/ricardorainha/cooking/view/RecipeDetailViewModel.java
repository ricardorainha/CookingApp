package com.ricardorainha.cooking.view;

import com.ricardorainha.cooking.model.Recipe;
import com.ricardorainha.cooking.model.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeDetailViewModel extends ViewModel {

    private MutableLiveData<Recipe> recipe = new MutableLiveData<>();

    public void init(int recipeIndex) {
        recipe.setValue(Repository.getInstance().getRecipe(recipeIndex));
    }

    public MutableLiveData<Recipe> getRecipe() {
        return recipe;
    }
}
