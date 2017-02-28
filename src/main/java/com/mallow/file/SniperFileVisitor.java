package com.mallow.file;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

/**
 * Created by lcy on 2017/2/15.
 */
public class SniperFileVisitor implements FileVisitor<Path> {
    private Map<String, String> fingerPrints;
    private Logger logger = LoggerFactory.getLogger(SniperFileVisitor.class);
    public SniperFileVisitor(Map<String, String> map) {
        fingerPrints = map;
    }
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.size() > 0) {
            FileInputStream fis = new FileInputStream(file.toFile());
            String md5 = DigestUtils.md5Hex(fis);
            if (fingerPrints.containsKey(md5)) {
                fingerPrints.put(md5, fingerPrints.get(md5) + "_" + file.toString());
                logger.info(System.currentTimeMillis() + " duplicate files: " + fingerPrints.get(md5));
            } else {
                fingerPrints.put(md5, file.toString());
            }
        }
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
