package com.javachina.service.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import com.blade.ioc.annotation.Component;
import com.javachina.Constant;
import com.javachina.service.SendMailService;
import com.jmail.MailMessage;
import com.jmail.MailSender;
import com.jmail.MailSenderImpl;

import blade.kit.logging.Logger;
import blade.kit.logging.LoggerFactory;
import jetbrick.template.JetEngine;
import jetbrick.template.JetTemplate;

@Component
public class SendMailServiceImpl implements SendMailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendMailServiceImpl.class);
	
	private MailSender mailSender = new MailSenderImpl();
	private MailMessage mailMessage = new MailMessage();
	
	@Override
	public void signup(String username, String email, String code) {

		try {
			String link_url = Constant.SITE_URL + "/active/" + code;
			
			String url = "<a href='"+link_url+"'>"+ link_url +code+"</a>";
	        JetEngine engine = JetEngine.create();
	        
	        JetTemplate template = engine.getTemplate("/template/register.html");
	        
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("username", username);
			context.put("email", Constant.MAIL_ADMIN);
			context.put("url", url);

			StringWriter writer = new StringWriter();
	        template.render(context, writer);
	        
			String output = writer.toString();
			
//			System.out.println(output);
			
			mailMessage
			.subject("BladeJava 注册邮件")
			.from(Constant.MAIL_USER)
			.content(output)
			.addTo(email);
			
			mailSender.host(Constant.MAIL_HOST).username(Constant.MAIL_USER).password(Constant.MAIL_PASS);
			
			mailSender.send(mailMessage, true);
			
			LOGGER.info("user {} signup, send mail [{}] success, code = [{}]", username, email, code);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void forgot(String username, String email, String code) {
		try {
			String link_url = Constant.SITE_URL + "/active/" + code;
			
			String url = "<a href='"+link_url+"'>"+ link_url +code+"</a>";
	        JetEngine engine = JetEngine.create();
	        
	        JetTemplate template = engine.getTemplate("/template/forgot.html");
	        
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("username", username);
			context.put("email", Constant.MAIL_ADMIN);
			context.put("url", url);
			
			StringWriter writer = new StringWriter();
	        template.render(context, writer);
	        
			String output = writer.toString();
			
//			System.out.println(output);
			
			mailMessage
			.subject("BladeJava 找回密码邮件")
			.from(Constant.MAIL_USER)
			.content(output)
			.addTo(email);
			
			mailSender.host(Constant.MAIL_HOST).username(Constant.MAIL_USER).password(Constant.MAIL_PASS);
			
			mailSender.send(mailMessage, true);
			
			LOGGER.info("user {} signup, send mail [{}] success, code = [{}]", username, email, code);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
