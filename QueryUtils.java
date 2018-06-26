package com.example.android.newsappstage1;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();



    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {



        }



    /**
     * Return a list of {@link Article} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Article> extractArticles() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Article> articles = new ArrayList<>();


        return articles;
    }



    // Query the GUARDIAN dataset and return an {@link Article} object to represent a single article.

        public static Article fetchArticleData (String requestUrl){
            // Create URL object
            URL url = createUrl(requestUrl);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = null;
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error closing input stream", e);
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            Article article = extractArticleFromJson(jsonResponse);

            // Return the {@link Article}
            return article;
        }




    /**
     * Returns a formatted date and time string for the article.
     */
    private String getDateString(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }
        /**
         * Returns new URL object from the given string URL.
         */
        private static URL createUrl (String stringUrl){
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Error with creating URL ", e);
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private static String makeHttpRequest (URL url) throws IOException {
            String jsonResponse = "";

            // If the URL is null, then return early.
            if (url == null) {
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the article JSON results.", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private static String readFromStream (InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return an {@link Article} object by parsing out information
         * about the first earthquake from the input earthquakeJSON string.
         */
        private static Article extractArticleFromJson (String articleJSON){
            // If the JSON string is empty or null, then return early.
            if (TextUtils.isEmpty(articleJSON)) {
                return null;
            }

            try {
                JSONObject baseJsonResponse = new JSONObject(articleJSON);
                JSONArray articleArray = baseJsonResponse.getJSONArray("articles");

                // If there are results in the features array
                if (articleArray.length() > 0) {
                    // Extract out the first feature (which is an earthquake)
                    JSONObject firstArticle = articleArray.getJSONObject(0);
                    JSONObject results = firstArticle.getJSONObject("results");

                    // Extract out the title, number of people, and perceived strength values
                    String title = results.getString("title");
                    String section = results.getString("section");
                    String date = results.getString("date");
                    String author = results.getString("author");


                    // Create a new {@link Event} object
                    return new Article(title, section, date, author);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the article JSON results", e);
            }
            return null;
        }





    }
