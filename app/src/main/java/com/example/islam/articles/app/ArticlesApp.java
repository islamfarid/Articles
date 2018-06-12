package com.example.islam.articles.app;

import android.app.Application;

/**
 * Created by islam on 12/06/18.
 */

public class ArticlesApp extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
