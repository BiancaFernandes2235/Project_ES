package main;

public class PublicInsurance implements InsuranceStrategy {
    public double calculate(double amount) { 
    	return amount * 0.50; 
    } // Patient pays 50%
}
