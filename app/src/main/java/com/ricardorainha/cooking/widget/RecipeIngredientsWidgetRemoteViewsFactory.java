package com.ricardorainha.cooking.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeIngredientsWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private List<String> ingredientsList = new ArrayList<>();
    private int appWidgetId;


    public RecipeIngredientsWidgetRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        this.appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        this.ingredientsList.clear();
    }

    @Override
    public void onCreate() {
        updateWidgetIngredients();
    }

    @Override
    public void onDataSetChanged() {
        updateWidgetIngredients();
    }

    @Override
    public void onDestroy() {
        this.ingredientsList.clear();
    }

    @Override
    public int getCount() {
        return (ingredientsList != null) ? ingredientsList.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews itemView = new RemoteViews(context.getPackageName(), android.R.layout.simple_list_item_1);
        itemView.setTextViewText(android.R.id.text1, ingredientsList.get(position));

        return itemView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private void updateWidgetIngredients() {
        if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            String ingredientsText = WidgetUtils.getIngredientsPreference(context, appWidgetId);
            if (ingredientsText != null) {
                String[] ingredients = TextUtils.split(ingredientsText, "\n");
                ingredientsList = Arrays.asList(ingredients);
            }
        }
    }
}
