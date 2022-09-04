package com.da.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HospitalDataTest {
    public HospitalData k;

    @BeforeEach
    void setUp() {
        k = new HospitalData(1,"asd","asd");
    }

    @Test
    void setName() {
        k.setName("Tahbib");
    }
}