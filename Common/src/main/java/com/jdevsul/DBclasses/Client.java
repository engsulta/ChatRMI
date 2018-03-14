/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.DBclasses;

import java.sql.Date;

/**
 *
 * @author Eman-PC
 */
public class Client {

    int clientID;
    String clientEmail;
    String clientName;
    String clientPassword;
    String clientStatus;
    Date clientCreationDate;
    String clientImage;
    String clientGender;
    Date clientBirthdate;
    int clientOnline;

    private boolean isValidName(String checkName) {
        String regexName
                = "^[a-zA-Z]+([ ][a-zA-Z]*)*$";

        if (checkName != null && checkName.matches(regexName)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
        }
        return isValidInteger;
    }

    private boolean isValidEmail(String checkEmail) {
        String regexEmail
                = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";

        if (checkEmail != null && checkEmail.matches(regexEmail)) {
            return true;
        } else {
            return false;
        }
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        if (isValidEmail(clientEmail)) {
            this.clientEmail = clientEmail;
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    public String getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }

    public Date getClientCreationDate() {
        return clientCreationDate;
    }

    public void setClientCreationDate(Date clientCreationDate) {
        this.clientCreationDate = clientCreationDate;
    }

    public String getClientImage() {
        return clientImage;
    }

    public void setClientImage(String clientImage) {
        this.clientImage = clientImage;
    }

    public String getClientGender() {
        return clientGender;
    }

    public void setClientGender(String clientGender) {
        this.clientGender = clientGender;
    }

    public Date getClientBirthdate() {
        return clientBirthdate;
    }

    public void setClientBirthdate(Date clientBirthdate) {
        this.clientBirthdate = clientBirthdate;
    }

    public int getClientOnline() {
        return clientOnline;
    }

    public void setClientOnline(int clientOnline) {
        this.clientOnline = clientOnline;
    }

}
