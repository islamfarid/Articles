package com.example.islam.articles.app;

import android.app.Application;

import com.example.islam.articles.data.ArticlesRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by islam on 12/06/18.
 */

@Singleton
@Component(modules = { AppModule.class})
public interface AppComponent {

    void inject(ArticlesApp app);
    ArticlesRepository getArticlesRepository();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
