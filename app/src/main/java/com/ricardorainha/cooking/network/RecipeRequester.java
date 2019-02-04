package com.ricardorainha.cooking.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ricardorainha.cooking.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RecipeRequester extends Observable {

    private static final String API_BASE_URL = "https://go.udacity.com/";
    private static final String ENDPOINT_RECIPES = "android-baking-app-json";
    private static API recipesApi;
    private static List<Recipe> recipes = new ArrayList<>();

    interface API {
        @GET(ENDPOINT_RECIPES)
        Call<List<Recipe>> retrieveRecipes();
    }

    public RecipeRequester() {
        recipesApi = getAPI();
    }

    private static API getAPI() {
        if (recipesApi == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            recipesApi = retrofit.create(API.class);
        }

        return recipesApi;
    }

    public void doRequest() {
        if (recipesApi != null) {
            if (recipes.isEmpty()) {
                Call<List<Recipe>> call = recipesApi.retrieveRecipes();
                call.enqueue(new Callback<List<Recipe>>() {
                    @Override
                    public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                        if (response.isSuccessful()) {
                            recipes = response.body();
                            notifyRecipes();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Recipe>> call, Throwable t) {
                        t.printStackTrace();
                        setChanged();
                        notifyObservers(t);
                    }
                });
            }
            else {
                notifyRecipes();
            }
        }
    }

    private void notifyRecipes() {
        setChanged();
        notifyObservers(recipes);
    }


}
