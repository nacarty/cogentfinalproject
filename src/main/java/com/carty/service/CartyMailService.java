package com.carty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


import com.carty.model.User;
import com.carty.model.UserDto;



@Component
public class CartyMailService {


	@Autowired
	JavaMailSender mailsender;
	
	void sendEmail(UserDto user) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(user.getEmail(), "nacarty@gmail.com");
		msg.setFrom("nacarty@gmail.com");
		msg.setSubject("CARTY'S INSURANCE WELCOME EMAIL");
		String message = "\r\n" + 
				"<h2>Welcome To CARTY'S INSURANCE!</h2>\r\n" + 
				"<p style=\"font-size: 1.5em;\">On behalf of the Management and Staff of Carty's Insurance, I wish to extend a warm welcome to you as we partner to help secure your future.</p>\r\n" + 
				"<p style=\"font-size: 1.5em;\">To log into our system to view your account details and make online payments, you are required to use the following credentials:</p>\r\n" + 
				"<p style=\"font-size: 1.5em;\">Your email: +\"\"</p>\r\n" + 
				"<p style=\"font-size: 1.5em;\">Password: +\"\"</p>\r\n" + 
				"<p style=\"font-size: 1.5em;\">We have the following personal details on our record:</p>\r\n" + 
				"<table style=\"border-color: black; height: 239px; background-color: #22ee22;\" border=\"3\" width=\"500\" cellspacing=\"1\" cellpadding=\"5\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 490px;\" colspan=\"3\"><span style=\"color: #000000;\">PERSONAL DETAILS</span></td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">Name</td>\r\n" + 
				"<td style=\"width: 159.33px;\">"+user.fname+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">"+user.dob+"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">Email</td>\r\n" + 
				"<td style=\"width: 159.33px;\">"+user.email+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">SSN</td>\r\n" + 
				"<td style=\"width: 159.33px;\">"+user.SSN+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">DOB</td>\r\n" + 
				"<td style=\"width: 159.33px;\">"+user.dob+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">&nbsp;</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 490px;\" colspan=\"3\">ADDRESS</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">&nbsp;STREET</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;"+user.address.getStreet()+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">&nbsp;UNIT/APT</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;"+user.address.getUnit()+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">CITY</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;"+user.address.getCity()+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">STATE</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;"+user.address.getState()+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td style=\"width: 159.34px;\">ZIP CODE</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;"+user.address.getZip()+"-"+user.address.getZipplus()+"</td>\r\n" + 
				"<td style=\"width: 159.33px;\">&nbsp;</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"<p>&nbsp;YOUR PERSONAL INSURANCE AGENT,</p>\r\n" + 
				"<p>Name</p>";
		
		msg.setText(message);
		mailsender.send(msg);
	}



}
