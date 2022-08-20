package com.da.model;

public class ReqAppData {
    private int id;
    private String name;
    private int docId;
    private String doctor;
    private int slot;

    public ReqAppData(int id, String name, int docId, String doctor, int slot) {
        this.id = id;
        this.name = name;
        this.docId = docId;
        this.doctor = doctor;
        this.slot = slot;
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

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
