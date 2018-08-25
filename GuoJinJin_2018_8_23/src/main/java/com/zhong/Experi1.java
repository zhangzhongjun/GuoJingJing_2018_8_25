package com.zhong;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Experi1 {
    public static void main(String[] args) {
        byte[] key_bytes = null;
        try {
            String key = "12345678901234561234567890123456";
            key_bytes = key.getBytes("utf-8");
            // 下面加密一次来抹除static块对时间的影响
            String temp="test";
            byte[] temp_bytes = temp.getBytes("utf-8");
            AES_ECB.encryptAES_ECB_256(key_bytes, temp_bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            double count = 100000.0;
            while (count <= 1000000) {
                double num_double = 2 * Math.log10(count) / Math.log10(2);
                System.out.println("2 * log" + count + " = " + num_double);
                int num = (int) Math.ceil(num_double);
                System.out.println("即将获取 " + num + " 个数据");

                // 加载数据库
                ArrayList<String> lines = getNRecords(num);

                long time = 0;
                for (Iterator<String> stringIterator = lines.iterator(); stringIterator.hasNext(); ) {
                    String next = stringIterator.next();
                    byte[] next_byte = next.getBytes("utf-8");
                    long begin = System.currentTimeMillis();
                    // 使用AES_ECB_256加密
                    AES_ECB.encryptAES_ECB_256(key_bytes, next_byte);
                    long end = System.currentTimeMillis();
                    time = time + (end - begin);
                }
                System.out.println("加密所用时间为 " + time + " ms");
                System.out.println("============================================");

                count = count + 100000.0;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList getNRecords(int count) {
        ArrayList<String> res = new ArrayList<>();
        String rootDir = System.getProperty("user.dir").replace("\\", "/");
        File file = new File(rootDir, "resources");
        file = new File(file, "Gowalla_1000.txt");
        try {
            List<String> lines = IOUtils.readLines(new FileReader(file));
            for (int i = 0; i < count; i++) {
                res.add(lines.get(i).split("\t")[1]);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
