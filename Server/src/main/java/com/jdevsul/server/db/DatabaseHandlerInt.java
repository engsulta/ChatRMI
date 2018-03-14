/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.db;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.DBclasses.FriendRequest;
import com.jdevsul.DBclasses.Group;
import java.util.ArrayList;

/**
 *
 * @author Eman-PC
 */
public interface DatabaseHandlerInt {

    /*------------------------- Client ----------------------------*/
    public boolean addNewClient(Client client);

    public boolean updateClient(Client client);

    public boolean removeClient(int clientID);

    public Client getClientByID(int clientID);

    public Client getClientByEmail(String clientEmail);

    public ArrayList<Client> getAllClients();

    /*------------------------- Group ----------------------------*/
    public boolean addNewGroup(Group newGroup);

    public boolean updateGroup(Group group);

    public boolean removeGroup(int groupID);

    //return the groups of specific client
    public ArrayList<Group> getMyGroups(int clientID);

    /*------------------------- Contact ----------------------------*/
    public boolean addNewContact(Contact contact);

    //return the contacts of specific id
    public ArrayList<Client> getMyContacts(int clientID);

    public boolean removeContact(int contactID);

    /*---------------------- FriendRequest --------------------------*/
    public boolean addNewFriendRequest(FriendRequest request);

    //type=0 --> accept request so add to contacts
    //anything else --> reject request
    //in both cases the request will be deleted from the friendRequest table
    public boolean removeFriendRequest(FriendRequest request, int type);

    //return the friend requests of specific id
    public ArrayList<Client> getMyFriendRequests(int clientID);

    /*------------------------ Statistics ----------------------------*/
    public int getNumberOfAllClients();

    public int getNumberOfOnlineClients();

    public int getNumberOfOfflineClients();

    public int getNumberOfFemaleClients();

    public int getNumberOfMaleClients();

    public int getNumberOfAwayClients();

    public int getNumberOfBusyClients();

    public int getNumberOfAvailableClients();

}
