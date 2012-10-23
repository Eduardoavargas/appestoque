package br.com.appestoque;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class Util {

	public static void enviarEmail(String email, String assunto,CharSequence corpo) {
		try {			
			String[] emails = email.split(Constantes.DELIMITADOR_EMAIL);
			Properties props = new Properties();
			Session mailSession = Session.getDefaultInstance(props, null);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress("appestoque@gmail.com"));		
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(emails[0]));
			if(emails.length>1){
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(emails[1]));
			}
//			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//			msg.addRecipient(Message.RecipientType.CC, new InternetAddress("leila@perfilmec.com.br"));
			msg.setSubject(assunto);
			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(corpo.toString(),"text/html");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static String dateToStr( String formato, Date data ){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		return simpleDateFormat.format(data);
	}
	
	public static String doubleToString( double valor, String mask){
		DecimalFormat decimalFormat = new DecimalFormat(mask);
		return decimalFormat.format(valor);   
	}
	
}