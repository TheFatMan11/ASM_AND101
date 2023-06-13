package com.thuydev.asm1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PhongBan extends AppCompatActivity {
    public static final String KEY_NS = "ns";
    public static final String KEY_name = "hc";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vp_layout);

        Toolbar toolbar = findViewById(R.id.tb_toolbar2);
       Button nhanSu = findViewById(R.id.btn_ns1);
       Button hanhChinh = findViewById(R.id.btn_hc2);
       Button daoTao = findViewById(R.id.btn_dt3);
       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Phòng ban");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nhanSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhongBan.this,NhanVien.class);
                intent.putExtra(KEY_NS,1);
                intent.putExtra(KEY_name,"Phòng Ban Nhân Sự");
                startActivity(intent);
            }
        });
        hanhChinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhongBan.this,NhanVien.class);
                intent.putExtra(KEY_NS,2);
                intent.putExtra(KEY_name,"Phòng Ban Hành chính");
                startActivity(intent);
            }
        });
        daoTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhongBan.this,NhanVien.class);
                intent.putExtra(KEY_NS,3);
                intent.putExtra(KEY_name,"Phòng Ban Đào Tạo");
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            finish();
        } else if (item.getItemId()== R.id.it_Search) {
            Toast.makeText(this, "Đang trong quá trình phát triển", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
