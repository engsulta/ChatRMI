/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.io.Serializable;

/**
 *
 * @author sulta
 */
public class Notification implements Serializable{
    int fromID;
    int toID;
    String Content;

    public Notification(int fromID, int toID, String Content) {
        this.fromID = fromID;
        this.toID = toID;
        this.Content = Content;
    }

    public int getFromID() {
        return fromID;
    }

    public void setFromID(int fromID) {
        this.fromID = fromID;
    }

    public int getToID() {
        return toID;
    }

    public void setToID(int toID) {
        this.toID = toID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    @Override
    public String toString() {
        return "Notification{" + "fromID=" + fromID + ", toID=" + toID + ", Content=" + Content + '}';
    }
    
    
}
