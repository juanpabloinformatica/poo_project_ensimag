package classes;
import robots.*;

/**
 * the class represent all the data needed to make the simualtion
 */
public class DonneesSimulation {

    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;

    /**
     * create a DonneesSimulation object receiving the map of the simulation
     *  as well as the fires and the robots
     * @param carte
     * @param incendies
     * @param robots
     */
    public DonneesSimulation(Carte carte, Incendie[] incendies, Robot[] robots){
        // REVIEW: have we to hard copy arrays???
        this.carte = carte;
        this.incendies = incendies;
        this.robots = robots;
    }

    
    /** 
     * get the map of the simulation
     * @return Carte
     */
    public Carte getCarte() {
        return carte;
    }
    
    /** 
     * get the fires of the simulation
     * @return Incendie[]
     */
    public Incendie[] getIncendies() {
        return incendies;
    }
    
    /** 
     * get the firefighter robots of the simulation
     * @return Robot[]
     */
    public Robot[] getRobots() {
        return robots;
    }

    
    /** 
     * describe the content of the DonneeSimulation
     * @return String
     */
    @Override
    public String toString() {
        String res = "Carte\n" + carte.toString() + "\n Incendies : \n";
        for (int i = 0; i < incendies.length; i++)
            res += incendies[i].toString();

        res += "\n";
        for (int i = 0; i < robots.length; i++)
            res += robots[i].toString();
        return res;
    }
}
