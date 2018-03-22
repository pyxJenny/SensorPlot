package com.example.admin.msensorplot;

/**
 * Created by admin on 2016/11/13.
 */
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MainShowWIFI extends Activity{
    private WifiInfo wifiInfo = null;       //获得的Wifi信息
    private WifiManager wifiManager = null; //Wifi管理器
    private Handler handler;
    private ImageView wifi_image;           //信号图片显示
    private int level,IPadd,LinkSpeed;                      //信号强度值
    TextView wifiState;

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        wifiState = (TextView) findViewById(R.id.wifiState);
        //图片控件初始化
        wifi_image = (ImageView) findViewById(R.id.wifi_image);
        // 获得WifiManager
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        // 使用定时器,每隔5秒获得一次信号强度值
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                wifiInfo = wifiManager.getConnectionInfo();
                IPadd = wifiInfo.getIpAddress();
                LinkSpeed = wifiInfo.getLinkSpeed();
                //获得信号强度值
                level = wifiInfo.getRssi();
                //根据获得的信号强度发送信息
                if (level <= 0 && level >= -50) {
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                } else if (level < -50 && level >= -70) {
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } else if (level < -70 && level >= -80) {
                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                } else if (level < -80 && level >= -100) {
                    Message msg = new Message();
                    msg.what = 4;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.what = 5;
                    handler.sendMessage(msg);
                }

            }

        }, 1000, 5000);
        // 使用Handler实现UI线程与Timer线程之间的信息传递,每5秒告诉UI线程获得wifiInto
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    // 如果收到正确的消息就获取WifiInfo，改变图片并显示信号强度
                    case 1:
                        wifi_image.setImageResource(R.drawable.single4);
                        wifiState.setText("信号强度" + level + "信号最好" + "\nIP地址为" + IPadd + "\n连接速度为" + LinkSpeed);
                        //Toast.makeText(MainShowWIFI.this,
                                //"信号强度：" + level + " 信号最好", Toast.LENGTH_SHORT)
                                //.show();
                        break;
                    case 2:
                        wifi_image.setImageResource(R.drawable.single3);
                        break;
                    case 3:
                        wifi_image.setImageResource(R.drawable.single2);
                        wifiState.setText("信号强度" + level + "信号较好" + "\nIP地址" + IPadd + "\n连接速度为" + LinkSpeed);
                        break;
                    case 4:
                        wifi_image.setImageResource(R.drawable.single1);
                        wifiState.setText("信号强度" + level + "信号较差" + "\nIP地址" + IPadd + "\n连接速度为" + LinkSpeed);
                        break;
                    case 5:
                        wifi_image.setImageResource(R.drawable.single0);
                        wifiState.setText("信号强度" + level + "无信号" + "\nIP地址" + IPadd + "\n连接速度为" + LinkSpeed);
                        break;
                    default:
                        //以防万一
                        wifi_image.setImageResource(R.drawable.single0);
                        wifiState.setText("信号强度" + level + "无信号" + "\nIP地址" + IPadd + "\n连接速度为" + LinkSpeed);
                }
            }

        };

    }
}

