package com.quircode.testcarso.Service;

import android.util.Log;

import com.quircode.testcarso.Contract.MovieListContract;
import com.quircode.testcarso.Model.Pelis;
import com.quircode.testcarso.Model.PelisListResponse;
import com.quircode.testcarso.Net.ApiNetClient;
import com.quircode.testcarso.Net.ApiNetInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelisListModel implements MovieListContract.Model {

    private final String TAG = "PelisListModel";
    private int PageNo = 1;

    @Override
    public void getPelisList(OnFinishListener onFinishListener, int pageNo) {
        ApiNetInterface apiService = ApiNetClient.getClient().create(ApiNetInterface.class);
        Call<PelisListResponse> call = apiService.getRecentMovies(ApiNetClient.API_KEY,pageNo);

        call.enqueue(new Callback<PelisListResponse>() {
            @Override
            public void onResponse(Call<PelisListResponse> call, Response<PelisListResponse> response) {
                List<Pelis> pelis = response.body().getResults();
                Log.e(TAG,"Numero de peliculas recibidas: "+pelis.size());
                onFinishListener.onFinish(pelis);
            }

            @Override
            public void onFailure(Call<PelisListResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
                onFinishListener.onFailure(t);
            }
        });
    }
}
