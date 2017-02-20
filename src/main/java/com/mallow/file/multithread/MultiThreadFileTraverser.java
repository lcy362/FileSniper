package com.mallow.file.multithread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lcy on 2017/2/15.
 */
public class MultiThreadFileTraverser {
    public static void main(String args[]) {
        Path p = Paths.get("D://");
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try {
            Files.walkFileTree(p, new MultiThreadFileVisitor(map, pool));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
