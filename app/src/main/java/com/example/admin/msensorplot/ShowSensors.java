package com.example.admin.msensorplot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 2017/1/11.
 */
public class ShowSensors extends Activity {

    private Button detail;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsensors);
        detail = (Button) findViewById(R.id.detail);

        detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(ShowSensors.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final TextView tv = (TextView) findViewById(R.id.tv);
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> allSensor = sm.getSensorList(Sensor.TYPE_ALL);
        tv.setText("经检测该手机有" + allSensor.size() + "个传感器，它们分别是：\n");

        for (Sensor s : allSensor){
            String tempString = "\n" + " 设备名称：" + s.getName() + "\n" + " 设备版本：" + s.getVersion() + "\n" + " 供应商：" + s.getVendor() + "\n";

            switch (s.getType()){
                case Sensor.TYPE_ACCELEROMETER:
                    tv.setText(tv.getText().toString() + s.getType() + " 加速度传感器accelerometer" + tempString);
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    tv.setText(tv.getText().toString() + s.getType() + " 陀螺仪传感器gyroscope" + tempString);
                    break;
                case Sensor.TYPE_LIGHT:
                    tv.setText(tv.getText().toString() + s.getType() + " 环境光强传感器light" + tempString);
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    tv.setText(tv.getText().toString() + s.getType() + " 电磁场传感器magnetic field" + tempString);
                    break;
                case Sensor.TYPE_ORIENTATION:
                    tv.setText(tv.getText().toString() + s.getType() + " 方向传感器orientation" + tempString);
                    break;
                case Sensor.TYPE_PRESSURE:
                    tv.setText(tv.getText().toString() + s.getType() + " 压力传感器pressure" + tempString);
                    break;
                default:
                    tv.setText(tv.getText().toString() + s.getType() + " 其它传感器" + tempString);
                    break;
            }
        }

    }
}
