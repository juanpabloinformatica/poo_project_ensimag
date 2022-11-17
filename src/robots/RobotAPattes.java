package robots;
import classes.Case;
import constants.*;

/**
 * the class RobotAPattes represent the firefighter robot a pattes 
 */
public class RobotAPattes extends Robot {

    /**
     * create the robot a pattes that receives an initial position and speed
     * @param pos - case
     */
    public RobotAPattes(Case pos) {
        super(pos, Integer.MAX_VALUE);
        this.setVitesse(30*1000/3600);
        this.setTempsRemplissage(Integer.MAX_VALUE); // 30 min * 60 sec
        this.setVidage(10, 1);
    }
   
    
    /** 
     * set position of the robot 
     * @param pos - case
     */
    @Override
    public void setPosition(Case pos) {
        if (pos.getNatureTerrain() != NatureTerrain.EAU)
            this.position = pos;
        // REVIEW: throw error?
    }
    
    /** 
     * get the speed of the robot a pattes
     * @return double
     */
    public double getVitesse() {
        NatureTerrain natureTerrain = this.getPosition().getNatureTerrain();
        if (natureTerrain == NatureTerrain.ROCHE)
            return 10;
        return this.vitesse;
    }

    
    /** 
     * get the speed of the case
     * @param nT - natureTerrain
     * @return double
     */
    @Override
    public double getVitesseNature(NatureTerrain nT) {
        if (nT == NatureTerrain.ROCHE)
            return 10;
        return vitesse;
    }
    
    /** 
     * describe the robot a pattes
     * @return String
     */
    @Override
    public String toString() {
        return "robot a pattes";
    }
    
    /** 
     * get the type of the robot
     * @return TypeRobot
     */
    public TypeRobot getTypeRobot() {
        return TypeRobot.PATTES;
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
        if (nT == NatureTerrain.EAU)
            return false;
        return true;
    }

    
}
