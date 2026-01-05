package main;

public class BillingService {
    private InsuranceStrategy strategy;
    public void setStrategy(InsuranceStrategy s) { 
    	this.strategy = s; 
    }

    public double calculate(double amount) {
        if (strategy == null) 
        	return amount;
        else
        	return strategy.calculate(amount);
    }
}
