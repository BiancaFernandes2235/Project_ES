package test;
import main.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ClinicalTest {
    private InteractionEngine engine;
    private Nurse nurse;
    private Patient patient;

    @BeforeEach
    void setUp() {
        engine = new InteractionEngine();
    
        nurse = new Nurse("N1", "Nurse Joy", "pass");
        patient = new Patient("P1", "John", "pass");
    }

    // --- Interaction Engine (Safety)
    @Test
    void testInteraction_Risk_AspirinWarfarin() {
        patient.addPrescription(new Prescription("Warfarin", "5mg", new Date()));
        // Aspirin + Warfarin = Danger
        assertTrue(engine.checkInteraction("Aspirin", patient));
    }

    @Test
    void testInteraction_Safe_VitaminC() {
        patient.addPrescription(new Prescription("Warfarin", "5mg", new Date()));
        // Vitamin C is safe
        assertFalse(engine.checkInteraction("Vitamin C", patient));
    }

    @Test
    void testInteraction_NoPrescriptions() {
        // Patient takes nothing, so Aspirin is safe
        assertFalse(engine.checkInteraction("Aspirin", patient));
    }

    @Test
    void testInteraction_UnknownDrug() {
        patient.addPrescription(new Prescription("Advil", "200mg", new Date()));
        // "Water" is not in the danger list
        assertFalse(engine.checkInteraction("Water", patient));
    }

    // Nurse Triage (Red/Green Only)
    @Test
    void testTriage_Critical_HighHeart() {
        // Heart Rate 130 > 120 -> RED
        assertEquals("Red", nurse.triage(130, 37.0));
    }

    @Test
    void testTriage_Critical_HighTemp() {
        // Temp 40.0 > 39.5 -> RED
        assertEquals("Red", nurse.triage(80, 40.0));
    }

    @Test
    void testTriage_Critical_BothHigh() {
        assertEquals("Red", nurse.triage(130, 40.0));
    }

    @Test
    void testTriage_Green_Normal() {
        assertEquals("Green", nurse.triage(80, 36.5));
    }

    @Test
    void testTriage_Green_Borderline() {
        // 120 is exactly the limit, so it should be Green (since logic is > 120)
        assertEquals("Green", nurse.triage(120, 37.0));
    }

    // Prescription Data
    @Test
    void testPrescription_GetName() {
        Prescription p = new Prescription("Tylenol", "500mg", new Date());
        assertEquals("Tylenol", p.getName());
    }
    
    @Test
    void testPatient_AddPrescription() {
        Prescription p = new Prescription("Tylenol", "500mg", new Date());
        patient.addPrescription(p);
        assertEquals(1, patient.getActivePrescriptions().size());
    }
}
