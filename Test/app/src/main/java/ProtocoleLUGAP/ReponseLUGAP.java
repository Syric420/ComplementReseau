/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProtocoleLUGAP;

import ProtocoleLUGAPM.Bagage;
import java.io.*;
import Server.Reponse;
import java.util.Vector;
/**
 *
 * @author Vince
 */
public class ReponseLUGAP implements Reponse, Serializable
{
    /*public static int KEY_GENERATED = 202;
    public static int WRONG_PASSWORD = 401;*/
    public static int LOGIN_OK = 101;
    public static int LOGIN_FAIL = 102;
    public static int VOL_OK = 301;
    public static int VOL_FAIL = 302;
    public static int LUG_OK = 601;
    public static int LUG_FAIL = 602;
    public static int LUG_SHOW = 201;

    private int codeRetour;
    private String chargeUtile;
    private Vector<Bagage> vecBagage;
    public ReponseLUGAP(int c, String chu)
    {
        codeRetour = c;
        setChargeUtile(chu);
        vecBagage=null;
    }

    public ReponseLUGAP(int c, String chu, Vector<Bagage> bag)
    {
        codeRetour = c;
        setChargeUtile(chu);
        vecBagage = bag;
    }
    public int getCode() { return codeRetour; }
    public String getChargeUtile() { return chargeUtile; }
    public void setChargeUtile(String chargeUtile) { this.chargeUtile = chargeUtile; }

    /**
     * @return the vecBagage
     */
    public Vector<Bagage> getVecBagage() {
        return vecBagage;
    }

    /**
     * @param vecBagage the vecBagage to set
     */
    public void setVecBagage(Vector<Bagage> vecBagage) {
        this.vecBagage = vecBagage;
    }
}
