package com.mallow.file.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        Logger logger = LoggerFactory.getLogger(MultiThreadFileTraverser.class);
        int filterSize = 15; //ignore files smaller than filterSize (mb) , default 15
        String path = "D://"; //the path to run
        if (args.length > 0) {
            path = args[0];
        }
        if (args.length > 1) {
            filterSize = Integer.parseInt(args[1]);
        }
        Path p = Paths.get(path);
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try {
            // use walkFileTree to traverse the file system
            Files.walkFileTree(p, new MultiThreadFileVisitor(map, pool, filterSize));
//            logger.info("finish, size: " + map.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
