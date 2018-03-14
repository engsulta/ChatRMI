/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.interfaces;

import com.jdevsul.DBclasses.Client;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author gehad
 */
public interface ServerAuthInt extends Remote {

    public boolean signup(ClientInterface clientRef) throws RemoteException;

    public Client login(String clientEmail, String clientPassword) throws RemoteException;

    public boolean logout(ClientInterface clientRef) throws RemoteException;

    public boolean updateMe(ClientInterface client) throws RemoteException;

    public Client searchForClient(String Email) throws RemoteException;

}
