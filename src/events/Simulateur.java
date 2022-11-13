package events;

import java.util.LinkedList;

import classes.ChefPompier;
import gui.GUISimulator;
import gui.Simulable;

public class Simulateur implements Simulable {
    private LinkedList<Evenement> events;
    private LinkedList<Evenement> removedEvents;
    private GUISimulator gui;
    private int dateSimulation;
    private ChefPompier chef;
    private int n;

    public Simulateur(GUISimulator gui, ChefPompier chef) {
        dateSimulation = 0;
        this.chef = chef;
        events = new LinkedList<Evenement>();
        removedEvents = new LinkedList<Evenement>();
        this.gui = gui;
        this.n = 1000;
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
        if (dateSimulation % n == 0){
            this.chef.strategieElementaire();
        }
        if (simulationTerminee())
            return;
        LinkedList<Evenement> toRemove = new LinkedList<>();
        LinkedList<Evenement> toAdd = new LinkedList<>();
        for (Evenement e: events) {
            if (e.getDate() <= dateSimulation) {
                Evenement newEv = e.execute();
                if (newEv != null)
                    toAdd.add(newEv);
                toRemove.add(e);
            } else {
                break;
            }
        }
        for (Evenement e: toAdd)
            addEvenement(e);

        for (Evenement e: toRemove)
            events.remove(e);
        removedEvents.addAll(toRemove);
        dateSimulation++;
    }

    public void restartEvents() {
        events.addAll(0, removedEvents);
        removedEvents.clear();
    }

    public boolean simulationTerminee() {
        chef.strategieElementaire();
        return events.isEmpty();
    }



    @Override
    public void next() {
    }

    @Override
    public void restart() {
    }
}
