/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.DBclasses.FriendRequest;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jdevsul.server.db.DatabaseHandler;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author szmoh
 */
public class ServerRequestImpl extends UnicastRemoteObject implements ServerRequestsInt, Serializable {

    private DatabaseHandler dbHandler;

    ServerRequestImpl() throws RemoteException {
        dbHandler = DatabaseHandler.getInstance();

    }

    @Override
    public Contact acceptNewRequest(FriendRequest request) throws RemoteException {
        //mfrod a7oto fl clients vector wla la?
        Contact newFriend = new Contact();
        newFriend.setClientID(request.getClientID());
        newFriend.setContactID(request.getFriendID());
        DatabaseHandler.getInstance().addNewContact(newFriend);
        return newFriend;
    }

    @Override
    public void addNewRequest(FriendRequest request) throws RemoteException {
        DatabaseHandler.getInstance().addNewFriendRequest(request);

    }

    @Override
    public ArrayList<Client> getMyRequested(int myID) throws RemoteException {
        return DatabaseHandler.getInstance().getMyFriendRequests(myID);
    }

    @Override
    public void removeFriendRequest(FriendRequest fr, int status) throws RemoteException {
        dbHandler.removeFriendRequest(fr, status);
    }

    @Override
    public boolean isFriendRequestExist(FriendRequest request) throws RemoteException {
        return DatabaseHandler.getInstance().isFriendRequestExist(request);
    }

}
