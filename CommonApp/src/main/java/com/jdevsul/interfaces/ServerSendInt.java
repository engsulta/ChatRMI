/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.interfaces;

import com.jdevsul.common.Notification;
import com.jdevsul.common.TheFile;
import com.jdevsul.common.TheMessage;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author gehad
 */
public interface ServerSendInt extends Remote {

    public boolean sendFile(TheFile file) throws RemoteException;

    public void sendMsg(TheMessage message) throws RemoteException;

    public void sendNotification(Notification notification) throws RemoteException;
}
