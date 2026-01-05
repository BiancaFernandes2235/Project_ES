package main;

import java.util.*;

public class ScheduleManager {
    private List<Appointment> apps = new ArrayList<>();

    public boolean book(Date d, Doctor doc, Patient p) {
        for (Appointment a : apps) {
            // If doctor is busy at this exact time -> Block it
            if (a.getDoctor() == doc && a.getDate().equals(d)) 
            	return false;
        }
        apps.add(new Appointment(d, doc, p));
        return true;
    }
}
