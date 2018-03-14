/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.mainui;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.helper.ServerHelper;
import com.jdevsul.server.db.DatabaseHandler;
import com.jdevsul.server.graph.GraphsHandler;
import com.jdevsul.server.imp.ServerAuthImpl;

import com.jdevsul.server.imp.ServerManagerImpl;
import com.jdevsul.server.util.ServerAssistUtil;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sulta
 */
public class MainFXMLController implements Initializable {

    ControllerOperations controllerOperations;

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTabPane mainTabPane;
//    @FXML
//    private JFXTextField PortNumber;
//    @FXML
//    private JFXTextField Ip;
    @FXML
    private Tab showClientTab;
    @FXML
    private PieChart OnlineChart;
    @FXML
    private HBox client_info;
    @FXML
    private StackPane clientInfoContainer;
    @FXML
    private Text clientUserName;
    @FXML
    private Text clientEmail;
    @FXML
    private Text clientStatus;
    @FXML
    private PieChart GenderChart;
    @FXML
    private JFXButton renew;
    private GraphsHandler graphsHandler;

    @FXML
    private Button btnSendNotification;
    private Stage loginStage;
    private double xoffset;
    private double yoffset;
    @FXML
    private JFXToggleButton togglePower;
    private SimpleStringProperty serverportNumber;
    private SimpleStringProperty serverIP;
    @FXML
    private JFXTextField PortNumber;
    @FXML
    private JFXTextField Ip;
    @FXML
    private JFXTextField clientEmailInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        controllerOperations = new ControllerOperations();

        controllerOperations.setmain(this);

        graphsHandler = new GraphsHandler();
        initGraphs();
        ServerAuthImpl.setMainFXMLController(this);

        serverportNumber = new SimpleStringProperty("7474");
        serverIP = new SimpleStringProperty("10.145.3.30");
        serverportNumber.setValue("7474");
        try {
            serverIP.setValue(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PortNumber.textProperty().bind(serverportNumber);
        Ip.textProperty().bind(serverIP);

    }

    private void testGraphs() {
        final ObservableList<PieChart.Data> Onlinedetails = FXCollections.observableArrayList();
        final ObservableList<PieChart.Data> genderDetails = FXCollections.observableArrayList();

        ////onlinechart show data && GenderChart
        Onlinedetails.addAll(new PieChart.Data("Cost1", 20),
                new PieChart.Data("Cost2", 25),
                new PieChart.Data("Cost6", 15)
        );
        genderDetails.addAll(new PieChart.Data("Cost1", 20),
                new PieChart.Data("Cost2", 25),
                new PieChart.Data("Cost6", 15)
        );

        OnlineChart.setData(Onlinedetails);
        OnlineChart.setTitle("ChatPieChart");
        OnlineChart.setLegendSide(Side.RIGHT);
        OnlineChart.setLabelsVisible(true);
        OnlineChart.setStartAngle(90);
        OnlineChart.setAnimated(true);
        //----------------------------  -------------------------------//
        GenderChart.setData(Onlinedetails);
        GenderChart.setTitle("ChatPieChart");
        GenderChart.setLegendSide(Side.RIGHT);
        GenderChart.setLabelsVisible(true);
        GenderChart.setStartAngle(90);
        GenderChart.setAnimated(true);
    }

    private void initGraphs() {
        OnlineChart.setData(graphsHandler.getOnlineGraphStatistics());
        OnlineChart.setTitle("ChatPieChart");
        OnlineChart.setLegendSide(Side.RIGHT);
        OnlineChart.setLabelsVisible(true);
        OnlineChart.setStartAngle(90);
        OnlineChart.setAnimated(true);

        GenderChart.setData(graphsHandler.getGenderGraphStatistics());
        GenderChart.setTitle("ChatPieChart");
        GenderChart.setLegendSide(Side.RIGHT);
        GenderChart.setLabelsVisible(true);
        GenderChart.setStartAngle(90);
        GenderChart.setAnimated(true);

    }

    public void refreshGraphs() {
        OnlineChart.setData(graphsHandler.getOnlineGraphStatistics());
        GenderChart.setData(graphsHandler.getGenderGraphStatistics());
    }
//
//    private void HandleOnPowerPressed(MouseEvent event) {
//        controllerOperations.toggleService();
//    }

//    @FXML
//    private void HandleOnShowPressed(MouseEvent event) {
//    }
//
//    private void HandleOnRenewPressed(MouseEvent event) {
//        initGraphs();
//    }
//
//    @FXML
//    private void HandleShowClient(ActionEvent event) {
//    }
//
//    @FXML
//    private void HandleRenewAction(ActionEvent event) {
//    }
    @FXML
    private void HandleStatisticsTab(Event event) {
    }

//    @FXML
//    private void handleSendNotification(MouseEvent event) {
//
//        try {
//            controllerOperations.sendNotification(null, "test title", "test content");
//        } catch (RemoteException ex) {
//            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void handleMouseDragged(MouseEvent event) {
//        loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        xoffset = loginStage.getX() - event.getScreenX();
//        yoffset = loginStage.getY() - event.getScreenY();
//
//    }
//
//    private void handlePanePressed(MouseEvent event) {
//        loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        xoffset = loginStage.getX() - event.getScreenX();
//        yoffset = loginStage.getY() - event.getScreenY();
//
//    }
    @FXML
    private void HandleOnPaneDragged(MouseEvent event) {
        System.out.println("dragged");
        loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginStage.setX(event.getScreenX() + xoffset);
        loginStage.setY(event.getScreenY() + yoffset);

    }

    @FXML
    private void HandleOnPanePressed(MouseEvent event) {
        System.out.println("pressed");
        loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        xoffset = loginStage.getX() - event.getScreenX();
        yoffset = loginStage.getY() - event.getScreenY();

    }
//
//    @FXML
//    private void HandleSendNotification(ActionEvent event) {
//        try {
//            controllerOperations.sendNotification(null, "test title", "test content");
//        } catch (RemoteException ex) {
//            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @FXML
    private void HandleOnClose(MouseEvent event) {
        ServerHelper.closeWindow(((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    private void HandleOnMinimize(MouseEvent event) {
        ServerHelper.minimizeWindow(((Node) event.getSource()).getScene().getWindow());
    }

    @FXML
    private void HandleOnPowerAction(ActionEvent event) {
        togglePower.setText(controllerOperations.toggleService());

    }

    public SimpleStringProperty getServerportNumber() {
        return serverportNumber;
    }

    public String getServerportNumberString() {
        return serverportNumber.getValue();
    }

    public void setServerportNumber(String serverportNumber) {
        this.serverportNumber.setValue(serverportNumber);
    }

    public SimpleStringProperty getServerIP() {
        return serverIP;

    }

    public String getServerIPString() {
        return serverIP.getValue();
    }

    public void setServerIP(String serverIP) {
        this.serverIP.setValue(serverIP);
    }

    @FXML
    private void handleSearchByemail(ActionEvent event) {
        DatabaseHandler db = DatabaseHandler.getInstance();
        Client cl = db.getClientByEmail(clientEmailInput.getText());
        clientStatus.setText("Status:" + cl.getClientStatus());
        clientUserName.setText("UserName:" + cl.getClientName());
        clientEmail.setText("Gender" + cl.getClientGender());
    }

    @FXML
    private void HandleRenewAction(ActionEvent event) {
        initGraphs();
    }

    @FXML
    private void HandleShowNotification(ActionEvent event) {
        ServerAssistUtil.loadWindow(getClass().getResource("/fxml/NotificationPopUp.fxml"), null, "Notification");
    }

}
