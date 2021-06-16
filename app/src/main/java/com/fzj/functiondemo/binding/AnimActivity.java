package com.fzj.functiondemo.binding;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.animation.LinearInterpolator;

import com.fzj.functiondemo.R;
import com.fzj.functiondemo.databinding.ActivityAnim2Binding;
import com.fzj.functiondemo.databinding.ActivityAnimBinding;

public class AnimActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAnim2Binding binding;
    private int scrollX = -100;

    public void setScrollX(int scrollX) {
        this.scrollX = scrollX;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnim2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.img.setScrollX(scrollX);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.btn4.setOnClickListener(this);
        binding.btn5.setOnClickListener(this);
        binding.btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: {
                final ObjectAnimator animator = ObjectAnimator.ofInt(binding.img, "scrollX", -100, 0);
                animator.setDuration(100);
                animator.setInterpolator(new LinearInterpolator());
                animator.start();
            }
        }
    }
}
