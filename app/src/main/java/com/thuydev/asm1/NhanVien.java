package com.thuydev.asm1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NhanVien extends AppCompatActivity {
    ArrayList<Nhanvien_modle> list = new ArrayList<>();
    AdpterNV adpterNV ;
    Nhanvien_modle nhanvien_modle;
    int changeData = 0;
    public static final String KEY_DATA = "data";

    ActivityResultLauncher getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==1){
                        Intent intent = result.getData();
                        Nhanvien_modle nhanVien = (Nhanvien_modle) intent.getSerializableExtra(KEY_DATA);
                        list.add(nhanVien);
                        adpterNV.notifyDataSetChanged();
                    }
                }
            }
    );

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nv_layout);
        ListView lv_nv = findViewById(R.id.lv_list_nv);
        Button them = findViewById(R.id._add);
        list.add(new Nhanvien_modle("mmm","thuy","maketing"));
        adpterNV = new AdpterNV(this,list);
        lv_nv.setAdapter(adpterNV);

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVien.this,ADD_NV.class);
                intent.putExtra("text","Thêm nhân viên");
                setResult(2,intent);
                getData.launch(intent);
            }
        });
    }
ActivityResultLauncher UpdateData = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==1){
                    Intent intent =result.getData();
                    nhanvien_modle = (Nhanvien_modle) intent.getSerializableExtra(KEY_DATA);
                    list.set(changeData,nhanvien_modle);
                    adpterNV.notifyDataSetChanged();
                }
            }
        }
);
    public class AdpterNV extends BaseAdapter {
        Activity activity;
        ArrayList<Nhanvien_modle> list = new ArrayList<>();

        public AdpterNV(Activity activity, ArrayList<Nhanvien_modle> list) {
            this.activity = activity;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater =activity.getLayoutInflater();
           convertView =  inflater.inflate(R.layout.nv_modle,parent,false);
            TextView ID = convertView.findViewById(R.id.tv_ma_nv);
            TextView Name = convertView.findViewById(R.id.tv_ten_nv);
            TextView VanPhong = convertView.findViewById(R.id.tv_vanphong_nv);
            ImageButton xoa = convertView.findViewById(R.id.btn_xoa);
            ImageButton Update = convertView.findViewById(R.id.btn_edit);

           nhanvien_modle = list.get(position);

            ID.setText(nhanvien_modle.getID());
            Name.setText(nhanvien_modle.getHoTen());
            VanPhong.setText(nhanvien_modle.getPhongban());

            xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(activity, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
                }
            });

            Update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeData=position;

                    Intent intent = new Intent(NhanVien.this,ADD_NV.class);
                    intent.putExtra("text","Sửa nhân viên");
                    intent.putExtra("list",nhanvien_modle);
                    setResult(2,intent);
                    UpdateData.launch(intent);

                }
            });

            return convertView;
        }
    }
}
