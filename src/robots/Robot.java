package robots;
import classes.Case;
import constants.NatureTerrain;
import constants.TypeRobot;

/**
 * the Robot class represent all the different firefighter robots 
 * that will extinguish the fire in a map
 */
public abstract class Robot {

    protected Case position;
    private final int RESERVOIR; // Capacité du réservoir (constante)
    private int currReservoir; // Volume d'eau se trouvant dans le currReservoir
    private int tempsRemplissage; // en seconds
    private int volVidage; // volume litres vidé lors d'une intervention unitaire
    private int tempsVidage; // en seconds lors d'une intervention unitaire
    protected double vitesse; // vitesse en m/s
    private Case oldPosition;

    public void restart() {
        position = oldPosition;
        currReservoir = RESERVOIR;
    }


    /** 
     * get the speed of certain terrain different in every robot
     * @param nT - speed
     * @return double
     */
    // return la vitesse dans une cas de la nature nT;
    public abstract double getVitesseNature(NatureTerrain nT);
    
    /** 
     * get the resultat of if a robot can go to certain case
     * @param c - case
     * @return boolean
     */
    // Return si le robot peut se rendre sur la case c
    public abstract boolean canGo(Case c);

    
    /** 
     * update the speed of the robot
     * @param vitesse - speed
     */
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    
    /** 
     * get the time to refill the tank of a robot
     * @return int
     */
    public int getTempsRemplissage() {
        return tempsRemplissage;
    }
    
    /** 
     * get the capacity of throw water of a robot
     * @return int
     */
    public int getVolVidage() {
        return volVidage;
    }
    
    /** 
     * set the capacity that a robot has to throw water
     * @param volVidage - throw water
     */
    public void setVolVidage(int volVidage) {
        this.volVidage = volVidage;
    }
    
    /** 
     * get the time spent of a robot throwing water to a fire 
     * @return int
     */
    public int getTempsVidage() {
        return tempsVidage;
    }
    
    /** 
     * 
     * @param tempsVidage - time spent emptying the tank
     */
    public void setTempsVidage(int tempsVidage) {
        this.tempsVidage = tempsVidage;
    }

    public abstract double getVitesse();
    public abstract void setPosition(Case pos);
    public abstract TypeRobot getTypeRobot();


    /**
     * create a robot that receives a case and a tank capacity
     * @param pos - case situed
     * @param RESERVOIR - tank capacity
     */
    public Robot(Case pos, int RESERVOIR) {
        position = pos;
        oldPosition = pos;
        this.RESERVOIR = RESERVOIR;
        this.currReservoir = RESERVOIR;
    }

    /** 
     * get the position
     * @return Case
     */
    public Case getPosition() {
        return position;
    }
    
    /** 
     * uodate the current cantity of water in the tank
     * @param vol - water used 
     */
    public void deverserEau(int vol) {
        if (this.currReservoir >= vol) {
            this.currReservoir -= vol;
        } else {
            this.currReservoir = 0;
        }
    }
    public void remplirReservoir() {
    }
    
    
    /** 
     * set the current water cantity in the tank
     * @param currReservoir - actual water cantity
     */
    public void setCurrReservoir(int currReservoir) {
        this.currReservoir = currReservoir;
    }
    
    /** 
     * get the current water cantity
     * @return int
     */
    public int getCurrReservoir() {
        return this.currReservoir;
    }
    
    /** 
     * get the total capacity that a robot has to stock water
     * @return int
     */
    public int getRESERVOIR() {
        return RESERVOIR;
    }

    
    /** 
     * set the time that a robot takes to spend the water in its tank
     * @param t - time
     */
    public void setTempsRemplissage(int t) {
        this.tempsRemplissage = t;
    }

    
    /** 
     * set the time to spend water
     * @param l - liters
     * @param temps - time
     */
    public void setVidage(int l, int temps) {
        this.volVidage = l;
        this.tempsVidage = temps;
    }
}
