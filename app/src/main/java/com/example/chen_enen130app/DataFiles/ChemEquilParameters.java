package com.example.chen_enen130app.DataFiles;

import android.util.Log;

public class ChemEquilParameters {
    private Integer position;
    private Double stoichCoeff;
    private Double A, B, C, D;
    private Double gibbs298K, enth298K;

    public ChemEquilParameters() {
        position = null;
        stoichCoeff = null;

        A = null;
        B = null;
        C = null;
        D = null;

        gibbs298K = null;
        enth298K = null;
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

    public Double getA() {return A;}

    public void setA(Double a) {A = a;}

    public Double getB() {return B;}

    public void setB(Double b) {B = b;}

    public Double getC() {return C;}

    public void setC(Double c) {C = c;}

    public Double getD() {return D;}

    public void setD(Double d) {D = d;}

    public Double getGibbs298K() {return gibbs298K;}

    public void setGibbs298K(Double gibbs298K) {this.gibbs298K = gibbs298K;}

    public Double getEnth298K() {return enth298K;}

    public void setEnth298K(Double enth298K) {this.enth298K = enth298K;}

    /**
     * Class method to calculate the Gibbs Energy of Reaction given appropriate parameters.
     * This method calculates the energy of reaction in the gas phase.
     * This method should be used in conjunction with <b><i>equilibriumConstant(...)</i></b>,
     *      to use the result of this method as a parameter to calculate the equilibrium constant.
     * @param cEPArray (<b>ChemEquilParameters[]</b>): An array that holds ChemEquilParameters.
     * @param temperature (<b>Double</b>): The temperature of the reaction, in K.
     *                   Note that this method will not check for whether the reaction happens below the boiling point of one of the constituents.
     *                   It is up to the user to decide how to check for valid temperature inputs.
     * @return (<b>double</b>): The energy of reaction, given in J/mol.
     */
    public static double gibbsEnergyofReaction(ChemEquilParameters[] cEPArray, Double temperature) {
        double totalA = 0, totalB = 0, totalC = 0, totalD = 0, totalEnthalpy = 0, totalGibbs = 0;
        double enthalpyInt, gibbsInt;

        //GRT = ΔG/RT
        double GRT, gibbsEnergyRxn;

        for (ChemEquilParameters chemEquilParameters : cEPArray) {
            double coeff = chemEquilParameters.getStoichCoeff();

            totalA += coeff * chemEquilParameters.getA();
            totalB += coeff * chemEquilParameters.getB();
            totalC += coeff * chemEquilParameters.getC();
            totalD += coeff * chemEquilParameters.getD();
            totalEnthalpy += coeff * chemEquilParameters.getEnth298K();
            totalGibbs += coeff * chemEquilParameters.getGibbs298K();
        }

        totalB /= Math.pow(10,3);
        totalC /= Math.pow(10,6);
        totalD /= Math.pow(10,-5);

        enthalpyInt = totalA * (temperature - 298.15);
        enthalpyInt += totalB/2 * (Math.pow(temperature, 2) - Math.pow(298.15, 2));
        enthalpyInt += totalC/3 * (Math.pow(temperature, 3) - Math.pow(298.15, 3));
        enthalpyInt += totalD * (temperature - 298.15)/(temperature * 298.15);

        Log.e(null, "Enthalpy integration: " + enthalpyInt);

        gibbsInt = totalA * Math.log(temperature/298.15);
        gibbsInt += (totalB + (totalC + totalD/(Math.pow(298.15, 2) * Math.pow(temperature, 2))) * ((temperature + 298.15)/2)) * (temperature - 298.15);

        GRT = (totalGibbs - totalEnthalpy)/(8.314 * 298.15) + (totalEnthalpy)/(8.314 * temperature);
        GRT += 1/(temperature) * enthalpyInt - gibbsInt;

        gibbsEnergyRxn = GRT * 8.314 * temperature;

        Log.e(null, "Gibbs energy of reaction: " + gibbsEnergyRxn);

        return gibbsEnergyRxn;
    }

    /**
     * Class method to calculate the equilibrium constant.
     * @param gibbsEnergyRxn (<b>Double</b>): The Gibbs energy of reaction, in J/mol.
     *                       This parameter should take in the return result of <b><i>gibbsEnergyofReaction(...)</i></b>.
     * @param temperature (<b>Double</b>): The temperature, in K. Should correspond to the same temperature used in <b><i>gibbsEnergyofReaction(...)</i></b>.
     * @return (<b>double</b>): The equilibrium constant.
     */
    public static double equilibriumConstant(Double gibbsEnergyRxn, Double temperature) {
        double equilK = Math.exp(gibbsEnergyRxn/(8.314 * temperature));

        Log.e(null, "Equilibrium constant: " + equilK);

        return equilK;
    }
}