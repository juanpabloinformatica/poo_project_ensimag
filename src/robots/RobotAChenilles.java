package robots;
import classes.Case;
import constants.*;

public class RobotAChenilles extends Robot {
    private double vitesse;
    public RobotAChenilles(Case pos, int vitesse) {
        super(pos);
        if (vitesse == -1 || vitesse > 80) {
            this.vitesse = 60; // vitesse par défaut
        } else {
            this.vitesse = vitesse;
        }
        this.setReservoir(2000);
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
