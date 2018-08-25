package com.zhong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PrefixUtils {

    /**
     * 计算一个数字的前缀编码
     *
     * @param l 要计算的数字
     * @param w 位数
     * @return
     */
    public static List<String> F(Long l, int w) {
        String bin = Long.toBinaryString(l);
        bin = flushLeft('0', w, bin);
        // System.out.println(bin + " " + bin.length());
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i <= bin.length(); i++) {
            String temp = bin.substring(0, i);
            // System.out.println(temp+" "+temp.length());
            for (int j = 0; j < w - i; j++) {
                temp = temp + "*";
            }
            list.add(temp);
            // System.out.println(temp+" "+temp.length());
        }
        return list;
    }

    /**
     * 计算区间[a,b]的前缀编码
     *
     * @param a 区间下界
     * @param b 区间上界
     * @param w 位数
     * @return
     */
    public static List<String> S(Long a, Long b, int w) {
        HashSet<String> res = new HashSet<String>(F(a, w));
        for (long i = a + 1; i <= b; i++) {
            HashSet<String> temp = new HashSet<String>(F(i, w));
            res.retainAll(temp);
        }
        return new ArrayList<String>(res);
    }

    /**
     * 计算区间[0,b]的前缀编码
     *
     * @param b 上界
     * @param w 位长
     * @return 区间[0, b]的前缀编码
     */
    public static List<String> S(final long b, final int w) {
        final char[] arr = new char[w];
        Arrays.fill(arr, '0');

        long temp_b = b;
        List<String> res = new ArrayList<String>();
        while (true) {
            int l = 0;
            while (!(Math.pow(2, l) <= temp_b && temp_b < Math.pow(2, l + 1))) {
                l++;
            }

            if (temp_b == Math.pow(2, l + 1) - 1) {
                for (int i = w - l - 1; i < w; i++) {
                    arr[i] = '*';
                }
                res.add(Arrays.toString(arr));
                break;
            } else if (temp_b == Math.pow(2, l)) {
                for (int i = w - l; i < w; i++) {
                    arr[i] = '*';
                }
                res.add(Arrays.toString(arr));

                for (int i = w - l; i < w; i++) {
                    arr[i] = '0';
                }
                arr[w - l - 1] = '1';
                res.add(Arrays.toString(arr));
                break;
            } else {
                for (int i = w - l; i < w; i++) {
                    arr[i] = '*';
                }
                res.add(Arrays.toString(arr));

                arr[w - l - 1] = '1';
                for (int i = w - l; i < w; i++) {
                    arr[i] = '0';
                }
                temp_b = (long) (temp_b - Math.pow(2, l));
            }
        }
        return res;
    }

    /**
     * 左填充字符串
     *
     * @param c       要填充的字符
     * @param length  填充之后字符串的总长度
     * @param content 要格式化的字符串
     * @return 格式化之后的字符串
     */
    public static String flushLeft(char c, long length, String content) {
        String str = "";
        String cs = "";
        if (content.length() > length) {
            str = content;
        } else {
            for (int i = 0; i < length - content.length(); i++) {
                cs = cs + c;
            }
        }
        str = cs + content;
        return str;
    }
}
