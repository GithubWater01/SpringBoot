package com.my.myproject.util;

import java.sql.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发生邮件
 */
public class MailUtil {

    // 邮件发送协议
    private final static String PROTOCOL = "smtp";

    // SMTP邮件服务器
    private final static String HOST = "smtp.qq.com";

    // SMTP邮件服务器默认端口
    private final static String PORT = "465";

    // 是否要求身份认证
    private final static String IS_AUTH = "true";

    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final static String IS_ENABLED_DEBUG_MOD = "true";

    // 发件人 (填写发邮件的用户名)
    private static String from = "977428563@qq.com";
    // 发件人密码(填写发邮件的用户密码)
    private static String password = "wtppjmxcqvjcbced";

    // 收件人 (自定义填写一个邮箱)
    private static String to = "18331932788@163.com,977428563@qq.com";

    // 初始化连接邮件服务器的会话信息
    private static Properties props = null;

    private static Session session;

    static {
        //初始化
        props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);
        props.setProperty("mail.smtp.host", HOST);
        //props.setProperty("mail.smtp.port", PORT);
        props.setProperty("mail.smtp.auth", IS_AUTH);
        props.setProperty("mail.debug", IS_ENABLED_DEBUG_MOD);
        // 创建Session实例对象
        session = Session.getDefaultInstance(props);
    }

    public static void sendMail(String recipients, String title, String content) throws AddressException, MessagingException {
        // 创建MimeMessage实例对象
        MimeMessage message = new MimeMessage(session);
        // 设置发件人
        message.setFrom(new InternetAddress(from));
        // 设置邮件主题
        message.setSubject(title);
        message.addHeader("X-Priority", "3");
        message.addHeader("X-MSMail-Priority", "Normal");
        message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869"); //本文以outlook名义发送邮件，不会被当作垃圾邮件
        message.addHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.2869");
        message.addHeader("ReturnReceipt", "1");

        // 设置收件人
        message.setRecipients(RecipientType.TO, InternetAddress.parse(recipients));
        // 设置发送时间
        message.setSentDate(new Date(System.currentTimeMillis()));
        // 设置纯文本内容为邮件正文
//        message.setText(content);
        message.setContent(content, "text/html;charset=UTF-8");

        // 保存并生成最终的邮件内容
        message.saveChanges();

        // 获得Transport实例对象
        Transport transport = session.getTransport();
        // 打开连接需要邮箱帐号密码
        transport.connect(from, password);
        // 将message对象传递给transport对象，将邮件发送出去
        transport.sendMessage(message, message.getAllRecipients());
        // 关闭连接（一定要关闭）
        transport.close();
    }

    public static void main(String[] args) throws AddressException, MessagingException {
        sendMail("18331932788@163.com", "标题", "测试11");
    }

}
