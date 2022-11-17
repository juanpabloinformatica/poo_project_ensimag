package classes;

import java.util.ArrayList;
import java.util.List;

import constants.Direction;

/**
 * The carte class represent a map 
 * who has nxm cases, in this one 
 * all the elements are going to be 
 * situed
 */
public class Carte {
    private int nbLignes;
    private int nbColonnes;
    private int tailleCases;
    private Case[][] cases;

    /**
     * create a map receiving number of lines, number of columns and the size 
     * of each case
     * 
     * @param nbLignes - number of rows of the map
     * @param nbColonnes - number of columns of the map
     * @param taillesCases - size of a single case
     */
    public Carte(int nbLignes, int nbColonnes, int taillesCases){
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.tailleCases = taillesCases;
        this.cases = new Case[nbLignes][nbColonnes];
    }
        
    
    /** 
     * get the number of rows
     * @return int
     */
    public int getNbLignes() {
        return nbLignes;
    }
    
    /**
     * get the number of columns 
     * @return int
     */
    public int getNbColonnes() {
        return nbColonnes;
    }
    
    /** 
     * get the size of the case
     * @return int
     */
    public int getTailleCases() {
        return tailleCases;
    }
    
    /** 
     * get an specific case of the map
     * @param ligne - row in which the case is situed in the map
     * @param colonne -column in which the case is situed in the map
     * @return Case
     */
    public Case getCase(int ligne,int colonne){
        return this.cases[ligne][colonne];
    }
    
    /** 
     * set an specific case of the map
     * @param i - select row of the case that will be set
     * @param j - select column of the case that will be set
     * @param pos - take a case an this will be replace for the set case
     */
    public void setCase(int i, int j, Case pos){
        this.cases[i][j] = pos;
    }

    
    /** 
     * describe the content of the map
     * @return String
     */
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


    
    
    /** 
     * return the direction that a robot will follow
     * @param prev - case where we are situed
     * @param next - case where we wanto to arrive
     * @return Direction
     */
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
