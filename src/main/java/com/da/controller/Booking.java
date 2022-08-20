package com.da.controller;

import com.da.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import static com.da.controller.LandingPage.*;

public class Booking {

    public ObservableList<String> hospital = FXCollections.observableArrayList();
    public ArrayList<String> hospitalId = new ArrayList<>();
    public ArrayList<String> hospitalName = new ArrayList<>();

    public ObservableList<String> doctor = FXCollections.observableArrayList();
    public ArrayList<String> doctorId = new ArrayList<>();
    public ArrayList<String> doctorName = new ArrayList<>();

    public ToggleGroup tg;
    public RadioButton s1;
    public RadioButton s2;
    public RadioButton s3;
    public RadioButton s4;

    @FXML
    public ComboBox<String> hospitalC;
    @FXML
    public ComboBox<String> doctorC;

    @FXML
    public Button book;

    public int radioA;
    public int doctorA;

    @FXML
    public void initialize(){

        s1.setToggleGroup(tg);
        s2.setToggleGroup(tg);
        s3.setToggleGroup(tg);
        s4.setToggleGroup(tg);
        s1.setDisable(true);
        s2.setDisable(true);
        s3.setDisable(true);
        s4.setDisable(true);
        doctorC.setDisable(true);
        book.setDisable(true);


        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM db_da.hospital;";

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                hospital.add(rs.getString(2));
                hospitalName.add(rs.getString(2));
                hospitalId.add(rs.getString(1));
            }

            hospitalC.setItems(hospital);
            hospitalC.setOnAction(this::getFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFood(ActionEvent e) {
        doctorC.setDisable(false);
        hospitalC.setDisable(true);
        int a = 0;
        String acc = hospitalC.getValue();
        Iterator<String> iterate = hospital.iterator();
        while(iterate.hasNext()){
            if (iterate.next()==acc){
                a = Integer.parseInt(hospitalId.get(hospital.indexOf(acc)));
                break;
            }
        }
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM db_da.doctor where hospital_id="+a+";";

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                doctor.add(rs.getString(2));
                doctorName.add(rs.getString(2));
                doctorId.add(rs.getString(1));
            }

            doctorC.setItems(doctor);
            doctorC.setOnAction(this::getFood2);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    private void getFood2(ActionEvent actionEvent) {
        doctorC.setDisable(true);
        int a = 0;
        String acc = doctorC.getValue();
        Iterator<String> iterate = doctor.iterator();
        while(iterate.hasNext()){
            if (iterate.next()==acc){
                a = Integer.parseInt(doctorId.get(doctor.indexOf(acc)));
                break;
            }
        }
        doctorA = a;
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM db_da.doctor where id="+a+";";
        boolean a1=false, a2=false, a3=false, a4=false;

        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                a1 = rs.getBoolean(5);
                a2 = rs.getBoolean(6);
                a3 = rs.getBoolean(7);
                a4 = rs.getBoolean(8);
            }
            s1.setDisable(a1);
            s2.setDisable(a2);
            s3.setDisable(a3);
            s4.setDisable(a4);

        } catch (Exception ee) {
            ee.printStackTrace();
        }

    }
    public void toggleB(ActionEvent e) {
        System.out.println(doctorA);
        if (s1.isSelected()){
            book.setDisable(false);
            radioA = 1;
        }
        else if (s2.isSelected()){
            book.setDisable(false);
            radioA = 2;
        }
        else if (s3.isSelected()){
            book.setDisable(false);
            radioA = 3;
        }
        else if (s4.isSelected()){
            book.setDisable(false);
            radioA = 4;
        }
    }

    public void book(ActionEvent e) throws SQLException, IOException {
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "UPDATE `db_da`.`doctor` SET `s"+radioA+"` = '1' WHERE (`id` = '"+doctorA+"');";
        statement.executeUpdate(q);

        String w = "UPDATE `db_da`.`user` SET `status` = 'w', `notif` = 'pending', `seat` = '"+radioA+"|"+doctorA+"' WHERE (`id` = '"+userId+"');";
        statement.executeUpdate(w);

        status = "w";
        notif = "pending";
        seat = radioA+"|"+doctorA;

        URL url = new File("src/main/resources/com/da/dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void back(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
