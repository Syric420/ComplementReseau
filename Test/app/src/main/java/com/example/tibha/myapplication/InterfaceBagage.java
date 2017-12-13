package com.example.tibha.myapplication;

/**
 * Created by tibha on 05/12/2017.
 */
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ProtocoleLUGAP.ReponseLUGAP;
import ProtocoleLUGAP.RequeteLUGAP;
import ProtocoleLUGAPM.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.*;
import android.widget.Toast;

public class InterfaceBagage extends Activity implements AdapterView.OnItemClickListener{
    List<Bagage> modeleBagage = new ArrayList<Bagage>();
    ArrayAdapter<Bagage> controleurBagage = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bagage);

        ListView vueBagage = (ListView) findViewById(R.id.listBagages);
        controleurBagage = new ArrayAdapter<Bagage>(this, android.R.layout.simple_list_item_checked, modeleBagage);
        vueBagage.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        vueBagage.setAdapter(controleurBagage);
        vueBagage.setOnItemClickListener(this);
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() {
        RequeteLUGAP req = new RequeteLUGAP(RequeteLUGAP.REQUEST_SHOWLUGAGE, "Charge utile");;
        ObjectOutputStream oos = null;
        try
        {
            oos = new ObjectOutputStream(InterfaceLogin.cliSock.getOutputStream());
            oos.writeObject(req); oos.flush();
        }
        catch (IOException e)
        {
            System.err.println("Erreur réseau ? [" + e.getMessage() + "]");
        }
        ObjectInputStream ois = null;
        ReponseLUGAP rep = null;
        ois = null;
        try

        {
            ois = new ObjectInputStream(InterfaceLogin.cliSock.getInputStream());
            rep = (ReponseLUGAP) ois.readObject();
        } catch (
                ClassNotFoundException e)

        {
            System.out.println("--- erreur sur la classe = " + e.getMessage());
        } catch (
                IOException e)

        {
            System.out.println("--- erreur IO = " + e.getMessage());
        }

        for(int i=0; i<rep.getVecBagage().size();i++)
        {
            controleurBagage.add(rep.getVecBagage().elementAt(i));
        }

        controleurBagage.notifyDataSetChanged();
            }
        });
        thread.start();


        /*List<Bagage> modele = new ArrayList<Bagage>();
        ArrayAdapter<Bagage> adapter =
                new ArrayAdapter<Bagage>(this,android.R.layout.simple_list_item_checked,
                        modele);*/
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, " checked : " , Toast.LENGTH_SHORT).show();
    }
}
