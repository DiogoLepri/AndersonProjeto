package com.example.andersonprojeto;

import java.sql.Timestamp;

public class Prescription {
    private String patientName;
    private String doctorName;
    private String medicineName;
    private String description;
    private String dosage;
    private int amount;
    private String admin;
    private String obs;
    private Timestamp createdAt;

    public Prescription(String patientName, String doctorName, String medicineName, String description, String dosage, int amount, String admin, String obs, Timestamp createdAt) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.medicineName = medicineName;
        this.description = description;
        this.dosage = dosage;
        this.amount = amount;
        this.admin = admin;
        this.obs = obs;
        this.createdAt = createdAt;
    }

    // Getters and setters for each field
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
