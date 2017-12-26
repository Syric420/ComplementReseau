/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Gui.JApplication_Mail;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
            
            for (int i=0; i<msg.length; i++)
            {
                Thread.sleep(10);
                //System.out.println("Date : " + msg[i].getSentDate());
                // Récupération du conteneur Multipart
                /*if(msg[i].isMimeType("multipart/*"))
                {
                    Multipart msgMP = (Multipart)msg[i].getContent();
                    int np = msgMP.getCount();
                    //System.out.println("-- Nombre de composantes = " + np);
                    // Scan des BodyPart
                    for (int j=0; j<np; j++)
                    {
                        Part p = ((Multipart)msgMP).getBodyPart(j);
                        String d = p.getDisposition();
                        if (p.isMimeType("text/plain"))
                        {
                            //gui.dlm.addElement(msg[i]);
                        }
                        if (d!=null && d.equalsIgnoreCase(Part.ATTACHMENT))
                        {
                            InputStream is = p.getInputStream();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            int c;
                            while ((c = is.read()) != -1) baos.write(c);
                            baos.flush();
                            String nf = p.getFileName();
                            FileOutputStream fos =new FileOutputStream(nf);
                            baos.writeTo(fos);
                            fos.close();
                        }
                    } // fin for j
                    
                }
                else if(msg[i].isMimeType("text/*"))
                {
                     
                    
                    
                }*/
                
                gui.dlm.addElement(msg[i]);
            }
            System.out.println("Fin des messages");
            
            Thread.sleep(this.getTempsMili());
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
