package com.thuydev.asm1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ADD_NV extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_nv);
        EditText ten = findViewById(R.id.et_ten);
        EditText ma = findViewById(R.id.et_ma);
        Spinner vp = findViewById(R.id.et_vp);
        Button them = findViewById(R.id.btn_luu);
        TextView tille = findViewById(R.id.title);
        String [] list =new String[]{
                "Nhân sự","Hành chính","Đào tạo"
        };
        ArrayAdapter <String> adapter =new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,list);
        vp.setAdapter(adapter);
        Intent intent = getIntent();
        tille.setText(""+intent.getStringExtra("text"));
        Nhanvien_modle  nv = (Nhanvien_modle) getIntent().getSerializableExtra("list");
        if(nv!=null){
            int i;
            ten.setText(nv.getHoTen());
            ma.setText(nv.getID());
            if(nv.getPhongban().equals("Nhân sự")){
                i=0;
            } else if (nv.getPhongban().equals("Hành chính")) {
                i=1;
            }else {
                i=2;
            }
            vp.setSelection(i);
        }


        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= getIntent();
                String a = ten.getText().toString();
                String b = ma.getText().toString();
                String c ="";

                if(vp.getSelectedItemPosition()==0){
                    c="Nhân sự";
                } else if (vp.getSelectedItemPosition()==1) {
                    c="Hành chính";
                }else {
                    c="Đào tạo";
                }

                Nhanvien_modle nv = new Nhanvien_modle(b,a, c);
                intent.putExtra(NhanVien.KEY_DATA,nv);
                setResult(1,intent);
                finish();


            }
        });
    }
}
