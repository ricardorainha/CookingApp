package com.ricardorainha.cooking.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipeIngredientsWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeIngredientsWidgetRemoteViewsFactory(getApplicationContext(), intent);
    }
}
