package com.huijava.superiorjavablogs.util.captcha;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/28.
 * Time: 下午 1:16.
 * Explain: 验证码工具类
 */
public class WordsUtils {
    /**
     * 所有成语
     */
    public static List<String> words = new ArrayList<String>();

    //这里应该去数据库中读取成语，然后存储在内存中
    //在实际开发中，应该是可以在后台中添加成语，以及刷新成语到内存中去！利用访问某个方法来实现
    static {
        words.add("一唱一和");
        words.add("一呼百应");
        words.add("一干二净");
        words.add("一举两得");
        words.add("一落千丈");
        words.add("两面三刀");
        words.add("六神无主");
        words.add("千辛万苦");
        words.add("万无一失");
        words.add("拔刀相助");
        words.add("过时黄花");
        words.add("地动山摇");
        words.add("不可多得");
        words.add("沧海一粟");
        words.add("水泄不通");
        words.add("不可计数");
    }

}
