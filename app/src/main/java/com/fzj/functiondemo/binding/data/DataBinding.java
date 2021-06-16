package com.fzj.functiondemo.binding.data;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fzj.functiondemo.databinding.ActivityDataBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static android.service.controls.ControlsProviderService.TAG;
import static java.lang.Thread.sleep;

public class DataBinding extends AppCompatActivity {
    private ActivityDataBinding binding;
    private MediaRecorder recorder;
    private Thread thread;
    private Runnable runnable;
    private Handler handler;
    private Boolean isOk;
    private String videoPath;
    private String audioPath;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        PackageManager pm = getPackageManager();
        boolean flag = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission("android.permission.CAMERA", "packageName"));
        if (flag) {
//有这个权限bai，做相相应处理
        } else {
//没有权限
            requestPermissions(new String[]{"android.permission.RECORD_AUDIO"
                    , "android.permission.WRITE_EXTERNAL_STORAGE"
                    , "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA"}, 1);
        }
        user = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(User.class);
    }
}
