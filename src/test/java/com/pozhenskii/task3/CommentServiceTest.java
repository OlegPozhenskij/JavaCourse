package com.pozhenskii.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest {
    @Test
    void testGetModeratedCommentsSortedByDateDesc() throws InterruptedException {
        CommentService commentService = new CommentService();
        Comment moderatedComment1 = new Comment("Author1", new Date(), true, "Text1");
        Thread.sleep(1000);
        Comment moderatedComment2 = new Comment("Author2", new Date(), true, "Text2");
        Thread.sleep(1000);
        Comment unmoderatedComment = new Comment("Author3", new Date(), false, "Text3");

        commentService.addComment(moderatedComment1);
        commentService.addComment(moderatedComment2);
        commentService.addComment(unmoderatedComment);

        Comment[] result = commentService.getModeratedCommentsSortedByDateDesc(0, 2);

        assertArrayEquals(new Comment[]{moderatedComment2, moderatedComment1}, result);
    }

    @Test
    void testGetCommentsByAuthorSortedByModeration() {
        CommentService commentService = new CommentService();
        Comment moderatedComment1 = new Comment("Author1", new Date(), true, "Text1");
        Comment moderatedComment2 = new Comment("Author1", new Date(), true, "Text2");
        Comment unmoderatedComment = new Comment("Author1", new Date(), false, "Text3");

        commentService.addComment(moderatedComment1);
        commentService.addComment(moderatedComment2);
        commentService.addComment(unmoderatedComment);

        Comment[] result = commentService.getCommentsByAuthorSortedByModeration("Author1", 0, 2);

        assertArrayEquals(new Comment[]{unmoderatedComment, moderatedComment1}, result);
    }

    @Test
    void testGetAuthorsAfterDate() {
        CommentService commentService = new CommentService();
        Comment comment1 = new Comment("Author1", new Date(), true, "Text1");
        Comment comment2 = new Comment("Author2", new Date(), true, "Text2");
        Comment comment3 = new Comment("Author3", new Date(System.currentTimeMillis() + 10000), true, "Text3");

        commentService.addComment(comment1);
        commentService.addComment(comment2);
        commentService.addComment(comment3);

        String[] result = commentService.authorsAfterDate(new Date());

        assertArrayEquals(new String[]{"Author3"}, result);
    }

    private CommentService commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentService();
        commentService.addComment(new Comment("User1", new Date(),  true, "Content1"));
        commentService.addComment(new Comment("User2", new Date(),  false, "Content2"));
        commentService.addComment(new Comment("User3", new Date(),  true, "Content3"));
    }

    @Test
    void testGetModeratedCommentsSortedByDateDescWithoutParams() {
        Comment[] result = commentService.getModeratedCommentsSortedByDateDesc();
        assertEquals(2, result.length);
    }

    @Test
    void testGetCommentsByAuthorSortedByModerationWithoutParams() {
        Comment[] result = commentService.getCommentsByAuthorSortedByModeration("User1");
        assertEquals(1, result.length);
    }

    @Test
    void testAuthorsAfterDate() {
        String[] result = commentService.authorsAfterDate(new Date(0), 0, 10);
        assertEquals(3, result.length);
    }

}