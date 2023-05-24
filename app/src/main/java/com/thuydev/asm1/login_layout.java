package com.thuydev.asm1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class login_layout extends AppCompatActivity {

    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 2) {
                        Intent intent = result.getData();
                        Bundle bundle = intent.getExtras();
                        EditText uSer = findViewById(R.id.txt_user);
                        EditText pAss = findViewById(R.id.txt_pass);

                        uSer.setText(bundle.getString("User1"));
                        pAss.setText(bundle.getString("Pass1"));
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Button bnt = findViewById(R.id.btn_nut1);
        Button bnt2 = findViewById(R.id.btn_nut2);

        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_layout.this, sign_in_layout.class);
                getData.launch(intent);
            }
        });

        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_layout.this,main_layout.class);
                startActivity(intent);
            }
        });

    }
}