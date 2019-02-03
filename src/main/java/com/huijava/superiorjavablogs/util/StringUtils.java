package com.huijava.superiorjavablogs.util;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/18.
 * Time: 下午 5:07.
 * Explain: 字符串的帮助类
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String CHARSET_NAME = "UTF-8";

    public static String gtLtTransform(String gtlt) {
        return gtlt.replace("<", "&lt;").replace(">", "&gt;");
    }

    /**
     * 获取博客的token
     *
     * @return
     */
    public static String getBlogToken() {
        return DateUtils.getDateTimeMSString() + getFixLenthString(6);
    }

    public static String URLDecode(String str, String enc) throws UnsupportedEncodingException {
        return URLDecoder.decode(str, enc);
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 将将普通字符串转换成转换application/x-www-form-urlencoded字符串
     * 必须强调的是编码方式必须正确，如baidu的是gb2312，而google的是UTF-8
     *
     * @param str
     * @param enc
     * @return
     */
    public static String URLEncoder(String str, String enc) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, enc);
    }

    /**
     * 默认使用UTF-8
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String URLEncoder(String str) throws UnsupportedEncodingException {
        return URLEncoder(str, CHARSET_NAME);
    }

    /**
     * 默认使用UTF-8
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String URLDecode(String str) throws UnsupportedEncodingException {
        return URLDecode(str, CHARSET_NAME);
    }

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 字节数组转换为字符串。以UTF-8字符集
     *
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return EMPTY;
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }


    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 生成长度为length的随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ~!@#$%^&*_+=-,.;':";
        StringBuffer sb = getStringBuffer(length, base);
        return sb.toString();
    }

    /**
     * 生成红包字符串
     *
     * @param length
     * @return
     */
    public static String getRedPaxketRandomString(int length) {
        String base = "abcdefghijkmnpqrstuvwxyzABCDEFGHKLMNPQRSTUVWXYZ23456789";
        StringBuffer sb = getStringBuffer(length, base);
        return sb.toString();
    }

    private static StringBuffer getStringBuffer(int length, String base) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb;
    }

    /**
     * 生成一个32位的盐,加密用的
     *
     * @return
     */
    public static String buildSalt() {
        return getRandomString(32);
    }

    /**
     * 返回长度为【strLength】的随机数
     */
    public static String getFixLenthString(int strLength) {
        Random rm = new Random(System.currentTimeMillis());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strLength; i++) {
            String num = String.valueOf(rm.nextInt(10));
            sb.append(num);
        }
        return sb.toString();
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytearray
     * @return
     */
    public static String byteToStr(byte[] bytearray) {
        String strDigest = "";
        for (int i = 0; i < bytearray.length; i++) {
            strDigest += byteToHexStr(bytearray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param ib
     * @return
     */
    public static String byteToHexStr(byte ib) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    /**
     * @return 返回id
     */
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 订单编号,格式为当前年月日时分秒毫秒+随机生成10位数字，如："20170228155316339*****"
     *
     * @return 返回的是订单编号
     */
    public static String getOrderId() {
        return DateUtils.formatDate(new Date(), "yyyyMMddHHmmssSSS") + getFixLenthString(10);
    }

    /**
     * 商品编号，格式为"g"+当前年月日时分秒毫秒，如："g20170309140139567"
     *
     * @return 返回商品编号
     */
    public static String getGoodId() {
        return "g" + DateUtils.formatDate(new Date(), "yyyyMMddHHmmssSSS");
    }

    public static void main(String[] args) {
        //密码生成盐从这里随机获取到长度为32位
        //String str = StringUtils.getRandomString(32);
        //System.out.print(str);
        //System.out.println(getFixLenthString(10));
        System.out.println("订单:" + getOrderId());
        System.out.println("商品编号:" + getGoodId());
        System.out.println(getUUID());
    }

}