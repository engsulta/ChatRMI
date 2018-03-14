/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.helper;

import java.io.File;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author sulta
 */
public class ServerHelper {
     public static void closeWindow(Window window) {
         Stage currentStage = (Stage) (window);
         currentStage.close();
         System.exit(1);
         Platform.exit();
         //currentStage.close();
    }

    public static void minimizeWindow(Window window) {
        Stage currentStage = (Stage) (window);
        currentStage.setIconified(true);

    }
    public static File chooseFile(Window window){
    
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG imgFiles (.jpg)", ".JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG imgFiles (.png)", ".PNG");
         FileChooser filechooser=new FileChooser();
        filechooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = filechooser.showOpenDialog(window);

        if (file != null) {return file;}
        else return null;

    }

    
}
