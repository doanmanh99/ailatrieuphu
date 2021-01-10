package com.dumv.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.Random;

public class DialogKhanGiaTraLoi extends Dialog {
    public DialogKhanGiaTraLoi(Context context,int vtD,int size) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_tro_giup_khan_gia);
        TextView txvChonA,txvChonB,txvChonC,txvChonD;
        txvChonA = findViewById(R.id.txvChonA);
        txvChonB = findViewById(R.id.txvChonB);
        txvChonC = findViewById(R.id.txvChonC);
        txvChonD = findViewById(R.id.txvChonD);
        if (size==2){
            txvChonC.setVisibility(View.GONE);
            txvChonD.setVisibility(View.GONE);
            int a=25,b=25;
            if (vtD==1) {
                a=new Random().nextInt(5)+70;
                b=100-a;
            } else {
                b=new Random().nextInt(5)+70;
                a=100-b;
            }
            txvChonA.setText("A : "+a+" %");
            txvChonB.setText("B : "+b+" %");
        }else {
            int a = 0,b = 0,c = 0,d = 0;
            if (vtD==1) {
                a=new Random().nextInt(5)+70;
                b=new Random().nextInt(100-a);
                c=new Random().nextInt(100-a-b);
                d=100-a-b-c;
            }
            if (vtD==2) {
                b=new Random().nextInt(5)+70;
                a=new Random().nextInt(100-b);
                c=new Random().nextInt(100-b-a);
                d=100-a-b-c;
            }
            if (vtD==3) {
                c=new Random().nextInt(5)+70;
                b=new Random().nextInt(100-c);
                a=new Random().nextInt(100-c-b);
                d=100-a-b-c;
            }
            if (vtD==4) {
                d=new Random().nextInt(5)+70;
                b=new Random().nextInt(100-d);
                c=new Random().nextInt(100-d-b);
                a=100-d-b-c;
            }
            txvChonA.setText("A : "+a+" %");
            txvChonB.setText("B : "+b+" %");
            txvChonC.setText("C : "+c+" %");
            txvChonD.setText("D : "+d+" %");
        }


    }
}
