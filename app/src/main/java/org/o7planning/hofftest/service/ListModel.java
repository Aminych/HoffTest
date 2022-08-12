package org.o7planning.hofftest.service;

import android.util.Log;

import org.o7planning.hofftest.contract.ListContract;
import org.o7planning.hofftest.model.Constructor;
import org.o7planning.hofftest.model.Models;
import org.o7planning.hofftest.network.ApiClient;
import org.o7planning.hofftest.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListModel implements ListContract.Model {

    private final String TAG = "ListModel";
    private final int pageNo = 1;

    @Override
    public void getConstructorList(onFinishedCall onFinishedCall, int pageNo) {

        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<List<Models>> call = apiService.getCatalog();
//                "3a7612bcc84813b5", "true", "1.8.16", "19", "36f40b3d665cdb9435904796172dde5e3f13aa8a%3A4630");

        call.enqueue(new Callback<List<Models>>() {

            @Override
            public void onResponse(Call<List<Models>> call, Response<List<Models>> response) {
                ArrayList<Models> constructors = (ArrayList<Models>) response.body();
                Log.e(TAG, "Numbers of product received" + constructors.size());
                onFinishedCall.onResponse(constructors);
            }

            @Override
            public void onFailure(Call<List<Models>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedCall.onFailure(t);
            }
        });
    }
}