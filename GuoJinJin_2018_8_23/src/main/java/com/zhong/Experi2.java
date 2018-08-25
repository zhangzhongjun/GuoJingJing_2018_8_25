package com.zhong;

import java.util.ArrayList;
import java.util.List;

public class Experi2 {
    public static void main(String[] args) {
        long x1 = 158393592l;
        long x2 = 166730098l;
        long x3 = 205078021l;

        long time = 0l;

        long begin = System.currentTimeMillis();
        List<String> prefix1 = PrefixUtils.S(x1, 32);
        List<String> prefix2 = PrefixUtils.S(x2, 32);
        List<String> prefix3 = PrefixUtils.S(x3, 32);
        long end = System.currentTimeMillis();
        time = time + (end - begin);

        List<String> prefixs = new ArrayList<String>();
        prefixs.addAll(prefix1);
        prefixs.addAll(prefix2);
        prefixs.addAll(prefix3);

        System.out.println("t= " + prefixs.size());

        begin = System.currentTimeMillis();
        BloomFilter<String> bf = new BloomFilter<String>(0.0000001, 100);
        for (String string : prefixs) {
            bf.add(string);
        }
        end = System.currentTimeMillis();
        time = time + (end - begin);

        System.out.println("所用时间 " + time + " ms");
    }
}
