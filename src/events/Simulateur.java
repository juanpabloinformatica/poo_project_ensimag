package events;

import java.util.LinkedList;

import gui.GUISimulator;
import gui.Simulable;

public class Simulateur implements Simulable {
    private LinkedList<Evenement> events;
    private LinkedList<Evenement> removedEvents;
    private GUISimulator gui;
    private int dateSimulation;

    public Simulateur(GUISimulator gui) {
        dateSimulation = 0;
        events = new LinkedList<Evenement>();
        removedEvents = new LinkedList<Evenement>();
        this.gui = gui;
    }

    public int getDateSimulation() {
        return dateSimulation;
    }

    public void setDateSimulation(int dateSimulation) {
        this.dateSimulation = dateSimulation;
    }

    public void addEvenement(Evenement e) {
        if (events.isEmpty()) {
            events.add(e);
            return;
        }
        boolean inserted = false;
        for (int i = 0; i < events.size() && !inserted; i++) {
            if (e.getDate() < events.get(i).getDate()) {
                events.add(i, e);
                inserted = true;
            }
        }
        if (!inserted)
            events.add(e);
    }

    public void incrementeDate() {
        LinkedList<Evenement> toRemove = new LinkedList<>();
        if (!simulationTerminee()) {
            for (Evenement e: events) {
                if (e.getDate() <= dateSimulation) {
                    e.execute();
                    toRemove.add(e);
                } else {
                    break;
                }
            }
            for (Evenement e: toRemove)
                events.remove(e);
            removedEvents.addAll(toRemove);
        }
        dateSimulation++;
    }

    public void restartEvents() {
        events.addAll(0, removedEvents);
        removedEvents.clear();
    }

    public boolean simulationTerminee() {
        return events.isEmpty();
    }



    @Override
    public void next() {
    }

    @Override
    public void restart() {
    }
}
