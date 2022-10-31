package robots;
import classes.Case;
import constants.*;

public class RobotARoues extends Robot {
    private double vitesse;

    public RobotARoues(Case pos, double vitesse) {
        super(pos);
        if (vitesse == -1) {
            this.vitesse = 80; // vitesse par défaut
        } else {
            this.vitesse = vitesse;
        }
        this.setReservoir(5000);
        this.setTempsRemplissage(10*60); // 30 min * 60 sec
        this.setVidage(100, 5); // 100L en 5 sec

    }

    @Override
    public void setPosition(Case pos) {
        NatureTerrain nT = pos.getNatureTerrain();
        if (nT == NatureTerrain.TERRAIN_LIBRE || nT == NatureTerrain.HABITAT)
            this.position = pos;
        // REVIEW: throw error ???
    }

    @Override
    public double getVitesse() {
        return this.vitesse;
    }

    @Override
    public String toString() {
        return "robot a roues";
    }
    public TypeRobot getTypeRobot() {
        return TypeRobot.ROUES;
    }
}
