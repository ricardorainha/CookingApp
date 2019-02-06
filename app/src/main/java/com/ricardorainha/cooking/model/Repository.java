package com.ricardorainha.cooking.model;

import com.ricardorainha.cooking.network.RecipeRequester;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Repository extends Observable implements Observer {

    public static final byte RECIPES_SUCCESSFULLY_LOADED = 1;
    public static final byte PROBLEM_LOADING_RECIPES = 2;
    private static final Repository ourInstance = new Repository();

    public static Repository getInstance() {
        return ourInstance;
    }
    RecipeRequester requester;
    List<Recipe> recipes;

    private Repository() {
        requester = new RecipeRequester();
        requester.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof List) {
            recipes = (List<Recipe>) arg;
            setChanged();
            notifyObservers(RECIPES_SUCCESSFULLY_LOADED);
        }
        else if (arg instanceof Throwable) {
            setChanged();
            notifyObservers(PROBLEM_LOADING_RECIPES);
        }
    }

    public void requestRecipes() {
        requester.doRequest();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Recipe getRecipe(int index) {
        if (recipes != null && (index < recipes.size()))
            return recipes.get(index);
        else
            return null;
    }
}
