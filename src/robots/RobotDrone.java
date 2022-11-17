package robots;
import classes.Case;
import constants.*;

/**
 * the class RobotADrone represent the firefighter robot a Drone 
 */
public class RobotDrone extends Robot{

    /**
     * create the robot a drone that receives an initial position and speed
     * @param pos - case
     * @param vitesse - speed
     */
    public RobotDrone(Case pos, int vitesse) {
        super(pos, 10000);
        if (vitesse == -1 || vitesse > 150) {
            this.setVitesse(100*1000/3600); // vitesse par défaut
        } else {
            this.setVitesse(vitesse*1000/3600); // vitesse par lu en m/s
        }
        this.setTempsRemplissage(30*60); // 30 min * 60 sec
        this.setVidage(10000, 30);
    }
    

    
    /** 
     * set position of the robot a roues
     * @param pos - case
     */
    @Override
    public void setPosition(Case pos) {
        this.position = pos; // pas des contraintes sur la natureTerrain
    }

    
    /** 
     * get if a robot can go to certain case
     * 
     * @param c - case
     * @return boolean
     */
    @Override
    public boolean canGo(Case c) {
        return true;
    }

    
    /** 
     * get the speed of the robot a drone
     * @return double
     */
    @Override
    public double getVitesseNature(NatureTerrain nT) {
        return vitesse;
    }

    
    /** 
     * get the speed of the drone robot
     * @return double
     */
    @Override
    public double getVitesse() {
        return vitesse;
    }
    
    /** 
     * describe the drone robot
     * @return String
     */
    @Override
    public String toString() {
        return "robot drone";
    }
    
    /** 
     * get the robot type
     * @return TypeRobot
     */
    public TypeRobot getTypeRobot() {
        return TypeRobot.DRONE;
    }
    
}
