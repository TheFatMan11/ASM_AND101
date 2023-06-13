package com.thuydev.asm1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ADD_NV extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nv);
        EditText ten = findViewById(R.id.et_ten);
        EditText ma = findViewById(R.id.et_ma);
        EditText vp = findViewById(R.id.et_vp);
        Button them = findViewById(R.id.btn_luu);
        TextView tille = findViewById(R.id.title);

        Intent intent = getIntent();
        tille.setText(""+intent.getStringExtra("text"));
        Nhanvien_modle  nv = (Nhanvien_modle) getIntent().getSerializableExtra("list");
        if(nv!=null){
            ten.setText(nv.getHoTen());
            ma.setText(nv.getID());
            vp.setText(nv.getPhongban());
        }

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= getIntent();
                String a = ten.getText().toString();
                String b = ma.getText().toString();
                String c = vp.getText().toString();

                Nhanvien_modle nv = new Nhanvien_modle(b,a,c);
                intent.putExtra(NhanVien.KEY_DATA,nv);
                setResult(1,intent);
                finish();
            }
        });
    }
}
