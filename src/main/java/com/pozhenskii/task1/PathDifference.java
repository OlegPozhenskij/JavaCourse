package com.pozhenskii.task1;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class PathDifference {
    public enum PathDifferenceStatus {
        NotExists,
        SameFile,
        BiggerFile,
        SmallerFile,
        SameSizeFile,
        SameDirectory,
        SameAbsoluteNameDepth,
        SamePrefix,
        SameRoot,
        SubPath,
        ParentPath
    }

    public static List<PathDifferenceStatus> difference(Path path1, Path path2) throws IOException {
        var lStats = new ArrayList<PathDifferenceStatus>();

        if(!exists(path1) || !exists(path2)){
            lStats.add(PathDifferenceStatus.NotExists);
        } else {
            if(Files.isRegularFile(path1) && Files.isRegularFile(path2)) {
                checkSameFile(path1, path2, lStats);
                checkNameCount(path1, path2, lStats);
                checkFileSize(path1, path2, lStats);
            }
            if(Files.isDirectory(path1) && Files.isDirectory(path2)) {
                checkSameDirectory(path1, path2, lStats);
            }
        }
        checkSameStartPrefix(path1, path2, lStats);
        checkSameRoot(path1, path2, lStats);
        checkSubPath(path1, path2, lStats);
        checkParentPath(path1, path2, lStats);

        return lStats;
    }

    public static void main(String[] args) {
        Path dir1 = Paths.get("/tmp");
        Path dir2 = Paths.get("/tmp");
        System.out.println(Files.isDirectory(dir1));
    }

    private static boolean exists(Path path) {
        return Files.exists(path) && Files.isReadable(path);
    }

    private static void checkSameFile(Path path1, Path path2, List<PathDifferenceStatus> list) throws IOException {
        if(Files.isSameFile(path1, path2)) {
            list.add(PathDifferenceStatus.SameFile);
        }
    }

    private static void checkFileSize(Path path1, Path path2, List<PathDifferenceStatus> list) throws IOException {
        var size1 = Files.size(path1);
        var size2 = Files.size(path2);

        if (size1 > size2) {
            list.add(PathDifferenceStatus.BiggerFile);
        } else if (size1 < size2) {
            list.add(PathDifferenceStatus.SmallerFile);
        } else {
            list.add(PathDifferenceStatus.SameSizeFile);
        }
    }

    private static void checkSameDirectory(Path path1, Path path2, List<PathDifferenceStatus> list) throws IOException {
        if (Files.isSameFile(path1, path2)) {
            list.add(PathDifferenceStatus.SameDirectory);
        }
    }

    private static void checkNameCount(Path path1, Path path2, List<PathDifferenceStatus> list) {
        if(path1.getNameCount() == path2.getNameCount()) {
            list.add(PathDifferenceStatus.SameAbsoluteNameDepth);
        }
    }

    private static void checkSameStartPrefix(Path path1, Path path2, List<PathDifferenceStatus> list) {
        if(path1.getRoot() != null) path1 = path1.getRoot().relativize(path1);
        if (path2.getRoot() != null) path2 = path2.getRoot().relativize(path2);

        int depth = Math.min(path1.getNameCount(), path2.getNameCount());
        for (int i = 0; i < depth - 1; i++) {
            if (!path1.getName(i).equals(path2.getName(i))) {
                return;
            }
        }
        list.add(PathDifferenceStatus.SamePrefix);
    }

    private static void checkSameRoot(Path path1, Path path2, List<PathDifferenceStatus> list) {
        if (path1.getRoot() != null && path2.getRoot() != null) {
            if (path1.getRoot().equals(path2.getRoot())) {
                list.add(PathDifferenceStatus.SameRoot);
            }
        } else if (path1.getRoot() == null && path2.getRoot() == null) {
            list.add(PathDifferenceStatus.SameRoot);
        }
    }

    private static void checkSubPath(Path path1, Path path2, List<PathDifferenceStatus> list) {
        if(path2.startsWith(path1)) {
            list.add(PathDifferenceStatus.SubPath);
        }
    }

    private static void checkParentPath(Path path1, Path path2, List<PathDifferenceStatus> list) {
        if (path1.startsWith(path2)) {
            list.add(PathDifferenceStatus.ParentPath);
        }
    }
}

