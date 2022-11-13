package robots;
import classes.Case;
import classes.Incendie;
import constants.NatureTerrain;
import constants.TypeRobot;

public abstract class Robot {
    protected Case position;
    private final int RESERVOIR; // Capacité du réservoir (constante)
    private int currReservoir; // Volume d'eau se trouvant dans le currReservoir
    private int tempsRemplissage; // en seconds
    private int volVidage; // volume litres vidé lors d'une intervention unitaire
    private int tempsVidage; // en seconds lors d'une intervention unitaire
    protected double vitesse; // vitesse en m/s
    private boolean occupied;
    private PathCalculator pathCalculator;

    public void setPathCalculator(PathCalculator pathC) {
        this.pathCalculator = pathC;
    }

    // return la vitesse dans une cas de la nature nT;
    public abstract double getVitesseNature(NatureTerrain nT);
    public boolean propose(Incendie i) {
        if (occupied == true || currReservoir <= 0) {
            return false; // decliner la proposition
        }
        // TODO: calculer le sp pour aller a l'incendie i

        Path path = pathCalculator.computePath(this, i);
        // ajouter dans le simulateur les evenementsdeplacer
        pathCalculator.addPathEventsToSimulateur(this, path);
        // + occupe + dispobible + eteindre
        //
        return true;
    }

    // Return si le robot peut se rendre sur la case c
    public abstract boolean canGo(Case c);
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }
    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    public int getTempsRemplissage() {
        return tempsRemplissage;
    }
    public int getVolVidage() {
        return volVidage;
    }
    public void setVolVidage(int volVidage) {
        this.volVidage = volVidage;
    }
    public int getTempsVidage() {
        return tempsVidage;
    }
    public void setTempsVidage(int tempsVidage) {
        this.tempsVidage = tempsVidage;
    }

    public abstract double getVitesse();
    public abstract void setPosition(Case pos);
    public abstract TypeRobot getTypeRobot();

    public Robot(Case pos, int RESERVOIR) {
        position = pos;
        this.RESERVOIR = RESERVOIR;
        this.currReservoir = RESERVOIR;
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
