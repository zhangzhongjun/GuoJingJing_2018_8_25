package com.zhong;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrefixUtilsTest {
    @Test
    public void FTest() {
        List<String> ress = PrefixUtils.F(12l, 32);
        for (String res : ress) {
            System.out.println(res);
        }
    }

    @Test
    public void t1() throws Exception {
        ArrayList<String> res = new ArrayList<>();
        String rootDir = System.getProperty("user.dir").replace("\\", "/");
        File file = new File(rootDir, "resources");
        file = new File(file, "Gowalla_time_32位.txt");
        List<String> ss = IOUtils.readLines(new FileReader(file));
        for (int i = 0; i < 1000; i++) {
            Random r = new Random();
            String id = ss.get(r.nextInt(ss.size()));
            long t1 = System.currentTimeMillis();
            // 1.计算区间[0,id]的编码
            List<String> prefixs = PrefixUtils.S(Long.parseLong(id), 28);
            long t2 = System.currentTimeMillis();
            System.out.println(Long.parseLong(id) + " " + " " + " " + (t2 - t1) + " ms");
        }
    }

    @Test
    public void t2() {
        long l = 123123l;
        List<String> res = PrefixUtils.F(l, 28);
        System.out.println(res.size());
        BloomFilter<String> bf = new BloomFilter<String>(0.0000001, 100);
        for (String string : res) {
            System.out.println(string);
            bf.add(string);
        }

        System.out.println(bf.contains("asd"));
        System.out.println(bf.contains("00**************************************************************"));
        System.out.println(bf.contains("00000***********************************************************"));
    }

    @Test
    public void t3() {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            Long l = (long) r.nextInt(10000);
            System.out.println(l);
            PrefixUtils.S(l, 32);
        }
    }

    @Test
    public void t4() {
        List<String> strs = PrefixUtils.F(158393592l, 32);
        System.out.println(strs.size());
    }

    @Test
    public void t5() {
        List<String> strs = PrefixUtils.S(158393592l, 32);
        System.out.println(strs.size());
    }

}
