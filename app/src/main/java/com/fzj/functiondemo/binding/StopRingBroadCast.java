package com.fzj.functiondemo.binding;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class StopRingBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
        manager.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING:
                        String message = "";
                        try {
                            URL url = new URL("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + phoneNumber);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(5 * 1000);
                            connection.connect();
                            InputStream inputStream = connection.getInputStream();
                            byte[] data = new byte[1024];
                            StringBuffer sb = new StringBuffer();
                            int length = 0;
                            while ((length = inputStream.read(data)) != -1) {
                                String s = new String(data, Charset.forName("utf-8"));
                                sb.append(s);
                            }
                            message = sb.toString();
                            inputStream.close();
                            connection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }
}
