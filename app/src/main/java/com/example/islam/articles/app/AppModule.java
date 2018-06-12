package com.example.islam.articles.app;

import android.app.Application;
import android.content.Context;

import com.example.islam.articles.data.ArticlesRepository;
import com.example.islam.articles.data.local.Local;
import com.example.islam.articles.data.local.SharedPreferenceUtils;
import com.example.islam.articles.data.remote.ArticlesDataSource;
import com.example.islam.articles.data.remote.Remote;
import com.example.islam.articles.data.remote.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 12/06/18.
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }


    @Provides
    @Singleton
    @Local
    SharedPreferenceUtils providePreferencesHelper(Context context) {
        return new SharedPreferenceUtils(context);
    }

    @Singleton
    @Provides
    @Remote
    ArticlesDataSource provideApiProvider() {
        return ServiceGenerator.createService(ArticlesDataSource.class);
    }
}
