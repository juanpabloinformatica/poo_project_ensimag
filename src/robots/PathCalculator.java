package robots;

import classes.Carte;
import classes.Incendie;
import events.Simulateur;

public abstract class PathCalculator {
    private Carte carte;
    private Simulateur simulateur;

    public PathCalculator(Simulateur simulateur, Carte carte) {
        this.carte = carte;
        this.simulateur = simulateur;
    }

    public Carte getCarte() {
        return carte;
    }
    public void setCarte(Carte carte) {
        this.carte = carte;
    }
    public Simulateur getSimulateur() {
        return simulateur;
    }

    public void setSimulateur(Simulateur simulateur) {
        this.simulateur = simulateur;
    }

    public abstract Path computePath(Robot r, Incendie i);
    public abstract void addPathEventsToSimulateur(Robot r, Incendie i, Path path);
    public abstract Path computePathToWater(Robot r);
}
