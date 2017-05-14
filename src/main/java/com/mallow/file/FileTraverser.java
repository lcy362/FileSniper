package com.mallow.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by lcy on 2017/2/15.
 */
public class FileTraverser {
    public static void main(String args[]) {
        Path p = Paths.get("D://doc");
        HashMap<String, String> map = new HashMap<>();
        try {
            Files.walkFileTree(p, new SniperFileVisitor(map));
            System.out.println("finish, size: " + map.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
