package com.ricardorainha.cooking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ricardorainha.cooking.R;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_activity);
        if (savedInstanceState == null) {
            int recipeIndex = -1;
            if (getIntent().hasExtra("recipeIndex")) {
                recipeIndex = getIntent().getIntExtra("recipeIndex", recipeIndex);
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, RecipeDetailFragment.newInstance(recipeIndex))
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
