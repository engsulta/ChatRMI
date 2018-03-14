/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.login;

import com.jdevsul.helper.ServerHelper;
import com.jdevsul.server.util.ServerAssistUtil;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class LoginFxmlController implements Initializable {

    @FXML
    private JFXTextField AdminName;
    @FXML
    private JFXPasswordField AdminPassword;
    private final String LoginName = "admin";
    private final String LoginPassword = "admin";
    @FXML
    private AnchorPane loginpane;
    private Stage loginStage;
    private double xoffset;
    private double yoffset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void HandleLoginAction(ActionEvent event) {
        System.out.println("login");
        if (AdminName.getText().equals(LoginName) && AdminPassword.getText().equals(LoginPassword)) {
            Stage currentstage = (Stage) (AdminName.getScene().getWindow());
            if (currentstage != null) {
                ServerAssistUtil.loadWindow(getClass().getResource("/fxml/MainFXML.fxml"), currentstage, "Server Operator");
            } else {
                ServerAssistUtil.loadWindow(getClass().getResource("/fxml/MainFXML.fxml"), null, "Server Operator");
            }

        } else {
            AdminName.getStyleClass().add("text-error");
            AdminPassword.getStyleClass().add("text-error");
        }

    }

    @FXML
    private void HandleCancelAction(ActionEvent event) {
        System.out.println("cancelled");
    }

    @FXML
    private void handleMouseDragged(MouseEvent event) {
        System.out.println("dragged");

        loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginStage.setX(event.getScreenX() + xoffset);
        loginStage.setY(event.getScreenY() + yoffset);

    }

    @FXML
    private void handlePanePressed(MouseEvent event) {
        System.out.println("pressed");

        loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xoffset = loginStage.getX() - event.getScreenX();
        yoffset = loginStage.getY() - event.getScreenY();

    }

    @FXML
    private void HandleOnClosePressed(MouseEvent event) {
        ServerHelper.closeWindow(((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    private void handleOnMinimizePressed(MouseEvent event) {
        ServerHelper.minimizeWindow(((Node) event.getSource()).getScene().getWindow());

    }

}
