package com.da.controller;

import com.da.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.da.controller.LandingPage.*;

public class PopDelete {

    public Label ld;
    public void DeleteReq(ActionEvent e) throws SQLException, IOException {
        String[] a = seat.split("\\|");

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "UPDATE `db_da`.`user` SET `status` = 'x', `notif` = 'no', `seat` = '.' WHERE (`id` = '"+userId+"');";
        statement.executeUpdate(q);
        String w = "UPDATE `db_da`.`doctor` SET `s"+a[0]+"` = '0' WHERE (`id` = '"+a[1]+"');";
        statement.executeUpdate(w);
        ld.setText("Deleted");

        status="x";
        notif="no";
        seat=".";

        URL url = new File("src/main/resources/com/da/dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
