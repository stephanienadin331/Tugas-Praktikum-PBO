package com.example.demo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {

    @NotBlank(message = "NPM tidak boleh kosong")
    private String npm;

    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;

    @DecimalMin(value = "0.0", message = "IPK minimal 0.0")
    @DecimalMax(value = "4.0", message = "IPK maksimal 4.0")
    private Double ipk;

    // ===== GETTER & SETTER =====

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIpk() {
        return ipk;
    }

    public void setIpk(Double ipk) {
        this.ipk = ipk;
    }
}
