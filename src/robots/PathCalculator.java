package robots;

import java.util.List;

import classes.Carte;
import classes.Case;

public abstract class PathCalculator {
    private Carte carte;
    private List<Case> WaterCases;
    private List<Case> NeighbourOfWaterCases;
    public PathCalculator(Carte carte) {
        this.carte = carte;
    }
    public Carte getCarte() {
        return carte;
    }

    public abstract List<Case> computePath(Robot r, Case target) ;
    public abstract List<Case> computePathToWater(Robot r);
    public abstract double getTimeToCase(Robot r, Case target);
}
