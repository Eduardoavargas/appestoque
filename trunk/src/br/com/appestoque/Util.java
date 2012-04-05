package br.com.appestoque;

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

	public static void enviarEmail(String email, String assunto, CharSequence corpo){
//		Properties props = new Properties();
//	    Session mailSession = Session.getDefaultInstance(props, null);    
        try {
        	
//        	Message msg = new MimeMessage(mailSession);
//            msg.setFrom(new InternetAddress("appestoque@gmail.com"));
//            msg.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
//            msg.setSubject(assunto);
//            
//            msg.setText(corpo.toString());
            
//            Multipart multipart = new MimeMultipart();
//            MimeBodyPart mimeBodyPart = new MimeBodyPart();
//            mimeBodyPart.setContent(corpo.toString(),"text/html");
//            multipart.addBodyPart(mimeBodyPart);
//            msg.setContent(multipart);
            
//            Transport.send(msg);
        	
        	Properties props = new Properties();
            Session mailSession = Session.getDefaultInstance(props, null);    
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress("appestoque@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject(assunto);
            msg.setText(corpo.toString());
            Transport.send(msg);
        	
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
}