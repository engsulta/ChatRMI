/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sulta
 */
public class Notification implements Serializable {

    private ArrayList<Integer> toID;
    private String title;
    private String content;
    private byte[] imgBytes;

    public Notification() {
        this.toID = null;
        this.title = null;
        this.content = null;
        this.imgBytes = null;
    }

    public Notification(ArrayList toUsers, String title, String content, byte[] img) {
        this.toID = toUsers;
        this.title = title;
        this.content = content;
        this.imgBytes = img;
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the imgBytes
     */
    public byte[] getImgBytes() {
        return imgBytes;
    }

    /**
     * @param imgBytes the imgBytes to set
     */
    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    /**
     * @return the toID
     */
    public ArrayList<Integer> getToID() {
        return toID;
    }

    /**
     * @param toID the toID to set
     */
    public void setToID(ArrayList<Integer> toID) {
        this.toID = toID;
    }


}
