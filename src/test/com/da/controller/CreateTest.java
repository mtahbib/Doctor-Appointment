package com.da.controller;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CreateTest {
    public Create k = new Create();

    @BeforeEach
    void setUp() {
    }

    @Test
    void create() throws SQLException, IOException {
        k.create(new ActionEvent());
    }

    @Test
    void checkEmail() {
        boolean a =  k.checkEmail("asd.com");
        System.out.println(a);
    }

    @Test
    void isNumeric() {
        boolean a = k.isNumeric(String.valueOf(10));
        System.out.println(a);
    }
}