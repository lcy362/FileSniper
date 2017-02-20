package com.mallow.file.multithread;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by lcy on 2017/2/15.
 * just write a multi-thread version for show
 * in fact, as the bottleneck is IO, rather than CPU, can't see will multi-thread be effective
 */

//TODO has not tested
public class MultiThreadFileVisitor implements FileVisitor<Path> {
    private Map<String, String> fingerPrints;
    private ExecutorService pool;
    public MultiThreadFileVisitor(Map<String, String> map, ExecutorService pool) {
        fingerPrints = map;
        this.pool = pool;
    }
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        pool.execute(new FileHandler(file, attrs, fingerPrints));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("failed: " + file);
        exc.printStackTrace();
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }
}
