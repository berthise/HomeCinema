/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author seb
 */
public class SendMail {
  public static void send (String to, String subject, String msg) throws SendMailException {
      // Sender's email ID needs to be mentioned
      final String from = "homecinema@mailoo.org";
      
      /* TODO: put in a config file */
      final String zz = "copier_n'est_pas_coller";


      // Get system properties
      Properties properties = new Properties();

      // Setup mail server
      properties.put("mail.smtp.host", "mail.mailoo.org"); //SMTP Host
      properties.put("mail.smtp.port", "587"); //TLS Port
      properties.put("mail.smtp.auth", "true"); //enable authentication
      properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

      //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, zz);
            }
        };
        Session session = Session.getInstance(properties, auth);
   

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);
	 message.addHeader("Content-type", "text/HTML; charset=UTF-8");

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(RecipientType.TO,
                                  new InternetAddress(to));

         // Set Subject: header field
         message.setSubject(subject, "UTF-8");

         // Now set the actual message
         message.setText(msg, "UTF-8");

         // Send message
         Transport.send(message);
	 Logger.getLogger(SendMail.class.getName()).log(Level.INFO, null, "envoie d'un mail vers: " + to);

      }catch (MessagingException mex) {
         throw new SendMailException();
      }
  }
}
