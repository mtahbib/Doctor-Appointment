package com.da.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserDataTest {

    public UserData k;

    @BeforeEach
    void setUp() {
        k = new UserData(1,"a","a","a","m","asd");
    }

    @Test
    void getAge() {
    }

    @Test
    void setAge() {
        try {
            k.setAge(String.valueOf(100));
        }
        catch (Exception e){
            System.out.println("fail");
        }
    }
}