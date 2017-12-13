package com.example.tibha.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import java.io.*;
import ProtocoleLUGAP.*;
import Utilities.*;
import android.content.*;
import java.net.InetAddress;
import java.net.Socket;
import android.os.*;

public class InterfaceLogin extends AppCompatActivity {

    private boolean logged;
    private ObjectInputStream ois;
    public static Socket cliSock;
    private int PORT_CHECKIN;
    private String IP_ADDRESS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
    }
    public void login(View v)
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run() {
                cliSock = null;


                TextView textViewLogin = (TextView) findViewById(R.id.textLogin);
                TextView textViewPassword = (TextView) findViewById(R.id.textPassword);
                String login = textViewLogin.getText().toString();
                String pass = textViewPassword.getText().toString();

                try

                {
                    cliSock = new Socket(InetAddress.getByName("192.168.1.51"),26084);
                } catch (
                        IOException e)

                {
                    e.printStackTrace();
                }


                Identify log = new Identify();
                if (login != null && !"".equals(login) && pass != null && !"".equals(pass))

                {
                    log.setLogin(login);
                    log.setPassword(pass);
                    log.setMd();

                    RequeteLUGAP req;
                    req = log.sendLogin();

                    ObjectOutputStream oos = null;
                    try {
                        oos = new ObjectOutputStream(cliSock.getOutputStream());
                        oos.writeObject(req);
                        oos.flush();
                    } catch (IOException e) {
                        System.err.println("Erreur réseau ? [" + e.getMessage() + "]");
                    }
                }

                ReponseLUGAP rep = null;
                ois = null;
                try

                {
                    ois = new ObjectInputStream(cliSock.getInputStream());
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


                if (rep.getChargeUtile().equals("LOGIN OK"))

                {
                    System.out.println("Connection OK");
                    Intent coOK = new Intent(InterfaceLogin.this,InterfaceBagage.class);
                    startActivity(coOK);
                } else

                {
                    setLogged(false);
                    System.out.println("Connection NOT OK");
                    showToast("mauvaise combinaison de user/password");
                }
            }

        });
        thread.start();
    }
    public void showToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            public void run()
            {
                Toast.makeText(InterfaceLogin.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }
}
