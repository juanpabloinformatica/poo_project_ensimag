package events;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import classes.ChefPompier;
import gui.GUISimulator;
import gui.Simulable;


/**
 * the class Simulateur represents the order of the differents events in a simulation,
 * keep in order all the differents actions to carry out
 * 
 */
public class Simulateur implements Simulable {
    private Queue<Evenement> events;
    private LinkedList<Evenement> removedEvents;
    private GUISimulator gui;
    private int dateSimulation;
    private ChefPompier chef;
    private int n;

    /**
     * create a simulation that receives a graphical user interfece and the boss robot
     * @param gui - graphical user interface
     * @param chef - the boss robot
     */
    public Simulateur(GUISimulator gui, ChefPompier chef) {
        dateSimulation = 0;
        this.chef = chef;
        events = new PriorityQueue<Evenement>();
        this.gui = gui;
        n = 100;
    }

    
    /** 
     * get the date of simualtion
     * @return int
     */
    public int getDateSimulation() {
        return dateSimulation;
    }

    
    /** 
     * set the date of simulation
     * @param dateSimulation - date
     */
    public void setDateSimulation(int dateSimulation) {
        this.dateSimulation = dateSimulation;
    }

    
    /** 
     * add an event in the list of events
     * @param e - event 
     */
    public void addEvenement(Evenement e) {
        if (e != null)
            events.add(e);
    }

    /**
     * increment the date to performs the following events
     */
    public void incrementeDate() {
        if (dateSimulation == 0)
            chef.verifyUnusableRobots();
        if (simulationTerminee() && dateSimulation != 0) {
            System.out.println("SIMULATION TERMINEE");
            return;
        }
        if (dateSimulation % n == 0){
            this.chef.strategie();
            // this.chef.strategieElementaire();
        }
        while(events.peek() != null && events.peek().getDate() <= dateSimulation) {
            events.poll().execute();
        }
        dateSimulation++;
    }


    /** 
     * indicate if the simulations are finished
     * @return boolean
     */
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
