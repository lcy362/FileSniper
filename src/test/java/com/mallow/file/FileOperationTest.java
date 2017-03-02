package com.mallow.file;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
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
        HashMap<String, String> map = new HashMap<>();
        try {
            Files.walkFileTree(p, new SniperFileVisitor(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void md5test() throws IOException {
        FileInputStream fis = new FileInputStream("D://esper.txt");
        String md5 = DigestUtils.md5Hex(fis);
        System.out.println(md5);
        FileInputStream fis1 = new FileInputStream("D://esper - 副本.txt");
        String md51 = DigestUtils.md5Hex(fis1);
        System.out.println(md51);
    }

    @Test
    public void showSeq() {
        Path p = Paths.get("D://doc");
        try {
            Files.walkFileTree(p, new VisitorTest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
