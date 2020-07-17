package com.alirnp.dagger.di.module;


import com.alirnp.dagger.ApiService;
import com.alirnp.dagger.di.scopes.UserScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class GitHubModule {

    @Provides
    @UserScope
    public ApiService providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
