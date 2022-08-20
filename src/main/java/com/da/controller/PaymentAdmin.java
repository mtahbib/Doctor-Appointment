package com.da.controller;

import com.da.DatabaseConnection;
import com.da.model.PaymentData;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentAdmin {
    @FXML
    private TableView<PaymentData> dataTableView;
    @FXML
    private TableColumn<PaymentData, String> methodTableColumn;
    @FXML
    private TableColumn<PaymentData, String> slotTableColumn;
    @FXML
    private TableColumn<PaymentData, String> trxTableColumn;
    @FXML
    private TableColumn<PaymentData, String> userTableColumn;

    public ArrayList<Integer> id = new ArrayList<>();
    public ArrayList<String> name = new ArrayList<>();
    public ArrayList<String> trx = new ArrayList<>();
    public ArrayList<String> method = new ArrayList<>();
    public ArrayList<String> status = new ArrayList<>();

    public ObservableList<PaymentData> user = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM db_da.transaction;";
        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String trx = rs.getString(3);
                String method = rs.getString(4);
                String status = "";
                if (!rs.getBoolean(5))
                    status = "Pending";
                else status = "Accepted";
                int user = rs.getInt(6);
                this.user.add(new PaymentData(id, name, trx, method, status, user));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        userTableColumn.setCellValueFactory(new PropertyValueFactory<PaymentData,String>("name"));
        trxTableColumn.setCellValueFactory(new PropertyValueFactory<PaymentData,String>("trx"));
        methodTableColumn.setCellValueFactory(new PropertyValueFactory<PaymentData,String>("method"));
        slotTableColumn.setCellValueFactory(new PropertyValueFactory<PaymentData,String>("status"));
        dataTableView.setItems(user);
    }

    public void accept(ActionEvent e) throws IOException, SQLException {
        ObservableList<PaymentData> var = this.dataTableView.getSelectionModel().getSelectedItems();
        int id = ((PaymentData)var.get(0)).getId();
        int userId = ((PaymentData)var.get(0)).getUser();

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "UPDATE `db_da`.`transaction` SET `status` = '1' WHERE (`id` = '"+id+"');";
        statement.executeUpdate(q);

        String w = "UPDATE `db_da`.`user` SET `notif` = 'done' WHERE (`id` = '"+userId+"');";
        statement.executeUpdate(w);

        URL url = new File("src/main/resources/com/da/payment-admin-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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
