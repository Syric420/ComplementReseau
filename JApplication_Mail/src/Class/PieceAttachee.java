/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;

/**
 *
 * @author Vince
 */
public class PieceAttachee implements Serializable {
    public static int IMAGE = 1;
    public static int DIGEST = 2;
    public static int PIECE_ATTACHEE = 3;
    
    private int type;
    private String fileName;
    private String pathFile;
    private byte[] digest;
    
    public PieceAttachee(int type, byte[]di)
    {
        this.type = type;
        this.digest = di;
    }

    public PieceAttachee(int type, String path, String fn) 
    {
            this.type = type;
            this.pathFile = path;
            this.fileName = fn;
    }
    
    public MimeBodyPart createMsgBodyPartDigest()
    {
        /*try {
            
            MimeBodyPart msgBP = new MimeBodyPart();
            System.out.println("Digest = "+digest.toString());
            DataSource so = new FileDataSource(digest);
            msgBP.setDataHandler (new DataHandler (so));
            msgBP.setFileName(fileName);
            //DataSource so = new 
            return msgBP;
        } catch (MessagingException ex) {
            System.err.println("Message erreur : "+ex);
        }*/
        return null;
    }
    
    public MimeBodyPart createMsgBodyPartImage()
    {
        try {
            
            MimeBodyPart msgBP = new MimeBodyPart();
            System.out.println("Path = "+pathFile);
            DataSource so = new FileDataSource(pathFile);
            msgBP.setDataHandler (new DataHandler (so));
            msgBP.setFileName(fileName);
            return msgBP;
        } catch (MessagingException ex) {
            System.err.println("Message erreur : "+ex);
        }
        return null;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the pathFile
     */
    public String getPathFile() {
        return pathFile;
    }

    /**
     * @param pathFile the pathFile to set
     */
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
    
}
