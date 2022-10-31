package robots;
import classes.Case;
import constants.*;

public class RobotAPattes extends Robot {
    private double vitesse;
    public RobotAPattes(Case pos) {
        super(pos);
        this.vitesse = 30;
        this.setReservoir(Integer.MAX_VALUE);
        this.setTempsRemplissage(Integer.MAX_VALUE); // 30 min * 60 sec
        this.setVidage(10, 1);
    }

    @Override
    public void setPosition(Case pos) {
        if (pos.getNatureTerrain() != NatureTerrain.EAU)
            this.position = pos;
        // REVIEW: throw error?
    }
    public double getVitesse() {
        NatureTerrain natureTerrain = this.getPosition().getNatureTerrain();
        if (natureTerrain == NatureTerrain.ROCHE)
            return 10;
        return this.vitesse;
    }
    @Override
    public String toString() {
        return "robot a pattes";
    }
    public TypeRobot getTypeRobot() {
        return TypeRobot.PATTES;
    }
}
