/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.mainui;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.common.Notification;
import com.jdevsul.server.db.DatabaseHandler;
import com.jdevsul.server.imp.ServerManagerImpl;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author szmoh
 */
public class ControllerOperations {

    private Registry reg;
    private boolean serviceEnabled;
    private MainFXMLController Main;

    public ControllerOperations() {
        serviceEnabled = false;
        reg = null;
    }

    void startService() {

        try {
            if (reg == null) {
                reg = LocateRegistry.createRegistry(Integer.parseInt(Main.getServerportNumberString()));
            }
            reg.rebind("ChatService", ServerManagerImpl.getInstance());
            System.out.println("Server is up");

        } catch (RemoteException ex) {
            Logger.getLogger(ControllerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void stopService() {

        try {
            reg.unbind("ChatService");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ControllerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String toggleService() {
        if (isServiceEnabled()) {
            stopService();
            setServiceEnabled(false);
            return ("SERVER OFF");
        } else {
            startService();
            setServiceEnabled(true);
            return ("SERVER ON");
        }
    }

    /**
     * @return the serviceEnabled
     */
    public boolean isServiceEnabled() {
        return serviceEnabled;
    }

    /**
     * @param serviceEnabled the serviceEnabled to set
     */
    public void setServiceEnabled(boolean serviceEnabled) {
        this.serviceEnabled = serviceEnabled;
    }

    public static void sendNotification(String imgURL, String title, String content) throws RemoteException {
        Notification notification = new Notification();

        ArrayList<Integer> usersIDs = new ArrayList<>();
        ArrayList<Client> allUsers = DatabaseHandler.getInstance().getAllClients();

        for (Client user : allUsers) {
            usersIDs.add(user.getClientID());
        }

        notification.setImgBytes(null);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setToID(usersIDs);

        ServerManagerImpl.getInstance().getServerSend().sendNotification(notification);
    }

    public void setmain(MainFXMLController main) {
        Main = main;
    }

}
