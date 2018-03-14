/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author sulta
 */

//teeeest comment 
public class TheMessage implements Serializable{
    int fromID;
    ArrayList<Integer> ToID;
    String body;
    String color;
    String font_family;
    String font_size;
    LocalDateTime localDateTime;

    @Override
    public String toString() {
        return "TheMessage{" + "fromID=" + fromID + ", ToID=" + ToID + ", body=" + body + ", color=" + color + ", font_family=" + font_family + ", font_size=" + font_size + ", localDateTime=" + localDateTime + '}';
    }

    public TheMessage(int fromID, ArrayList<Integer> ToID, String body, String color, String font_family, String font_size, LocalDateTime localDateTime) {
        this.fromID = fromID;
        this.ToID = ToID;
        this.body = body;
        this.color = color;
        this.font_family = font_family;
        this.font_size = font_size;
        this.localDateTime = localDateTime;
    }

    public int getFromID() {
        return fromID;
    }

    public void setFromID(int fromID) {
        this.fromID = fromID;
    }

    public ArrayList<Integer> getToID() {
        return ToID;
    }

    public void setToID(ArrayList<Integer> ToID) {
        this.ToID = ToID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont_family() {
        return font_family;
    }

    public void setFont_family(String font_family) {
        this.font_family = font_family;
    }

    public String getFont_size() {
        return font_size;
    }

    public void setFont_size(String font_size) {
        this.font_size = font_size;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
}