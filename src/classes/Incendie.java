package classes;

import events.Evenement;

/**
 * the class represent a fire in a map
 */
public class Incendie{
    
    private Case position;
    private int intensite;

    /**
     * create a fire receiving its position and its intensity
     * @param position - case of the map where the fire is situated
     * @param intensite - instensity of the fire
     */
    public Incendie(Case position, int intensite){
        this.position = position;
        this.intensite = intensite;
    }

    
    /** 
     * get the position of the fire
     * @return Case
     */
    public Case getPosition() {
        return position;
    }

    
    /** 
     * get the instensity of the fire
     * @return int
     */
    public int getIntensite() {
        return intensite;
    }
    
    
    /** 
     * @param intensite
     */
    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }

    
    /** 
     * describe the content of the fire
     * @return String
     */
    @Override
    public String toString() {
        return "Incendie a la " + position.toString() + "Intensite lvl = " +
            intensite + "\n";
    }
    
    
}
