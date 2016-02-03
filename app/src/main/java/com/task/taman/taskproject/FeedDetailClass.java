package com.task.taman.taskproject;

import java.io.Serializable;

/**
 * Created by Xerric on 2/3/2016.
 */
public class FeedDetailClass  {
    private   String name;
    private  String message;
    private  String photo;
    private  int likes;
    private  int comments;

    public FeedDetailClass(){

    }

    public FeedDetailClass(String name, String message, String photo, int likes, int comments) {
        this.name = name;
        this.message = message;
        this.photo = photo;
        this.likes = likes;
        this.comments = comments;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
