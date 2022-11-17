package classes;

import events.Evenement;

public class Incendie{
    
    private Case position;
    private int intensite;

    public Incendie(Case position, int intensite){
        this.position = position;
        this.intensite = intensite;
    }

    
    /** 
     * @return Case
     */
    public Case getPosition() {
        return position;
    }

    
    /** 
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
     * @return String
     */
    @Override
    public String toString() {
        return "Incendie a la " + position.toString() + "Intensite lvl = " +
            intensite + "\n";
    }
    
    
}
