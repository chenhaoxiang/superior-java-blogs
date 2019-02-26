package com.huijava.superiorjavablogs.util.pass;


/**
 * 密码加密工具
 */
public class PasswordUtils {

    /**
     * @return 返回加密用的盐
     */
    public static String getSalt() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 用户登录等密码的加密方法
     *
     * @param password
     * @param salt
     * @return
     */
    public static String getPassword(String password, String salt) {
        return MD5Utils.md5Encode(MD5Utils.md5Encode(password) + salt);
    }

    /**
     * 生成 token 的方式-验证的令牌，不会过期的token
     * 在用户点击验证邮箱地址时使用，密码使用的是盐和加密后的密码！
     *
     * @param salt
     * @param password
     * @return
     */
    public static String getTokenForever(String salt, String password) {
        return getPassword(MD5Utils.md5Encode(salt + password), salt);
    }


    /**
     * 生成 token 的方式-验证的令牌
     * 在用户点击验证邮箱地址时使用，密码使用的是盐和加密后的密码！
     *
     * @param salt
     * @param password
     * @return
     */
    public static String getToken(String salt, String password, Long time) {
        return getPassword(MD5Utils.md5Encode(salt + password), time.toString());
    }

}
