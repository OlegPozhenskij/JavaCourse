package com.pozhenskii.task3;

import java.util.Date;

public class Comment {
    private String author;
    private Date creationDate;
    private boolean moderated;
    private String text;

    public Comment(String author, Date creationDate, boolean moderated, String text) {
        this.author = author;
        this.creationDate = creationDate;
        this.moderated = moderated;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isModerated() {
        return moderated;
    }

    public String getText() {
        return text;
    }
}
