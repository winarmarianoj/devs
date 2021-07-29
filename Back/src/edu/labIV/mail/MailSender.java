package edu.labIV.mail;

import edu.labIV.cfg.Config;
import edu.labIV.cfg.ConfigKey;
import edu.labIV.cfg.ConfigSection;
import edu.labIV.exception.MailException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailSender {

    private static MailSender instance;

    private String account;
    private String pass;
    private String protocol;
    private String host;
    private String port;
    private DataSource logoSource;

    private MailSender(){
        account = Config.getInstance().getMailAccount();
        pass = Config.getInstance().getMailPassword();
        protocol = Config.getInstance().getMailProtocol();
        host = Config.getInstance().getMailHost();
        port = Config.getInstance().getMailPort();
        logoSource = new FileDataSource("res/logo/logo.jpg");
    }

    public static MailSender getInstance() {
        if(instance == null)
            instance = new MailSender();
        return instance;
    }

    public void sendMail(String recipient, String subject, String body) throws MailException {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", account);
        props.put("mail.smtp.clave", pass);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(props);

        try {
            MimeMessage message = new MimeMessage(session);
            InternetAddress address = new InternetAddress(recipient);
            message.setFrom(address);
            message.addRecipient(Message.RecipientType.TO, address);
            message.setSubject(subject);

            MimeMultipart multipart = new MimeMultipart("related");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setDataHandler(new DataHandler(logoSource));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport transport = session.getTransport(protocol);
            transport.connect(host, account, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e){
            throw new MailException(recipient);
        }
    }

}
