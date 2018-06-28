package com.example.android.newsappstage1;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;


public class ArticleLoader extends AsyncTaskLoader<List<Article>> {



            /**
             * Tag for log messages
             */
    private static final String LOG_TAG = ArticleLoader.class.getName();


    /**
     * Constructs a new {@link ArticleLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    private String mUrl = "";

    public ArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;

    }


    @Override

    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * * This is on a background thread.
     */
    @Override
    public List<Article> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Article> articles = (List<Article>) QueryUtils.fetchArticleData(mUrl);
        return articles;
    }

}




