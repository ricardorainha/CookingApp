package com.ricardorainha.cooking.view;

import android.view.View;

import com.ricardorainha.cooking.model.Recipe;
import com.ricardorainha.cooking.model.Repository;
import com.ricardorainha.cooking.model.Step;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeStepViewModel extends ViewModel {

    private Recipe recipe;
    private MutableLiveData<Step> selectedStep = new MutableLiveData<>();
    private MutableLiveData<Integer> currentStepIndex = new MutableLiveData<>();
    private MutableLiveData<Boolean> shouldShowLayout = new MutableLiveData<>();
    private ObservableField<Long> currentVideoPosition = new ObservableField<>();

    public RecipeStepViewModel() {
        resetVideoPosition();
    }

    public void init(int recipeIndex, int stepIndex) {
        recipe = Repository.getInstance().getRecipe(recipeIndex);
        currentStepIndex.observeForever(newIndex -> {
            selectedStep.setValue(recipe.getSteps().get(newIndex));
            shouldShowLayout.setValue((currentStepIndex.getValue() != null) && (currentStepIndex.getValue() != -1));
        });
        if (currentStepIndex.getValue() == null && stepIndex != -1)
            currentStepIndex.setValue(stepIndex);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public MutableLiveData<Step> getSelectedStep() {
        return selectedStep;
    }

    public MutableLiveData<Integer> getCurrentStepIndex() {
        return currentStepIndex;
    }

    public ObservableField<Long> getCurrentVideoPosition() {
        return currentVideoPosition;
    }

    public MutableLiveData<Boolean> getShouldShowLayout() {
        return shouldShowLayout;
    }

    public void onNextStepClicked(View view) {
        if (currentStepIndex.getValue() < (recipe.getStepsTotal()-1)) {
            resetVideoPosition();
            currentStepIndex.setValue(currentStepIndex.getValue() + 1);
        }
    }

    public void onPreviousStepClicked(View view) {
        if (currentStepIndex.getValue() > 0) {
            resetVideoPosition();
            currentStepIndex.setValue(currentStepIndex.getValue() - 1);
        }
    }

    private void resetVideoPosition() {
        currentVideoPosition.set(0L);
    }
}
