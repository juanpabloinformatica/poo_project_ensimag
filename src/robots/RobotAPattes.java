package robots;
import classes.Case;
import constants.*;

public class RobotAPattes extends Robot {
    public RobotAPattes(Case pos) {
        super(pos, Integer.MAX_VALUE);
        this.setVitesse(30*1000/3600);
        this.setTempsRemplissage(Integer.MAX_VALUE); // 30 min * 60 sec
        this.setVidage(10, 1);
    }
   
    
    /** 
     * @param pos
     */
    @Override
    public void setPosition(Case pos) {
        if (pos.getNatureTerrain() != NatureTerrain.EAU)
            this.position = pos;
        // REVIEW: throw error?
    }
    
    /** 
     * @return double
     */
    public double getVitesse() {
        NatureTerrain natureTerrain = this.getPosition().getNatureTerrain();
        if (natureTerrain == NatureTerrain.ROCHE)
            return 10;
        return this.vitesse;
    }

    
    /** 
     * @param nT
     * @return double
     */
    @Override
    public double getVitesseNature(NatureTerrain nT) {
        if (nT == NatureTerrain.ROCHE)
            return 10;
        return vitesse;
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "robot a pattes";
    }
    
    /** 
     * @return TypeRobot
     */
    public TypeRobot getTypeRobot() {
        return TypeRobot.PATTES;
    }

    
    /** 
     * @param c
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
