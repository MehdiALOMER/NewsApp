package com.example.newsapp.ui.onboarding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;

public class OnboardingViewHolder extends RecyclerView.ViewHolder {
    private final ImageView imageView;
    private final TextView title;
    private final TextView description;

    public OnboardingViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        title = itemView.findViewById(R.id.textTitle);
        description = itemView.findViewById(R.id.textDescription);
    }

    public void bind(OnboardingItem item) {
        imageView.setImageResource(item.getImageRes());
        title.setText(item.getTitle());
        description.setText(item.getDescription());
    }
}