package com.ricardorainha.cooking.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricardorainha.cooking.R;
import com.ricardorainha.cooking.databinding.RecipeDetailFragmentBinding;

public class RecipeDetailFragment extends Fragment {

    private int recipeIndex;
    private RecipeDetailViewModel mViewModel;
    @BindView(R.id.rv_steps)
    RecyclerView rvSteps;

    public static RecipeDetailFragment newInstance(int recipeIndex) {
        RecipeDetailFragment instance = new RecipeDetailFragment();
        instance.recipeIndex = recipeIndex;

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecipeDetailFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.recipe_detail_fragment, container, false);
        binding.setLifecycleOwner(this);
        View rootView = binding.getRoot();
        ButterKnife.bind(this, rootView);

        mViewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
        mViewModel.init(recipeIndex);
        binding.setViewModel(mViewModel);

        setupViews();
        configureObservables();

        return rootView;
    }

    private void setupViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mViewModel.getRecipe().getName());
        rvSteps.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSteps.setHasFixedSize(true);
    }

    private void configureObservables() {
        mViewModel.getAdapter().observe(this, stepsAdapter -> {
            rvSteps.setAdapter(stepsAdapter);
        });
        mViewModel.getStepSelectedIndex().observe(this, stepIndex -> {
            Intent stepSelectedIntent = new Intent(getContext(), RecipeStepActivity.class);
            stepSelectedIntent.putExtra("recipeIndex", recipeIndex);
            stepSelectedIntent.putExtra("stepIndex", stepIndex);
            startActivity(stepSelectedIntent);
        });
    }
}
