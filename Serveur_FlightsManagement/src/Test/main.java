/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Test.SaxParser.*;
import static Test.SaxParser.trace;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*; 
/**
 *
 * @author vincevin
 */

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // TODO code application logic here
    trace("Création du handler");
    SaxParser sc =
    new SaxParser("C:\\Users\\vincevin\\Google Drive\\Troisième année\\Programmation Réseaux, Web\\Serveur_FlightsManagement\\src\\Test\\vols_prevus_poubelle-air_000214.xml");
    
    trace("Création de la factory");
    SAXParserFactory spf = SAXParserFactory.newInstance();

    try
    {
        trace("Création du parser");
        SAXParser sp = spf.newSAXParser();
        trace("Mise en route du parsing \n\n");
        sp.parse(new File(sc.getNomFichierXML()), sc);
    }
    catch (ParserConfigurationException e)
    {
        System.out.println("Oh oh Problème de config : " + e.getMessage());
    }
    catch (SAXException e)
    {
        System.out.println("Oh oh Problème de SAX : " + e.getMessage());
    }
    catch (IOException e)
    {
        System.out.println("Oh oh Problème d'IO : " + e.getMessage());
    } 

    }
    
}
