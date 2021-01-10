package com.dumv.ailatrieuphu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dumv.ailatrieuphu.adapter.AnswerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity implements AnswerAdapter.OnItemClick {
    ArrayList<Result> cauHoi;
    int viTriCauHoi = 0;
    TextView txvCauHoi;
    RecyclerView recyclerView;
    AnswerAdapter adapter;
    public List<String> list;
    Boolean kt;
    int soCauDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        kt = false;
        soCauDung = 0;
        anhXa();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Call<CauHoi> call = myAPICall.getData();
        call.enqueue(new Callback<CauHoi>() {
            @Override
            public void onResponse(Call<CauHoi> call, Response<CauHoi> response) {
                if (response.code() == 200) {
                    cauHoi = (ArrayList<Result>) response.body().getResults();
                    hienCauHoi();
                } else {
                    Log.d("AAAA", "lỗi");
                }

            }

            @Override
            public void onFailure(Call<CauHoi> call, Throwable t) {
                Log.d("AAAA", "lỗi ko tra ve gi");
            }
        });
    }

    public void anhXa() {
        recyclerView = findViewById(R.id.recyclerView);
        txvCauHoi = findViewById(R.id.txvCauHoi);
    }

    public void hienCauHoi() {
        kt = false;
        txvCauHoi.setText(cauHoi.get(viTriCauHoi).getQuestion());
        list = new ArrayList<>();
        list = (ArrayList<String>) cauHoi.get(viTriCauHoi).getIncorrectAnswers();
        list.add(cauHoi.get(viTriCauHoi).getCorrectAnswer());
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int vt1 = r.nextInt(list.size());
            int vt2 = r.nextInt(list.size());
            String a = list.get(vt1);// 0 1 2 3
            list.set(vt1, list.get(vt2));
            list.set(vt2, a);
        }
        adapter = new AnswerAdapter(list, getApplicationContext());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    boolean troGiup5050 = true;

    public void trogiup5050(View view) {
        if (!troGiup5050) {
            return;
        }
        Random r = new Random();
        int sodanAnAnDi = list.size() / 2;
        do {
            int vitriDanAnAn = r.nextInt(list.size());// 1
            String t = list.get(vitriDanAnAn);
            if (!t.equals(cauHoi.get(viTriCauHoi).getCorrectAnswer())) {
                list.remove(vitriDanAnAn);
                sodanAnAnDi--;
            }
        } while (sodanAnAnDi > 0);
        adapter.notifyDataSetChanged();
        troGiup5050 = false;
    }

    boolean troGiupKhanGia = true;

    public void troGiupKhanGia(View view) {
        if (!troGiupKhanGia) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            String t = list.get(i);
            if (t.equals(cauHoi.get(viTriCauHoi).getCorrectAnswer())) {
                new DialogKhanGiaTraLoi(this, i + 1, list.size()).show();
                if (viTriCauHoi == 0)
                    break;
            }
        }
        troGiupKhanGia = false;
    }

    boolean troGiupDoiCauHoi = true;

    public void trogiupDoiCauHoi(View view) {
        if (!troGiupDoiCauHoi) {
            return;
        }
        viTriCauHoi++;
        hienCauHoi();
        troGiupDoiCauHoi = false;
    }

    boolean troGiupGoiDien = true;

    public void call(View view) {
        if (!troGiupGoiDien) {
            return;
        }
        String answer;
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    answer = "A";
                    break;
                case 1:
                    answer = "B";
                    break;
                case 2:
                    answer = "C";
                    break;
                default:
                    answer = "D";
                    break;
            }

            String t = list.get(i);
            if (t.equals(cauHoi.get(viTriCauHoi).getCorrectAnswer())) {
                new CallDialog(this, answer).show();

            }
        }
        troGiupGoiDien = false;
    }

    @Override
    public void onItemClick(int position, TextView view) {

        if (!kt) {
            kt = true;
            view.setBackgroundResource(R.drawable.bg_cau_chon);
            String s = list.get(position).toString();
            new CountDownTimer(2000, 100) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    if (s.equals(cauHoi.get(viTriCauHoi).getCorrectAnswer())) {
                        view.setBackgroundResource(R.drawable.bg_cau_dung);
                        PlayTrue playTrue = new PlayTrue();
                        playTrue.execute();
                        soCauDung++;
                    }

                    new CountDownTimer(2000, 100) {
                        @Override
                        public void onTick(long l) {
                        }

                        @Override
                        public void onFinish() {
                            if (s.equals(cauHoi.get(viTriCauHoi).getCorrectAnswer())) {
                                viTriCauHoi++;
                                if (soCauDung == 15) {
                                    Intent intent = new Intent(Main2Activity.this, TienThuongActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("socautraloidung", soCauDung);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                                hienCauHoi();
                            } else {
                                PlayFalse playFalse = new PlayFalse();
                                playFalse.execute();
                                Intent intent = new Intent(Main2Activity.this, TienThuongActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("socautraloidung", soCauDung);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }
                    }.start();
                }
            }.start();
        }
    }

    public class PlayTrue extends AsyncTask<Void, Void, Void> {
        MediaPlayer mediaPlayer;

        @Override
        protected Void doInBackground(Void... voids) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.caudung);
            mediaPlayer.start();
            return null;
        }
    }

    public class PlayFalse extends AsyncTask<Void, Void, Void> {
        MediaPlayer mediaPlayer;

        @Override
        protected Void doInBackground(Void... voids) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.causai);
            mediaPlayer.start();
            return null;
        }
    }
}


