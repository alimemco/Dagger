package com.alirnp.dagger.di.component;


import com.alirnp.dagger.ApiService;
import com.alirnp.dagger.MainActivity;
import com.alirnp.dagger.di.module.GitHubModule;
import com.alirnp.dagger.di.scopes.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = GitHubModule.class)
public interface GitHubComponent {
    void inject(MainActivity activity);
}
