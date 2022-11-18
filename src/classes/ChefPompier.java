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
    private HashSet<Incendie> affectedIncendies; // chaque robot est affecte' a un incendie
    
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
        this.affectedIncendies = new HashSet<Incendie>(); //pour la strat elementaire
    }

    public void restart() {
        this.affectedIncendies = new HashSet<Incendie>();
        for (Robot rl: robot)
            rl.restart();
    }


    /**
     * the boss give orders to their robots so that they put out the fires
     */
    // execute tous les n pas de temps
    public void strategieElementaire() {
        Double INF = Double.POSITIVE_INFINITY;
        for (Incendie i: incendies) {
            if (i.getIntensite() <= 0 || affectedIncendies.contains(i))
                continue;
            for (Robot r: robot) {
                if (!r.isAvailable() && r.timeToGo(i) != INF) {
                    // robot accepte se rendre a l'incendie i
                    r.affect(i);
                    affectedIncendies.add(i);
                    break;
                }
            }
        }
    }
    public void strategieEvolved() {
        for (Robot r: robot) {
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
