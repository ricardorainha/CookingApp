package com.ricardorainha.cooking.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;

import com.ricardorainha.cooking.R;

public class RecipeStepActivity extends AppCompatActivity {

    private RecipeStepViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_step_activity);
        if (savedInstanceState == null) {
            int recipeIndex = -1;
            int stepIndex = -1;
            if (getIntent().hasExtra("recipeIndex") && getIntent().hasExtra("stepIndex")) {
                recipeIndex = getIntent().getIntExtra("recipeIndex", recipeIndex);
                stepIndex = getIntent().getIntExtra("stepIndex", stepIndex);
            }
            mViewModel = ViewModelProviders.of(this).get(RecipeStepViewModel.class);
            mViewModel.init(recipeIndex, stepIndex);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_container, RecipeStepFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
