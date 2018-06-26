package com.example.android.newsappstage1;

import android.content.Context;
import android.content.AsyncTaskLoader;


public class ArticleLoader extends AsyncTaskLoader<Void> {


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


    public ArticleLoader(Context context, String url) {
        super(context);

    }



    /**
     * Query URL
     */


    @Override

    protected void onStartLoading() {
        forceLoad();
    }


    /**
     * * This is on a background thread.
     */

    @Override
    public Void loadInBackground() {
        return null;
    }
}


