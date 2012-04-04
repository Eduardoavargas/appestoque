package br.com.appestoque;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Util {

	public static void enviarEmail(){
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        String msgBody = "...";
        try {
            Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("suporte@appestoque.com.br", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,new InternetAddress("andre.tricano@gmail.com", "Mr. User"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (UnsupportedEncodingException e) {    
        	// ...
        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
	}
	
}