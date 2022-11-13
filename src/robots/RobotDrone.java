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
    

    @Override
    public void setPosition(Case pos) {
        this.position = pos; // pas des contraintes sur la natureTerrain
    }

    @Override
    public boolean canGo(Case c) {
        return true;
    }

    @Override
    public double getVitesseNature(NatureTerrain nT) {
        return vitesse;
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
