package com.example.chen_enen130app.DataFiles;

public class ChemEquilParameters {
    private Integer position;
    private Double stoichCoeff;

    public ChemEquilParameters() {
        position = null;
        stoichCoeff = null;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Double getStoichCoeff() {
        return stoichCoeff;
    }

    public void setStoichCoeff(Double stoichCoeff) {
        this.stoichCoeff = stoichCoeff;
    }
}