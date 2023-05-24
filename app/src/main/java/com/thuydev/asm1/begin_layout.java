package com.thuydev.asm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class begin_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_layout);
        new Thread(){
            @Override
            public void run() {
                super.run();
                int a = 0;
                while (true){
                    try {
                        sleep(1000);
                        a++;
                        Log.d("1", "run: "+a);
                        if(a==2){
                            Intent intent = new Intent(begin_layout.this, login_layout.class);
                            startActivity(intent);
                            System.exit(0);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }
}