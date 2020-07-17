package com.alirnp.dagger;


import android.app.Application;

import com.alirnp.dagger.di.component.DaggerGitHubComponent;
import com.alirnp.dagger.di.component.DaggerNetComponent;
import com.alirnp.dagger.di.component.GitHubComponent;
import com.alirnp.dagger.di.component.NetComponent;
import com.alirnp.dagger.di.module.AppModule;
import com.alirnp.dagger.di.module.GitHubModule;
import com.alirnp.dagger.di.module.NetModule;

public class MyApp extends Application {

    private NetComponent mNetComponent;
    private GitHubComponent mGitHubComponent;

    public MyApp() {
    }

    @Override
    public void onCreate() {
        super.onCreate();


        // specify the full namespace of the component
        // Dagger_xxxx (where xxxx = component name)
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://api.github.com"))
                .build();

        mGitHubComponent = DaggerGitHubComponent.builder()
                .netComponent(mNetComponent)
                .gitHubModule(new GitHubModule())
                .build();


    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public GitHubComponent getGitHubComponent() {
        return mGitHubComponent;
    }
}