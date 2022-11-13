package robots;
import classes.Case;
import constants.*;

public class RobotAChenilles extends Robot {
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

    @Override
    public void setPosition(Case pos) {
        NatureTerrain nT = pos.getNatureTerrain();
        if (nT != NatureTerrain.EAU && nT != NatureTerrain.ROCHE) {
            this.position = pos;
        } else {
            // REVIEW: throw error?
        }
    }

    @Override
    public boolean canGo(Case c) {
        NatureTerrain nT = c.getNatureTerrain();
        if (nT != NatureTerrain.EAU && nT != NatureTerrain.ROCHE)
            return true;
        return false;
    }

    // return la vitesse dans la natur nT
    @Override
    public double getVitesseNature(NatureTerrain nT) {
        if (nT == NatureTerrain.FORET)
            return  this.vitesse / 2;
        return this.vitesse;
    }

    @Override
    public double getVitesse() {
        NatureTerrain nT = getPosition().getNatureTerrain();
        if (nT == NatureTerrain.FORET)
            return  this.vitesse / 2;
        return this.vitesse;
    }

    @Override
    public String toString() {
        return "robot a chenilles";
    }

    public TypeRobot getTypeRobot() {
        return TypeRobot.CHENILLES;
    }
    
    

}
