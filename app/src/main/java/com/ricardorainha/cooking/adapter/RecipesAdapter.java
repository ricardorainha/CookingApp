package com.ricardorainha.cooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ricardorainha.cooking.R;
import com.ricardorainha.cooking.model.Recipe;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Recipe> recipes;
    OnClickListener listener;

    public RecipesAdapter(List<Recipe> recipes, OnClickListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_list_item, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecipeViewHolder viewHolder = (RecipeViewHolder) holder;
        Recipe recipe = recipes.get(position);

        viewHolder.tvRecipeName.setText(recipe.getName());
        viewHolder.tvRecipeServings.setText(holder.itemView.getContext().getString(R.string.serves, recipe.getServings()));
    }

    @Override
    public int getItemCount() {
        if (recipes != null)
            return recipes.size();
        else
            return 0;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recipe_name)
        TextView tvRecipeName;
        @BindView(R.id.tv_recipe_servings)
        TextView tvRecipeServings;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onRecipeSelected(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onRecipeSelected(int position);
    }
}
