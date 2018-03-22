package com.example.admin.msensorplot;

/**
 * Created by admin on 2016/11/13.
 */

import java.util.Timer;

import org.achartengine.GraphicalView;

import com.example.admin.msensorplot.ChartService2;
import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class MainShowACC extends Activity implements SensorEventListener {

    private LinearLayout maccLayout;//存放acc图表的布局容器
    private LinearLayout mvLayout;//存放v0~v2图表的布局容器
    private GraphicalView mView, mView1;//左右图表
    private ChartService2 mService,mServiceV;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);

        maccLayout = (LinearLayout) findViewById(R.id.acc);
        mvLayout = (LinearLayout) findViewById(R.id.v);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        mService = new ChartService2(this);
        mService.setXYMultipleSeriesDataset("acc","acc","acc");
        mService.setXYMultipleSeriesRenderer(0, 100, 0, 10, "acc", "time", "m/s^2",
                Color.WHITE, Color.WHITE,  Color.BLACK);
        mView = mService.getGraphicalView();

        mServiceV = new ChartService2(this);
        mServiceV.setXYMultipleSeriesDataset("x","y","z");
        mServiceV.setXYMultipleSeriesRenderer(0, 100, 0, 10, "v", "time", "m/s",
                Color.WHITE, Color.WHITE,  Color.BLACK);
        mView1 = mServiceV.getGraphicalView();

        //将左右图表添加到布局容器中
        maccLayout.addView(mView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mvLayout.addView(mView1, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
    }
    protected void onStop(){
        mSensorManager.unregisterListener(this);
        super.onStop();
    }
    protected void onPause(){
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    //@Override
   // public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        //return true;
   // }

    public void onAccuracyChanged(Sensor sensor, int accuracy){}
    private int t = 0, delay = 0;
    private double acc = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        final float[] values = event.values;
        acc = Math.sqrt(values[0]*values[0]+values[1]*values[1]+values[2]*values[2]);
        //int t = 0;
        // handler = new Handler() {
        //定时更新图表
        // public void handleMessage(Message msg) {
        if (delay == 50){
            mService.updateChart(t, acc, acc, acc);
            mServiceV.updateChart(t, values[0], values[1], values[2]);
            t += 5;
            delay = 0;
        }
        else {delay += 1;}
        // }
        // };
    }

}

