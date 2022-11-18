package robots;

import java.util.List;

import classes.Carte;
import classes.Case;

/**
 * the pathCalculator class represent the parent class of naive path an dijsktra
 * this has the necesary methods that has to be implemented for its child classes 
 */
public abstract class PathCalculator {
    private Carte carte;

    /**
     * create a path calculator that receives a map
     * @param carte - map
     */
    public PathCalculator(Carte carte) {
        this.carte = carte;
    }
    
    /** 
     * get the map of the simulation
     * @return Carte
     */
    public Carte getCarte() {
        return carte;
    }
    /**
     * get a possible path to reache certain destination
     * @param r - robot
     * @param target - destination case
     * @return List<Case>
     */
    public abstract List<Case> computePath(Robot r, Case target) ;

    /** 
     *  get a naive path of the possible paths to arrive certain place to refill the tank
     * @param r - robot
     * @return List<Case>
     */
    public abstract List<Case> computePathToWater(Robot r);
    
    /** 
     * calculate the time in traversing a path to a destination
     * @param r - robot
     * @param target - destination
     * @return double
     */
    public abstract double getTimeToCase(Robot r, Case target);
}
