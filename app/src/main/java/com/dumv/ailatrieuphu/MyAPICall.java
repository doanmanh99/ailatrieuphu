package com.dumv.ailatrieuphu;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPICall {

    //https://opentdb.com/api.php?amount=15
    @GET("api.php?amount=16")
    Call<CauHoi> getData();
}
