package com.example.islam.articles.articles.dagger;

import com.example.islam.articles.articles.ArticlesContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 12/06/18.
 */

@Module
public class ArticlesModule {
    private final ArticlesContract.View mView;

    public ArticlesModule(ArticlesContract.View view) {
        this.mView = view;
    }

    @Provides
    ArticlesContract.View provideArticlesView() {
        return mView;
    }

}

