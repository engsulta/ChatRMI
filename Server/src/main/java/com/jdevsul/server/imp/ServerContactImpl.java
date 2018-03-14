/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.interfaces.ServerContactInt;
import com.jdevsul.server.db.DatabaseHandler;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author szmoh
 */
public class ServerContactImpl extends UnicastRemoteObject implements ServerContactInt, Serializable {

    ServerContactImpl() throws RemoteException {
    }

    @Override
    public boolean removeContact(Contact contact) throws RemoteException {
        return DatabaseHandler.getInstance().removeContact(contact.getContactID());
    }

    @Override
    public ArrayList<Client> getMyContacts(int myID) throws RemoteException {
        return DatabaseHandler.getInstance().getMyContacts(myID);
    }

    @Override
    public boolean isContact(Contact contact) throws RemoteException {
        return DatabaseHandler.getInstance().isContact(contact);
    }

}
