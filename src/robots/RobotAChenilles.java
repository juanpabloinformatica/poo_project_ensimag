package robots;
import classes.Case;
import constants.*;

/**
 * the class RobotACHenilles represent the firefighter robot a chenilles 
 */
public class RobotAChenilles extends Robot {

    /**
     * create the robot a chenilles that receives an initial position and speed
     * @param pos - case
     * @param vitesse - speed
     */
    public RobotAChenilles(Case pos, int vitesse) {
        super(pos, 2000);
        if (vitesse == -1 || vitesse > 80) {
            this.setVitesse(60 * 1000 / 3600); // vitesse par défaut en m/s
        } else {
            this.setVitesse(vitesse * 1000 / 3600); // vitesse par lu en m/s
        }
        this.setTempsRemplissage(10*60); // 10 min * 60 sec
        this.setVidage(100, 8);
    }

    
    /** 
     * set position 
     * @param pos - case
     */
    @Override
    public void setPosition(Case pos) {
        NatureTerrain nT = pos.getNatureTerrain();
        if (nT != NatureTerrain.EAU && nT != NatureTerrain.ROCHE) {
            this.position = pos;
        } else {
            // REVIEW: throw error?
        }
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
        if (nT != NatureTerrain.EAU && nT != NatureTerrain.ROCHE)
            return true;
        return false;
    }

    
    /** 
     * get the speed of terrain of certain case
     * @param nT - nature of the terrain
     * @return double
     */
    // return la vitesse dans la natur nT
    @Override
    public double getVitesseNature(NatureTerrain nT) {
        if (nT == NatureTerrain.FORET)
            return  this.vitesse / 2;
        return this.vitesse;
    }

    
    /** 
     * get the speed of the robot
     * @return double
     */
    @Override
    public double getVitesse() {
        NatureTerrain nT = getPosition().getNatureTerrain();
        if (nT == NatureTerrain.FORET)
            return  this.vitesse / 2;
        return this.vitesse;
    }

    
    /** 
     * describe the robot a chenilles
     * @return String
     */
    @Override
    public String toString() {
        return "robot a chenilles";
    }

    
    /** 
     * get the type of the robot
     * @return TypeRobot
     */
    public TypeRobot getTypeRobot() {
        return TypeRobot.CHENILLES;
    }
    
    

}
