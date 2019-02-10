package com.ricardorainha.cooking.widget;

import android.content.Context;
import android.content.SharedPreferences;

import com.ricardorainha.cooking.model.Recipe;

public class WidgetUtils {
    private static final String PREFS_NAME = "RecipeIngredientsWidget";
    private static final String PREF_PREFIX_RECIPE_KEY = "appwidget_recipe_";
    private static final String PREF_PREFIX_INGREDIENTS_KEY = "appwidget_ingredients_";

    static void saveIngredientsPreferences(Context context, int appWidgetId, Recipe recipe) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_RECIPE_KEY + appWidgetId, recipe.getName());
        prefs.putString(PREF_PREFIX_INGREDIENTS_KEY + appWidgetId, recipe.getIngredientsText());
        prefs.apply();
    }

    static void deleteIngredientsPreferences(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_RECIPE_KEY + appWidgetId);
        prefs.remove(PREF_PREFIX_INGREDIENTS_KEY + appWidgetId);
        prefs.apply();
    }

    private static String getPreference(Context context, String name) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String preferenceValue = prefs.getString(name, null);
        if (preferenceValue != null)
            return preferenceValue;
        else
            return "";
    }

    static String getRecipeNamePreference(Context context, int appWidgetId) {
        return getPreference(context, PREF_PREFIX_RECIPE_KEY + appWidgetId);
    }

    static String getIngredientsPreference(Context context, int appWidgetId) {
        return getPreference(context, PREF_PREFIX_INGREDIENTS_KEY + appWidgetId);
    }
}
