package com.ricardorainha.cooking.view;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricardorainha.cooking.R;

public class RecipeDetailFragment extends Fragment {

    private int recipeIndex;
    private RecipeDetailViewModel mViewModel;


    public static RecipeDetailFragment newInstance(int recipeIndex) {
        RecipeDetailFragment instance = new RecipeDetailFragment();
        instance.recipeIndex = recipeIndex;

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
        mViewModel.init(recipeIndex);

        mViewModel.getRecipe().observe(this, recipe -> {

        });
    }

}
