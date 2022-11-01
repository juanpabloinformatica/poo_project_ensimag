package robots;
import classes.Case;
import constants.*;

public class RobotDrone extends Robot{
    private double vitesse;
    // public RobotDrone(Case pos, int vitesse) {
    //     super(pos);
    //     if (vitesse == -1 || vitesse > 150) {
    //         this.vitesse = 100; // vitesse par défaut
    //     } else {
    //         this.vitesse = vitesse;
    //     }
    //     this.setReservoir(10000);
    //     this.setTempsRemplissage(30*60); // 30 min * 60 sec
    //     this.setVidage(10000, 30);
    // }
    public RobotDrone(Case pos, int vitesse,Integer date) {
        super(pos,date);
        if (vitesse == -1 || vitesse > 150) {
            this.vitesse = 100; // vitesse par défaut
        } else {
            this.vitesse = vitesse;
        }
        this.setReservoir(10000);
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
     /*
     * /*
     * implement what happens if a robot move,
     * if it spend water
     * intervenir in the cell he is
     * remplir
     * and possible events that can happen!
     */
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }
}
