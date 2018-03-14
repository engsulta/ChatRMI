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
public class ServerAdsense implements Serializable {

    String content;
    byte[] icon;

    public ServerAdsense(String content, byte[] icon) {
        this.content = content;
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
    
}
