/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.interfaces;

import com.jdevsul.DBclasses.Group;
import com.jdevsul.interfaces.ClientInterface;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author gehad
 */
public interface ServerGroupsInt extends Remote {

    public void creatNewGroup(Group group) throws RemoteException;

    public boolean removeGroup(Group group) throws RemoteException;

    public ArrayList<Group> getMyGroups(int myID) throws RemoteException;

    public void updateMyGroup(Group group) throws RemoteException;

}
