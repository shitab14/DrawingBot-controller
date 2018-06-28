package com.hexdotapps.drawingbot;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int SPLASH_TIME_OUT=2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(this);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                Intent intent= new Intent(MainActivity.this, Devicelist.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    public void onClick(View v){

        Intent intent= new Intent(MainActivity.this, Devicelist.class);
        startActivity(intent);
        finish();

    }

}
