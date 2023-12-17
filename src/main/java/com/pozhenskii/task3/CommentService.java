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
                .filter(Comment::moderated)
                .sorted(Comparator.comparing(Comment::creationDate).reversed())
                .skip((long) page * pageSize)
                .limit(pageSize)
                .toArray(Comment[]::new);
    }

    public Comment[] getModeratedCommentsSortedByDateDesc() {
        return comments.stream()
                .filter(Comment::moderated)
                .sorted(Comparator.comparing(Comment::creationDate).reversed())
                .toArray(Comment[]::new);
    }

    public Comment[] getCommentsByAuthorSortedByModeration(String author, int page, int pageSize) {
        return comments.stream()
                .filter(comment -> comment.author().equals(author))
                .sorted(Comparator.comparing(comment -> comment.moderated() ? 1 : 0))
                .skip(page * pageSize)
                .limit(pageSize)
                .toArray(Comment[]::new);
    }

    public Comment[] getCommentsByAuthorSortedByModeration(String author) {
        return comments.stream()
                .filter(comment -> comment.author().equals(author))
                .sorted(Comparator.comparing(comment -> comment.moderated() ? 1 : 0))
                .toArray(Comment[]::new);
    }

    public String[] authorsAfterDate(Date date, int page, int pageSize) {
        return comments.stream()
                .filter(comment -> comment.creationDate().after(date))
                .map(Comment::author)
                .skip(page * pageSize)
                .limit(pageSize)
                .distinct()
                .toArray(String[]::new);
    }

    public String[] authorsAfterDate(Date date) {
        return comments.stream()
                .filter(comment -> comment.creationDate().after(date))
                .map(Comment::author)
                .distinct()
                .toArray(String[]::new);
    }

}
