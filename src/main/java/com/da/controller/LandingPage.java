package com.da.controller;

import com.da.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LandingPage {
    public TextField userID;
    public TextField password;
    public Label label;

    public static int userId;
    public static String email;
    public static String name1;
    public static String name2;
    public static int age;
    public static String gender;
    public static String address;
    public static String status;
    public static String notif;
    public static String seat;

    @FXML
    protected void logIn(ActionEvent e) throws IOException {

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String id = userID.getText();
        String pass = password.getText();

        String q = "SELECT * FROM db_da.user;";
        boolean logged = false;

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            Parent root;
            Scene scene;
            Stage window;

            while (rs.next()) {
                if (id.equals(rs.getString(2)) && pass.equals(rs.getString(3))) {
                    logged = true;

                    userId = rs.getInt(1);
                    email = rs.getString(2);
                    name1 = rs.getString(4);
                    name2 = rs.getString(5);
                    age = rs.getInt(6);
                    gender = rs.getString(7);
                    address = rs.getString(8);
                    status = rs.getString(9);
                    notif = rs.getString(10);
                    seat = rs.getString(11);

                    this.label.setText("");

                    URL url = new File("src/main/resources/com/da/dashboard-view.fxml").toURI().toURL();
                    root = (Parent)FXMLLoader.load(url);
                    scene = new Scene(root);
                    window = (Stage)((Node)e.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                    break;
                }
            }

            if (!logged) {
                this.label.setText("Wrong ID or Password");
                this.password.setText("");
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        }
    }

    @FXML
    protected void admin(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/admin-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void create(MouseEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/create-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}