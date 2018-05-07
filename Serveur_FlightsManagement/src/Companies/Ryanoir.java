/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Companies;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author vincevin
 */
public class Ryanoir implements Company {

    @Override
    public void createDestination(String source, String dest) {
        System.out.println("Create destination : "+source+" to "+dest);
    }

    @Override
    public void createFlights(String source, String dest, Date d, Time t, double pr) {
        System.out.println("Create flight : "+source+" to "+dest+"\n on "+d.toString()+" at "+t.toString()+" price = "+pr);
    }

    @Override
    public void cancelDestination(String source, String dest) {
        System.out.println("Cancel destination : "+source+" to "+dest);
    }

    @Override
    public void cancelFlights(String source, String dest, Date d, Time t, double pr) {
        System.out.println("Create flight : "+source+" to "+dest+"\n on "+d.toString()+" at "+t.toString()+" price = "+pr);
    }
    
}
