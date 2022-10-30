

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
    public void addCase(Case c){
        this.cases[c.getLigne()][c.getColonne()] = c;
    }
    // public boolean voisinExiste(Case src,Direction dir){
        
    //     for(ArrayList<Case> ligne:this.cases){
    //         for(Case colonne:ligne){
    //             if(colonne.get){

    //             }
    //         }
    //     }
    // }
    
    

   
    
    



}
