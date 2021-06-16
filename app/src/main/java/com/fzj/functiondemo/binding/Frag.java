package com.fzj.functiondemo.binding;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fzj.functiondemo.R;
import com.fzj.functiondemo.databinding.ActivityAnimBinding;
import com.fzj.functiondemo.databinding.ActivityReRecyclerviewBinding;
import com.fzj.functiondemo.databinding.SingleTextBinding;

import java.util.ArrayList;
import java.util.List;

public class Frag extends Fragment {

    private MyAdapter adapter;
    ActivityReRecyclerviewBinding binding;
    List<String> list;
    LinearLayoutManager manager;
    public RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityReRecyclerviewBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + "");
        }
        adapter = new MyAdapter();
        manager = new LinearLayoutManager(requireActivity());
        binding.recycler.setLayoutManager(manager);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setHasFixedSize(true);


    }

    private class MyAdapter extends RecyclerView.Adapter<Frag.MyAdapter.MyHolder> {

        @NonNull
        @Override
        public Frag.MyAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.single_text, parent, false);
            Frag.MyAdapter.MyHolder holder = new Frag.MyAdapter.MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Frag.MyAdapter.MyHolder holder, int position) {
            SingleTextBinding textBinding = SingleTextBinding.bind(holder.itemView);
            textBinding.text.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        private class MyHolder extends RecyclerView.ViewHolder {
            public SingleTextBinding textBinding;

            public MyHolder(@NonNull View itemView) {
                super(itemView);


            }

        }
    }
}
