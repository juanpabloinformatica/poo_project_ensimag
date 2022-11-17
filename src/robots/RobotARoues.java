package robots;
import classes.Case;
import constants.*;

public class RobotARoues extends Robot {
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
     * @param pos
     */
    @Override
    public void setPosition(Case pos) {
        NatureTerrain nT = pos.getNatureTerrain();
        if (nT == NatureTerrain.TERRAIN_LIBRE || nT == NatureTerrain.HABITAT)
            this.position = pos;
        // REVIEW: throw error ???
    }

    
    /** 
     * @param c
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
     * @return double
     */
    @Override
    public double getVitesse() {
        return this.vitesse;
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
     * @return String
     */
    @Override
    public String toString() {
        return "robot a roues";
    }
    
    /** 
     * @return TypeRobot
     */
    public TypeRobot getTypeRobot() {
        return TypeRobot.ROUES;
    }
    /*
     * /*
     * implement what happens if a robot move,
     * if it spend water
     * intervenir in the cell he is
     * remplir
     * and possible events that can happen!
     */
    
}
