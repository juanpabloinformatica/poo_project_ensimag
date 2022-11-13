package classes;

import java.util.HashSet;

import robots.Robot;

public class ChefPompier {
    private Carte carte;
    private Robot[] robots;
    private Incendie[] incendies;
    public ChefPompier(Carte carte, Robot[] robots, Incendie[] incendies) {
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

    private HashSet<Incendie> affectedIncendies; // chaque robot est affecte' a un incendie
    // execute tous les n pas de temps
    public void strategieElementaire() {
        for (Incendie i: incendies) {
            for (Robot r: robots) {
                if (!r.isOccupied() && r.propose(i)) {
                    // robot accepte se rendre a l'incendie i
                    affectedIncendies.add(i);
                    break;
                }
            }
        }
    }
}
