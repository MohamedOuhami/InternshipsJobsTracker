package com.v01d.LearnSpringSecurityJWT.util;

import java.util.Properties;
import java.util.Random;

import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {

  private final String username = "mohamed.ouhami2001@gmail.com";
  private final String password = "tfro fiks baay qqlg";

  public int sendActivationCode(String email) {

    Random random = new Random();
    int activationCode = 10000 + random.nextInt(90000);
    System.out.println("Random 5-digit number: " + activationCode);

    sendEmail(email,activationCode);

    return activationCode;

  }

  public void sendEmail(String email,int activationCode) {
    // Set mail server properties
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", "465");
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.socketFactory.port", "465");
    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    // Create a session with an authenticator
    Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    try {
      // Create a new email message
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("mohamed.ouhami2001@gmail.com"));
      message.setRecipients(
          Message.RecipientType.TO,
          InternetAddress.parse(email));
      message.setSubject("Testing Gmail SSL");
      message.setText("Dear Receiver, this is your activaton code : " + activationCode);

      // Send the email
      Transport.send(message);

      System.out.println("Email sent successfully!");

    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
