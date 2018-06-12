package com.example.islam.articles.articles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.islam.articles.R;
import com.example.islam.articles.app.AppComponent;
import com.example.islam.articles.app.ArticlesApp;
import com.example.islam.articles.articles.dagger.ArticlesModule;
//import com.example.islam.articles.articles.dagger.DaggerArticlesComponent;
import com.example.islam.articles.articles.dagger.DaggerArticlesComponent;
import com.example.islam.articles.utils.ActivityUtils;

import javax.inject.Inject;

public class ArticlesActivity extends AppCompatActivity {
    @Inject
    ArticlesPresenter articlesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        ArticlesFragment articlesFragment =
                (ArticlesFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (articlesFragment == null) {
            // Create the fragment
            articlesFragment = ArticlesFragment.newInstance();
            articlesFragment.setArguments(getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    getFragmentManager(), articlesFragment, R.id.contentFrame);
        }
        DaggerArticlesComponent.builder().
                appComponent(((ArticlesApp) getApplication()).
                        getmAppComponent()).articlesModule(new ArticlesModule(articlesFragment)).build().inject(this);
    }
}
