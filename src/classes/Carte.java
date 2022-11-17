package classes;

import java.util.ArrayList;
import java.util.List;

import constants.Direction;

public class Carte {
    /*we can create a variable static
    instead
    public static int matriceCarte[x][y]; 
    */
    // int mapMatrix[][];

    // public Carte(int n, int m) {
    //     this.mapMatrix = new int[n][m];
    // }
    private int nbLignes;
    private int nbColonnes;
    private int tailleCases;
    private Case[][] cases;

    public Carte(int nbLignes, int nbColonnes, int taillesCases){
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.tailleCases = taillesCases;
        this.cases = new Case[nbLignes][nbColonnes];

        /*this method need to be modified
            just for testing
        */
        // this.fillingCases();
    }
    
    // private void fillingCases(){
    //     for(int i = 0; i < this.nbLignes;i++){
    //         ArrayList<Case> temp = new ArrayList<>();
    //         for(int j = 0 ; j < this.nbColonnes; j++){
    //             temp.add(new Case(i,j,NatureTerrain.EAU));
    //         }
    //         this.cases.add(temp);
    //     }
    // }
    
    public int getNbLignes() {
        return nbLignes;
    }
    public int getNbColonnes() {
        return nbColonnes;
    }
    public int getTailleCases() {
        return tailleCases;
    }
    public Case getCase(int ligne,int colonne){
        return this.cases[ligne][colonne];
    }
    public void setCase(int i, int j, Case pos){
        this.cases[i][j] = pos;
    }
    // public boolean voisinExiste(Case src,Direction dir){
        
    //     for(ArrayList<Case> ligne:this.cases){
    //         for(Case colonne:ligne){
    //             if(colonne.get){

    //             }
    //         }
    //     }
    // }
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                res += this.cases[i][j].toString();
                res += "\n";
            }
        }
        return res;
    }


    
    public Direction calculateDirection(Case prev, Case next) {
        try {
            int diff_i = prev.getLigne() - next.getLigne();
            int diff_j = prev.getColonne() - next.getColonne();
            if (diff_i == 0) {
                if (diff_j == -1)
                    return Direction.EST;
                else if (diff_j == 1)
                    return Direction.OUEST;
                else
                    throw new Exception("CalculateDirection: Cases non contigues");
            } else if (diff_j == 0) {
                if (diff_i == -1)
                    return Direction.SUD;
                else if (diff_i == 1)
                    return Direction.NORD;
                else
                    throw new Exception("CalculateDirection: Cases non contigues");
            } else {
                    throw new Exception("CalculateDirection: Cases non contigues");
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    public List<Case> getNeighbours(Case pos) {
        List<Case> neighbours = new ArrayList<Case>();
        int i_moves[] = {-1, 0, 0, 1};
        int j_moves[] = {0, -1, 1, 0};
        for (int i = 0; i < 4; i++) {
            int neigh_i = pos.getLigne() + i_moves[i];
            int neigh_j = pos.getColonne() + j_moves[i];
            if (neigh_i >= 0 && neigh_i < nbLignes
                && neigh_j >= 0 && neigh_j < nbColonnes)
                neighbours.add(cases[neigh_i][neigh_j]);
        }
        return neighbours;
    }
   
    
    



}
