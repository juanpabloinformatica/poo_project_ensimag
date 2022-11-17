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

    public void restart() {
        this.affectedIncendies = new HashSet<Incendie>();
        for (RobotLogic rl: robotsLogics)
            rl.restart();
    }

    private HashSet<Incendie> affectedIncendies; // chaque robot est affecte' a un incendie
    // execute tous les n pas de temps
    public void strategieElementaire() {
        Double INF = Double.POSITIVE_INFINITY;
        for (Incendie i: incendies) {
            if (i.getIntensite() <= 0 || affectedIncendies.contains(i))
                continue;
            for (RobotLogic r: robotsLogics) {
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
        for (RobotLogic r: robotsLogics) {
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
