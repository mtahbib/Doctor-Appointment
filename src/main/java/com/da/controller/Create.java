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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Create {

    @FXML
    private TextField address;
    @FXML
    private TextField age;
    @FXML
    private TextField email;
    @FXML
    private RadioButton female;
    @FXML
    private Label labelA;
    @FXML
    private Label labelAge;
    @FXML
    private Label labelE;
    @FXML
    private Label labelN1;
    @FXML
    private Label labelN2;
    @FXML
    private Label labelP;
    @FXML
    private Label labelR;
    @FXML
    private RadioButton male;
    @FXML
    private TextField name1;
    @FXML
    private TextField name2;
    @FXML
    private TextField password;
    @FXML
    private ToggleGroup tg;

    public String radio="";

    private static final String regex = "^(.+)@(.+)$";

    @FXML
    public void initialize(){
        male.setToggleGroup(tg);
        female.setToggleGroup(tg);
    }

    public void create(ActionEvent e) throws SQLException, IOException {
        boolean a = true;
        labelE.setText("");
        labelN1.setText("");
        labelN2.setText("");
        labelP.setText("");
        labelA.setText("");
        labelAge.setText("");
        labelR.setText("");


        if (Objects.equals(email.getText(), "")){
            labelE.setText("Blank");
            email.setText("");
            a = false;
        }else{
            if (!checkEmail(email.getText())){
                labelE.setText("Invalid");
                email.setText("");
                a = false;
            }
        }

        if (Objects.equals(password.getText(), "")){
            labelP.setText("Blank");
            password.setText("");
            a = false;
        }else if (password.getText().length()<6){
            password.setText("");
            labelP.setText("Min 6");
            a = false;
        }

        if (Objects.equals(name1.getText(), "")){
            name1.setText("");
            labelN1.setText("Blank");
            a = false;
        }

        if (Objects.equals(name2.getText(), "")){
            name2.setText("");
            labelN2.setText("Blank");
            a = false;
        }

        if (Objects.equals(address.getText(), "")){
            address.setText("");
            labelA.setText("Blank");
            a = false;
        }

        if (Objects.equals(age.getText(), "")){
            age.setText("");
            labelAge.setText("Blank");
            a = false;
        }else if (!isNumeric(age.getText())){
            age.setText("");
            labelAge.setText("Not Number");
            a = false;
        }

        if (radio.equals("")){
            labelR.setText("Choose one");
            a = false;
        }

        if (a){
            DatabaseConnection con = new DatabaseConnection();
            Connection conBD = con.getConnection();
            Statement statement = conBD.createStatement();
            String q = "INSERT INTO `db_da`.`user` (`email`, `password`, `first_name`, `last_name`, `age`, `gender`, `address`, `status`, `notif`, `seat`) VALUES ('"+email.getText()+"', '"+password.getText()+"', '"+name1.getText()+"', '"+name2.getText()+"', '"+age.getText()+"', '"+radio+"', '"+address.getText()+"', 'x', 'no', '.');";
            statement.executeUpdate(q);

            URL url = new File("src/main/resources/com/da/landing-page-view.fxml").toURI().toURL();
            Parent root = (Parent) FXMLLoader.load(url);
            Scene scene = new Scene(root);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    }

    public boolean checkEmail(String a){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(a);
        return matcher.matches();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void toggle(ActionEvent e) {
        if (male.isSelected()){
            radio = "male";
        }
        else if (female.isSelected()){
            radio = "female";
        }
    }

    public void back(ActionEvent e) throws IOException {
        URL url = new File("src/main/resources/com/da/landing-page-view.fxml").toURI().toURL();
        Parent root = (Parent) FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}