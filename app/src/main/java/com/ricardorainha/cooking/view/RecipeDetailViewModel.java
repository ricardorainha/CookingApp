package com.ricardorainha.cooking.view;

import android.view.View;

import com.ricardorainha.cooking.adapter.StepsAdapter;
import com.ricardorainha.cooking.model.Recipe;
import com.ricardorainha.cooking.model.Repository;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeDetailViewModel extends ViewModel implements StepsAdapter.OnClickListener {

    private MutableLiveData<Boolean> showIngredients = new MutableLiveData<>();
    private MutableLiveData<StepsAdapter> adapter = new MutableLiveData<>();
    private MutableLiveData<Integer> stepSelectedIndex = new MutableLiveData<>();
    private Recipe recipe;
    private int recipeIndex;

    public RecipeDetailViewModel() {
        showIngredients.setValue(false);
    }

    public void init(int recipeIndex) {
        this.recipeIndex = recipeIndex;
        recipe = Repository.getInstance().getRecipe(recipeIndex);
        adapter.setValue(new StepsAdapter(recipe.getSteps(), this));
        stepSelectedIndex.setValue(null);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getRecipeIndex() {
        return recipeIndex;
    }

    public MutableLiveData<Boolean> getShowIngredients() {
        return showIngredients;
    }

    public MutableLiveData<StepsAdapter> getAdapter() {
        return adapter;
    }

    public MutableLiveData<Integer> getStepSelectedIndex() {
        return stepSelectedIndex;
    }

    public void onIngredientsClicked(View view) {
        showIngredients.setValue(!showIngredients.getValue());
    }


    @Override
    public void onStepSelected(int index) {
        stepSelectedIndex.setValue(index);
    }
}
