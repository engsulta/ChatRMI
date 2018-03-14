/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.server.db.DatabaseHandler;
import com.jdevsul.server.mainui.MainFXMLController;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author szmoh
 */
public class ServerAuthImpl extends UnicastRemoteObject implements ServerAuthInt, Serializable {

    testBinding c = new testBinding();
    static MainFXMLController mainController;
    static int count = 0;

    private static Vector<ClientInterface> clientsImplRef;

    public static void setMainFXMLController(MainFXMLController main) {
        mainController = main;
    }

    public ServerAuthImpl(Vector<ClientInterface> client) throws RemoteException {
        clientsImplRef = client;
        c.offlineClientsProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                    Object newVal) {
                System.out.println("offline has changed!");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainController.refreshGraphs();
                    }
                });
            }
        });
        c.onlineClientsProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                    Object newVal) {
                System.out.println("online has changed!");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainController.refreshGraphs();
                    }
                });

            }
        });
        c.femaleClientsProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                    Object newVal) {
                System.out.println("female has changed!");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainController.refreshGraphs();
                    }
                });
            }
        });
        c.maleClientsProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue o, Object oldVal,
                    Object newVal) {
                System.out.println("male has changed!");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        mainController.refreshGraphs();
                    }
                });
            }
        });
    }

    @Override
    public boolean signup(ClientInterface clientRef) throws RemoteException {
        // not tested yet
        Client currentClient = clientRef.getCurrentClient();
        boolean userExist = false;
        boolean userAdded = false;
        ArrayList<Client> allClients = DatabaseHandler.getInstance().getAllClients();
        for (int i = 0; i < allClients.size(); i++) {
            if (allClients.get(i).getClientEmail().equals(currentClient.getClientEmail())) {
                userExist = true;
            }

        }
        if (!userExist) {
            userAdded = DatabaseHandler.getInstance().addNewClient(clientRef.getCurrentClient());
            clientRef.getCurrentClient().setClientOnline(0);
            DatabaseHandler.getInstance().updateClient(clientRef.getCurrentClient());
            if (clientRef.getCurrentClient().getClientGender().equals("female")) {
                c.setFemaleClients(DatabaseHandler.getInstance().getNumberOfFemaleClients());
            } else {
                c.setMaleClients(DatabaseHandler.getInstance().getNumberOfMaleClients());
            }
        }

        return userAdded;
    }

    @Override
    public Client login(String clientEmail, String clientPassword) throws RemoteException {

        Client client = DatabaseHandler.getInstance().getClientByEmail(clientEmail);
        boolean invalidLogin;
        if (client != null) {
            if (!clientEmail.equals(client.getClientEmail()) || !clientPassword.equals(client.getClientPassword())) {
                client = null;
                invalidLogin = true;
            } else {
                client.setClientOnline(0);
                DatabaseHandler.getInstance().updateClient(client);
                c.setOnlineClients(DatabaseHandler.getInstance().getNumberOfOnlineClients());
                updateClientContacts();
            }

        }

        return client;
    }

    public void updateClientContacts() {
        System.out.println(clientsImplRef.size());
        for (ClientInterface clientRef : clientsImplRef) {
            try {
                clientRef.updateContactsInList();
            } catch (RemoteException ex) {
                Logger.getLogger(ServerAuthImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean logout(ClientInterface clientRef) throws RemoteException {
        Client client = clientRef.getCurrentClient();
        client.setClientOnline(1);
        System.out.println(client.getClientOnline());
        DatabaseHandler.getInstance().updateClient(client);
        c.setOfflineClients(DatabaseHandler.getInstance().getNumberOfOfflineClients());
        updateClientContacts();

        return true;
    }

    @Override
    public boolean updateMe(ClientInterface client) throws RemoteException {
        return DatabaseHandler.getInstance().updateClient(client.getCurrentClient());
    }

    @Override
    public Client searchForClient(String Email) throws RemoteException {
        Client client = DatabaseHandler.getInstance().getClientByEmail(Email);
        return client;
    }

}
