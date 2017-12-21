/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Gui.JApplication_Mail;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;

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
        this.user = user;
        this.mdp = mdp;
        this.gui = gui;
        this.host = host;
    }
    
    
    
    @Override
    public void run() {
        try {
            Properties prop = System.getProperties();
            
            System.out.println("Création d'une session mail");
            prop.put("mail.pop3.host", host);
            prop.put("mail.disable.top", true);
            prop.put("mail.pop3.port", 995);
            prop.put("mail.smtp.starttls.enable", "true");
            Session sess = Session.getDefaultInstance(prop, null);
            //prop.list(System.out);
            String Local = this.getUser();
            String pwd = this.getMdp();
            System.out.println("Obtention d'un objet store");
            Store st = sess.getStore("pop3");
            //st.connect(host, Local, pwd);
            st.connect(host, 995, user, pwd);
            System.out.println("Obtention d'un objet folder");
            Folder f = st.getFolder("INBOX");
            f.open(Folder.READ_ONLY);
            System.out.println("Obtention des messages");
            Thread.sleep(this.getTempsMili());
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadReception.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(ThreadReception.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
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
