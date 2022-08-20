package com.da.model;

public class PaymentData {
    private int id;
    private String name;
    private String trx;
    private String method;
    private String status;
    private int user;

    public PaymentData(int id, String name, String trx, String method, String status, int user) {
        this.id = id;
        this.name = name;
        this.trx = trx;
        this.method = method;
        this.status = status;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrx() {
        return trx;
    }

    public void setTrx(String trx) {
        this.trx = trx;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
