package com.mallow.file;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

/**
 * Created by lcy on 2017/2/15.
 */
public class FileOperationTest {
    @Test
    public void filesWalkTest() {
        try (Stream<Path> paths = Files.walk(Paths.get("D://doc"))) {
            paths.forEach(path -> {
                System.out.println(path);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fileVisitorTest() {
        Path p = Paths.get("D://");
        try {
            Files.walkFileTree(p, new TraverseFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
