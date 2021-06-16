package com.fzj.functiondemo.binding;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.PopupWindowCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fzj.functiondemo.R;
import com.fzj.functiondemo.databinding.ActivityReRecyclerviewBinding;
import com.fzj.functiondemo.databinding.SingleTextBinding;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.ItemTouchHelper.END;
import static androidx.recyclerview.widget.ItemTouchHelper.START;

public class ReRecyclerVIew extends AppCompatActivity {

    private ActivityReRecyclerviewBinding binding;
    private MyAdapter adapter;
    private LinearLayoutManager manager;
    private ArrayList<String> list = new ArrayList<>();
    private float transY = 0f;

    private void animate() {
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 0, Animation.ABSOLUTE, this.transY, Animation.ABSOLUTE, 0);
        animation.start();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReRecyclerviewBinding.inflate(getLayoutInflater());
        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData data = manager.getPrimaryClip();
                binding.editQuery.setHint(data.getItemAt(0).getText());
            }
        });
        setContentView(binding.getRoot());
        for (int i = 0; i < 16; i++) {
            list.add(i + "");
        }
        adapter = new MyAdapter();
        manager = new LinearLayoutManager(this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(manager);
        binding.editQuery.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                }
            }
        });
        binding.recycler.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (binding.recycler.computeVerticalScrollOffset() == 0) {
                    manager.scrollToPositionWithOffset(0, oldScrollY);
                }
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getAdapterPosition() == list.size() - 1)
                    return makeMovementFlags(0, 0);
                return makeMovementFlags(ItemTouchHelper.UP | START | END | ItemTouchHelper.DOWN, ItemTouchHelper.UP | START | END | ItemTouchHelper.DOWN);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                RecyclerView.Adapter adapter1 = recyclerView.getAdapter();
//                adapter1.notifyItemRangeChanged(viewHolder.getAdapterPosition(),1);
                String s = list.get(viewHolder.getAdapterPosition());
                list.remove(s);
                list.add(target.getAdapterPosition(), s);
                if (adapter1 != null) {
                    adapter1.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                }
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                list.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean canDropOver(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder current, @NonNull RecyclerView.ViewHolder target) {
                if (target.getAdapterPosition() == list.size() - 1) return false;
                return super.canDropOver(recyclerView, current, target);
            }

            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                if (viewHolder != null) {
                    viewHolder.itemView.setScaleX(1.1f);
                    viewHolder.itemView.setScaleY(1.1f);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                if (viewHolder != null) {
                    viewHolder.itemView.setScaleX(1.0f);
                    viewHolder.itemView.setScaleY(1.0f);
                }
                super.clearView(recyclerView, viewHolder);
            }
        });
        helper.attachToRecyclerView(binding.recycler);
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ReRecyclerVIew.this).inflate(R.layout.single_text, parent, false);
            MyHolder holder = new MyHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            SingleTextBinding textBinding = SingleTextBinding.bind(holder.itemView);
            textBinding.text.setText(list.get(position));
            textBinding.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupWindow window = new PopupWindow(ReRecyclerVIew.this);
                    window.setFocusable(true);
                    window.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setBackgroundDrawable(new ColorDrawable(0x00ffffff));
                    View view = getLayoutInflater().inflate(R.layout.single_text, null);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PopupWindow window1 = new PopupWindow(ReRecyclerVIew.this);
                            window1.setFocusable(true);
                            window1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                            window1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                            window1.setBackgroundDrawable(new ColorDrawable(0x00ffffff));
                            window1.setContentView(getLayoutInflater().inflate(R.layout.activity_record, null));
                            window1.showAsDropDown(holder.itemView, 0, -holder.itemView.getHeight(), Gravity.TOP);
                        }
                    });
                    window.setContentView(view);
                    window.showAsDropDown(holder.itemView);
                }
            });
            textBinding.text.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipData data = ClipData.newPlainText(textBinding.text.getText().toString(), textBinding.text.getText().toString());
                    ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    manager.setPrimaryClip(data);
                    Toast.makeText(ReRecyclerVIew.this, "已复制", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
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
