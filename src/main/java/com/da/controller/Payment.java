package com.da.controller;

import com.da.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static com.da.controller.LandingPage.*;

public class Payment {

    public ToggleGroup tg;
    public RadioButton bkash;
    public RadioButton rocket;

    public String radio = "lol";

    @FXML
    public Label lbl;
    public TextField trx;


    @FXML
    public void initialize(){
        bkash.setToggleGroup(tg);
        rocket.setToggleGroup(tg);
    }
    public void submit(ActionEvent e) throws SQLException, IOException {
        if (trx.getText().equals("") || radio.equals("lol")){
            this.lbl.setText("Fields is blank");
            return;
        }
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "INSERT INTO `db_da`.`transaction` (`name`, `trx`, `medthod`, `user`) VALUES ('"+name1+" "+name2+"', '"+trx.getText()+"', '"+radio+"', '"+userId+"');";
        statement.executeUpdate(q);

        String w = "UPDATE `db_da`.`user` SET `notif` = 'paymentW' WHERE (`id` = '"+userId+"');";
        statement.executeUpdate(w);
        notif = "paymentW";

        URL url = new File("src/main/resources/com/da/dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void toggleB(ActionEvent e) {
        if (bkash.isSelected())
            radio = "Bkash";
        else if (rocket.isSelected())
            radio = "Rocket";
    }

    public void back(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
