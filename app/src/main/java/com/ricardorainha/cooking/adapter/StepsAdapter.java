package com.ricardorainha.cooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardorainha.cooking.R;
import com.ricardorainha.cooking.model.Step;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Step> steps;
    OnClickListener listener;

    public StepsAdapter(List<Step> steps, OnClickListener listener) {
        this.steps = steps;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_detail_step_list_item, parent, false);
        StepsViewHolder viewHolder = new StepsViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StepsViewHolder viewHolder = (StepsViewHolder) holder;
        Step step = steps.get(position);

        viewHolder.tvShortDescription.setText(step.getShortDescription());
        viewHolder.ivVideoIcon.setVisibility(step.hasVideo() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        if (steps != null)
            return steps.size();
        else
            return 0;
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_step_short_description)
        TextView tvShortDescription;
        @BindView(R.id.iv_step_video_icon)
        ImageView ivVideoIcon;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onStepSelected(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onStepSelected(int index);
    }

}
