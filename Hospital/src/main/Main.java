package main;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hospital System Demo\n");

        // Factory
        User doctor = UserFactory.createUser("doctor", "D1", "Dr. House", "pass");
        User patient = UserFactory.createUser("patient", "P1", "John Doe", "pass");
        User nurse = UserFactory.createUser("nurse", "N1", "Nurse Joy", "pass");
        User admin = UserFactory.createUser("admin", "A1", "Admin Alice", "pass");

        // Login
        System.out.println("Login Check: " + doctor.login("pass"));

        // Nurse (Triage)
        System.out.println("\n(Nurse) Triage Check");
        Nurse n = (Nurse) nurse;
   
        System.out.println("Critical Patient (130bpm): " + n.triage(130, 40.0)); 
        System.out.println("Normal Patient (80bpm): " + n.triage(80, 36.5));

        // 4. Doctor (Safety and Interactions)
        System.out.println("\n(Doctor) Safety Check");
        InteractionEngine engine = new InteractionEngine();
        Patient p = (Patient) patient;
        
        // Paciente toma Varfarina
        p.addPrescription(new Prescription("Warfarin", "5mg", new Date()));
        
        // Médico tenta dar Aspirina (PERIGO!)
        boolean isDangerous = engine.checkInteraction("Aspirin", p);
        
        if (isDangerous) {
            System.out.println("Alert: Interaction Detected");
            ((Doctor) doctor).overrideSafetyAlert("Medical emergency requires override.");
        }

        // 5. Admin (Report)
        System.out.println("\n (Admin) Reporting");
        ((Administrator) admin).generateReport();

        // Billing (Strategy Pattern)
        System.out.println("\n Strategy Check");
        BillingService bill = new BillingService();
        
        bill.setStrategy(new PrivateInsurance());
        System.out.println("Bill (Private): " + bill.calculate(100.0)); 

        bill.setStrategy(new PublicInsurance());
        System.out.println("Bil (Public): " + bill.calculate(100.0)); 

    }
}