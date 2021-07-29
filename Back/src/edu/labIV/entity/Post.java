package edu.labIV.entity;

import edu.labIV.cfg.Config;

import java.time.LocalDateTime;

public class Post {

    public final static int MAX_TEXT_LENGTH = Config.getInstance().getPostBufferSize();

    private int userId;
    private int postId;
    private String text;
    private String imagePath;
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;        
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Post(int userId, String text, String imagePath, LocalDateTime date) {
        setUserId(userId);
        setText(text);
        setDate(date);
        setImagePath(imagePath);
    }

    public Post() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}
