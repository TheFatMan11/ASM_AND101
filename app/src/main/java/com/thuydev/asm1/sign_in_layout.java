package com.thuydev.asm1;

import android.annotation.SuppressLint;
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
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

public class sign_in_layout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_layout);
        Button dk = findViewById(R.id.btn_dangnhap);
        Button tv = findViewById(R.id.btn_treve);
        EditText user = findViewById(R.id.edt_username);
        EditText pass = findViewById(R.id.edt_password);
        EditText rePass = findViewById(R.id.edt_re_password);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sUser = user.getText().toString().trim();
                String sPass = pass.getText().toString().trim();
                String sRePass = rePass.getText().toString().trim();


                    if (sUser.equals("")){
                        Toast.makeText(sign_in_layout.this,"Vui lòng không để trống User",Toast.LENGTH_SHORT).show();
                    }else if(sPass.equals("")){
                    Toast.makeText(sign_in_layout.this,"Vui lòng không để trống Password",Toast.LENGTH_SHORT).show();
                }

                else if(!sPass.equals(sRePass)){

                        Toast.makeText(sign_in_layout.this,"Mật khẩu nhập lại không chính xác",Toast.LENGTH_SHORT).show();

                }else {

                        Intent intent = new Intent(getApplicationContext(),login_layout.class);
                        Bundle bundle = new Bundle();


                        bundle.putString("User1", sUser);
                        bundle.putString("Pass1", sPass);
                        intent.putExtras(bundle);
                        setResult(2,intent);
                        finish();


                    }
            }
        });

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    finish();
                }
            });
}
}