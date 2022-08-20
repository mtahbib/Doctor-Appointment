package com.da.controller;

import com.da.DatabaseConnection;
import com.da.model.ReqAppData;
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

public class ReqApp {

    @FXML
    private TableColumn<ReqAppData, String> userTableColumn;
    @FXML
    private TableColumn<ReqAppData, String> doctorTableColumn;
    @FXML
    private TableColumn<ReqAppData, Integer> slotTableColumn;
    @FXML
    private TableView<ReqAppData> dataTableView;

    public ArrayList<Integer> userId = new ArrayList<>();
    public ArrayList<String> name = new ArrayList<>();
    public ArrayList<Integer> slot = new ArrayList<>();
    public ArrayList<Integer> docId = new ArrayList<>();
    public ArrayList<String> docName = new ArrayList<>();

    public ObservableList<ReqAppData> user = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();

        String q = "SELECT * FROM db_da.user;";
        try {
            Statement s = conBD.createStatement();
            ResultSet rs = s.executeQuery(q);

            while (rs.next()){
                if (rs.getString(9).equals("w")){
                    String[] a = rs.getString(11).split("\\|");

                    userId.add(rs.getInt(1));
                    name.add(rs.getString(4)+" "+rs.getString(5));
                    slot.add(Integer.valueOf(a[0]));
                    docId.add(Integer.valueOf(a[1]));
                }

            }
            for (int i = 0; i<docId.size(); i++){
                String qq = "SELECT * FROM db_da.doctor where id="+docId.get(i)+";";
                System.out.println(docId.get(i));
                Statement ss = conBD.createStatement();
                ResultSet rss = ss.executeQuery(qq);
                while (rss.next()){
                    docName.add(rss.getString(2));
                }
                this.user.add(new ReqAppData(userId.get(i),name.get(i), docId.get(i),docName.get(i),slot.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        userTableColumn.setCellValueFactory(new PropertyValueFactory<ReqAppData,String>("name"));
        doctorTableColumn.setCellValueFactory(new PropertyValueFactory<ReqAppData,String>("doctor"));
        slotTableColumn.setCellValueFactory(new PropertyValueFactory<ReqAppData,Integer>("slot"));
        dataTableView.setItems(user);
    }

    @FXML
    public void accept(ActionEvent e) throws SQLException, IOException {
        ObservableList<ReqAppData> var = this.dataTableView.getSelectionModel().getSelectedItems();
        int id = ((ReqAppData)var.get(0)).getId();

        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "UPDATE `db_da`.`user` SET `status` = 'p', `notif` = 'payment' WHERE (`id` = '"+id+"');";
        statement.executeUpdate(q);

        URL url = new File("src/main/resources/com/da/req-app-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void reject(ActionEvent e) throws SQLException, IOException {
        ObservableList<ReqAppData> var = this.dataTableView.getSelectionModel().getSelectedItems();
        int id = ((ReqAppData)var.get(0)).getId();
        int docId = ((ReqAppData)var.get(0)).getDocId();
        int slot = ((ReqAppData)var.get(0)).getSlot();


        DatabaseConnection con = new DatabaseConnection();
        Connection conBD = con.getConnection();
        Statement statement = conBD.createStatement();

        String q = "UPDATE `db_da`.`user` SET `status` = 'x', `notif` = 'cancel', `seat` = '.' WHERE (`id` = '"+id+"');";
        statement.executeUpdate(q);
        String w = "UPDATE `db_da`.`doctor` SET `s"+slot+"` = '0' WHERE (`id` = '"+docId+"');";
        statement.executeUpdate(w);

        URL url = new File("src/main/resources/com/da/req-app-view.fxml").toURI().toURL();
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
