package robots;
import classes.Case;
import constants.*;

public class RobotDrone extends Robot{
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
     * @param pos
     */
    @Override
    public void setPosition(Case pos) {
        this.position = pos; // pas des contraintes sur la natureTerrain
    }

    
    /** 
     * @param c
     * @return boolean
     */
    @Override
    public boolean canGo(Case c) {
        return true;
    }

    
    /** 
     * @param nT
     * @return double
     */
    @Override
    public double getVitesseNature(NatureTerrain nT) {
        return vitesse;
    }

    
    /** 
     * @return double
     */
    @Override
    public double getVitesse() {
        return vitesse;
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "robot drone";
    }
    
    /** 
     * @return TypeRobot
     */
    public TypeRobot getTypeRobot() {
        return TypeRobot.DRONE;
    }
    
}
