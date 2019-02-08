package com.ricardorainha.cooking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ricardorainha.cooking.R;

public class RecipeStepActivity extends AppCompatActivity {

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
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecipeStepFragment.newInstance(recipeIndex, stepIndex))
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
