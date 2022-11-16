package classes;
import javax.print.attribute.standard.MediaSize.Other;

import constants.NatureTerrain;

public class Case {
    
    private int ligne;
    private int colonne;
    private NatureTerrain  natureTerrain;
    
    public Case(int ligne, int colonne, NatureTerrain natureTerrain) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.natureTerrain = natureTerrain;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public NatureTerrain getNatureTerrain() {
        return natureTerrain;
    }

    @Override
    public String toString() {
        return String.format("Case(%d, %d) = %s", ligne, colonne,
                             natureTerrain.name());
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Case) {
            Case compared = (Case) other;
            return (this.ligne == compared.getLigne() && this.colonne == compared.getColonne());
        }
        return false;
    }
    

}
