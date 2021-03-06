package com.ricardorainha.cooking.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.ricardorainha.cooking.R;
import com.ricardorainha.cooking.databinding.RecipeStepFragmentBinding;

public class RecipeStepFragment extends Fragment {

    private RecipeStepViewModel mViewModel;
    private DataSource.Factory dataSourceFactory;
    private SimpleExoPlayer player;

    @BindView(R.id.tv_recipe_step_description)
    TextView tvRecipeStepDescription;
    @BindView(R.id.pv_recipe_step_video)
    PlayerView pvRecipeStepVideo;
    @Nullable
    @BindView(R.id.fl_recipe_step_video)
    FrameLayout flRecipeStepVideoLayout;

    public static RecipeStepFragment newInstance() {
        return new RecipeStepFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecipeStepFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.recipe_step_fragment, container, false);
        binding.setLifecycleOwner(this);
        View rootView = binding.getRoot();
        ButterKnife.bind(this, rootView);

        mViewModel = ViewModelProviders.of(getActivity()).get(RecipeStepViewModel.class);
        binding.setViewModel(mViewModel);

        setupViews();
        setupVideoPlayer();
        configureObservables();

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mViewModel.getSelectedStep().getValue().hasVideo())
            mViewModel.getCurrentVideoPosition().set(player.getCurrentPosition());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    private void setupViews() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(mViewModel.getRecipe().getName());
        tvRecipeStepDescription.setMovementMethod(new ScrollingMovementMethod());

        if (flRecipeStepVideoLayout != null) {
            ViewGroup.LayoutParams params = flRecipeStepVideoLayout.getLayoutParams();
            params.height = (int) (((float) 1080 / (float) 1920) * (float) params.width);
            flRecipeStepVideoLayout.setLayoutParams(params);
        }
    }

    private void setupVideoPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(getContext());
        player.setPlayWhenReady(true);
        pvRecipeStepVideo.setPlayer(player);
        dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), getString(R.string.app_name)));
    }

    private void configureObservables() {
        mViewModel.getSelectedStep().observe(this, step -> {
            player.stop();
            if (step.hasVideo()) {
                MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(step.getVideoOrThumbURL()));
                player.prepare(videoSource);
                player.seekTo(mViewModel.getCurrentVideoPosition().get());
            }
        });
    }

    private void releasePlayer() {
        player.stop();
        player.release();
    }

    @Override
    public void onPause() {
        super.onPause();
        player.stop();
    }
}
