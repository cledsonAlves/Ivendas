package Logica;


import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




/**  Classe responsavel pelo envio do e-mail ao cliente ...   Codigo retirado do site DEVMedia   **/ 



public class Email {		
	private static  Address[] destinatarios;
	


	   
	 public void enviaEmail(String rca){
		//  public void enviaEmail(String cliente){
	      Properties props = new Properties();           
	           props.put("mail.smtp.host", "mail.kelma.com.br");  
	           props.put("mail.smtp.auth", "true"); 
	           

 	           Session session = Session.getDefaultInstance(props,
	                       new javax.mail.Authenticator() {
	                            protected PasswordAuthentication getPasswordAuthentication()
	                            {
	                                  return new PasswordAuthentication("kelma033@kelma.com.br", "kelma@2012");
	                            }
	                       });

	           /** Ativa Debug para sess�o */
	           session.setDebug(true);
	           try {

	                 Message message = new MimeMessage(session);
	                 message.setFrom(new InternetAddress("marketing@kelma.com.br")); //Remetente

	               //  destinatarios = InternetAddress //Destinat�rio(s)
	                         //   .parse("ti@kelma.com.br,clsddd@hotmail.com"); 
	                    //alansadao@hotmail.com    marcosac22@gmail.com  //miqueiaskelma@gmail.com //carloskelma@gmail.com \esasimoes03@terra.com.br
	                 
	                // destinatarios = InternetAddress.parse(dados[5]+ ","+dados[6]+ ",naoresponder@kelma.com.br,miqueiaskelma@gmail.com"); 
	                 destinatarios = InternetAddress.parse(rca);

	                 message.setRecipients(Message.RecipientType.TO, destinatarios);
	                 message.setSubject("Espelho Pedido");//Assunto

	                 
	   

	 		        String mensagem = "Email enviado automaticamente, nao responder! \r\n"+
	 		            "\r\n"+
	 		        	"   Prezado Cliente :   \r\n" +                                       
	 		        	" acusamos o recebimento de seu pedido nesta data ,  o mesmo est� em processamento e ser� enviado o mais breve poss�vel. Informamos que o prazo medio de entregas e de '4' dias uteis!"+
	 		        	"Aproveitamos para confirmar o endere�o de entrega: \r\n "+
	 		            "\r\n" +
	 		        	"\r\n  qualquer diverg�ncia pedimos nos informar atrav�s do e-mail: sac@kelma.com.br ou pelo telefone 08007778855!  " +
	 		        	"\r\n" +
	 		        	"\r\n                               Agradecemos sua prefer�ncia."+
	 		        	"\r\n"+
	 		            "                                    Kelma Cosmeticos        ";
	                 
	 		       
	 	            //  envio da mensagem .... 
	                 message.setText(mensagem);
	                 
	                 
	                 /**M�todo para enviar a mensagem criada*/
	                 Transport.send(message);

	                 System.out.println("Feito!!!");

	           } catch (MessagingException e) {
	                 throw new RuntimeException(e);
	           }  
	   }



	
}
