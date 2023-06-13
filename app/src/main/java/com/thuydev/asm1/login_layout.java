package com.thuydev.asm1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class login_layout extends AppCompatActivity {
    String tenNguoi=null;
    String matKhau = null;
    CheckBox luu;
    ArrayList <User> users ;
    final  String KEY_DATA = "account.txt";
    private static final String KEY_PUSH = "nho";
    private static final String KEY_SAVE = "luu";
    public static final String KEY_USER = "user";
    public static final String KEY_PASS = "pass";
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
                        tenNguoi = bundle.getString("User1");
                        matKhau = bundle.getString("Pass1");
                        uSer.setText(tenNguoi);
                        pAss.setText(matKhau);
                        ghi();
                    }
                }
            }
    );

    public void ghi(){
       try {
           FileOutputStream fileOutputStream = openFileOutput(KEY_DATA,MODE_PRIVATE);
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
           objectOutputStream.writeObject(users);
           objectOutputStream.close();
           fileOutputStream.close();
       }catch (Exception e){

       }
    }
    public void  doc(){
        try {
            FileInputStream fileInputStream = openFileInput(KEY_DATA);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            users= (ArrayList<User>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        }catch (Exception e){

        }
    }
    public void luu (String user , String pass,boolean save){
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_PUSH,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER,user);
        editor.putString(KEY_PASS,pass);
        editor.putBoolean(KEY_SAVE,save);
        editor.apply();
    }
    public void checkBoolean (EditText a,EditText b){
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_PUSH,MODE_PRIVATE);
        String user = sharedPreferences.getString(KEY_USER,"");
        String pass = sharedPreferences.getString(KEY_PASS,"");
        boolean save = sharedPreferences.getBoolean(KEY_SAVE,false);
        luu.setChecked(save);
        if(luu.isChecked()==true){
            a.setText(user);
            b.setText(pass);
            tenNguoi=user;
            matKhau=pass;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        users = new ArrayList<>();
        if (users.size()<=0){
            users.add(new User("jshdgfsgdjfhgsdfsd","sdgúygudfyágdahsukd"));
        }
        Button bnt = findViewById(R.id.btn_nut1);
        Button bnt2 = findViewById(R.id.btn_nut2);
        EditText name = findViewById(R.id.txt_user);
        EditText Pass = findViewById(R.id.txt_pass);
        luu = findViewById(R.id.cbk_luu);
        doc();


        checkBoolean(name,Pass);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_layout.this , sign_in_layout.class);
                getData.launch(intent);
            }
        });
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = false;
                String sName = name.getText().toString();
                String sPass = Pass.getText().toString();
                Log.e("chckkkkkk", "sName  "+sName);
                Log.e("chckkkkkk", "sPass  "+sPass);
                Log.e("chckkkkkk", "Nguoi  "+tenNguoi);
                Log.e("chckkkkkk", "mk  "+matKhau);
                Intent intent = new Intent(login_layout.this,main_layout.class);
                if(sName.equals(tenNguoi)){
                    if (sPass.equals(matKhau)){
                        User user = users.get(0);
                        if(luu.isChecked()){

                            user.setUser(tenNguoi);
                            user.setPass(matKhau);
                            user.setSave(true);
                            ghi();
                            luu(user.getUser(),user.getPass(),user.isSave());

                        }else {
                            user.setUser(sName);
                            user.setPass(sPass);
                            user.setSave(false);
                            ghi();
                            luu(user.getUser(),user.getPass(),user.isSave());
                        }
                        check= true;
                        startActivity(intent);

                    }
                }
                if(check==false){
                    Toast.makeText(login_layout.this,"Sai tên đăng nhập hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(login_layout.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}