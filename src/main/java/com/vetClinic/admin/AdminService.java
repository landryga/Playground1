package com.vetClinic.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.mail.MailHandler;
import com.vetClinic.todo.Todo;

@Service
public class AdminService {
	
	private static List<UserMaintainer> users = new ArrayList<UserMaintainer>();
	
	public void addUser(UserMaintainer userMaintainer) {
		
		UsersDAOimpl userdao = new UsersDAOimpl();
		
		char[] chars = "abcdefghijklmnopqrstuvwxy123456789*&^%$#@!()z".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		
		String password = sb.toString();
		/*
		MailConfigurer conf = new MailConfigurer();
		
		JavaMailSenderImpl impl = conf.setMailSender();
		
		*/
		MailHandler handler = new MailHandler();
		
		userMaintainer.setPassword(password);
		
		userdao.addUser(userMaintainer);
		
		try {
			handler.send("testdevpw@gmail.com", userMaintainer.getEmail(), "Witaj w programie WetPrzychodnia/n/n Twoje haslo to: " + password, "Witaj w programie WetPrzychodnia");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void updateUser(UserMaintainer userMaintainer) {
		UsersDAOimpl userdao = new UsersDAOimpl();
		
	
		UserMaintainer dbRef = new UserMaintainer();
		
		int UserId = userMaintainer.getId();
		
		UsersDAOimpl userDB = new UsersDAOimpl();
		
		dbRef = userDB.get(UserId);
		
		//userMaintainer.getIs_admin();
		
		if(userMaintainer.getIs_admin() != dbRef.getIs_admin()) {
			
			userDB.updateUserRole(userMaintainer);
		}
		
		if(userMaintainer.isIs_doctor()!= dbRef.isIs_doctor()) {
			userDB.updateDoctorBoolean(userMaintainer);
		}
	}
	
	public List<UserMaintainer> retrieveUsers() {
		
		UsersDAOimpl userdao = new UsersDAOimpl();
		
		users = userdao.list();
			
		return users;
	}
	
	public UserMaintainer retrieveUser(int id) {
		for (UserMaintainer user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}
	
	public UserMaintainer retrieveUser(String name) {
		UsersDAOimpl userdao = new UsersDAOimpl();
		
		return userdao.get(name);
	}
	
	public List<UserMaintainer> retrieveDoctors() {
		
		UsersDAOimpl userdao = new UsersDAOimpl();
		
		users = userdao.listDoctors();
			
		return users;
	}
	
}
