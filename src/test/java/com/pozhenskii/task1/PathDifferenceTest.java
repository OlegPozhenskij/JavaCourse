package com.pozhenskii.task1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathDifferenceTest {

    @Test
    void testSameFile() throws IOException {
        Path file1 = Files.createTempFile(null, null);
        Path file2 = file1;

        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(file1, file2);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.SameFile));
    }

    @Test
    void testBiggerFile() throws IOException {
        Path file1 = Files.createTempFile(null, null);
        Files.write(file1, "1234567890".getBytes());

        Path file2 = Files.createTempFile(null, null);
        Files.write(file2, "123".getBytes());

        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(file1, file2);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.BiggerFile));
    }

    @Test
    void testSameDirectory() throws IOException {
        Path dir1 = Paths.get("./tmp");
        Path dir2 = Paths.get("./tmp");

        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(dir1, dir2);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.SameDirectory));
    }

    @Test
    void testNotExists() throws IOException {
        Path notExists = Paths.get("fake-path");
        Path dir = Paths.get("/tmp");

        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(notExists, dir);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.NotExists));
    }

    @Test
    void testSamePrefix() throws IOException {
        Path path1 = Paths.get("/usr/local/tmp");
        Path path2 = Paths.get("/usr/local/log");

        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(path1, path2);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.SamePrefix));
    }

    @Test
    void testSameRoot() throws IOException {
        Path path1 = Paths.get("/home/user");
        Path path2 = Paths.get("/tmp/file.txt");
        System.out.println(path1.getRoot() + "" + path2.getRoot());
        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(path1, path2);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.SameRoot));
    }

    @Test
    void testSubPath() throws IOException {
        Path path1 = Paths.get("/usr/local");
        Path path2 = Paths.get("/usr/local/tmp");

        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(path1, path2);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.SubPath));
    }

    @Test
    void testParentPath() throws IOException {
        Path path1 = Paths.get("/home/user/docs");
        Path path2 = Paths.get("/home/user");

        List<PathDifference.PathDifferenceStatus> result = PathDifference.difference(path1, path2);

        assertTrue(result.contains(PathDifference.PathDifferenceStatus.ParentPath));
    }

}