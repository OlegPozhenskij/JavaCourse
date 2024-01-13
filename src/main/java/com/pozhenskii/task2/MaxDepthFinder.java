package com.pozhenskii.task2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class MaxDepthFinder implements FileVisitor<Path> {
    int maxDepth = -1;
    int currDepth = -1;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if(++currDepth > maxDepth) maxDepth = currDepth;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        currDepth--;
        return FileVisitResult.CONTINUE;
    }


    public static void main(String[] args) throws IOException {
        var path = Paths.get(args[0]);
        System.out.println(path);
        var finder = new MaxDepthFinder();

        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, finder);
            System.out.println(finder.maxDepth);
        } else {
            System.out.println("This is not a directory");
        }
    }
}
