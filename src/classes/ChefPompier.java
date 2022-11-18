package classes;

import java.util.HashSet;

import robots.Robot;
import robots.Robot;

/**
 * the ChefPompier class represent the boss of the robots,
 * this robot has all the information related to the map,
 * firefighter robots and the incendies in the map
 */
public class ChefPompier {
    private Carte carte;
    private Robot[] robot;
    private Incendie[] incendies;

    /**
     * create the boss robot receiving the map, the robots and the fires
     * @param carte - map 
     * @param robots - array of firefighters robots in the map 
     * @param incendies - array of fires in the map
     */
    public ChefPompier(Carte carte, Robot[] robots, Incendie[] incendies) {
        this.carte = carte;
        this.incendies = incendies;
        this.robot = robots;
    }

    public void restart() {
        for (Robot rl: robot)
            rl.restart();
    }


    /*
     * if there are robots that cant go to any fire consider them as occupied
     * for performance enhancement
     *  */
    public void verifyUnusableRobots() {
        Double INF = Double.POSITIVE_INFINITY;
        boolean canMove;
        for (Robot r: robot) {
            canMove = false;
            for (Incendie i: incendies) {
                if (r.timeToGo(i) != INF)
                    canMove = true;
            }
            if (!canMove)
                r.setOccupied(true);
        }
    }

    public void strategie() {
        this.strategieEvolved();
        // this.strategieElementaire();
    }
    /**
     * the boss give orders to their robots so that they put out the fires
     */
    // execute tous les n pas de temps
    public void strategieElementaire() {
        Double INF = Double.POSITIVE_INFINITY;
        for (Incendie i: incendies) {
            if (i.getIntensite() <= 0)
                continue;
            for (Robot r: robot) {
                if (r.isAvailable() && r.timeToGo(i) != INF) {
                    // robot accepte se rendre a l'incendie i
                    r.affect(i);
                    r.setOccupied(true);
                    break;
                }
            }
        }
    }
    public void strategieEvolved() {
        for (Robot r: robot) {
            // System.out.println(r + " " + r.isAvailable());
            if (!r.isAvailable())
                continue;
            double minTime = Double.POSITIVE_INFINITY;
            double currTime;
            Incendie selectedIncendie = null;
            for (Incendie i: incendies) {
                if (i.getIntensite() <= 0)
                    continue;
                    currTime = r.timeToGo(i);
                    if (currTime < minTime) {
                        minTime = currTime;
                        selectedIncendie = i;
                    }
                }
            if (selectedIncendie != null) {
                r.affect(selectedIncendie);
                r.setOccupied(true);
            }
        }
    }
}
