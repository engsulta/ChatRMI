/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 */
public interface ServerManagerInt extends Remote {

    public ServerAuthInt getServerAuthentication() throws RemoteException;

    public ServerSendInt getServerSend() throws RemoteException;

    public ServerGroupsInt getServerGroups() throws RemoteException;

    public ServerRequestsInt getServerRequests() throws RemoteException;

    public ServerContactInt getServerContact() throws RemoteException;

    public void register(ClientInterface clientRef) throws RemoteException;

    public void unregister(ClientInterface clientRef) throws RemoteException;

}
