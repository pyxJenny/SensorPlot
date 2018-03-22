package com.example.admin.msensorplot;

/**
 * Created by admin on 2016/11/14.
 */

        import org.achartengine.GraphicalView;

        import android.app.Activity;
        import android.graphics.Color;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.os.Bundle;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;

public class MainShowMAG extends Activity implements SensorEventListener {

    private LinearLayout moriLayout;//存放acc图表的布局容器
    //private LinearLayout mvLayout;//存放v0~v2图表的布局容器
    private GraphicalView mView;//左右图表
    private ChartService2 mService;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mag);

        moriLayout = (LinearLayout) findViewById(R.id.mag);
        //mvLayout = (LinearLayout) findViewById(R.id.v);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        mService = new ChartService2(this);
        mService.setXYMultipleSeriesDataset("X","Y","Z");
        mService.setXYMultipleSeriesRenderer(0, 100, -50, 50, "mag", "time", "uT",
                Color.WHITE, Color.WHITE,  Color.BLACK);
        mView = mService.getGraphicalView();

        //将左右图表添加到布局容器中
        moriLayout.addView(mView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);
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
    //private double acc = 0;
    @Override
    public void onSensorChanged(SensorEvent event) {
        final float[] values = event.values;
        //acc = Math.sqrt(values[0]*values[0]+values[1]*values[1]+values[2]*values[2]);
        //int t = 0;
        // handler = new Handler() {
        //定时更新图表
        // public void handleMessage(Message msg) {
        if (delay == 50){
            //mService.updateChart(t, acc, acc, acc);
            mService.updateChart(t, values[0], values[1], values[2]);
            t += 5;
            delay = 0;
        }
        else {delay += 1;}
        // }
        // };
    }

}


