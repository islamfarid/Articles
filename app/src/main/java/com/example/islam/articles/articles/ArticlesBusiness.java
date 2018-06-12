package com.example.islam.articles.articles;

import android.util.SparseArray;

import com.example.islam.articles.app.Constants;
import com.example.islam.articles.data.ArticlesRepository;
import com.example.islam.articles.data.models.ArticlesItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by islam on 12/06/18.
 */

public class ArticlesBusiness {
    ArticlesRepository mArticlesRepository;
    HashMap<Integer, List<ArticlesItem>> mData;
    private boolean mShowMore = true;

    @Inject
    public ArticlesBusiness(ArticlesRepository articlesRepository) {
        mArticlesRepository = articlesRepository;
    }

    public Observable<List<ArticlesItem>> getArticlesPerPage(int pageNumber) {
      return Observable.create(emitter -> {
            Gson gson = new Gson();
            String jsonString = mArticlesRepository.getStringFromSharedPref(Constants.ARTICLES);
            if (jsonString != null) {
                Type type = new TypeToken<HashMap<Integer, List<ArticlesItem>>>() {
                }.getType();
                mData = gson.fromJson(mArticlesRepository.getStringFromSharedPref(Constants.ARTICLES), type);
                emitter.onNext(mData.get(pageNumber));
                emitter.onComplete();
            } else {
                if (mData == null) {
                    mData = new HashMap<>();
                }
                mArticlesRepository.getArticlesPerPage(pageNumber).map(ArticlesResponseModel -> {
                    for(ArticlesItem articlesItem : ArticlesResponseModel.getArticles()){
                        articlesItem.setDatePublished(getRightFormat(articlesItem.getDatePublished()));
                    }
                    mData.put(pageNumber, ArticlesResponseModel.getArticles());

                    if (ArticlesResponseModel.getArticles() == null || ArticlesResponseModel.getArticles().size() == 0) {
                        mShowMore = false;
                        mArticlesRepository.saveString(Constants.ARTICLES, gson.toJson(mData));
                    }
                    return ArticlesResponseModel.getArticles();
                }).subscribe(articlesItems -> {
                    emitter.onNext(articlesItems);
                    emitter.onComplete();
                });

            }
        });
    }

        public boolean isThereMoreArticles () {
            return mShowMore;
        }
        private String getRightFormat(String oldDateFormat){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            try {
                Date date = format.parse(oldDateFormat);
                format = new SimpleDateFormat("\"EEE, MMM d, ''yyyy\"\t");
                return format.format(date);
            } catch (ParseException e) {
               return oldDateFormat;
            }
        }
    }
