package main;

import java.util.*;

public class InteractionEngine {
    private Map<String, List<String>> badMixes = new HashMap<>();

    public InteractionEngine() {
    	//Aspirin cant mix with Warfarin
        badMixes.put("Aspirin", Arrays.asList("Warfarin"));
    }

    public boolean checkInteraction(String drug, Patient p) {
        if (!badMixes.containsKey(drug)) 
        	return false;

        List<String> conflicts = badMixes.get(drug);
        for (Prescription active : p.getActivePrescriptions()) {
            if (conflicts.contains(active.getName())) {
                AuditLogger.getInstance().log("Danger: " + drug + " mixes with " + active.getName());
                return true;
            }
        }
        return false;
    }
}
