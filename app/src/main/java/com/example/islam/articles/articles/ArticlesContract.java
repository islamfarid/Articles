package com.example.islam.articles.articles;


import com.example.islam.articles.app.BasePresenter;
import com.example.islam.articles.app.BaseView;
import com.example.islam.articles.data.models.ArticlesItem;

import java.util.List;

/**
 * Created by islam on 12/06/18.
 */

public interface ArticlesContract {
    interface View extends BaseView<Presenter> {
        void displayArticlesPerPage(List<ArticlesItem> realEstates);

        void showLoading();

        void showErrorMessage(String errorMsg);

        void hideLoading();
    }

    interface Presenter extends BasePresenter {
        void getArticlesPerPageNumber(int pageNumber);
        boolean isThereMoreArticles();
    }
}
