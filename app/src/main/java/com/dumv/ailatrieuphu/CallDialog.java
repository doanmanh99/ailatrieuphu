package com.dumv.ailatrieuphu;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;

public class CallDialog extends Dialog {
    CardView cardView1,cardView2,cardView3;
    public CallDialog(@NonNull Context context,String answer) {
        super(context);
        setContentView(R.layout.dialog_call);
        cardView1=findViewById(R.id.cardView1);
        cardView2=findViewById(R.id.cardView2);
        cardView3=findViewById(R.id.cardView3);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Trợ giúp từ BillGates");
                b.setMessage("Tôi chắc chắn với bạn đáp án đúng là đáp án " + answer);
                b.show();
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Trợ giúp từ Einstein Anhxtanh");
                b.setMessage("Tôi chắc chắn với bạn đáp án đúng là đáp án " + answer);
                b.show();
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Trợ giúp từ Mark Zuckerberg");
                b.setMessage("Tôi chắc chắn với bạn đáp án đúng là đáp án " + answer);
                b.show();
            }
        });
    }
}
