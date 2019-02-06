package com.ricardorainha.cooking.view;

import com.ricardorainha.cooking.adapter.RecipesAdapter;
import com.ricardorainha.cooking.model.Recipe;
import com.ricardorainha.cooking.model.Repository;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel implements Observer, RecipesAdapter.OnClickListener {

    private MutableLiveData<List<Recipe>> recipes = new MutableLiveData<>();
    private MutableLiveData<RecipesAdapter> adapter = new MutableLiveData<>();
    private MutableLiveData<Integer> recipeSelectedIndex = new MutableLiveData<>();
    private Repository repo;

    public void init() {
        repo = Repository.getInstance();
        repo.addObserver(this);
        loadRecipes();
    }

    private void loadRecipes() {
        repo.requestRecipes();
    }

    public MutableLiveData<RecipesAdapter> getAdapter() {
        return adapter;
    }

    public MutableLiveData<Integer> getRecipeSelectedIndex() {
        return recipeSelectedIndex;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Repository) {
            byte response = (byte) arg;
            if (response == Repository.RECIPES_SUCCESSFULLY_LOADED) {
                if (repo != null) {
                    recipes.setValue(repo.getRecipes());
                    adapter.setValue(new RecipesAdapter(recipes.getValue(), this));
                }
            }
        }
    }

    @Override
    public void onRecipeSelected(int position) {
        recipeSelectedIndex.setValue(position);
    }
}
