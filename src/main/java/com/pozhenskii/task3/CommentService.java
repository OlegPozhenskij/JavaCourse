package com.pozhenskii.task3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CommentService {
    private List<Comment> comments;

    public CommentService() {
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Comment[] getModeratedCommentsSortedByDateDesc(int page, int pageSize) {
        return comments.stream()
                .filter(Comment::isModerated)
                .sorted(Comparator.comparing(Comment::getCreationDate).reversed())
                .skip((long) page * pageSize)
                .limit(pageSize)
                .toArray(Comment[]::new);
    }

    public Comment[] getCommentsByAuthorSortedByModeration(String author, int page, int pageSize) {
        return comments.stream()
                .filter(comment -> comment.getAuthor().equals(author))
                .sorted(Comparator.comparing(comment -> comment.isModerated() ? 1 : 0))
                .skip(page * pageSize)
                .limit(pageSize)
                .toArray(Comment[]::new);
    }

    public String[] getAuthorsAfterDate(Date date) {
        return comments.stream()
                .filter(comment -> comment.getCreationDate().after(date))
                .map(Comment::getAuthor)
                .distinct()
                .toArray(String[]::new);
    }
}
