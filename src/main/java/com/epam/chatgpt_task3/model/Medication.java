package com.epam.chatgpt_task3.model;

import jakarta.persistence.*;
@Entity
@Table(name = "medications")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "duration")
    private String duration;

    public Medication(Long id, String medicationName, String dosage, String duration) {
        this.id = id;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public Medication() {
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
