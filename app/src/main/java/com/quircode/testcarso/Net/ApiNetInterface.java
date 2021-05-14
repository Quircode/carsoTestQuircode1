package com.quircode.testcarso.Net;

import com.quircode.testcarso.Model.PelisListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiNetInterface {
    @GET ("movie/popular")
    Call<PelisListResponse> getRecentMovies (@Query("api_key") String api_key, @Query("page") int pageNo);

}
