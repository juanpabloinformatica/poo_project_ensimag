package events;

import gui.GUISimulator;
import java.util.ArrayList;
import gui.Simulable;

public class Simulateur implements Simulable {
    private ArrayList<Evenement> events;
    private GUISimulator gui;
    private int dateSimulation;

    public Simulateur(GUISimulator gui) {
        dateSimulation = 0;
        events = new ArrayList<Evenement>();
        this.gui = gui;
    }

    public void addEvenement(Evenement e) {
        events.add(e);
    }

    public void incrementeDate() {
        try {
            events.get(dateSimulation++).execute();
        } catch (Exception e) {
            System.out.println("increment date exception");
        }
    }

    public boolean simulationTerminee() {
        return dateSimulation >= events.size();

    }



    @Override
    public void next() {
    }

    @Override
    public void restart() {
    }
}
