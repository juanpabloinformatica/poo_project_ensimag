package robots;
import classes.*;
import constants.TypeRobot;

public abstract class Robot {
    protected Case position;
    private int reservoir;
    private int tempsRemplissage; // en seconds
    private int volVidage; // volume litres vidé lors d'une intervention unitaire
    private int tempsVidage; // en seconds lors d'une intervention unitaire

    public abstract double getVitesse();
    public abstract void setPosition(Case pos);
    public abstract TypeRobot getTypeRobot();
    public Robot(Case pos) {
        position = pos;
    }
    public Case getPosition() {
        return position;
    }
    public void deverserEau(int vol) {

    }
    public void remplirReservoir() {
    }

    public void setReservoir(int res) {
        this.reservoir = res;
    }
    public void setTempsRemplissage(int t) {
        this.tempsRemplissage = t;
    }
    public void setVidage(int l, int temps) {
        this.volVidage = l;
        this.tempsVidage = temps;
    }
}
