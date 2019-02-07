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

public class RecipeStepFragment extends Fragment {

    private RecipeStepViewModel mViewModel;
    private int recipeIndex;
    private int stepIndex;

    public static RecipeStepFragment newInstance(int recipeIndex, int stepIndex) {
        RecipeStepFragment instance = new RecipeStepFragment();
        instance.recipeIndex = recipeIndex;
        instance.stepIndex = stepIndex;

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_step_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecipeStepViewModel.class);
        // TODO: Use the ViewModel
    }

}
