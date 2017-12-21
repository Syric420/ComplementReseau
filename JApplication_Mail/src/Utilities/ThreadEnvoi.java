/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Gui.JApplication_Mail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Vince
 */
public class ThreadEnvoi extends Thread {
    private String user;
    private String mdp;
    private String to;
    private String subject;
    private String message;
    private JApplication_Mail gui;

    public ThreadEnvoi(String user, String mdp, String to, String subject, String message, JApplication_Mail gui) {
        this.user = user;
        this.mdp = mdp;
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.gui = gui;
    }
    

    

    @Override
    public void run() {
        Properties prop = System.getProperties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            System.out.println("Création d'une session mail");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.port", "587");
            Session sess = Session.getDefaultInstance(prop, null);
            
            String mdp = this.getMdp();
            
            try
            {
                System.out.println("Création du message");
                String exp = this.getUser();
                String dest = this.getTo();
                String sujet = this.getSubject();
                String texte = this.getMessage();
                MimeMessage msg = new MimeMessage (sess);
                msg.setFrom (new InternetAddress (exp));
                msg.setRecipient (Message.RecipientType.TO, new InternetAddress (dest));
                msg.setSubject(sujet);
                msg.setText (texte);
                System.out.println("Envoi du message");
                Transport.send(msg, exp, mdp);
                System.out.println("Message envoyé");
            }
            catch (MessagingException e)
            {
                System.out.println("Errreur sur message : " + e.getMessage());
                JOptionPane.showMessageDialog(gui, "Veuillez vous reconnecter avec des bons identifiants");
                ((JApplication_Mail)gui).getLog().setVisible(true);
            }
            catch (Exception e)
            {
                System.out.println("Errreur sur message : " + e.getMessage());
            }
            
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
