package com.huijava.superiorjavablogs.util.email;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/15.
 * Time: 下午 12:20.
 * Explain:用户发送邮件必须实现的接口
 */
public interface SendEmail {
    /**
     * 用户的id
     *
     * @return
     */
    String getId();

    /**
     * 用户的令牌-通过2次MD5加密
     *
     * @return
     */
    String getToken();

    /**
     * 用户的昵称
     *
     * @return
     */
    String getName();

    /**
     * 用户的邮箱，也就是收件邮箱
     *
     * @return
     */
    String getEmail();
}