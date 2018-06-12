package com.example.islam.articles.articles;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.islam.articles.R;
import com.example.islam.articles.custom.EndlessRecyclerOnScrollListener;
import com.example.islam.articles.data.models.ArticlesItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by islam on 12/06/18.
 */

public class ArticlesFragment extends Fragment
        implements ArticlesContract.View {
    @BindView(R.id.rv_articles)
    RecyclerView mArticlesRecyclerView;
    @BindView(R.id.progressBar_loading)
    ProgressBar mLoadingProgressBar;
    private ArticlesContract.Presenter mPresenter;
    int mCurrentPage = 0;
    private ArticlesAdapter mArticlesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_articles, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mPresenter.getArticlesPerPageNumber(++mCurrentPage);
        return root;
    }


    @Override
    public void setPresenter(ArticlesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void displayArticlesPerPage(List<ArticlesItem> articlesItems) {
        if(mArticlesAdapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            mArticlesRecyclerView.setLayoutManager(linearLayoutManager);
            mArticlesAdapter = new ArticlesAdapter(getActivity(), (ArrayList<ArticlesItem>) articlesItems);
            mArticlesRecyclerView.setAdapter(mArticlesAdapter);
            mArticlesRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int current_page) {
                    if (mPresenter.isThereMoreArticles()) {
                        showLoading();
                        mPresenter.getArticlesPerPageNumber(++mCurrentPage);
                    }
                }
            });
        }
        mArticlesAdapter.addMoreArticles(articlesItems);
        mArticlesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Snackbar.make(mArticlesRecyclerView, errorMsg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        mLoadingProgressBar.setVisibility(View.GONE);
    }

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    public void onDestroy() {
        mPresenter.unSubscribe();
        super.onDestroy();
    }
}

