package com.da.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import static com.da.controller.LandingPage.*;

public class Dashboard {

    @FXML
    public Label d;
    @FXML
    public Label name;

    @FXML
    public Button payment;
    @FXML
    public Button booking;
    @FXML
    public Button bookingC;

    @FXML
    public void initialize(){
        if (notif.equals("no")){
            bookingC.setDisable(true);
            payment.setDisable(true);
            d.setText("No Notification");
        }else if (notif.equals("pending")){
            booking.setDisable(true);
            payment.setDisable(true);
            d.setText("Booking Approval Waiting");
        }else if (notif.equals("cancel")) {
            d.setText("Booking Canceled");
            bookingC.setDisable(true);
            payment.setDisable(true);
        }else if (notif.equals("payment")){
            d.setText("Waiting for Payment");
            bookingC.setDisable(true);
            booking.setDisable(true);
        }else if (notif.equals("paymentW")){
            d.setText("Payment Confirmation Pending");
            bookingC.setDisable(true);
            booking.setDisable(true);
            payment.setDisable(true);
        }else if (notif.equals("done")){
            d.setText("Booking Approved");
            bookingC.setDisable(true);
            booking.setDisable(true);
            payment.setDisable(true);
        }
        name.setText(name1+" "+name2);
    }

    public void booking(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/booking-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void cancelB(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/pop-delete.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void logout(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/landing-page-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void payment(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/payment-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
