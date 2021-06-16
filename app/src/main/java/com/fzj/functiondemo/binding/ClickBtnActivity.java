package com.fzj.functiondemo.binding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fzj.functiondemo.databinding.ActivityClickableBtnBinding;
import com.fzj.functiondemo.databinding.SingleTextBinding;

import java.util.ArrayList;
import java.util.List;

public class ClickBtnActivity extends AppCompatActivity {
    private ActivityClickableBtnBinding binding;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.Adapter adapter;
    private List<String> data = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClickableBtnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        data.add("可交互式信息弹出");
        data.add("测试信息弹出");
        data.add("改变列表排序");
        data.add("动画");
        data.add("滑动");
        binding.blank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.blank.setVisibility(View.GONE);
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    getSupportFragmentManager().beginTransaction().detach(fragment).commitAllowingStateLoss();
                }
            }
        });
        adapter = new RecyclerView.Adapter<MyHolder>() {
            @NonNull
            @Override
            public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyHolder(SingleTextBinding.inflate(getLayoutInflater(), parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull MyHolder holder, int position) {
                holder.itemBinding.text.setText(data.get(position));
                holder.itemBinding.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (position) {
                            case 0:
                                binding.blank.setVisibility(View.VISIBLE);
                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                Fragment fragment = new DialogToast(ClickBtnActivity.this, position);
                                transaction.add(binding.frame.getId(), fragment).show(fragment);
                                transaction.commitAllowingStateLoss();
                                break;
                            case 1:
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("a");
                                stringBuilder.append("b");
                                Log.e("stringBuilder", "onClick: " + stringBuilder);
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append("c");
                                stringBuffer.append("d");
                                Log.e("stringBuffer", "onClick: " + stringBuffer);
                                break;
                            case 2:
                                Intent intent = new Intent(ClickBtnActivity.this, ReRecyclerVIew.class);
                                startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(ClickBtnActivity.this, AnimActivity.class);
                                startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(ClickBtnActivity.this, ScrollActiivty.class);
                                startActivity(intent);
                                break;
                        }

                    }
                });
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        gridLayoutManager = new GridLayoutManager(this, 4);
        binding.recycler.setLayoutManager(gridLayoutManager);
        binding.recycler.setAdapter(adapter);
    }


    class MyHolder extends RecyclerView.ViewHolder {
        public SingleTextBinding itemBinding;

        public MyHolder(SingleTextBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
