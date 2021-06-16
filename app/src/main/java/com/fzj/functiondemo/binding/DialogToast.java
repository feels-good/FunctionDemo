package com.fzj.functiondemo.binding;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fzj.functiondemo.R;
import com.fzj.functiondemo.databinding.LayoutEmptyBinding;

public class DialogToast extends Fragment {
    private Activity activity;
    private int flag;
    private LayoutEmptyBinding binding;

    public DialogToast(Activity activity, int flag) {
        this.activity = activity;
        this.flag = flag;
    }

    @Nullable
    @Override
    public Object getEnterTransition() {
        Transition transition = new Slide();
        return transition;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LayoutEmptyBinding.inflate(inflater, container, false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(30, 10, 30, 10);
        for (int i = 0; i < 5; i++) {
            TextView button = new TextView(activity);
            button.setLayoutParams(layoutParams);
            button.setText("点我");
            button.setGravity(Gravity.CENTER);
            button.setPadding(0,10,0,10);
            button.setBackground(getResources().getDrawable(R.drawable.btn));
            binding.getRoot().addView(button);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
