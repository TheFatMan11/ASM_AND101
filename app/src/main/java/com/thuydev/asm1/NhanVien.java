package com.thuydev.asm1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NhanVien extends AppCompatActivity {
    ArrayList<Nhanvien_modle> list = new ArrayList<>();
    AdpterNV adpterNV ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nv_layout);
        ListView lv_nv = findViewById(R.id.lv_list_nv);
        list.add(new Nhanvien_modle("mmm","thuy","maketing"));
        adpterNV = new AdpterNV(this,list);
        lv_nv.setAdapter(adpterNV);
    }

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

            Nhanvien_modle nhanvien_modle = list.get(position);

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

                }
            });

            return convertView;
        }
    }
}
