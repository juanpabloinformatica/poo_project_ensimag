package classes;

import events.Evenement;

public class Incendie{
    
    private Case position;
    private int intensite;
    private int oldIntensite;

    public Incendie(Case position, int intensite){
        this.position = position;
        this.intensite = intensite;
        this.oldIntensite = intensite;
    }

    public Case getPosition() {
        return position;
    }

    public int getIntensite() {
        return intensite;
    }
    
    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }

    @Override
    public String toString() {
        return "Incendie a la " + position.toString() + "Intensite lvl = " +
            intensite + "\n";
    }
    public void restart() {
        intensite = oldIntensite;
    }
    
    
}
