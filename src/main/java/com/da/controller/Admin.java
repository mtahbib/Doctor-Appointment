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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Admin {

    @FXML
    public TextField userID;
    @FXML
    public TextField password;
    @FXML
    public Label label;

    public void logIn(ActionEvent e) {
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        String id = this.userID.getText();
        String pass = this.password.getText();
        String q = "SELECT * FROM db_da.admin;";
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

                    this.label.setText("");

                    URL url = new File("src/main/resources/com/da/admin-dashboard-view.fxml").toURI().toURL();
                    root = (Parent) FXMLLoader.load(url);
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

    public void back(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/landing-page-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}