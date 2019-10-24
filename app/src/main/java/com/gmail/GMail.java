package com.gmail;

import android.os.Environment;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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

public class GMail {

	final String emailPort = "587";// gmail's smtp port
	final String smtpAuth = "true";
	final String starttls = "true";
	final String emailHost = "smtp.gmail.com";

	String fromEmail;
	String fromPassword;
	List<String> toEmailList;
	String emailSubject;
	String emailBody;
	String filename;


	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public GMail() {

	}

	public GMail(String fromEmail, String fromPassword,List<String> toEmailList, String emailSubject, String emailBody,String pathname,String filename) {
		this.fromEmail = fromEmail;
		this.fromPassword = fromPassword;
		this.toEmailList = toEmailList;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		this.filename = filename;

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
			Log.i("GMail", "toEmail: " + toEmail);
			emailMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toEmail));
		}

		emailMessage.setSubject(emailSubject);
		BodyPart messageBodyPart = new MimeBodyPart();
		// Now set the actual message
		messageBodyPart.setText("This is message body");
		// Create a multipar message
		Multipart multipart = new MimeMultipart();
		// Set text message part
		multipart.addBodyPart(messageBodyPart);
		// Part two is attachment
	messageBodyPart = new MimeBodyPart();

	String filenameone =  Environment.getExternalStorageDirectory() + "/DCIM/Camera/"+filename;

	//	String filenameone =  Environment.getExternalStorageDirectory() + "/callrecorder/"+filename;


						DataSource source = new FileDataSource(filenameone);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);
		// Send the complete message parts
		emailMessage.setContent(multipart);
	//	emailMessage.setContent(emailBody, "text/html");// for a html email
	// emailMessage.setText(emailBody);// for a text email
		return emailMessage;
	}

	public void sendEmail() throws AddressException, MessagingException {

		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailHost, fromEmail, fromPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
	}

}
