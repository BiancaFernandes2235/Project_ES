package main;

public class UserFactory {
    public static User createUser(String type, String id, String name, String pass) {
        switch (type.toLowerCase()) {
            case "doctor": return new Doctor(id, name, pass, "General");
            case "patient": return new Patient(id, name, pass);
            case "nurse": return new Nurse(id, name, pass);
            case "admin": return new Administrator(id, name, pass);
            case "pharmacist": return new Pharmacist(id, name, pass);
            default: return null;
        }
    }
}
