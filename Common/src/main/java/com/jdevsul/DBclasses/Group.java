/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.DBclasses;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Eman-PC
 */
public class Group {

    int groupID;
    ArrayList<Integer> receiverID;
    Date groupCreationDate;
    int clientID;
    String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Integer> getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(ArrayList<Integer> receiverID) {
        this.receiverID = receiverID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public Date getGroupCreationDate() {
        return groupCreationDate;
    }

    public void setGroupCreationDate(Date groupCreationDate) {
        this.groupCreationDate = groupCreationDate;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

}
