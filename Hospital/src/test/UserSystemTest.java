package test;
import main.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserSystemTest {

    // Factory Creation
    @Test
    void testFactory_CreateDoctor() {
        User u = UserFactory.createUser("doctor", "1", "Name", "pass");
        assertTrue(u instanceof Doctor);
    }

    @Test
    void testFactory_CreateNurse() {
        User u = UserFactory.createUser("nurse", "1", "Name", "pass");
        assertTrue(u instanceof Nurse);
    }

    @Test
    void testFactory_CreatePatient() {
        User u = UserFactory.createUser("patient", "1", "Name", "pass");
        assertTrue(u instanceof Patient);
    }

    @Test
    void testFactory_CreateAdmin() {
        User u = UserFactory.createUser("admin", "1", "Name", "pass");
        assertTrue(u instanceof Administrator);
    }

    @Test
    void testFactory_CreatePharmacist() {
        User u = UserFactory.createUser("pharmacist", "1", "Name", "pass");
        assertTrue(u instanceof Pharmacist);
    }
    
    @Test
    void testFactory_InvalidType() {
        User u = UserFactory.createUser("alien", "1", "Name", "pass");
        assertNull(u, "Factory should return null for invalid types in simple code");
    }

    // Login System
    @Test
    void testLogin_Success() {
        User u = new Doctor("1", "Name", "secret123", "Spec");
        assertTrue(u.login("secret123"));
    }

    @Test
    void testLogin_Fail() {
        User u = new Doctor("1", "Name", "secret123", "Spec");
        assertFalse(u.login("wrongpass"));
    }
    
    @Test
    void testLogin_EmptyPass() {
        User u = new Doctor("1", "Name", "secret", "Spec");
        assertFalse(u.login(""));
    }

    // Data Integrity
    @Test
    void testUser_GetName() {
        User u = new Patient("1", "Bobby", "pass");
        assertEquals("Bobby", u.getName());
    }
    
    @Test
    void testUser_GetId() {
        User u = new Patient("99", "Bobby", "pass");
        assertEquals("99", u.getId());
    }
}
