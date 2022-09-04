package com.da.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorDataTest {

    public DoctorData k;

    @BeforeEach
    void setUp() {
        k = new DoctorData(1,"tahbib", "ex", "1",true,true,true,true);
    }

    @Test
    void setName() {
        k.setName("Tahbib");
    }

    @Test
    void setBoolean() {
        k.isS1(false);
    }
}