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
import android.widget.Button;
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

public class InterfaceBagage extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{
    List<Bagage> modeleBagage = new ArrayList<Bagage>();
    ArrayAdapter<Bagage> controleurBagage = null;
    ListView vueBagage = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bagage);

        vueBagage = (ListView) findViewById(R.id.listBagages);
        controleurBagage = new ArrayAdapter<Bagage>(this, android.R.layout.simple_list_item_checked, modeleBagage);
        vueBagage.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        vueBagage.setAdapter(controleurBagage);
        vueBagage.setOnItemClickListener(this);

        Button b = (Button) this.findViewById(R.id.bSauvegarder);
        b.setOnClickListener(this);

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


    public void onClick(View v)
    { if (v == this.findViewById(R.id.bSauvegarder))
    { /*String msg = "Salut";
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();*/

        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() {
                Vector<Bagage> monVec = new Vector<Bagage>();
                for(int i=0; i<modeleBagage.size();i++)
                {
                    monVec.add(modeleBagage.get(i));
                }
                RequeteLUGAP req = new RequeteLUGAP(RequeteLUGAP.REQUEST_UPDATELUGAGE, "Charge utile", monVec);;
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
            }
        });thread.start();

    }
    }

        @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if((modeleBagage.get(i).isChargeEnSoute().equalsIgnoreCase("N")))
        {
            modeleBagage.get(i).setChargeEnSoute("O");
            Toast.makeText(this, "Chargé en soute = 0", Toast.LENGTH_SHORT).show();
        }
        else
        {
            modeleBagage.get(i).setChargeEnSoute("N");
            Toast.makeText(this, "Chargé en soute = N", Toast.LENGTH_SHORT).show();
        }
    }
}
