package com.pozhenskii.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxDepthFinderTest {
    @TempDir
    Path tempDir;

    @Test
    void shouldReturnNegativeValueForFile() throws IOException {
        Path file = Files.createFile(tempDir.resolve("file.txt"));

        MaxDepthFinder finder = new MaxDepthFinder();
        Files.walkFileTree(file, finder);

        assertEquals(-1, finder.maxDepth);
    }

    @Test
    void shouldReturnZeroDepthForEmptyDir() throws IOException {
        Path dir = Files.createDirectory(tempDir.resolve("empty"));

        MaxDepthFinder finder = new MaxDepthFinder();
        Files.walkFileTree(dir, finder);

        assertEquals(0, finder.maxDepth);
    }

    @Test
    void shouldCalculateDepthCorrectly() throws IOException {
        Path level1 = Files.createDirectory(tempDir.resolve("level1"));
        Path level2 = Files.createDirectory(level1.resolve("level2"));
        Files.createDirectory(level2.resolve("level3"));

        MaxDepthFinder finder = new MaxDepthFinder();
        Files.walkFileTree(level1, finder);

        assertEquals(2, finder.maxDepth);
    }

}