package com.example.admin.msensorplot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by admin on 2017/1/11.
 */
public class WelcomeActivity extends Activity {
    private Button begin;

    protected void onCreate(Bundle savedInstanceStated){
        super.onCreate(savedInstanceStated);
        setContentView(R.layout.activity_welcome);
        begin = (Button) findViewById(R.id.begin);

        begin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this,ShowSensors.class);
                startActivity(intent);
            }
        });
    }
}
