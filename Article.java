package com.example.android.newsappstage1;



public class Article {
    //the title of the article/
    private String mTitle;
    //the name of the section/
    private String mSection;
    //the date of the article/
    private String mDate;
    //the author of the article/
    private String mAuthor;
    //the url of the article/
    private String mUrl;


    /**construct a new (@link Article} object .
     *
     * @param title is the title of the article.
     *
     * @param section is the name of the section of the article.
     *
     * @param date is the date of the article.
     *
     * @param author is the author of the article.
     *
     * @param url is the url of the article.*/



    public Article(String title, String section, String date, String author, String url) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mAuthor = author;
        mUrl = url;
    }

    public Article(String title, String section, String date, String author) {
    }

    //returns the title of the article/
    public String getTitle() {return mTitle;}

    //returns the section of the article/
    public String getSection() {return mSection;}

    //returns the date of the article/
    public String getDate() {return mDate;}

    //returns the author of the article/
    public String getAuthor() {return mAuthor;}

    public String getUrl() {return mUrl;}


}


