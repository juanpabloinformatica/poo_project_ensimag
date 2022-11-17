package classes;

import java.util.HashSet;

import robots.Robot;
import robots.RobotLogic;

/**
 * the ChefPompier class represent the boss of the robots,
 * this robot has all the information related to the map,
 * firefighter robots and the incendies in the map
 */
public class ChefPompier {
    private Carte carte;
    private RobotLogic[] robotsLogics;
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
        this.robotsLogics = new RobotLogic[robots.length];
        for (int i = 0; i < robots.length; i++) {
            this.robotsLogics[i] = new RobotLogic(robots[i]);
        }
        this.affectedIncendies = new HashSet<Incendie>();
    }

    /**
     * the boss give orders to their robots so that they put out the fires
     */
    // execute tous les n pas de temps
    public void strategieElementaire() {
        for (Incendie i: incendies) {
            if (i.getIntensite() <= 0)
                continue;
            for (RobotLogic r: robotsLogics) {
                if (!r.isOccupied() && r.propose(i)) {
                    // robot accepte se rendre a l'incendie i
                    affectedIncendies.add(i);
                    r.setOccupied(true);
                    break;
                }
            }
        }
    }
}
