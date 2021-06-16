package com.fzj.functiondemo.binding;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fzj.functiondemo.databinding.SingleTextBinding;

public class SpanActivity extends AppCompatActivity {
    SingleTextBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SingleTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String str = "我真 666";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 3, 6, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        binding.text.setText(spannableStringBuilder);
    }
}
