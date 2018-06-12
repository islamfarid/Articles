package com.example.islam.articles.articles.dagger;

import com.example.islam.articles.app.AppComponent;
import com.example.islam.articles.articles.ArticlesActivity;
import com.example.islam.articles.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by islam on 12/06/18.
 */

@FragmentScoped
@Component(dependencies = AppComponent.class,
        modules = ArticlesModule.class)
public interface ArticlesComponent {
    void inject(ArticlesActivity articlesActivity);
}
