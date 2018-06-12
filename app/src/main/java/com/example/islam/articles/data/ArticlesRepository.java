package com.example.islam.articles.data;


import com.example.islam.articles.data.local.Local;
import com.example.islam.articles.data.local.SharedPreferenceUtils;
import com.example.islam.articles.data.models.ArticlesResponseModel;
import com.example.islam.articles.data.remote.ArticlesDataSource;
import com.example.islam.articles.data.remote.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class ArticlesRepository {

    private final ArticlesDataSource mArticlesDataSource;
    private final SharedPreferenceUtils mSharedPreferenceUtils;

    @Inject
    public ArticlesRepository(@Remote ArticlesDataSource articlesDataSource, @Local SharedPreferenceUtils sharedPreferenceUtils) {
        this.mArticlesDataSource = articlesDataSource;
        this.mSharedPreferenceUtils = sharedPreferenceUtils;
    }

    public Observable<ArticlesResponseModel> getArticlesPerPage(int pageNumber) {
        return mArticlesDataSource.getArticlesPerPage(pageNumber);
    }

    public String getStringFromSharedPref(String key) {
        return mSharedPreferenceUtils.getStringValue(key, null);
    }
    public void saveString(String key , String value) {
         mSharedPreferenceUtils.setValue(key, value);
    }
}
