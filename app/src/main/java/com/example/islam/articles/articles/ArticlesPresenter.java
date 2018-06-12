package com.example.islam.articles.articles;

import com.example.islam.articles.utils.EspressoIdlingResource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by islam on 12/06/18.
 */

public class ArticlesPresenter implements ArticlesContract.Presenter {

    private ArticlesContract.View mView;
    private ArticlesBusiness mArticlesBusiness;
    private CompositeDisposable mSubscriptions;

    @Inject
    public ArticlesPresenter(ArticlesContract.View view) {
        this.mView = view;
        mSubscriptions = new CompositeDisposable();
    }

    @Inject
    public void setArticlesBusiness(ArticlesBusiness articlesBusiness) {
        this.mArticlesBusiness = articlesBusiness;
    }

    @Inject
    public void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void unSubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void getArticlesPerPageNumber(int pageNumber) {
        mView.showLoading();
        EspressoIdlingResource.increment();
        mSubscriptions.add(mArticlesBusiness.getArticlesPerPage(pageNumber).observeOn(AndroidSchedulers.
                mainThread()).subscribeOn(Schedulers.io()).doOnTerminate(() -> {
            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement(); // Set app as idle.
            }
        }).subscribe((articles) -> {
            mView.hideLoading();
            mView.displayArticlesPerPage(articles);
        }, throwable -> {
            mView.hideLoading();
            mView.showErrorMessage(throwable.getMessage());
        }));
    }

    @Override
    public boolean isThereMoreArticles() {
        return mArticlesBusiness.isThereMoreArticles();
    }
}
