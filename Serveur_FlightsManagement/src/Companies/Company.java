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
public interface Company {
    void createDestination(String source, String dest);
    void createFlights(String source, String dest, Date d, Time t, double pr);
    void cancelDestination(String source, String dest);
    void cancelFlights(String source, String dest, Date d, Time t, double pr);

}
