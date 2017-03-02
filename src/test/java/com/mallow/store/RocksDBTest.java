package com.mallow.store;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

/**
 * Created by lcy on 2017/3/2.
 */
public class RocksDBTest {
    public static void main(String args[]) {
        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();
        // the Options class contains a set of configurable DB options
        // that determines the behavior of a database.
        Options options = new Options().setCreateIfMissing(true);
        RocksDB db = null;
        try {
            // a factory method that returns a RocksDB instance
            db = RocksDB.open(options, "D://rocksdb");
            String key = "key";
            String value = "value";
//            db.put(key.getBytes(), value.getBytes());
            System.out.println(new String(db.get(key.getBytes())));
        } catch (RocksDBException e) {
          e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
                options.close();
            }
        }
    }
}
