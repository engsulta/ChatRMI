package com.jdevsul.interfaces;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.common.Notification;
import com.jdevsul.common.ServerAdsense;
import com.jdevsul.common.TheFile;
import com.jdevsul.common.TheMessage;
import java.rmi.Remote;
import java.rmi.RemoteException;
//Pull the data out of the ResultSet and put it into a serializable Collection class (HashMap, ArrayList, etc.) and send the Collection instead.

public interface ClientInterface extends Remote {

    public void recieveMsg(TheMessage message) throws RemoteException;

    public void recieveFile(TheFile file) throws RemoteException;

    public void recieveContact(Contact contact) throws RemoteException; //when you send and the friend accept will call this method to invoke you 

    public void recieveNotification(Notification notification) throws RemoteException;//

    public void recieveAdsense(ServerAdsense adsense) throws RemoteException;

    public Client getCurrentClient() throws RemoteException;

    public void setCurrentClient(Client client) throws RemoteException;

}
