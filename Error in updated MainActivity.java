package com.example.android.newsappstage1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {



         /**

  * Constant value for the news loader ID. We can choose any integer.

  * This really only comes into play if you're using multiple loaders.

  */

         private static final int ARTICLE_LOADER_ID = 1;



        /** Adapter for the list of new */

         private ArticleAdapter madapter;



         /** URL for news data from the Guardian API */



    /** Tag for the log messages */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /** URL to query the Guardian dataset for art and crafts information */
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?q=Art%20and%20Crafts&api-key=f5c30d8e-0b2b-4486-afd6-e1583d0520f1";

     /** TextView that is displayed when the list is empty */

             private TextView mEmptyStateTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         // Find a reference to the {@link ListView} in the layout

                 ListView articleListView = (ListView) findViewById(R.id.list);



                 // Create a new adapter that takes an empty list of news as input

                         madapter = new ArticleAdapter(this, new ArrayList<Article>());



                 // Set the adapter on the {@link ListView}

                         // so the list can be populated in the user interface

                                 listView.setAdapter(madapter);



                 mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

         listView.setEmptyView(mEmptyStateTextView); // Set an item click listener on the ListView, which sends an intent to a web browser

                 // to open a website with more information about the selected news.

                         articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

 @Override

 public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                 // Find the current news that was clicked on

                                         Article currentArticle = madapter.getItem(position);



                                         // Convert the String URL into a URI object (to pass into the Intent constructor)

                                                 Uri newsUri = Uri.parse(currentArticle.getUrl());



                                         // Create a new intent to view the news URI

                                                 Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);



                                         // Send the intent to launch a new activity

                                                 startActivity(websiteIntent);

                                 }

 });



                 // Get a reference to the ConnectivityManager to check state of network connectivity

                         ConnectivityManager connMgr = (ConnectivityManager)

                 getSystemService(Context.CONNECTIVITY_SERVICE);


                 // Get details on the currently active default data network

                         NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();



                 // If there is a network connection, fetch data

                         if (networkInfo != null && networkInfo.isConnected()) {

             // Get a reference to the LoaderManager, in order to interact with loaders.

                     LoaderManager loaderManager = getLoaderManager();



                     // Initialize the loader. Pass in the int ID constant defined above and pass in null for

                             // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid

                                     // because this activity implements the LoaderCallbacks interface).

                                             loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);

             } else {

             // Otherwise, display error

                     // First, hide loading indicator so error message will be visible

                             View loadingIndicator = findViewById(R.id.loading_indicator);

             loadingIndicator.setVisibility(View.GONE);

             // Update empty state with no connection error message

                     mEmptyStateTextView.setText(R.string.no_internet_connection);

             }

         }



         @Override

 public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {

         // Create a new loader for the given URL

                return new ArticleLoader(this, GUARDIAN_REQUEST_URL);

         }



         @Override

 public void onLoadFinished(Loader<List<Article>> loader, List<Article> allArticles) {

         // Hide loading indicator because the data has been loaded

                 View loadingIndicator = findViewById(R.id.loading_indicator);

         loadingIndicator.setVisibility(View.GONE);



                 // Set empty state text to display "No articles found."

                         mEmptyStateTextView.setText(R.string.no_articles);



                 // Clear the adapter of previous article data

                         madapter.clear();



                 // If there is a valid list of {@link Article}, then add them to the adapter's

                         // data set. This will trigger the ListView to update.

                                 if (allArticles != null && !allArticles.isEmpty()) {

             madapter.addAll(allArticles);

             }

         }



         @Override

 public void onLoaderReset(Loader<List<Article>> loader) {

         // Loader reset, so we can clear out our existing data.

                 madapter.clear();

         }


    //Get the list of articles from {@link QueryUtils}
    ArrayList<Article> articles = QueryUtils.extractArticles();
    // Create an {@link ArticleAdapter}, whose data source is a list of
    // {@link articles}. The adapter knows how to create list item views for each item
    // in the list.
    // Get a reference to the ListView, and attach the adapter to the listView.
    ListView listView = (ListView) findViewById(R.id.list);
    listView.setAdapter(adapter);


}
