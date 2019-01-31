package com.huijava.superiorjavablogs.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * 根据id混淆
 */
public class ConfusionIdUtils {

    /**
     * 随机字符串
     */
    private static final char[] CHARS = new char[]{'F', 'L', 'G', 'W', '5', 'X', 'C', '3',
            '9', 'Z', 'M', '6', '7', 'Y', 'R', 'T', '2', 'H', 'S', '8', 'D', 'V', 'E', 'J', '4', 'K',
            'Q', 'P', 'U', 'A', 'N', 'B'};

    private final static int CHARS_LENGTH = 32;
    /**
     * 邀请码长度
     */
    private final static int CODE_LENGTH = 6;

    /**
     * 随机数据
     */
    private final static long SLAT = 1234561L;

    /**
     * PRIME1 与 CHARS 的长度 L互质，可保证 ( id * PRIME1) % L 在 [0,L)上均匀分布
     */
    private final static int PRIME1 = 3;

    /**
     * PRIME2 与 CODE_LENGTH 互质，可保证 ( index * PRIME2) % CODE_LENGTH  在 [0，CODE_LENGTH）上均匀分布
     */
    private final static int PRIME2 = 11;

    /**
     * 生成邀请码
     *
     * @param id 唯一的id主键
     * @return code
     */
    public static String encode(Long id) {
        //补位，并扩大整体
        id = id * PRIME1 + SLAT;
        //将 id 转换成32进制的值
        long[] b = new long[CODE_LENGTH];
        //32进制数
        b[0] = id;
        for (int i = 0; i < CODE_LENGTH - 1; i++) {
            b[i + 1] = b[i] / CHARS_LENGTH;
            //扩大每一位的差异
            b[i] = (b[i] + i * b[0]) % CHARS_LENGTH;
        }
        b[5] = (b[0] + b[1] + b[2] + b[3] + b[4]) * PRIME1 % CHARS_LENGTH;

        //进行混淆
        long[] codeIndexArray = new long[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            codeIndexArray[i] = b[i * PRIME2 % CODE_LENGTH];
        }

        StringBuilder buffer = new StringBuilder();
        Arrays.stream(codeIndexArray).boxed().map(Long::intValue).map(t -> CHARS[t]).forEach(buffer::append);
        return buffer.toString();
    }

    /**
     * 将邀请码解密成原来的id
     *
     * @param code 邀请码
     * @return id
     */
    public static Long decode(String code) {
        if (code.length() != CODE_LENGTH) {
            return null;
        }
        //将字符还原成对应数字
        long[] a = new long[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            char c = code.charAt(i);
            int index = findIndex(c);
            if (index == -1) {
                //异常字符串
                return null;
            }
            a[i * PRIME2 % CODE_LENGTH] = index;
        }

        long[] b = new long[CODE_LENGTH];
        for (int i = CODE_LENGTH - 2; i >= 0; i--) {
            b[i] = (a[i] - a[0] * i + CHARS_LENGTH * i) % CHARS_LENGTH;
        }

        long res = 0;
        for (int i = CODE_LENGTH - 2; i >= 0; i--) {
            res += b[i];
            res *= (i > 0 ? CHARS_LENGTH : 1);
        }
        return (res - SLAT) / PRIME1;
    }


    /**
     * 查找对应字符的index
     *
     * @param c 字符
     * @return index
     */
    private static int findIndex(char c) {
        for (int i = 0; i < CHARS_LENGTH; i++) {
            if (CHARS[i] == c) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 够用了 3000w数据未出现重复数据
     *
     * @param args
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        //测试1000w数据
        TreeMap treeMap = new TreeMap();
        System.out.println("开始...");
        for (long i = 1L; i < 30000000L; i++) {
            String code = encode(i);
            if (treeMap.containsKey(code)) {
                System.out.println("出现了重复数据，map.size=" + treeMap.size() + ",i=" + i + ",code=" + code + ",hashMap.get=" + treeMap.get(code));
                return;
            }
            treeMap.put(code, true);
            if (i % 1000000 == 0) {
                System.out.println("百万数据未出现重复，i=" + i + ",map.size=" + treeMap.size() + ",i=" + i + ",code=" + code);
            }
        }
        System.out.println("map.size=" + treeMap.size());
    }

}