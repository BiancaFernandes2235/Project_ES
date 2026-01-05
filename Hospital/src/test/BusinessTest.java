package test;
import main.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class BusinessTest {
    private BillingService billing;
    private ScheduleManager scheduler;
    private Doctor doctor;
    private Patient patient;

    @BeforeEach
    void setUp() {
        billing = new BillingService();
        scheduler = new ScheduleManager();
        
        doctor = new Doctor("D1", "House", "pass", "General");
        patient = new Patient("P1", "John", "pass");
    }

    // Billing Strategy 
    @Test
    void testBilling_Private() {
        billing.setStrategy(new PrivateInsurance());
        // Simple Code: Private pays 20%
        assertEquals(20.0, billing.calculate(100.0), 0.01);
    }

    @Test
    void testBilling_Public() {
        billing.setStrategy(new PublicInsurance());
        // Simple Code: Public pays 50%
        assertEquals(50.0, billing.calculate(100.0), 0.01);
    }

    @Test
    void testBilling_NoStrategy() {
        // Should return full amount if no strategy set
        assertEquals(100.0, billing.calculate(100.0), 0.01);
    }

    @Test
    void testBilling_ZeroAmount() {
        billing.setStrategy(new PrivateInsurance());
        assertEquals(0.0, billing.calculate(0.0), 0.01);
    }

    @Test
    void testBilling_LargeAmount() {
        billing.setStrategy(new PublicInsurance());
        assertEquals(5000.0, billing.calculate(10000.0), 0.01);
    }
    
    @Test
    void testBilling_SwitchStrategy() {
        billing.setStrategy(new PrivateInsurance());
        double val1 = billing.calculate(100.0);
        billing.setStrategy(new PublicInsurance());
        double val2 = billing.calculate(100.0);
        assertNotEquals(val1, val2);
    }

    // Scheduling
    @Test
    void testBooking_Success() {
        boolean result = scheduler.book(new Date(), doctor, patient);
        assertTrue(result);
    }

    @Test
    void testBooking_Conflict() {
        Date now = new Date();
        scheduler.book(now, doctor, patient);
        // Same doctor, same time -> Fail
        boolean result = scheduler.book(now, doctor, new Patient("P2", "Jane", "pass"));
        assertFalse(result);
    }

    @Test
    void testBooking_DifferentDoctor() {
        Date now = new Date();
        scheduler.book(now, doctor, patient);
        // Different doctor, same time -> Success
        Doctor doc2 = new Doctor("D2", "Wilson", "pass", "Oncology");
        boolean result = scheduler.book(now, doc2, patient);
        assertTrue(result);
    }
    
    @Test
    void testBooking_DifferentTime() {
        Date time1 = new Date(100000);
        Date time2 = new Date(200000);
        scheduler.book(time1, doctor, patient);
        // Same doctor, different time -> Success
        assertTrue(scheduler.book(time2, doctor, patient));
    }
    
    @Test
    void testBooking_SamePatientDifferentDoc() {
        Date now = new Date();
        Doctor doc2 = new Doctor("D2", "Wilson", "pass", "Onc");
        scheduler.book(now, doctor, patient);
        // Simple scheduler only checks Doctor availability, so this might pass
        assertTrue(scheduler.book(now, doc2, patient));
    }
}
