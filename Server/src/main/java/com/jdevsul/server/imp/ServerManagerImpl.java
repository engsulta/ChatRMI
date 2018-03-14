/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerAuthInt;
import com.jdevsul.interfaces.ServerContactInt;
import com.jdevsul.interfaces.ServerGroupsInt;
import com.jdevsul.interfaces.ServerManagerInt;
import com.jdevsul.interfaces.ServerRequestsInt;
import com.jdevsul.interfaces.ServerSendInt;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author szmoh
 */
public class ServerManagerImpl extends UnicastRemoteObject implements ServerManagerInt, Serializable {

    private static ServerManagerImpl serverImpl = null;

    ServerAuthImpl authImpl = null;
    ServerSendImpl sendImpl = null;
    ServerGroupImpl groupImpl = null;
    ServerRequestImpl requestImpl = null;
    ServerContactImpl contactImpl = null;
    private static Vector<ClientInterface> clientsVector = null;

    private ServerManagerImpl() throws RemoteException {
        clientsVector = new Vector<>();
        authImpl = new ServerAuthImpl(clientsVector);
        sendImpl = new ServerSendImpl(clientsVector);
        groupImpl = new ServerGroupImpl();
        requestImpl = new ServerRequestImpl();
        contactImpl = new ServerContactImpl();
    }

    public static ServerManagerImpl getInstance() throws RemoteException {
        if (serverImpl == null) {
            serverImpl = new ServerManagerImpl();
        }
        return serverImpl;
    }

    @Override
    public ServerAuthInt getServerAuthentication() throws RemoteException {
        return authImpl;
    }

    @Override
    public ServerSendInt getServerSend() throws RemoteException {
        return sendImpl;
    }

    @Override
    public ServerGroupsInt getServerGroups() throws RemoteException {
        return groupImpl;
    }

    @Override
    public ServerRequestsInt getServerRequests() throws RemoteException {
        return requestImpl;
    }

    @Override
    public ServerContactInt getServerContact() throws RemoteException {
        return contactImpl;
    }

    @Override
    public void register(ClientInterface clientRef) throws RemoteException {
        clientsVector.add(clientRef);
    }

    @Override
    public void unregister(ClientInterface clientRef) throws RemoteException {
        clientsVector.remove(clientRef);
    }

}
