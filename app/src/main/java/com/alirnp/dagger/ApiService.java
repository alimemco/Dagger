package com.alirnp.dagger;

import com.alirnp.dagger.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{user}/repos")
    Call<List<Repository>> getRepositories(@Path("user") String username);
}
