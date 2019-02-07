package com.ricardorainha.cooking.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricardorainha.cooking.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesListFragment extends Fragment {

    MainViewModel viewModel;

    @BindView(R.id.rv_recipes)
    RecyclerView rvRecipes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipes_list, container, false);
        ButterKnife.bind(this, rootView);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        viewModel.init();

        setupViews();

        viewModel.getAdapter().observe(this, adapter -> rvRecipes.setAdapter(adapter));
        viewModel.getRecipeSelectedIndex().observe(this, recipeIndex -> {
            Intent recipeDetailsIntent = new Intent(getContext(), RecipeDetailActivity.class);
            recipeDetailsIntent.putExtra("recipeIndex", recipeIndex);
            startActivity(recipeDetailsIntent);
        });

        return rootView;
    }

    private void setupViews() {
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRecipes.setHasFixedSize(true);
    }

}
