package com.ricardorainha.cooking.view;

import android.view.View;

import com.ricardorainha.cooking.model.Recipe;
import com.ricardorainha.cooking.model.Repository;
import com.ricardorainha.cooking.model.Step;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeStepViewModel extends ViewModel {

    private Recipe recipe;
    private MutableLiveData<Step> selectedStep = new MutableLiveData<>();
    private ObservableField<Integer> currentStepIndex = new ObservableField<>();

    public void init(int recipeIndex, int stepIndex) {
        recipe = Repository.getInstance().getRecipe(recipeIndex);
        currentStepIndex.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int newIndex) {
                selectedStep.setValue(recipe.getSteps().get(currentStepIndex.get()));
            }
        });
        currentStepIndex.set(stepIndex);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public MutableLiveData<Step> getSelectedStep() {
        return selectedStep;
    }

    public int getCurrentStepIndex() {
        return currentStepIndex.get();
    }

    public void onNextStepClicked(View view) {
        if (currentStepIndex.get() < (recipe.getStepsTotal()-1))
            currentStepIndex.set(currentStepIndex.get()+1);
    }

    public void onPreviousStepClicked(View view) {
        if (currentStepIndex.get() > 0)
            currentStepIndex.set(currentStepIndex.get()-1);
    }
}
