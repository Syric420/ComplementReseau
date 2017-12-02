/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SNMP;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.snmp4j.*;
import org.snmp4j.event.*;
import org.snmp4j.mp.*;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;
import org.snmp4j.transport.*;

/**
 *
 * @author Vince
 */
public class SnmpLib {

    private TransportMapping transport;
    private Snmp snmp;
    private CommunityTarget target;
    
    public SnmpLib(String comm, String targetAd) {
        try {
            transport = new DefaultUdpTransportMapping();
            transport.listen();
            snmp = new Snmp(transport);
            target = new CommunityTarget();
            target.setCommunity(new OctetString(comm));
            Address targetAddress = GenericAddress.parse(targetAd);
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version2c);
        } catch (IOException ex) {
            Logger.getLogger(SnmpLib.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Get()
    {
        try
        {
            
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(".1.3.6.1.2.1.1.1.0")));
            pdu.setType(PDU.GET);
            SnmpListener listener = new SnmpListener(snmp);
            snmp.send(pdu, target, null, listener);
            synchronized(snmp)
            {
                snmp.wait();
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(SnmpLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(SnmpLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Set()
    {
        try
        {
            
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(".1.3.6.1.2.1.1.5.0"), new OctetString("Vince")));
            pdu.setType(PDU.SET);
            SnmpListener listener = new SnmpListener(snmp);
            snmp.send(pdu, target, null, listener);
            synchronized(snmp)
            {
                snmp.wait();
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(SnmpLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(SnmpLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
