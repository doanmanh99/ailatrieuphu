package com.dumv.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TienThuongActivity extends AppCompatActivity {
    TextView txvThuaGame;
    Button btnTiepTuc;
    List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tien_thuong);
        txvThuaGame=findViewById(R.id.txvThuaGame);
        btnTiepTuc=findViewById(R.id.btnTiepTuc);
        list=new ArrayList();
        list.add("50000");
        list.add("100000");
        list.add("200000");
        list.add("300000");
        list.add("400000");
        list.add("500000");
        list.add("600000");
        list.add("700000");
        list.add("800000");
        list.add("900000");
        list.add("1000000");
        list.add("1100000");
        list.add("1200000");
        list.add("1300000");
        list.add("1400000");
        list.add("1500000");
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        int socaudung=bundle.getInt("socautraloidung");
        txvThuaGame.setText("Bạn đã trả lời đúng "+socaudung+"/15 và nhận đc "+list.get(socaudung)+" đồng");
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TienThuongActivity.this,Main2Activity.class));
            }
        });
    }
}