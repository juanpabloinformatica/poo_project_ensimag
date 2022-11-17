package events;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import classes.ChefPompier;
import gui.GUISimulator;
import gui.Simulable;

public class Simulateur implements Simulable {
    private Queue<Evenement> events;
    private LinkedList<Evenement> removedEvents;
    private GUISimulator gui;
    private int dateSimulation;
    private ChefPompier chef;
    private int n;

    public Simulateur(GUISimulator gui, ChefPompier chef) {
        dateSimulation = 0;
        this.chef = chef;
        events = new PriorityQueue<Evenement>();
        this.gui = gui;
        n = 100;
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
        if (simulationTerminee() && dateSimulation != 0) {
            System.out.println("SIMULATION TERMINEE");
            return;
        }
        if (dateSimulation % n == 0){
            System.out.println("STARTEGIE EXECUTEEE");
            this.chef.strategieEvolved();
        }
        while(events.peek() != null && events.peek().getDate() <= dateSimulation) {
            events.poll().execute();
        }
        dateSimulation++;
    }

    public boolean simulationTerminee() {
        return events.isEmpty();
    }



    @Override
    public void next() {
        incrementeDate();
    }

    @Override
    public void restart() {
        dateSimulation = 0;
        events = new PriorityQueue<>();
        chef.restart();
    }
}
