package com.example.admin.msensorplot;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager mSensorManager;
    EditText etACC, etGYR, etMAG, etLight, etPressure;
    Button buttonACC, buttonGYR, buttonWIFI, buttonMAG, buttonCOM, buttonGSM, buttonLEVEL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etACC = (EditText) findViewById(R.id.etACC);
        etGYR = (EditText) findViewById(R.id.etGYR);
        etMAG = (EditText) findViewById(R.id.etMAG);
        etLight = (EditText) findViewById(R.id.etLight);
        etPressure = (EditText) findViewById(R.id.etPressure);
        buttonACC = (Button) findViewById(R.id.buttonACC);
        buttonGYR = (Button) findViewById(R.id.buttonGYR);
        buttonMAG = (Button) findViewById(R.id.buttonMAG);
        buttonWIFI = (Button) findViewById(R.id.buttonWIFI);
        buttonWIFI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,MainShowWIFI.class);
                startActivity(intent);
            }
        });
        buttonCOM = (Button) findViewById(R.id.buttonCOM);
        buttonCOM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,MainShowCOM.class);
                startActivity(intent);
            }
        });
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        buttonACC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,MainShowACC.class);
                startActivity(intent);
            }
        });
        buttonGYR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,MainShowGYR.class);
                startActivity(intent);
            }
        });
        buttonMAG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,MainShowMAG.class);
                startActivity(intent);
            }
        });
        buttonGSM = (Button) findViewById(R.id.buttonGSM);
        buttonGSM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,MainShowGSM.class);
                startActivity(intent);
            }
        });
        buttonLEVEL = (Button) findViewById(R.id.buttonLEVEL);
        buttonLEVEL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MainShowLEVEL.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this,mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onStop(){
        mSensorManager.unregisterListener(this);
        super.onStop();
    }
    @Override
    protected void onPause(){
        mSensorManager.unregisterListener(this);
        super.onPause();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){}
    @Override
    public void onSensorChanged(SensorEvent event){
        float[] values = event.values;
        int sensorType = event.sensor.getType();
        StringBuilder sb = null;
        switch (sensorType){
            case Sensor.TYPE_ACCELEROMETER:
                sb = new StringBuilder();
                sb.append("X轴方向上的加速度：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的加速度：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的加速度：");
                sb.append(values[2]);
                etACC.setText(sb.toString());
                break;
            case Sensor.TYPE_GYROSCOPE:
                sb = new StringBuilder();
                sb.append("绕X轴旋转的角速度：");
                sb.append(values[0]);
                sb.append("\n绕Y轴旋转的角速度：");
                sb.append(values[1]);
                sb.append("\n绕Z轴旋转的角速度：");
                sb.append(values[2]);
                etGYR.setText(sb.toString());
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                sb = new StringBuilder();
                sb.append("X轴方向上的磁场强度：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的磁场强度：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的磁场强度：");
                sb.append(values[2]);
                etMAG.setText(sb.toString());
                break;
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("当前光的强度为：");
                sb.append(values[0]);
                etLight.setText(sb.toString());
                break;
            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("当前压力为：");
                sb.append(values[0]);
                etPressure.setText(sb.toString());
                break;
        }
    }
}
