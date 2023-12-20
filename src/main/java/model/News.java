package model;

import java.io.Serializable;

public class News implements Serializable {
    private int id;
    private String author = "Author name";
    private String title = "Title";
    private String date = "Date";
    private String fullText = "Ora ora ora ora";
    private String annotation = "JoJo reference?";

    private boolean isFullTextShow;


    public News(String author, String title, String date, String fullText, String annotation, boolean isFullTextShow) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.fullText = fullText;
        this.annotation = annotation;
        this.isFullTextShow = isFullTextShow;
    }

    public News(int id) {
        this.id = id;
        isFullTextShow = false;
    }


    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public boolean isFullTextShow() {
        System.out.println("Nazhat!");
        return isFullTextShow;
    }

    public void setFullTextShow(boolean fullTextShow) {
        isFullTextShow = fullTextShow;

    }

    public String showFullText() {
        if (this.isFullTextShow) {
            return this.fullText;
        }
        else {
            return "";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
