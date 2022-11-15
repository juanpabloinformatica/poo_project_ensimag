package robots;

import java.util.ArrayList;

import classes.Carte;
import classes.Case;
import classes.Incendie;
import events.Simulateur;

public abstract class PathCalculator {
    private Carte carte;
    public PathCalculator(Simulateur simulateur, Carte carte) {
        this.carte = carte;
    }
    public Carte getCarte() {
        return carte;
    }
    public abstract ArrayList<Case> computePath(Robot r, Incendie i) ;
    public abstract ArrayList<Case> computePathToWater(Robot r);
}
