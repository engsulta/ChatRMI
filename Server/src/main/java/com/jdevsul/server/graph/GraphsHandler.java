/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.graph;

import com.jdevsul.server.db.DatabaseHandler;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author sulta
 */
//public int getNumberOfAllClients();
//
//    public int getNumberOfOnlineClients();
//
//    public int getNumberOfOfflineClients();
//
//    public int getNumberOfFemaleClients();
//
//    public int getNumberOfMaleClients();
//
//    public int getNumberOfAwayClients();
//
//    public int getNumberOfBusyClients();
//
//    public int getNumberOfAvailableClients();
public class GraphsHandler {

    private DatabaseHandler myhandler = DatabaseHandler.getInstance();

    public ObservableList<PieChart.Data> getOnlineGraphStatistics() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        data.add(new PieChart.Data("offline clients (" + myhandler.getNumberOfOfflineClients() + ")", myhandler.getNumberOfOfflineClients()));

        data.add(new PieChart.Data("online clients (" + myhandler.getNumberOfOnlineClients() + ")", myhandler.getNumberOfOnlineClients()));
        return data;
    }

    public ObservableList<PieChart.Data> getGenderGraphStatistics() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        data.add(new PieChart.Data("Male clients (" + myhandler.getNumberOfMaleClients() + ")", myhandler.getNumberOfOfflineClients()));
        data.add(new PieChart.Data("female clients (" + myhandler.getNumberOfFemaleClients() + ")", myhandler.getNumberOfOnlineClients()));
        return data;
    }

}
