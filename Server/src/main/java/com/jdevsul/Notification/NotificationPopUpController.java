/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.Notification;

import com.jdevsul.helper.ServerHelper;
import com.jdevsul.server.mainui.ControllerOperations;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class NotificationPopUpController implements Initializable {

    public static String name = "Noname";
    public static Image image = null;
    final FileChooser fileChooser = new FileChooser();
    public static URI imageURI;
    @FXML
    private JFXTextArea adsContent;
    @FXML
    private Circle adsPhoto;
    private Window window;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void HandleOnImageSelected(MouseEvent event) {

//        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG imgFiles (.jpg)", ".JPG");
//        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG imgFiles (.png)", ".PNG");
//        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        window = ((Node) event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);

        if (file != null) {

            System.out.println("file not null");
            imageURI = file.toURI();
            image = new Image(file.toURI().toString());
            ImagePattern myimImagePattern = new ImagePattern(image);
            adsPhoto.setFill(myimImagePattern);
            adsPhoto.setVisible(true);
            System.out.println("image setted in  circle");

        } else {

            File file2 = new File("/images/logo2.png");

            imageURI = file2.toURI();
            image = new Image(file.toURI().toString());
            ImagePattern myimImagePattern = new ImagePattern(image);
            adsPhoto.setFill(myimImagePattern);
            adsPhoto.setVisible(true);
            System.out.println("image setted in  circle default photo ");

        }

    }

    @FXML
    private void HandleSendAction(ActionEvent event) {
        try {
            ControllerOperations.sendNotification(null, "Notification", adsContent.getText());
        } catch (RemoteException ex) {
            Logger.getLogger(NotificationPopUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        window = ((Node) event.getSource()).getScene().getWindow();
        Stage currentStage = (Stage) window;
        currentStage.close();
    }

    @FXML
    private void HandleCancelAction(ActionEvent event) {

        window = ((Node) event.getSource()).getScene().getWindow();
        Stage currentStage = (Stage) window;
        currentStage.close();
        // ServerHelper.closeWindow(window);

    }

}
