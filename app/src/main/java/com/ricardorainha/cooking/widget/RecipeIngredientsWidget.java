package com.ricardorainha.cooking.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.ricardorainha.cooking.R;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link RecipeIngredientsWidgetConfigureActivity RecipeIngredientsWidgetConfigureActivity}
 */
public class RecipeIngredientsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cooking_app_recipe_widget);

        String recipeName = WidgetUtils.getRecipeNamePreference(context, appWidgetId);

        Intent ingredientsIntent = new Intent(context, RecipeIngredientsWidgetService.class);
        ingredientsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        ingredientsIntent.setData(Uri.parse(ingredientsIntent.toUri(Intent.URI_INTENT_SCHEME)));

        views.setTextViewText(R.id.tv_widget_recipe_name, recipeName);
        views.setRemoteAdapter(R.id.lv_widget_ingredients, ingredientsIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            WidgetUtils.deleteIngredientsPreferences(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

