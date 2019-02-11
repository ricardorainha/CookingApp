package com.ricardorainha.cooking.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ricardorainha.cooking.R;
import com.ricardorainha.cooking.databinding.RecipesListFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecipesListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.recipes_list_fragment, container, false);
        binding.setLifecycleOwner(this);
        View rootView = binding.getRoot();
        ButterKnife.bind(this, rootView);

        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        binding.setViewModel(viewModel);

        setupViews();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getRecipeSelectedIndex().setValue(null);
        configureObservables();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopObserving();
    }

    private void setupViews() {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        else {
            rvRecipes.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        rvRecipes.setHasFixedSize(true);
    }

    private void configureObservables() {
        viewModel.getAdapter().observe(this, adapter -> rvRecipes.setAdapter(adapter));
        viewModel.getRecipeSelectedIndex().observe(this, recipeIndex -> {
            if (recipeIndex != null) {
                Intent recipeDetailsIntent = new Intent(getContext(), RecipeDetailActivity.class);
                recipeDetailsIntent.putExtra("recipeIndex", recipeIndex);
                startActivity(recipeDetailsIntent);
            }
        });
    }

    private void stopObserving() {
        viewModel.getAdapter().removeObservers(this);
        viewModel.getRecipeSelectedIndex().removeObservers(this);
    }


}
