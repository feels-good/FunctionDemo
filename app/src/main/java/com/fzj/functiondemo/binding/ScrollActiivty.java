package com.fzj.functiondemo.binding;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.fzj.functiondemo.databinding.ActivityScrollBinding;
import com.google.android.material.tabs.TabLayout;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class ScrollActiivty extends AppCompatActivity {
    private ActivityScrollBinding binding;
    private Frag fragment;
    private Frag fragment1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScrollBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragment = new Frag();
        fragment1 = new Frag();
        TabLayout.Tab tab = binding.tab.newTab();
        tab.setText("sada");
        binding.tab.addTab(tab);
        binding.llHeader.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                System.out.println(scrollY);
            }
        });
        binding.tab.setupWithViewPager(binding.viewpaper);
        binding.viewpaper.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                if (position == 1)
                    return fragment;
                else return fragment1;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "666";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}