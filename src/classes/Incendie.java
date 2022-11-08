package classes;

import events.Evenement;

public class Incendie{
    private Case pos;
    private int intensite;

    public Incendie(Case pos, int intensite){
        this.pos = pos;
        this.intensite = intensite;
    }

    public Case getPos() {
        return pos;
    }

    public int getIntensite() {
        return intensite;
    }

    
    @Override
    public String toString() {
        return "Incendie a la " + pos.toString() + "Intensite lvl = " +
            intensite + "\n";
    }
    
    
}
