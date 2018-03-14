/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.interfaces;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.DBclasses.FriendRequest;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author gehad
 */
public interface ServerRequestsInt extends Remote {

    public Contact acceptNewRequest(FriendRequest request) throws RemoteException;//and the server will add the contact to you and your friend implecit

    public void addNewRequest(FriendRequest request) throws RemoteException;

    public ArrayList<Client> getMyRequested(int myID) throws RemoteException;

    public void removeFriendRequest(FriendRequest fr, int status) throws RemoteException;

    public boolean isFriendRequestExist(FriendRequest request) throws RemoteException;
}
