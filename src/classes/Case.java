package classes;
import javax.print.attribute.standard.MediaSize.Other;

import constants.NatureTerrain;

/**
 *  the Case class represent a cell of a map
 *  this are used as a position of the different 
 *  objects such as robots and incendies, besides
 *  it has a a cordinate and a type of terrain 
 */
public class Case {
    
    private int ligne;
    private int colonne;
    private NatureTerrain  natureTerrain;
    
    /**
     * create a case reciving the cordinate in x-axis,
     * y-axis and its type of terrain
     * @param ligne - position in the x-axis
     * @param colonne - position in thr y-axis
     * @param natureTerrain - type of terrain
     */
    public Case(int ligne, int colonne, NatureTerrain natureTerrain) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.natureTerrain = natureTerrain;
    }

    
    /** 
     * get x-axis
     * @return int
     */
    public int getLigne() {
        return ligne;
    }

    
    /** 
     * get y-axis
     * @return int
     */
    public int getColonne() {
        return colonne;
    }

    
    /** 
     * get type of the terrain
     * @return NatureTerrain
     */
    public NatureTerrain getNatureTerrain() {
        return natureTerrain;
    }

    
    /** 
     * describe the content of the case
     * @return String
     */
    @Override
    public String toString() {
        return String.format("Case(%d, %d) = %s", ligne, colonne,
                             natureTerrain.name());
    }
    
    
    /** 
     * compare to objects of type case having in count their attributes instead of
     * their memory addresses
     * @param other - object of class Object
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Case) {
            Case compared = (Case) other;
            return (this.ligne == compared.getLigne() && this.colonne == compared.getColonne());
        }
        return false;
    }
    

}
