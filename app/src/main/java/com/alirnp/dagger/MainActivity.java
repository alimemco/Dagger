package com.alirnp.dagger;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alirnp.dagger.model.Repository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    SharedPreferences mSharedPreferences;

    @Inject
    Retrofit mRetrofit;

    @Inject
    ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String BASE_URL = "https://api.github.com/";
        String username = "alipapital";

        ((MyApp) getApplication()).getGitHubComponent().inject(this);

        mApiService.getRepositories(username).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    initRecyclerView(response.body());


                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        /*

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        apiService.getRepositories(username).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repository>> call, @NonNull Response<List<Repository>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    initRecyclerView(response.body());


                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<Repository>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
*/

    }

    private void initRecyclerView(List<Repository> responseList) {
        RecyclerView recyclerView = findViewById(R.id.mainActivity_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RepositoryAdapter(responseList));
    }
}