package robots;

import java.util.ArrayList;
import java.util.List;

import classes.Carte;
import classes.Case;
import classes.Incendie;
import constants.Direction;
import events.ArrivedEvenement;
import events.DeplacerEvenement;
import events.DisponibleEvenement;
import events.EteindreEvenement;
import events.OccupiedEvenement;
import events.RemplirEvenement;
import events.Simulateur;

public class RobotLogic {
    private Robot robot;
    private static Simulateur simulateur;
    private static Carte carte;
    private static PathCalculator pathCalculator;
    private boolean occupied;

    public RobotLogic(Robot robot) {
        this.robot = robot;
    }
    
    /** 
     * @return Robot
     */
    public Robot getRobot() {
        return robot;
    }
    
    /** 
     * @param simulateur
     * @param carte
     * @param pathCalculator
     */
    public static void InitialiateStaticVariables(Simulateur simulateur,
                                                  Carte carte,
                                                  PathCalculator pathCalculator) {
        RobotLogic.simulateur = simulateur;
        RobotLogic.carte = carte;
        RobotLogic.pathCalculator= pathCalculator;
    }

    
    /** 
     * @param date
     * @param incendie
     */
    public void eteindreIncendie(Integer date, Incendie incendie) {
        // incendie a ete eteint entre temps
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, this));
            return;
        }

        incendie.setIntensite(incendie.getIntensite() - robot.getVolVidage());
        robot.setCurrReservoir(robot.getCurrReservoir() - robot.getVolVidage());
        if (incendie.getIntensite() > 0
            && robot.getCurrReservoir() >= robot.getVolVidage()) {
            simulateur.addEvenement(new EteindreEvenement(date + robot.getTempsVidage(),
                                                          this, incendie));
            return;
        }

        if (robot.getCurrReservoir() < robot.getVolVidage()) {
            seRemplir();
            return;
        }

        // nous venons d'eteindre l'incendie et le reservoir n'est pas vide
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, this));
            return;
        }
    }

    
    /** 
     * @return boolean
     */
    public boolean isOccupied() {
        return occupied;
    }

    
    /** 
     * @param occupied
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    
    /** 
     * @param date
     * @param incendie
     */
    public void arrivedToIncendie(Integer date, Incendie incendie) {
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, this));
            return;
        }
        simulateur.addEvenement(new EteindreEvenement(date + robot.getTempsVidage(),
                                                      this, incendie));
    }

    
    /** 
     * @param i
     * @return boolean
     */
    public boolean propose(Incendie i) {
        if (occupied == true || robot.getCurrReservoir() <= 0) {
            return false; // decliner la proposition
        }
        List<Case> pathCases= pathCalculator.computePath(robot, i.getPosition());
        if (pathCases == null) {
            System.out.println("Le robot " + this + " n'a pas trouve un chemin pour se rendre a " + i);
            return false; // pas de chemin trouve pour aller a l'incendie on
                          // decline la proposition
        }
        Path path = convertInPath(pathCases);
        addPathEventsToSimulateur(i, path);
        return true;
    }

    public void seRemplir() {
        System.out.println("SE REMPLIR :" + robot);
        // chercher la case d'eau la plus pret;
        // Path path = convertInPath(pathCalculator.computePathToWater(robot));
        // if (path != null)
        //     addPathEventsToSimulateur(null, path);
    }

    
    /** 
     * @param incendie
     * @param path
     */
    private void addPathEventsToSimulateur(Incendie incendie, Path path) {
        ArrayList<Integer> dates = path.getDates();
        ArrayList<Direction> directions = path.getNextMoves();
        System.out.println(robot);
        simulateur.addEvenement(new OccupiedEvenement(dates.get(0), this));
        for(int i = 0; i < dates.size(); i++) {
            simulateur.addEvenement(new DeplacerEvenement(dates.get(i), this, directions.get(i), carte));
        }
        // REVIEW : MONKEY PATCH
        if (incendie != null) {
            simulateur.addEvenement(new ArrivedEvenement(dates.get(dates.size()-1),
                                                         this, incendie));
        } else {
            int timeNeeded = robot.getTempsRemplissage();
            simulateur.addEvenement(new RemplirEvenement(dates.get(dates.size()-1)+timeNeeded, this));
            simulateur.addEvenement(new DisponibleEvenement(dates.get(dates.size()-1)+timeNeeded, this));
        }
    }

    
    /** 
     * @param robot
     * @param curr
     * @param date
     * @param sizeCase
     * @return Integer
     */
    private Integer calculateArrivalDate(Robot robot, Case curr, int date, int sizeCase) {
        return date + (int)(sizeCase/robot.getVitesseNature(curr.getNatureTerrain()));
    }
    
    /** 
     * @param pathCases
     * @return Path
     */
    // Convertit le tableau des Cases en Path (directions et date d'arrivee)
    private Path convertInPath(List<Case> pathCases) {
        Path path = new Path();
        Case prev = pathCases.get(0);
        int prevDate = simulateur.getDateSimulation();
        for (int i = 1; i < pathCases.size(); i++) {
            Case curr = pathCases.get(i);
            Direction d = carte.calculateDirection(prev, curr);
            Integer date = calculateArrivalDate(robot, curr, prevDate, carte.getTailleCases());
            prevDate = date;
            path.addNextMove(d, date);
            prev = curr;
        }
        return path;
    }

}
