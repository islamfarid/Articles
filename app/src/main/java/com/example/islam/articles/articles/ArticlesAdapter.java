package com.example.islam.articles.articles;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.islam.articles.R;
import com.example.islam.articles.custom.ResizableCustomView;
import com.example.islam.articles.data.models.ArticlesItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by islam on 12/6/18.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {
    Context context;
    private ArrayList<ArticlesItem> mArticlesItems;

    public ArticlesAdapter(Context context, ArrayList<ArticlesItem> articlesItems) {
        this.mArticlesItems = articlesItems;
        this.context = context;
    }


    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.autherName.setText(mArticlesItems.get(position).getAuthor().getName());
        holder.date.setText(mArticlesItems.get(position).getDatePublished());
        holder.description.setText(mArticlesItems.get(position).getDescription());
        holder.title.setText(mArticlesItems.get(position).getTitle());
        ResizableCustomView.doResizeTextView(holder.description, 3, "View More", true);
        Glide.with(context)
                .load(mArticlesItems.get(position).getImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.articleImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.articleImage)
        ;
        Glide.with(context)
                .load(mArticlesItems.get(position).getAuthor().getAvatar())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                .into( holder.autherImage);
    }

    @Override
    public int getItemCount() {
        return mArticlesItems.size();
    }

    public void addMoreArticles(List<ArticlesItem> articlesItems) {
        mArticlesItems.addAll(articlesItems);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_auther_name)
        TextView autherName;
        @BindView(R.id.tv_date)
        TextView date;
        @BindView(R.id.tv_article_desc)
        TextView description;
        @BindView(R.id.tv_article_title)
        TextView title;
        @BindView(R.id.iv_article)
        ImageView articleImage;
        @BindView(R.id.profile_image)
        CircleImageView autherImage;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
