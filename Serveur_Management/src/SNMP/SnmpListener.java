/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SNMP;

import java.util.Vector;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;

/**
 *
 * @author Vince
 */
public class SnmpListener implements ResponseListener
{
    private Snmp snmpManager;
    private String resultat;
    public SnmpListener (Snmp s)
    {
        snmpManager = s;
        resultat = null;
    }
    
    @Override
    public void onResponse(ResponseEvent event)
    {
        ((Snmp)event.getSource()).cancel(event.getRequest(), this);
        System.out.println("Réponse reçue (PDU): "+event.getResponse());
        
        Vector vecReponse = event.getResponse().getVariableBindings();
        if(vecReponse.size()==1)
            resultat = vecReponse.elementAt(0).toString();
        /*for (int i=0; i<vecReponse.size(); i++)
        {
            System.out.println("Elément n°"+i+ " : "+vecReponse.elementAt(i));
        }*/
        //resultat = event.getResponse().;
        synchronized(snmpManager)
        {
            snmpManager.notify();
        }
    }

    /**
     * @return the resultat
     */
    public String getResultat() {
        return resultat;
    }
}
            
