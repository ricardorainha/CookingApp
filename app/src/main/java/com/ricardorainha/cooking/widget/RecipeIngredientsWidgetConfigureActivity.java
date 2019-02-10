package com.ricardorainha.cooking.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ricardorainha.cooking.R;
import com.ricardorainha.cooking.adapter.RecipesAdapter;
import com.ricardorainha.cooking.model.Recipe;
import com.ricardorainha.cooking.model.Repository;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The configuration screen for the {@link RecipeIngredientsWidget RecipeIngredientsWidget} AppWidget.
 */
public class RecipeIngredientsWidgetConfigureActivity extends Activity {

    @BindView(R.id.rv_widget_recipes)
    RecyclerView rvWidgetRecipes;
    RecipesAdapter adapter;
    List<Recipe> recipes;

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    RecipesAdapter.OnClickListener adapterClickListener = new RecipesAdapter.OnClickListener() {
        @Override
        public void onRecipeSelected(int position) {
            final Context context = RecipeIngredientsWidgetConfigureActivity.this;

            WidgetUtils.saveIngredientsPreferences(context, mAppWidgetId, recipes.get(position));

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RecipeIngredientsWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    public RecipeIngredientsWidgetConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setResult(RESULT_CANCELED);

        setContentView(R.layout.cooking_app_recipe_widget_configure);
        ButterKnife.bind(this);

        setupViews();
        loadRecipes();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
    }

    private void setupViews() {
        rvWidgetRecipes.setLayoutManager(new LinearLayoutManager(this));
        rvWidgetRecipes.setHasFixedSize(true);
    }

    private void loadRecipes() {
        Repository repo = Repository.getInstance();
        repo.addObserver((o, arg) -> {
            if (o instanceof Repository) {
                byte response = (byte) arg;
                if (response == Repository.RECIPES_SUCCESSFULLY_LOADED) {
                    if (repo != null) {
                        recipes = repo.getRecipes();
                        adapter = new RecipesAdapter(recipes, adapterClickListener);
                        rvWidgetRecipes.setAdapter(adapter);
                    }
                }
            }
        });
        repo.requestRecipes();
    }
}

