package com.da.model;

public class DoctorData {
    private int id;
    private String name;
    private String type;
    private String hospitalId;
    private boolean s1;
    private boolean s2;
    private boolean s3;
    private boolean s4;

    public DoctorData(int id, String name, String type, String hospitalId, boolean s1, boolean s2, boolean s3, boolean s4) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.hospitalId = hospitalId;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public boolean isS1() {
        return s1;
    }

    public void setS1(boolean s1) {
        this.s1 = s1;
    }

    public boolean isS2() {
        return s2;
    }

    public void setS2(boolean s2) {
        this.s2 = s2;
    }

    public boolean isS3() {
        return s3;
    }

    public void setS3(boolean s3) {
        this.s3 = s3;
    }

    public boolean isS4() {
        return s4;
    }

    public void setS4(boolean s4) {
        this.s4 = s4;
    }
}
