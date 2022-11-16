package graph;

import java.util.ArrayList;

import classes.Carte;
import classes.Case;
import robots.Graph;
import robots.Robot;

public class GraphParser{
    
    public static Graph parserDrone(Carte carte,Robot robot, Case dest  ){
        //get nodes
        ArrayList <Case> listOfNodes;
        listOfNodes = gettingNodes(carte);
        //get adjacency list
        ArrayList <ArrayList<Case>> adjListDrone;
        adjListDrone = gettingAdjListDrone(carte);
        int i = 0;
        int j = 0 ;
        for(ArrayList<Case> l : adjListDrone){
            System.out.print("["+i+","+j+"]"+"->");
            for(Case c : l){
                System.out.print("->"+"["+c.getLigne()+","+c.getColonne()+"]");
            }
            System.out.println("");
        }



        return null;
    } 
    public static Graph parserRoues(Carte carte,Robot robot, Case dest  ){
        return null;
    } 
    public static Graph parserChenilles(Carte carte,Robot robot, Case dest  ){
        return null;
    } 
    public static Graph parserPattes(Carte carte,Robot robot, Case dest  ){
        return null;
    }

    private static ArrayList<Case> gettingNodes(Carte carte){
        ArrayList<Case> listOfNodes = new ArrayList<>();
        
        for(int i =0 ; i< carte.getNbLignes();i++){
            for(int j =0 ; j < carte.getNbColonnes();j++){
                listOfNodes.add(carte.getCase(i,j));
                
            }
        }
		return listOfNodes;
    }
    
    private static ArrayList<ArrayList<Case>> gettingAdjListDrone(Carte carte){
        ArrayList <ArrayList<Case>> adjListDrone = new ArrayList<>();
        Case actualCase;
        ArrayList<Case> nextCases;
        int iBounds = carte.getNbLignes();
        int jBounds = carte.getNbColonnes();
        for(int i = 0 ; i < carte.getNbLignes(); i++ ){
            nextCases = new ArrayList<>();
            for(int j = 0 ; j < carte.getNbColonnes();j++){  
                actualCase = carte.getCase(i,j);
                nextCases = gettingNextCases(carte,actualCase,iBounds,jBounds,"DRONE");
            }
            adjListDrone.add(nextCases);
        }
		return null;

    }
    private static ArrayList<Case> gettingNextCases(Carte carte, Case actualCase,int iBounds, int jBounds,String type){
        int iPossibleValues [] = {-1,1};
        int jPossibleValues [] = {-1,1};
        int actualCaseI = actualCase.getLigne();
        int actualCaseJ = actualCase.getColonne();

        ArrayList <Case> nextCases = new ArrayList<>();

        if(type.equals("DRONE")){
             if(!(actualCaseI-1 < 0))
            nextCases.add(carte.getCase(actualCaseI-1,actualCaseJ));
            if(!(actualCaseI+1 > iBounds))
             nextCases.add(carte.getCase(actualCaseI+1,actualCaseJ));
            if(!(actualCaseJ-1 < 0))
            nextCases.add(carte.getCase(actualCaseI, actualCaseJ-1));
            if(!(actualCaseJ+1> jBounds))
            nextCases.add(carte.getCase(actualCaseI, actualCaseJ+1));
        }else if(type.equals("RUE")){

        }else if(type.equals("CHENILLES")){

        }else{

        }
        // int number = 300;
       
        return nextCases;

    }
     
}

