package com.thuydev.asm1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NhanVien extends AppCompatActivity {
    ArrayList<Nhanvien_modle> List = new ArrayList<>();
    AdpterNV adpterNV ;
    Nhanvien_modle nhanvien_modle;
    int changeData = 0;
    public static final String KEY_DATA = "data";
    int xoaNV = 0;

    ActivityResultLauncher getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==1){
                        Intent intent = result.getData();
                        Nhanvien_modle nhanVien = (Nhanvien_modle) intent.getSerializableExtra(KEY_DATA);
                        List.add(nhanVien);
                        ghi();
                        adpterNV.notifyDataSetChanged();
                    }
                }
            }
    );
public static final String NAME_DATA = "employee.txt";
public void ghi(){
    try {
        FileOutputStream fileOutputStream = openFileOutput(NAME_DATA,MODE_PRIVATE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(List);
        objectOutputStream.close();
        fileOutputStream.close();
    }catch (Exception e){

    }
}

public void doc(){
   try {
       FileInputStream fileInputStream = openFileInput(NAME_DATA);
       ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
       List = (ArrayList<Nhanvien_modle>) objectInputStream.readObject();
       objectInputStream.close();
       fileInputStream.close();
   }catch (Exception e){

   }

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nv_layout);
        Intent intent = getIntent();
        ListView lv_nv = findViewById(R.id.lv_list_nv);
        Button them = findViewById(R.id._add);
        Toolbar toolbar = findViewById(R.id.tb_toolbar1);
        String tenpb = "";
        tenpb = intent.getStringExtra(PhongBan.KEY_name);

        setSupportActionBar(toolbar);
       if(tenpb==null){
           getSupportActionBar().setTitle("Nhân viên");
       }else {
           getSupportActionBar().setTitle(tenpb);
       }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        doc();
        adpterNV = new AdpterNV(NhanVien.this, List);
        lv_nv.setAdapter(adpterNV);


        int pb = 0;

        pb=intent.getIntExtra(PhongBan.KEY_NS,0);

        if(pb==1){
            int i=0;
            ArrayList <Nhanvien_modle> ok = new ArrayList<>();
            for (Nhanvien_modle a : List){
                if(a.getPhongban().equals("Nhân sự")){
                    ok.add(a);
                    adpterNV = new AdpterNV(NhanVien.this,ok);
                    lv_nv.setAdapter(adpterNV);
                    i++;

                }
            }
        }else  if(pb==2){
            int i=0;
            ArrayList <Nhanvien_modle> ok = new ArrayList<>();
            for (Nhanvien_modle a : List){
                if(a.getPhongban().equals("Hành chính")){
                    ok.add(a);
                    adpterNV = new AdpterNV(NhanVien.this,ok);
                    lv_nv.setAdapter(adpterNV);
                    i++;

                }
            }
        }else   if(pb==3){
            int i=0;
            ArrayList <Nhanvien_modle> ok = new ArrayList<>();
            for (Nhanvien_modle a : List){
                if(a.getPhongban().equals("Đào tạo")){
                    ok.add(a);
                    adpterNV = new AdpterNV(NhanVien.this,ok);
                    lv_nv.setAdapter(adpterNV);
                    i++;

                }
            }
        }
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
                    List.set(changeData,nhanvien_modle);
                    ghi();
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

         Nhanvien_modle  nhanvien = list.get(position);

            ID.setText(nhanvien.getID());
            Name.setText(nhanvien.getHoTen());
            VanPhong.setText(nhanvien.getPhongban());

            xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    ghi();
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
                    intent.putExtra("list",nhanvien);
                    setResult(2,intent);
                    UpdateData.launch(intent);

                }
            });

            return convertView;
        }
    }
}
