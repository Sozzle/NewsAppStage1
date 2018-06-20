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

    /**construct a new (@link Article} object .
     *
     * @param title is the title of the article.
     *
     * @param section is the name of the section of the article.
     *
     * @param date is the date of the article.
     *
     * @param author is the author of the article.
     */


    public Article(String title, String section, String date, String author) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mAuthor = author;
    }

    //returns the title of the article/
    public String getTitle() {return mTitle;}

    //returns the section of the article/
    public String getSection() {return mSection;}

    //returns the date of the article/
    public String getDate() {return mDate;}

    //returns the author of the article/
    public String getAuthor() {return mAuthor;}

}


