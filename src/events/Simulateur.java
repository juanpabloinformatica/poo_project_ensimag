package events;

import java.util.ArrayList;
import java.util.HashMap;

import gui.GUISimulator;
import gui.Simulable;
import robots.Robot;

public class Simulateur implements Simulable {
    private ArrayList<Evenement> events;
    private GUISimulator gui;
    private int dateSimulation;
    private HashMap<Robot, Boolean> availableRobots;

    public Simulateur(GUISimulator gui) {
        dateSimulation = 0;
        events = new ArrayList<Evenement>();
        this.gui = gui;
        availableRobots = new HashMap<Robot, Boolean>();
    }

    public void addEvenement(Evenement e) {
        events.add(e);
    }

    public void incrementeDate() {
        try {
            for (Evenement e: events) {
                // premier fois qu'on voit le robot
                System.out.println(availableRobots.get(e.getRobot()) + " " + e.getDateExecution() + " " + dateSimulation);
                if (availableRobots.get(e.getRobot()) == null) {
                    availableRobots.put(e.getRobot(), true);
                }
                System.out.println(availableRobots.get(e.getRobot()) + " " + e.getDateExecution() + " " + dateSimulation);
                // date d'execution du evenement non calcule
                // et le robot est disponible
                if (e.getDateExecution() == null &&
                    availableRobots.get(e.getRobot())) {
                    e.computeDateExecution(dateSimulation);
                    availableRobots.put(e.getRobot(), false);
                }
                System.out.println(availableRobots.get(e.getRobot()) + " " + e.getDateExecution() + " " + dateSimulation);

                if (e.getDateExecution() != null && dateSimulation >= e.getDateExecution()) {
                    e.execute();
                    e.setEventDone();
                    availableRobots.put(e.getRobot(), true);
                    events.remove(e);
                }
            }
            dateSimulation++;
        } catch (Exception e) {
            System.out.println("increment date exception" + e);
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
