package com.huijava.superiorjavablogs.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UsersForm implements Serializable {

    private static final long serialVersionUID = -8314352523375300461L;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 32, message = "用户名最长为32个字符，最少为3个字符")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码最长为32个字符，最少为6个字符")
    private String password;

    /**
     * 电子邮箱
     */
    @NotBlank(message = "电子邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 邮箱验证码
     */
    @NotBlank(message = "邮箱验证码不能为空")
    private String emailCode;
}