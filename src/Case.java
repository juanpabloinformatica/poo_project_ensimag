import constants.NatureTerrain;

public class Case {
    private int ligne;
    private int colonne;
    private NatureTerrain  natureTerrain;
    
    public Case(int ligne, int colonne, String natureTerrain) {
        this.ligne = ligne;
        this.colonne = colonne;
        setNatureTerrain(natureTerrain);
    }

    // EAU,
    // FORET,
    // ROCHE,
    // TERRAIN_LIBRE,
    // HABITAT

    private void setNatureTerrain(String natureTerrain){
        switch(natureTerrain){
            case "EAU":
            this.natureTerrain = NatureTerrain.EAU;
            break;
            case "FORET":
            this.natureTerrain = NatureTerrain.FORET;
            break;
            case "ROCHE":
            this.natureTerrain = NatureTerrain.ROCHE;
            break;
            case "TERRAIN_LIBRE":
            this.natureTerrain = NatureTerrain.TERRAIN_LIBRE;
            break;
            case "HABITAT":
            this.natureTerrain = NatureTerrain.HABITAT;
        }
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
    

    

}
