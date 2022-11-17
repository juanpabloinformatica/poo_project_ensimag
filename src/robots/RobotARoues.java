package robots;
import classes.Case;
import constants.*;

/**
 * the class RobotAPoues represent the firefighter robot a roues 
 */
public class RobotARoues extends Robot {

    /**
     * create the robot a roues that receives an initial position and speed
     * @param pos - case
     * @param vitesse - speed
     */
    public RobotARoues(Case pos, double vitesse) {
        super(pos, 5000);
        if (vitesse == -1) {
            this.setVitesse(80*1000/3600); // vitesse par défaut m/s
        } else {
            this.setVitesse(vitesse*1000/3600); // vitesse par lu en m/s
        }
        this.setTempsRemplissage(10*60); // 30 min * 60 sec
        this.setVidage(100, 5); // 100L en 5 sec

    }
    

    
    /** 
     * set position of the robot a roues
     * @param pos - case
     */
    @Override
    public void setPosition(Case pos) {
        NatureTerrain nT = pos.getNatureTerrain();
        if (nT == NatureTerrain.TERRAIN_LIBRE || nT == NatureTerrain.HABITAT)
            this.position = pos;
        // REVIEW: throw error ???
    }

    
    /** 
     * get if a robot can go to certain case
     * 
     * @param c - case
     * @return boolean
     */
    @Override
    public boolean canGo(Case c) {
        NatureTerrain nT = c.getNatureTerrain();
        if (nT == NatureTerrain.TERRAIN_LIBRE || nT == NatureTerrain.HABITAT)
            return true;
        return false;
    }

    
    /** 
     * get the speed of the robot a roues
     * @return double
     */
    @Override
    public double getVitesse() {
        return this.vitesse;
    }

    
    /** 
     * get the speed of certain terrain
     * @param nT
     * @return double
     */
    @Override
    public double getVitesseNature(NatureTerrain nT) {
        return vitesse;
    }

    
    /** 
     * describe the robot a roues
     * @return String
     */
    @Override
    public String toString() {
        return "robot a roues";
    }
    
    /** 
     * get the type of the robot
     * @return TypeRobot
     */
    public TypeRobot getTypeRobot() {
        return TypeRobot.ROUES;
    }
    
}
