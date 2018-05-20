package com.vetClinic.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailHandler  {
	
	
    
    public void send(String from, String to, String message, String subject) throws AddressException, MessagingException {
    	String host = "smtp.gmail.com";
        String username = from;
        String password = "prababcia";
        Properties props = new Properties();
        props.put("mail.smtp.from", "testdevpw@gmail.com");
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.port", 465);
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.socketFactory.port", 465);
    	props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	props.put("mail.smtp.socketFactory.fallback", "false");
    	props.put("mail.smtp.starttls.enable", "true");
        
       
        Session mailSession = Session.getInstance(props, new Authenticator() {
        	
    	    @Override
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication("testdevpw@gmail.com", password);
    	    }
    	
    	});
        
        
        MimeMessage msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress("testdevpw@gmail.com"));
        msg.setSubject(subject);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setContent(message, "text/html;charset=utf-8");
        msg.setSentDate(new java.util.Date());
        // set the message content here
        try {
			Transport.send(msg);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
	
	
	/*
	private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    public void handleMessage(String from, String to, String message, String subject) {
    	MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress(from));
                mimeMessage.setText(message);
                mimeMessage.setSubject(subject);
            }
        };

        try {
            this.mailSender.send(preparator);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.out.println(ex.getMessage());
        }
    }
    */
	
}
