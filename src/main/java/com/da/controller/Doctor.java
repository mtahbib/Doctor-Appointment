package com.da.controller;

import com.da.DatabaseConnection;
import com.da.model.DoctorData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class Doctor {
    @FXML
    private TableColumn<DoctorData, String> nameTableColumn;
    @FXML
    private TableColumn<DoctorData, String> specialityTableColumn;
    @FXML
    private TableColumn<DoctorData, String> hospitalTableColumn;
    @FXML
    private TableView<DoctorData> dataTableView;

    @FXML
    private ComboBox<String> hospitalC;

    @FXML
    private Label lb;
    @FXML
    private TextField name;
    @FXML
    private TextField speciality;

    public ObservableList<String> hospital = FXCollections.observableArrayList();
    public ObservableList<DoctorData> doctor = FXCollections.observableArrayList();
    public ArrayList<String> hospitalId = new ArrayList<>();
    public ArrayList<String> hospitalName = new ArrayList<>();

    public int hospitalS;
    public static int idDelete;

    public void initialize(){
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
            System.out.println(hospitalId);

            hospitalC.setItems(hospital);
            hospitalC.setOnAction(this::getFood);
        } catch (Exception e) {
            e.printStackTrace();
        }

        q = "SELECT * FROM db_da.doctor;";

        try {
            Statement statement = conBD.createStatement();
            ResultSet rs = statement.executeQuery(q);

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hId = rs.getInt("hospital_id");
                String type = rs.getString("type");
                boolean s1 = rs.getBoolean("s1");
                boolean s2 = rs.getBoolean("s2");
                boolean s3 = rs.getBoolean("s3");
                boolean s4 = rs.getBoolean("s4");

                String a = "";
                for (int i = 0; i< (long) hospitalId.size(); i++){
                    if (Integer.parseInt(hospitalId.get(i))==hId){
                        a=hospitalName.get(i);
                    }
                }
                this.doctor.add(new DoctorData(id, name, type, a,s1,s2,s3,s4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<DoctorData,String>("name"));
        specialityTableColumn.setCellValueFactory(new PropertyValueFactory<DoctorData,String>("type"));
        hospitalTableColumn.setCellValueFactory(new PropertyValueFactory<DoctorData,String>("hospitalId"));
        dataTableView.setItems(doctor);
    }
    private void getFood(ActionEvent e) {
        hospitalC.setDisable(true);
        String acc = hospitalC.getValue();
        Iterator<String> iterate = hospital.iterator();
        while(iterate.hasNext()){
            if (iterate.next()==acc){
                hospitalS = Integer.parseInt(hospitalId.get(hospital.indexOf(acc)));
                break;
            }
        }
    }

    @FXML
    void add(ActionEvent e) throws IOException, SQLException {
        if (hospitalS==0||name.getText().equals("")||speciality.getText().equals("")){
            lb.setText("Value is Missing");
            return;
        }
        lb.setText("");
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();
        String q = "INSERT INTO `db_da`.`doctor` (`name`, `hospital_id`, `type`, `s1`, `s2`, `s3`, `s4`) VALUES ('"+name.getText()+"', '"+hospitalS+"', '"+speciality.getText()+"', '0', '0', '0', '0');";
        statement.executeUpdate(q);

        URL url = new File("src/main/resources/com/da/doctor-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void delete(ActionEvent e) throws SQLException, IOException {
        ObservableList<DoctorData> var = this.dataTableView.getSelectionModel().getSelectedItems();
        idDelete = ((DoctorData)var.get(0)).getId();

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();
        String q = "DELETE FROM `db_da`.`doctor` WHERE (`id` = '"+idDelete+"');";
        statement.executeUpdate(q);

        URL url = new File("src/main/resources/com/da/doctor-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    void back(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/admin-dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}