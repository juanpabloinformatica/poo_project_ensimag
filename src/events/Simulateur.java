package events;

import java.util.LinkedList;
import java.util.PriorityQueue;

import classes.ChefPompier;
import gui.GUISimulator;
import gui.Simulable;

public class Simulateur implements Simulable {
    private PriorityQueue<Evenement> events;
    private LinkedList<Evenement> removedEvents;
    private GUISimulator gui;
    private int dateSimulation;
    private ChefPompier chef;
    private int n;

    public Simulateur(GUISimulator gui, ChefPompier chef) {
        dateSimulation = 0;
        this.chef = chef;
        events = new PriorityQueue<Evenement>();
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
        if (e != null)
            events.add(e);
    }

    public void incrementeDate() {
        if (simulationTerminee())
            return;
        if (dateSimulation % n == 0){
            this.chef.strategieElementaire();
        }
        while(events.peek() != null && events.peek().getDate() <= dateSimulation) {
            events.poll().execute();
        }
        dateSimulation++;
    }

    // public void restartEvents() {
    //     events.addAll(0, removedEvents);
    //     removedEvents.clear();
    // }

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
