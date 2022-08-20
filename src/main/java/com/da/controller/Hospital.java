package com.da.controller;

import com.da.DatabaseConnection;
import com.da.model.HospitalData;
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

public class Hospital {

    @FXML
    private TableView<HospitalData> dataTableView;
    @FXML
    private TableColumn<HospitalData, String> nameTableColumn;
    @FXML
    private TableColumn<HospitalData, String> addressTableColumn;

    @FXML
    public TextField name;
    @FXML
    public TextArea address;
    @FXML
    public Label lb;

    ObservableList<HospitalData> userObservableList = FXCollections.observableArrayList();
    public static int idDelete;

    @FXML
    public void initialize() {
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        String q = "SELECT * FROM hospital;";

        try {
            Statement statement = conBD.createStatement();
            ResultSet rs = statement.executeQuery(q);

            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                this.userObservableList.add(new HospitalData(id, name, address));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<HospitalData,String>("name"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<HospitalData,String>("address"));
        dataTableView.setItems(userObservableList);
    }

    public void add(ActionEvent e) throws SQLException, IOException {
        if (name.getText().equals("")||address.getText().equals("")){
            lb.setText("Value is Missing");
            return;
        }
        lb.setText("");
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();
        String q = "INSERT INTO `db_da`.`hospital` (`name`, `address`) VALUES ('"+name.getText()+"', '"+address.getText()+"');";
        statement.executeUpdate(q);

        URL url = new File("src/main/resources/com/da/hospital-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void delete(ActionEvent e) throws IOException, SQLException {
        ObservableList<HospitalData> var = this.dataTableView.getSelectionModel().getSelectedItems();
        idDelete = ((HospitalData)var.get(0)).getId();

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();
        String q = "DELETE FROM `db_da`.`hospital` WHERE (`id` = '"+idDelete+"');";
        statement.executeUpdate(q);

        URL url = new File("src/main/resources/com/da/hospital-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void back(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/admin-dashboard-view.fxml").toURI().toURL();
        Parent root = (Parent)FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}