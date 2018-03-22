package com.example.admin.msensorplot;

/**
 * Created by admin on 2016/11/17.
 */
        import android.app.Activity;
        import android.hardware.Sensor;
        import android.hardware.SensorEvent;
        import android.hardware.SensorEventListener;
        import android.hardware.SensorManager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.animation.Animation;
        import android.view.animation.RotateAnimation;
        import android.widget.ImageView;

public class MainShowCOM extends Activity implements SensorEventListener {

    ImageView znzImage;
    float currentDegree = 0f;
    SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);
        znzImage = (ImageView) findViewById(R.id.znzImage);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause(){
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop(){
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        int sensorType = event.sensor.getType();
        switch (sensorType)
        {
            case Sensor.TYPE_ORIENTATION:
                float degree = event.values[0];
                RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setDuration(200);
                znzImage.startAnimation(ra);
                currentDegree = -degree;
                break;
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}
}


