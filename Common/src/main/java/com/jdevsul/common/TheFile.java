/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author sulta
 */
public class TheFile implements Serializable{
    private int fromID ;
    private int toID;
    private byte[] data;
    private String name;  
    private LocalTime time;
    private int size;

    @Override
    public String toString() {
        return "TheFile{" + "fromID=" + fromID + ", toID=" + toID + ", data=" + data + ", name=" + name + ", time=" + time + ", size=" + size + '}';
    }

    public TheFile(int fromID, int toID, byte[] data, String name, LocalTime time, int size) {
        this.fromID = fromID;
        this.toID = toID;
        this.data = data;
        this.name = name;
        this.time = time;
        this.size = size;
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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
