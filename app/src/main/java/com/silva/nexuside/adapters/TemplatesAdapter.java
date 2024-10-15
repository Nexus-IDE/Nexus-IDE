package com.silva.nexuside.adapters;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.silva.nexuside.ui.activities.MainActivity;
import com.silva.nexuside.databinding.ItemTemplateWizardBinding;
import com.silva.nexuside.ui.fragments.WizardDetailsFragment;
import com.silva.nexuside.enums.ProjectTemplates;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplatesAdapter.ViewHolder> {
		
		List<ProjectTemplates> data;
        ItemTemplateWizardBinding binding;
        MainActivity main;
        Context context;
		
		public TemplatesAdapter(Context context, List<ProjectTemplates> data, MainActivity main) {
			this.data = data;
            this.context = context;
            this.main = main;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			binding = ItemTemplateWizardBinding.inflate(inflater, parent, false);
			RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            binding.getRoot().setLayoutParams(layoutParams);
			return new ViewHolder(binding.getRoot());
		}
		
		@Override
		public void onBindViewHolder(ViewHolder holder, final int position) {
        
            ProjectTemplates template = ProjectTemplates.safeValueOf(data.get(position).name());
            	
                 Glide.with(context) 
                      .asBitmap()
                      .load("file:///android_asset/" + template.getIconPath())
                      .diskCacheStrategy(DiskCacheStrategy.NONE)
                      .skipMemoryCache(true)
                      .centerCrop()
                      .into(binding.image);
            binding.title.setText(context.getString(template.getTitleResId()));
            binding.image.setOnClickListener(view -> {
                main.openFragment(new WizardDetailsFragment(template), "WizardDetails");
            });
		}
		
		@Override
		public int getItemCount() {
			return data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}