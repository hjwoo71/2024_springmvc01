package com.ict.edu05.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

// Service 에서 호출해서 사용할 클래스

public class EmailHandler {
	
	private JavaMailSender javaMailSender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public EmailHandler(JavaMailSender javaMailSender) throws Exception{
		this.javaMailSender = javaMailSender;
	
         message = this.javaMailSender.createMimeMessage(); 
         
		 messageHelper = new MimeMessageHelper(message , true , "UTF-8");

		 //단순한 텍스트 메세지만 사용
		 //messageHelper = new MimeMessageHelper(message ,  "UTF-8");
	     }
	
		 public void setSubject(String subject) throws Exception{
			 messageHelper.setSubject(subject);
		 }
		 
		 public void setText(String text) throws Exception{
			 
			 //true => 태그 사용가능
			 messageHelper.setText(text , true );
		 }
		 
		 public void setForm(String email, String name) throws Exception{
			 messageHelper.setFrom(email, name); 
		 }
		 
		 public void setTo(String email) throws Exception{
			 messageHelper.setTo(email);
		 }
		 
		 public void send() {
			 javaMailSender.send(message);
		 }
	
	
}
