package com.fzj.functiondemo.binding;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fzj.functiondemo.databinding.ActivityRefreshBinding;
import com.fzj.functiondemo.databinding.ActivityViewBinding;
import com.fzj.functiondemo.databinding.MergeOneBinding;

public class RefreshActivity extends Activity {
    ActivityRefreshBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRefreshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        PackageManager pm = getPackageManager();
        boolean flag = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.READ_PHONE_STATE", "packageName"));
        if (flag) {
//有这个权限bai，做相相应处理
        } else {
//没有权限
            requestPermissions(new String[]{"android.permission.RECORD_AUDIO"
                    , "android.permission.WRITE_EXTERNAL_STORAGE"
                    , "android.permission.READ_EXTERNAL_STORAGE","android.permission.READ_PHONE_STATE","android.permission.READ_CALL_LOG"}, 1);
        }
        binding.srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.btnClick.setText("我正在刷新。。。");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onCreate(savedInstanceState);
            }
        });
    }
}
