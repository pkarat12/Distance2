package ua.karatnyk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ua.karatnyk.service.MailService;
import ua.karatnyk.service.utilities.Mail;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSender sender;

	@Override
	public void sentMessage(final Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mail.getFrom());
		message.setTo(mail.getTo());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		sender.send(message);
	}

}
