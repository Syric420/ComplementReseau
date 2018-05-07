/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*; 
/**
 *
 * @author vincevin
 */

public class SaxParser extends DefaultHandler {
    protected String nomFichierXML;
    int cptTags =0; 

    public SaxParser()
    {
        this.setNomFichierXML(null);
    }
    
    public SaxParser(String s)
    {
        this.setNomFichierXML(s);
    }
    /**
     * @return the nomFichierXML
     */
    public String getNomFichierXML() {
        return nomFichierXML;
    }

    /**
     * @param nomFichierXML the nomFichierXML to set
     */
    public void setNomFichierXML(String nomFichierXML) {
        this.nomFichierXML = nomFichierXML;
    }
    
    static protected void trace (String s)
    {
        System.out.println(s);
    }
    static protected void trace (String sCte, String s)
    {
        System.out.println(sCte + " : " + s);
    } 
    
    static protected void trace (String sCte, int i)
    {
       System.out.println(sCte + " " + i);
    } 

    // Quelques méthodes du ContentHandler
    public void characters(char[] ch, int start,int length) throws SAXException
    {
        String chaine = new String(ch, start, length).trim();
        if (chaine.length() > 0) 
            trace("@ Caractères", chaine);
    }

    public void startDocument()throws SAXException
    {
        trace("** Début du document **");
    }
    public void endDocument()throws SAXException
    {
        trace("** Fin du document **");
    }

    public void startElement(java.lang.String uri, java.lang.String localName, java.lang.String qName, Attributes attr) throws SAXException
    {
        
        
        trace("-Début d'un élément numero-", cptTags);
        cptTags++;
        if (uri != null && uri.length()>0) 
            trace(" uri", uri);
        if (uri != null && uri.length()>0) 
            trace(" nom complet", qName);
        
        int nAttr = attr.getLength();
        trace(" nombre d'attributs", nAttr);
        if (nAttr ==0) return; // Denys like
        for (int i=0; i<nAttr; i++)
            trace(" attribut n°" + i + " = " + attr.getLocalName(i) + " avec valeur : " + attr.getValue(i));
    }

    public void endElement(java.lang.String uri, java.lang.String localName,java.lang.String qName) throws SAXException
    {
        trace("* Fin de l'élément " + qName);
        cptTags++;
        trace("++ compteur de tags", cptTags);
    } 

}
