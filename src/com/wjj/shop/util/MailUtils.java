package com.wjj.shop.util;

import com.sun.mail.util.MailSSLSocketFactory;
import com.wjj.shop.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    /**
     * 邮件发送
     * @param user
     */
    public static void sendMail(User user){
        //设置配置信息
        //账号
        String account = "2014232502@qq.com";
        //授权码
        String myPass = "srkqylhvnxshdjeb";
        //对会话进行设置
        Properties props = new Properties();
        //设置发送协议smtp
        props.setProperty("mail.transport.protocol","smtp");
        //设置邮件服务器
        props.setProperty("mail.host","smtp.qq.com");
        //是否开启认证功能
        props.setProperty("mail.smtp.auth","true");
        //是否开启ssl加密功能
        props.setProperty("mail.smtp.ssl.enable","true");
        //设置socket连接工厂 可以不写 默认就是这个
        MailSSLSocketFactory factory = null;
        try {
            factory = new MailSSLSocketFactory();
            factory.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.factory",factory);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        //建立会话 创建会话对象
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            //进行与邮件服务器的认证
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人邮箱账号 不是邮箱密码是授权码
                return new PasswordAuthentication(account,myPass);
            }
        });
        //开发阶段可以设置为debug模式
//        session.setDebug(true);
        try {
            //获取一个传输窗口
            Transport transport = session.getTransport();
            //此处必须明确的连接 否则会产生异常
            transport.connect(account,myPass);
            //创建Message方法
            MimeMessage message = createMsg(session,account,user);
            //利用传输端口发送邮件
            transport.sendMessage(message,message.getAllRecipients());
            //关闭传输窗口
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建Message对象
     * @param session
     * @param account
     * @param
     * @return
     */
    private static MimeMessage createMsg(Session session, String account, User user) {
        //创建邮件对象
        MimeMessage message =new MimeMessage(session);
        try {
            //发件人
            message.setFrom(new InternetAddress(account,"小米商城","UTF-8"));
            //收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername(), "UTF-8"));
            //主题
            message.setSubject("小米商城激活邮件","UTF-8");
            //邮件内容
            //获取本机的ip地址
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            String url = "http://"+hostAddress+":8080/user?method=active&c="+Base64Utils.encode(user.getCode());
            message.setContent(user.getUsername()+",你好！<br>欢迎你注册Shopwjj商城！" +
                    "请点击链接进行激活:<a href='"+url+"'>点击此处</a>","text/html;charset=utf-8");
            //设置邮件发送时间
            message.setSentDate(new Date());
            //保存上面设置
            message.saveChanges();
        } catch (MessagingException | UnsupportedEncodingException | UnknownHostException e) {
            e.printStackTrace();
        }
        return message;
    }

}
