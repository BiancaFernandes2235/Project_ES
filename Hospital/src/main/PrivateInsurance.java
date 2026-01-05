package main;

public class PrivateInsurance implements InsuranceStrategy {
    public double calculate(double amount) { 
    	return amount * 0.20; 
    } // Patient pays 20%
}
