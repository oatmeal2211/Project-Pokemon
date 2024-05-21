import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class GmailSender {
    public static void sendEmail(String recipientEmail){
        // 设置SMTP服务器属性
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");  // 开启认证
        properties.put("mail.smtp.starttls.enable", "true");  // 开启TLS
        properties.put("mail.smtp.host", "smtp.gmail.com");  // 设置SMTP服务器
        properties.put("mail.smtp.port", "587");  // 设置端口号

        // 认证信息
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yongbinzhang514@gmail.com", "uylirlwlubrczxfg");
            }
        });

        try {
            // 创建邮件
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yongbinzhang514@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("z13933825916@163.com"));
            message.setSubject("Test");
            message.setText("Hello,world");

            // 发送邮件
            Transport.send(message);

            System.out.println("Email is sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {}
}