package com.fzj.functiondemo.binding;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fzj.functiondemo.databinding.ActivityRecordBinding;
import com.fzj.functiondemo.databinding.SingleTextBinding;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity {
    private ActivityRecordBinding binding;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Map<String, Calendar> map = new HashMap<>();
        Calendar calendar = new Calendar();
        calendar.setDay(23);
        calendar.setMonth(3);
        calendar.setYear(2021);
        calendar.setScheme("666");
        calendar.setSchemeColor(Color.GREEN);
        map.put("1", calendar);
        binding.calendarView.setSchemeDate(map);
        boolean s = calendar.hasScheme();
        data.add("可交互式信息弹出");
        data.add("测试信息弹出");
        binding.calendarView.setOnViewChangeListener(new CalendarView.OnViewChangeListener() {
            @Override
            public void onViewChange(boolean isMonthView) {
                if (binding.getRoot().isRefreshing()) return;
                binding.getRoot().setEnabled(isMonthView);
            }
        });
        adapter = new RecyclerView.Adapter<CalendarActivity.MyHolder>() {
            @NonNull
            @Override
            public CalendarActivity.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyHolder(SingleTextBinding.inflate(getLayoutInflater(), parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull CalendarActivity.MyHolder holder, int position) {
                holder.itemBinding.text.setText(data.get(position));
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        };
        linearLayoutManager = new LinearLayoutManager(this);
        binding.rvUnforget.setLayoutManager(linearLayoutManager);
        binding.rvUnforget.setAdapter(adapter);
    }


    class MyHolder extends RecyclerView.ViewHolder {
        public SingleTextBinding itemBinding;

        public MyHolder(SingleTextBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}

