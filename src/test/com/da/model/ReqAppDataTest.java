package com.da.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReqAppDataTest {

    public ReqAppData k;

    @BeforeEach
    void setUp() {
        k = new ReqAppData(1,"asd",1,"asd",1);
    }

    @Test
    void setId() {
        k.setId(1);
    }

    @Test
    void setName() {
        k.setName("Tahbib");
    }
}