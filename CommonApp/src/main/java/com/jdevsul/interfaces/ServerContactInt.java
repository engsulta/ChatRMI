/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.interfaces;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author gehad
 */
public interface ServerContactInt extends Remote {

    public boolean removeContact(Contact contact) throws RemoteException;

    public ArrayList<Client> getMyContacts(int myID) throws RemoteException;

    public boolean isContact(Contact contact) throws RemoteException;
}
