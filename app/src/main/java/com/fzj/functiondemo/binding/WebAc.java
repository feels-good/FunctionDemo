package com.fzj.functiondemo.binding;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fzj.functiondemo.R;

import java.io.FileOutputStream;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;

import static android.webkit.WebView.enableSlowWholeDocumentDraw;

public class WebAc extends AppCompatActivity {
    private WebView mWebView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableSlowWholeDocumentDraw();
        setContentView(R.layout.we);

        mWebView = findViewById(R.id.web);
        button = findViewById(R.id.save);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString());
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        mWebView.setDrawingCacheEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://www.baidu.com/s?ie=utf-8&f=3&rsv_bp=1&rsv_idx=1&tn=baidu&wd=salute&fenlei=256&rsv_pq=d582bb02001c7dc6&rsv_t=1236VSzCFvxzvLHXTG%2FmSm6%2FFs8EJOp%2B5W0XZvRd5lF1IeCzrYucO0ZxNZI&rqlang=cn&rsv_enter=1&rsv_dl=ts_0&rsv_sug3=3&rsv_sug1=1&rsv_sug7=100&rsv_sug2=1&rsv_btype=i&prefixsug=sa&rsp=0&inputT=976&rsv_sug4=1201");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermission(Manifest.permission.INTERNET, 1, 1) != PackageManager.PERMISSION_GRANTED
                        || checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1, 1) != PackageManager.PERMISSION_GRANTED
                        || checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, 1, 1) != PackageManager.PERMISSION_GRANTED)
                    requestPermissions(new String[]{Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                WebView wv_capture = mWebView;
                float scale = mWebView.getScale();
                int width = mWebView.getWidth();
                int height = mWebView.getContentHeight();
                Bitmap longImage = Bitmap.createBitmap(wv_capture.getWidth(), 1000, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(longImage);
//绘制
//                int index = height / mWebView.getHeight();
//                int diff = height - index * mWebView.getHeight();
//                Bitmap bitmap1 = Bitmap.createBitmap(wv_capture.getWidth(), diff, Bitmap.Config.ARGB_8888);
//                Canvas canva1 = new Canvas(bitmap1);
//                mWebView.draw(canva1);
//                canvas.drawBitmap(bitmap1, 0, 0, null);
//                mWebView.scrollTo(0, diff);
//                for (int i = 0; i < index; i++) {
//                mWebView.scrollTo(0, 2000);
                mWebView.draw(canvas);
//                }
                try {
                    String fileName = Environment.getExternalStorageDirectory().getPath() + "/webview_capture4.jpg";
                    FileOutputStream fos = new FileOutputStream(fileName);
                    //压缩bitmap到输出流中
                    longImage.compress(Bitmap.CompressFormat.JPEG, 70, fos);
                    fos.close();

                } catch (Exception e) {
                    Log.e("6666666", e.getMessage());
                } finally {


                }
            }
        });
    }
}
