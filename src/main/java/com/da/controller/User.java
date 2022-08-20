package com.da.controller;

import com.da.DatabaseConnection;
import com.da.model.ReqAppData;
import com.da.model.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    @FXML
    private TableColumn<UserData, String> ageTable;
    @FXML
    private TableColumn<UserData, String> emailTable;
    @FXML
    private TableColumn<UserData, String> genderTable;
    @FXML
    private TableColumn<UserData, String> nameTable;

    @FXML
    private TableView<UserData> dataTableView;

    public ObservableList<UserData> user = FXCollections.observableArrayList();

    public void initialize(){
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM db_da.user;";
        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                int id = Integer.parseInt(rs.getString(1));
                String email = rs.getString(2);
                String name = rs.getString(4)+" "+rs.getString(5);
                String age = rs.getString(6);
                String gender = "";
                if (rs.getString(7).equals("male"))
                    gender = "Male";
                else gender = "Female";
                String address = rs.getString(8);


                this.user.add(new UserData(id,email,name,age,gender,address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        emailTable.setCellValueFactory(new PropertyValueFactory<UserData,String>("email"));
        nameTable.setCellValueFactory(new PropertyValueFactory<UserData,String>("name"));
        ageTable.setCellValueFactory(new PropertyValueFactory<UserData,String>("age"));
        genderTable.setCellValueFactory(new PropertyValueFactory<UserData,String>("gender"));
        dataTableView.setItems(user);
    }

    public void back(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/admin-dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
