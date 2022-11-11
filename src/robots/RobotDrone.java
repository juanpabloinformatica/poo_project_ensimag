package robots;
import classes.Case;
import constants.*;

public class RobotDrone extends Robot{
    private double vitesse;
    public RobotDrone(Case pos, int vitesse) {
        super(pos, 10000);
        if (vitesse == -1 || vitesse > 150) {
            this.vitesse = 100; // vitesse par défaut
        } else {
            this.vitesse = vitesse;
        }
        this.setTempsRemplissage(30*60); // 30 min * 60 sec
        this.setVidage(10000, 30);
    }
    

    @Override
    public void setPosition(Case pos) {
        this.position = pos; // pas des contraintes sur la natureTerrain
    }

    @Override
    public double getVitesse() {
        return vitesse;
    }
    @Override
    public String toString() {
        return "robot drone";
    }
    public TypeRobot getTypeRobot() {
        return TypeRobot.DRONE;
    }
    
}
