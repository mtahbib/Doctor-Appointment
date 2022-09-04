package com.da.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDataTest {

    public PaymentData k;

    @BeforeEach
    void setUp() {
        k = new PaymentData(1,"asd","asd","bkash","asd",1);
    }

    @Test
    void setName() {
        k.setName("Tahbib");
    }
}