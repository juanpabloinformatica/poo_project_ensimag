package classes;

import java.util.HashSet;

import robots.Robot;
import robots.RobotLogic;

public class ChefPompier {
    private Carte carte;
    private RobotLogic[] robotsLogics;
    private Incendie[] incendies;
    public ChefPompier(Carte carte, Robot[] robots, Incendie[] incendies) {
        this.carte = carte;
        this.incendies = incendies;
        this.robotsLogics = new RobotLogic[robots.length];
        for (int i = 0; i < robots.length; i++) {
            this.robotsLogics[i] = new RobotLogic(robots[i]);
        }
        this.affectedIncendies = new HashSet<Incendie>();
    }

    private HashSet<Incendie> affectedIncendies; // chaque robot est affecte' a un incendie
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
