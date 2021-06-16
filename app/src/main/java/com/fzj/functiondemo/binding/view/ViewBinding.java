package com.fzj.functiondemo.binding.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.fzj.functiondemo.databinding.ActivityViewBinding;
import com.fzj.functiondemo.databinding.MergeOneBinding;

public class ViewBinding extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewBinding binding = ActivityViewBinding.inflate(getLayoutInflater());
        MergeOneBinding mergeOneBinding = binding.mergeView;
        setContentView(binding.getRoot());
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mergeOneBinding.tvName.setText("666");
                int n = Integer.parseInt(binding.tvShow.getText().toString());
                binding.tvShow.setText(++n + "");
            }
        });
    }
}
