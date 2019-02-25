package com.huijava.superiorjavablogs.util.email.impl;

import com.huijava.superiorjavablogs.util.email.EmailConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 陈浩翔.
 * Date: 2017/3/15.
 * Time: 下午 12:33.
 * Explain:我们的发件邮箱类实现类
 */
@Component
public class EmailConfigImpl implements EmailConfig {
    /**
     * 发件邮箱地址
     */
    @Value("${email.main.add}")
    private String mainAdd;
    /**
     * TODO 配置修改  发件人的名称
     */
    @Value("${{email.mail.name}")
    private String mainName;
    /**
     * 发件邮箱密码-也叫授权码
     */
    @Value("email.mail.password")
    private String password;
    /**
     * 发件邮箱端口
     */
    @Value("email.mail.port")
    private String port;
    /**
     * 设置邮件服务器主机名
     */
    @Value("email.mail.host")
    private String mailHost;
    /**
     * 发送邮件协议名称
     */
    @Value("email.mail.protocol")
    private String mailProtocol;
    /**
     * 邮件内容:主题
     */
    @Value("email.subject")
    private String subject;
    /**
     * 邮件激活的URL地址，未带状态以及用户id
     */
    @Value("email.active.mail.add")
    private String activeMailAdd;
    /**
     * 客服邮箱
     */
    @Value("email.service.mail")
    private String serviceMail;
    /**
     * 网站帮助中心URL地址
     */
    @Value("email.help.center.url")
    private String helpCenterUrl;
    /**
     * 邮件的正文内容
     */
    @Value("email.content")
    private String content;

    public EmailConfigImpl() {
    }

    @Override
    public String getMainAdd() {
        return mainAdd;
    }

    public void setMainAdd(String mainAdd) {
        this.mainAdd = mainAdd;
    }

    @Override
    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    @Override
    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    @Override
    public String getMailProtocol() {
        return mailProtocol;
    }

    public void setMailProtocol(String mailProtocol) {
        this.mailProtocol = mailProtocol;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getActiveMailAdd() {
        return activeMailAdd;
    }

    public void setActiveMailAdd(String activeMailAdd) {
        this.activeMailAdd = activeMailAdd;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getServiceMail() {
        return serviceMail;
    }

    public void setServiceMail(String serviceMail) {
        this.serviceMail = serviceMail;
    }

    @Override
    public String getHelpCenterUrl() {
        return helpCenterUrl;
    }

    public void setHelpCenterUrl(String helpCenterUrl) {
        this.helpCenterUrl = helpCenterUrl;
    }
}
