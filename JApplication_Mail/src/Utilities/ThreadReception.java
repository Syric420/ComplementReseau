/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Class.MessageView;
import Gui.JApplication_Mail;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vince
 */
public class ThreadReception extends Thread{

    private long tempsMili;
    private String user;
    private String mdp;
    private String host;
    private JApplication_Mail gui;

    public ThreadReception(long tempsMili, String user, String mdp, String host, JApplication_Mail gui) {
        this.tempsMili = tempsMili;
        this.user = "recent:"+user;
        this.mdp = mdp;
        this.gui = gui;
        this.host = host;
    }
    
    
    
    @Override
    public void run() {
        try {
            int nbMessages, nbNewMessages;
            Properties prop = System.getProperties();
            
            System.out.println("Création d'une session mail");
            prop.put("mail.store.protocol", "pop3s");
            prop.put("mail.pop3.host", "pop.gmail.com");  
           
            prop.put("mail.pop3.user", user);
            prop.put("mail.pop3.socketFactory", 995);
            prop.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.pop3.port", 995);

    Session session = Session.getInstance(prop,new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(user, mdp);

      }
    });
      
            

            //prop.list(System.out);
            String Local = this.getUser();
            String pwd = this.getMdp();
            Store st = session.getStore("pop3");
            st.connect(Local, pwd);
            Folder f = st.getFolder("INBOX");
            f.open(Folder.READ_ONLY);
            Message msg[] = f.getMessages();
            nbMessages = f.getMessageCount();
            System.out.println(nbMessages);
             if(SwingUtilities.isEventDispatchThread()) {
            System.out.println("updating list from Event Dispatch Thread");
            }else {
                System.out.println("updating list NOT from Event Dispatch Thread");
            }
            
            for (int i=0; i<msg.length; i++)
            {
                MessageView msgView = new MessageView(msg[i]);
                 
                //System.out.println(msgView);
                gui.dlm.add(0,msgView);
                
                //Thread.sleep(2);
            }
            
            System.out.println("Fin de la découverte des messages");
            //Une fois avoir tout lu, il va surveiller si il y a pas de nouveaux messages toutes les 5 min
            
            while(true)
            {
                Thread.sleep(5000);
                //Thread.sleep(this.getTempsMili());
                f = st.getFolder("INBOX");
                f.open(Folder.READ_ONLY);
                nbNewMessages = f.getMessageCount();
                
                System.out.println("Test si nouveau message");
                if(nbNewMessages > nbMessages)
                {
                    //Si il y a un/des nouveaux messages, on boucle pour les ajouter dans la liste (ils sont tjrs ajoutés à la fin)
                    JOptionPane.showMessageDialog(gui, "Un nouveau message est arrivé");
                    msg = f.getMessages();
                    for(int i=nbMessages; i<nbNewMessages;i++)
                        gui.dlm.add(0, new MessageView(msg[i]));
                }   
                else
                    System.out.println("Pas de nouveau message");
                
                nbMessages = nbNewMessages;
                
                
            }
        } catch (MessagingException ex) {
            Logger.getLogger(ThreadReception.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(ThreadReception.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the tempsMili
     */
    public long getTempsMili() {
        return tempsMili;
    }

    /**
     * @param tempsMili the tempsMili to set
     */
    public void setTempsMili(long tempsMili) {
        this.tempsMili = tempsMili;
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
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the gui
     */
    public JApplication_Mail getGui() {
        return gui;
    }

    /**
     * @param gui the gui to set
     */
    public void setGui(JApplication_Mail gui) {
        this.gui = gui;
    }
    
}
