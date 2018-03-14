/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import com.jdevsul.DBclasses.Group;
import com.jdevsul.interfaces.ClientInterface;
import com.jdevsul.interfaces.ServerGroupsInt;
import com.jdevsul.server.db.DatabaseHandler;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author szmoh
 */
public class ServerGroupImpl extends UnicastRemoteObject implements ServerGroupsInt, Serializable {

    public ServerGroupImpl() throws RemoteException {
    }

    @Override
    public void creatNewGroup(Group group) throws RemoteException {
        DatabaseHandler.getInstance().addNewGroup(group);
    }

    @Override
    public boolean removeGroup(Group group) throws RemoteException {
        return DatabaseHandler.getInstance().removeGroup(group.getGroupID());
    }

    @Override
    public ArrayList<Group> getMyGroups(int myID) throws RemoteException {
        return DatabaseHandler.getInstance().getMyGroups(myID);
    }

    @Override
    public void updateMyGroup(Group group) throws RemoteException {
         DatabaseHandler.getInstance().updateGroup(group);
    }

}
