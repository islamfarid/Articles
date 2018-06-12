package com.example.islam.articles.data.remote;


import com.example.islam.articles.data.models.ArticlesResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by islam on 12/06/18.
 */

public interface ArticlesDataSource {

    @GET("articles")
    Observable<ArticlesResponseModel> getArticlesPerPage(@Query("page") int pageNumber);
}
