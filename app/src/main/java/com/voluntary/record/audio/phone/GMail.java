package com.voluntary.record.audio.phone;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import android.os.Environment;
import android.util.Log;


public class GMail {

    final String emailPort = "587";// gmail's smtp port
    final String smtpAuth = "true";
    final String starttls = "true";
    final String emailHost = "smtp.gmail.com";
    // final String fromUser = "giftvincy@gmail.com";
    // final String fromUserEmailPassword = "jk2008gv";

    String fromEmail;
    String fromPassword;
    List<String> toEmailList;
    String emailSubject;
    String emailBody;

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    public GMail() {

    }

    public GMail(String fromEmail, String fromPassword,
                 List<String> toEmailList, String emailSubject, String emailBody) {
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmailList = toEmailList;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;


        emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);

    }

    public MimeMessage createEmailMessage() throws AddressException,
            MessagingException, UnsupportedEncodingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);

        emailMessage.setFrom(new InternetAddress(fromEmail, fromEmail));
        for (String toEmail : toEmailList) {

            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));
        }

        emailMessage.setSubject(emailSubject);

/*
        MimeBodyPart mbp1 = new MimeBodyPart();
    //    mbp1.setText(body);

        MimeBodyPart mbp2 = new MimeBodyPart();

        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File(sdPath.getAbsolutePath() + "/audiorecorder/1record.3gpp");

        FileDataSource fds = new FileDataSource(sdPath);     // + "/audiorecorder/1record.3gpp"
        mbp2.setDataHandler(new DataHandler(fds));
        mbp2.setFileName(fds.getName());

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp1);
        mp.addBodyPart(mbp2);
        emailMessage.setText(emailBody,mp);//
//        emailMessage.setContent(mp);

*/
        /*

        File sdPath = Environment.getExternalStorageDirectory();
        sdPath = new File("/sdcard/audiorecorder/1record.3gpp");

        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText(""+sdPath);

        MimeBodyPart mbp2 = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(sdPath);
        mbp2.setDataHandler(new DataHandler(fds));
        mbp2.setFileName(fds.getName());

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp1);
        mp.addBodyPart(mbp2);

        emailMessage.setContent(mp);
*/

   //   emailMessage.setContent(emailBody, "text/html");// for a html email

        emailMessage.setContent(emailBody,  "text/plain; charset=\"UTF-8\"");// for a html email


        return emailMessage;
    }

    public void sendEmail() throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);

        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();

    }

}


