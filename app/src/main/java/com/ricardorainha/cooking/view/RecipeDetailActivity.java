package com.ricardorainha.cooking.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.ricardorainha.cooking.R;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecipeDetailViewModel mViewModel;
    private RecipeStepViewModel mStepsViewModel;
    @Nullable
    @BindView(R.id.ll_details_steps)
    LinearLayout llDetailsSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail_activity);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            int recipeIndex = -1;
            if (getIntent().hasExtra("recipeIndex")) {
                recipeIndex = getIntent().getIntExtra("recipeIndex", recipeIndex);
            }
            mViewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
            mViewModel.init(recipeIndex);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, RecipeDetailFragment.newInstance())
                    .commitNow();

            if (llDetailsSteps != null) {
                mViewModel.setHasTwoPane(true);
                mStepsViewModel = ViewModelProviders.of(this).get(RecipeStepViewModel.class);
                mStepsViewModel.init(recipeIndex, -1);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.step_container, RecipeStepFragment.newInstance())
                        .commitNow();
            }
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
