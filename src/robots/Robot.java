package robots;
import classes.*;
import constants.TypeRobot;
import events.Evenement;

public abstract class Robot {
    protected Case position;
    private final int RESERVOIR; // Capacité du réservoir (constante)
    private int currReservoir; // Volume d'eau se trouvant dans le currReservoir
    private int tempsRemplissage; // en seconds
    private int volVidage; // volume litres vidé lors d'une intervention unitaire
    private int tempsVidage; // en seconds lors d'une intervention unitaire

    public abstract double getVitesse();
    public abstract void setPosition(Case pos);
    public abstract TypeRobot getTypeRobot();
    
    public Robot(Case pos, int RESERVOIR) {
        position = pos;
        this.RESERVOIR = RESERVOIR;
    }
    public Case getPosition() {
        return position;
    }
    public void deverserEau(int vol) {
        if (this.currReservoir >= vol) {
            this.currReservoir -= vol;
        } else {
            this.currReservoir = 0;
        }
    }
    public void remplirReservoir() {
    }
    
    public void setCurrReservoir(int currReservoir) {
        this.currReservoir = currReservoir;
    }
    public int getCurrReservoir() {
        return this.currReservoir;
    }
    public int getRESERVOIR() {
        return RESERVOIR;
    }

    public void setTempsRemplissage(int t) {
        this.tempsRemplissage = t;
    }

    public void setVidage(int l, int temps) {
        this.volVidage = l;
        this.tempsVidage = temps;
    }
}
