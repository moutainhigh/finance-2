package com.july.company.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


@Slf4j
@Component
public class MailUtil {

    @Value("${Email.Account}")
    private String EmailAccount;
    @Value("${Email.Password}")
    private String EmailPassword;
    @Value("${Email.SMTPHost}")
    private String EmailSMTPHost;
    @Value("${Email.SMTPPort}")
    private String SMTPPort;

    public void sendToReceiveMail(String receiveMailAccount, String title, String msg, File file) throws Exception {
        // 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", EmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        props.setProperty("mail.smtp.port", SMTPPort);
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.ssl.enable", "true");
        props.setProperty("mail.smtp.socketFactory.port", SMTPPort);

        // 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        //  From: 发件人(集团订单中心)
        String nick = javax.mail.internet.MimeUtility.encodeText("测试");
        message.setFrom(new InternetAddress(nick+" <"+EmailAccount+">"));

        // To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount));

        // Subject: 邮件主题
        message.setSubject(title);

        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(msg, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);

        // 添加附件的内容
        if (file != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

            //MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }

        // 将multipart对象放到message中
        message.setContent(multipart);

        // 保存设置
        message.saveChanges();

        // 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        transport.connect(EmailAccount, EmailPassword);

        // 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 关闭连接
        transport.close();
    }
}
