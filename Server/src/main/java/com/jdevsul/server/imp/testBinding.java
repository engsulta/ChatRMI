/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.imp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Eman-PC
 */
class testBinding {
      // Define a variable to store the property
    private DoubleProperty onlineClients = new SimpleDoubleProperty();
    private DoubleProperty offlineClients = new SimpleDoubleProperty();
    private DoubleProperty femaleClients = new SimpleDoubleProperty();
    private DoubleProperty maleClients = new SimpleDoubleProperty();

    public double getOnlineClients() {
        return onlineClients.get();
    }

    public void setOnlineClients(double onlineClients) {
        this.onlineClients.set(onlineClients);
    }

    public double getOfflineClients() {
        return offlineClients.get();
    }

    public void setOfflineClients(double offlineClients) {
        this.offlineClients.set(offlineClients);
    }

    public double getFemaleClients() {
        return femaleClients.get();
    }

    public void setFemaleClients(double femaleClients) {
        this.femaleClients.set(femaleClients);
    }

    public double getMaleClients() {
        return maleClients.get();
    }

    public void setMaleClients(double maleClients) {
        this.maleClients.set(maleClients);
    }

    // Define a getter for the property itself
    public DoubleProperty offlineClientsProperty() {
        return offlineClients;
    }
     public DoubleProperty onlineClientsProperty() {
        return onlineClients;
    }
      public DoubleProperty femaleClientsProperty() {
        return femaleClients;
    }
       public DoubleProperty maleClientsProperty() {
        return maleClients;
    }

}
