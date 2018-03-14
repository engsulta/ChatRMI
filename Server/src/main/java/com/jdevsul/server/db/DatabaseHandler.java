/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdevsul.server.db;

import com.jdevsul.DBclasses.Client;
import com.jdevsul.DBclasses.Contact;
import com.jdevsul.DBclasses.FriendRequest;
import com.jdevsul.DBclasses.Group;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author Eman-PC
 */
//Singleton class
public class DatabaseHandler {

    Connection con;

    private static DatabaseHandler myInstance = null;

    private DatabaseHandler() {
        connectToDB();
    }

    public static DatabaseHandler getInstance() {
        if (myInstance == null) {
            myInstance = new DatabaseHandler();
        }

        return myInstance;
    }

    private void connectToDB() {
        try {
            //open connection with database
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
            // Class.forName("org.apache.derby.jdbc.ClientDriver");
             //con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in openning connection");
        } //catch (ClassNotFoundException ex) {
//            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void closeConnectionDB() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in closing connection");
        }

    }

    /*------------------------- Client ----------------------------*/
    public boolean addNewClient(Client client) {
        int affectedRows = 0;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("insert into client"
                            + "(clientid, clientEmail,clientName,clientPassword,clientStatus"
                            + ",clientImage,clientGender,clientOnline)"
                            + "values(CLIENT_SEQ.nextval,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setString(1, client.getClientEmail());
                myStatement.setString(2, client.getClientName());
                myStatement.setString(3, client.getClientPassword());
                myStatement.setString(4, client.getClientStatus());
                myStatement.setString(5, client.getClientImage());
                myStatement.setString(6, client.getClientGender());
                myStatement.setInt(7, client.getClientOnline());
                
                affectedRows = myStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new client");
        }

        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public boolean updateClient(Client client) {
        int affectedRows = 0;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("update client "
                    + "set clientEmail=?, clientName=?, clientPassword=?, "
                    + "clientStatus=?, clientCreationDate=?, clientImage=?, clientGender=?, "
                    + "clientBirthdate=? ,clientOnline=? where clientID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setString(1, client.getClientEmail());
                myStatement.setString(2, client.getClientName());
                myStatement.setString(3, client.getClientPassword());
                myStatement.setString(4, client.getClientStatus());
                myStatement.setDate(5, client.getClientCreationDate());
                myStatement.setString(6, client.getClientImage());
                myStatement.setString(7, client.getClientGender());
                myStatement.setDate(8, client.getClientBirthdate());
                myStatement.setInt(9, client.getClientOnline());
                System.out.println(client.getClientOnline());
                myStatement.setInt(10, client.getClientID());

                affectedRows = myStatement.executeUpdate();
                con.commit();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in updating client");
        }

        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public boolean removeClient(int clientID) {
        int affectedRows = 0;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("delete from client"
                            + " where clientID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, clientID);
                affectedRows = myStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in removing client");
        }
        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public Client getClientByID(int clientID) {
        Client client = null;
        try {

            try (PreparedStatement myStatement = con.prepareStatement("select * from client where clientid=?",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, clientID);
                try (ResultSet resultSet = myStatement.executeQuery()) {
                    while (resultSet.next()) {
                        client = new Client();
                        client.setClientBirthdate(resultSet.getDate("clientBirthdate"));
                        client.setClientCreationDate(resultSet.getDate("clientCreationDate"));
                        client.setClientEmail(resultSet.getString("clientEmail"));
                        client.setClientGender(resultSet.getString("clientGender"));
                        client.setClientID(resultSet.getInt("clientID"));
                        client.setClientImage(resultSet.getString("clientImage"));
                        client.setClientName(resultSet.getString("clientName"));
                        client.setClientOnline(resultSet.getInt("clientOnline"));
                        client.setClientPassword(resultSet.getString("clientPassword"));
                        client.setClientStatus(resultSet.getString("clientStatus"));
                    }
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting client by ID");
        }
        return client;
    }

    public Client getClientByEmail(String clientEmail) {
        Client client = null;
        try {

            try (PreparedStatement myStatement = con.prepareStatement("select * from client where lower(clientEmail)"
                            + " =lower(?)",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setString(1, clientEmail);
                try (ResultSet resultSet = myStatement.executeQuery()) {
                    while (resultSet.next()) {
                        client = new Client();
                        client.setClientBirthdate(resultSet.getDate("clientBirthdate"));
                        client.setClientCreationDate(resultSet.getDate("clientCreationDate"));
                        client.setClientEmail(resultSet.getString("clientEmail"));
                        client.setClientGender(resultSet.getString("clientGender"));
                        client.setClientID(resultSet.getInt("clientID"));
                        client.setClientImage(resultSet.getString("clientImage"));
                        client.setClientName(resultSet.getString("clientName"));
                        client.setClientOnline(resultSet.getInt("clientOnline"));
                        client.setClientPassword(resultSet.getString("clientPassword"));
                        client.setClientStatus(resultSet.getString("clientStatus"));
                    }
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting client by email");
        }
        return client;
    }

    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clients = new ArrayList<>();
        try {

            try (PreparedStatement myStatement = con.prepareStatement("select * from client",
                            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);ResultSet resultSet = myStatement.executeQuery()) {
                while (resultSet.next()) {
                    Client client = new Client();
                    client.setClientBirthdate(resultSet.getDate("clientBirthdate"));
                    client.setClientCreationDate(resultSet.getDate("clientCreationDate"));
                    client.setClientEmail(resultSet.getString("clientEmail"));
                    client.setClientGender(resultSet.getString("clientGender"));
                    client.setClientID(resultSet.getInt("clientID"));
                    client.setClientImage(resultSet.getString("clientImage"));
                    client.setClientName(resultSet.getString("clientName"));
                    client.setClientOnline(resultSet.getInt("clientOnline"));
                    client.setClientPassword(resultSet.getString("clientPassword"));
                    client.setClientStatus(resultSet.getString("clientStatus"));
                    clients.add(client);
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting all clients");
        }
        return clients;
    }

    /*------------------------- Group ----------------------------*/
    public boolean addNewGroup(Group newGroup) {
        int affectedRows = 0;
        try {
            ArrayList<Integer> receiverIDs = newGroup.getReceiverID();

            for (int count = 0; count < receiverIDs.size(); count++) {

                try (PreparedStatement myStatement = con.prepareStatement("insert into groupChat"
                                + "(groupID,receiverID,groupCreationDate,clientID,groupName)"
                                + "values(?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    myStatement.setInt(1, newGroup.getGroupID());
                    myStatement.setInt(2, receiverIDs.get(count));
                    myStatement.setDate(3, newGroup.getGroupCreationDate());
                    myStatement.setInt(4, newGroup.getClientID());
                    myStatement.setString(5, newGroup.getGroupName());
                    
                    affectedRows = myStatement.executeUpdate();
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new group");
        }

        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public boolean updateGroup(Group group) {
        removeGroup(group.getGroupID());
        boolean result = addNewGroup(group);
        return result;
    }

    public boolean removeGroup(int groupID) {
        int affectedRows = 0;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("delete from groupChat"
                            + " where groupID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, groupID);
                affectedRows = myStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in removing group");
        }
        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public ArrayList<Group> getMyGroups(int clientID) {
        ArrayList<Group> groups = new ArrayList<>();
        ArrayList<Integer> recievers = null;
        Group newGroup = null;
        try {
            //get all group IDs first
            try (PreparedStatement myStatement = con.prepareStatement("select DISTINCT groupID from groupChat where "
                    + "clientID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, clientID);
                try (ResultSet resultSet = myStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int groupID = resultSet.getInt("groupID");
                        newGroup = new Group();
                        recievers = new ArrayList<>();
                        //loop for each group ID and fetch its recievers
                        try (PreparedStatement myStatement2 = con.prepareStatement("select * from groupChat where "
                                + "groupID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                            myStatement2.setInt(1, groupID);

                            try (ResultSet resultSet2 = myStatement2.executeQuery()) {
                                while (resultSet2.next()) {
                                    newGroup.setClientID(clientID);
                                    newGroup.setGroupCreationDate(resultSet2.getDate("GROUPCREATIONDATE"));
                                    newGroup.setGroupName(resultSet2.getString("groupname"));
                                    newGroup.setReceiverID(recievers);
                                    newGroup.setGroupID(groupID);
                                    recievers.add(resultSet2.getInt("receiverID"));
                                }
                            }
                        }
                        groups.add(newGroup);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting all groups");
        }
        return groups;

    }

    /*------------------------- Contact ----------------------------*/
    public boolean isContact(Contact contact) {
        boolean check = false;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("select * from contact "
                    + "where clientID=? and contactID=? ", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, contact.getClientID());
                myStatement.setInt(2, contact.getContactID());
                try (ResultSet resultSet = myStatement.executeQuery()) {
                    while (resultSet.next()) {
                        check = true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new contact");
        }

        return check;
    }

    public boolean addNewContact(Contact contact) {
        int affectedRows = 0;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("insert into contact "
                    + "(clientID, contactID) values(?, ?)", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, contact.getClientID());
                myStatement.setInt(2, contact.getContactID());
                affectedRows = myStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new contact");
        }

        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public ArrayList<Client> getMyContacts(int clientID) {
        ArrayList<Client> contacts = new ArrayList<>();
        try {
            try (PreparedStatement myStatement = con.prepareStatement("select contactID from contact "
                    + "where clientID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, clientID);
                try (ResultSet resultSet = myStatement.executeQuery()) {
                    while (resultSet.next()) {
                        contacts.add(getClientByID(resultSet.getInt("contactID")));
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting contacts");
        }
        return contacts;
    }

    public boolean removeContact(int contactID) {
        int affectedRows = 0;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("delete from contact"
                    + " where contactID=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, contactID);
                affectedRows = myStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in removing contact");
        }

        //if affectedRows !=0 return true else return false
        return affectedRows != 0;

    }

    /*---------------------- FriendRequest --------------------------*/
    public boolean isFriendRequestExist(FriendRequest request) {
        boolean check = false;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("select * from friendRequest "
                    + "where clientID=? and friendID=? ", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, request.getClientID());
                myStatement.setInt(2, request.getFriendID());
                try (ResultSet resultSet = myStatement.executeQuery()) {
                    while (resultSet.next()) {
                        check = true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new contact");
        }

        return check;
    }

    public boolean addNewFriendRequest(FriendRequest request) {
        int affectedRows = 0;
        try {
            try (PreparedStatement myStatement = con.prepareStatement("insert into friendRequest"
                    + "(clientID,friendID,requestDate)"
                    + "values(?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, request.getClientID());
                myStatement.setInt(2, request.getFriendID());
                myStatement.setDate(3, request.getRequestDate());
                affectedRows = myStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in adding new friend request");
        }

        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public boolean removeFriendRequest(FriendRequest request, int type) {
        int affectedRows = 0;
        try {
            //type=0 --> accept request so add to contacts
            //anything else --> reject request
            //in both cases the request will be deleted from the friendRequest table
            try (PreparedStatement myStatement = con.prepareStatement("delete from friendRequest "
                    + "where friendID=? and clientID=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, request.getFriendID());
                myStatement.setInt(2, request.getClientID());
                affectedRows = myStatement.executeUpdate();

                if (type == 0) {
                    Contact contact = new Contact();
                    contact.setClientID(request.getClientID());
                    contact.setContactID(request.getFriendID());
                    addNewContact(contact);

                    Contact contact2 = new Contact();
                    contact2.setClientID(request.getFriendID());
                    contact2.setContactID(request.getClientID());
                    addNewContact(contact2);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in removing friend request");
        }

        //if affectedRows !=0 return true else return false
        return affectedRows != 0;
    }

    public ArrayList<Client> getMyFriendRequests(int clientID) {
        ArrayList<Client> friendRequests = new ArrayList<>();
        try {
            try (PreparedStatement myStatement = con.prepareStatement("select friendID from friendRequest "
                    + "where clientID = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                myStatement.setInt(1, clientID);
                try (ResultSet resultSet = myStatement.executeQuery()) {
                    while (resultSet.next()) {
                        friendRequests.add(getClientByID(resultSet.getInt("friendID")));
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting friend requests");
        }
        return friendRequests;
    }

    /*------------------------ Statistics ----------------------------*/
    public int getNumberOfAllClients() {
        int clients = 0;
        try {

            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "select count(*) from client";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        clients = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of all clients");
        }
        return clients;
    }

    public int getNumberOfOnlineClients() {
        int onlineClients = 0;
        try {

            //if clientOnlie =0 then online 
            //if clientOnlie=1 then offline
            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

                String query = "select count(*) from client where clientOnline = 0";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        onlineClients = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of online clients");
        }
        return onlineClients;
    }

    public int getNumberOfOfflineClients() {
        int onlineClients = 0;
        try {

            //if clientOnlie =0 then online 
            //if clientOnlie=1 then offline
            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "select count(*) from client where clientOnline <> 0";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        onlineClients = resultSet.getInt(1);
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of offline clients");
        }
        return onlineClients;
    }

    public int getNumberOfFemaleClients() {
        int femaleClients = 0;
        try {

            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "select count(*) from client where lower(clientGender)=lower('female')";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        femaleClients = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of female clients");
        }
        return femaleClients;
    }

    public int getNumberOfMaleClients() {
        int maleClients = 0;
        try {

            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "select count(*) from client where lower(clientGender)=lower('male')";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        maleClients = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of male clients");
        }
        return maleClients;
    }

    public int getNumberOfAwayClients() {
        int awayClients = 0;
        try {

            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "select count(*) from client where lower(clientStatus)=lower('away')";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        awayClients = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of away clients");
        }
        return awayClients;
    }

    public int getNumberOfBusyClients() {
        int busyClients = 0;
        try {

            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "select count(*) from client where lower(clientStatus)=lower('busy')";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        busyClients = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of busy clients");
        }
        return busyClients;
    }

    public int getNumberOfAvailableClients() {
        int availableClients = 0;
        try {

            try (Statement myStatement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                String query = "select count(*) from client where lower(clientStatus)=lower('available')";
                try (ResultSet resultSet = myStatement.executeQuery(query)) {
                    while (resultSet.next()) {
                        availableClients = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error in getting number of available clients");
        }
        return availableClients;
    }

}
