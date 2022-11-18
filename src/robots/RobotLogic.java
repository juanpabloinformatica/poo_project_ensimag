package robots;

import java.util.ArrayList;
import java.util.List;

import classes.Carte;
import classes.Case;
import classes.ChefPompier;
import classes.Incendie;
import constants.Direction;
import constants.NatureTerrain;
import constants.TypeRobot;
import events.ArrivedEvenement;
import events.DeplacerEvenement;
import events.DisponibleEvenement;
import events.EteindreEvenement;
import events.OccupiedEvenement;
import events.RemplirEvenement;
import events.Simulateur;

/**
 * the RobotLogic class represent the logic that a robot has, its own intelligence
 */
public class RobotLogic {
    private static ChefPompier chefPompier;
    private static Simulateur simulateur;
    private static Carte carte;
    private static PathCalculator pathCalculator;

    private Robot robot;
    private boolean occupied;

    public void restart() {
        robot.restart();
        occupied = false;
    }
    /**
     * create the logic of the robot
     * @param robot - robot
     */
    public RobotLogic(Robot robot) {
        this.robot = robot;
    }
    

    /** 
     * inicialize all the variables to begin with the simulation
     * @param simulateur - simulator
     * @param carte - map
     * @param chefPompier - boss robot
     */
    public static void InitStaticVariables(Simulateur simulateur,
                                           Carte carte,
                                           ChefPompier chefPompier) {
        RobotLogic.simulateur = simulateur;
        RobotLogic.carte = carte;
        RobotLogic.pathCalculator = new DijkstraPathCalculator(carte);
        RobotLogic.chefPompier = chefPompier;
    }

    
    /** 
     * put out a fire 
     * @param date - the date when is being putting out
     * @param incendie - the fire to put out
     */
    public void eteindreIncendie(Integer date, Incendie incendie) {
        // incendie a ete eteint entre temps
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, robot, chefPompier));
            return;
        }

        incendie.setIntensite(incendie.getIntensite() - robot.getVolVidage());
        robot.setCurrReservoir(robot.getCurrReservoir() - robot.getVolVidage());
        if (incendie.getIntensite() > 0
            && robot.getCurrReservoir() >= robot.getVolVidage()) {
            simulateur.addEvenement(new EteindreEvenement(date + robot.getTempsVidage(),
                                                          robot, incendie));
            return;
        }

        if (robot.getCurrReservoir() < robot.getVolVidage()) {
            seRemplir();
            return;
        }

        // nous venons d'eteindre l'incendie et le reservoir n'est pas vide
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, robot,
                                                            chefPompier));
            return;
        }
    }

    
    /** 
     * get if a robot is busy putting out a fire or refilling
     * @return boolean
     */
    public boolean isOccupied() {
        return occupied;
    }

    
    /** 
     * set busy state to a robot
     * @param occupied - bussy
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    
    /** 
     * add the arriving of a robot to the events handler
     * @param date - date of the arrival
     * @param incendie - the fire
     */
    public void arrivedToIncendie(Integer date, Incendie incendie) {
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, robot,
                                                            chefPompier));
            return;
        }
        simulateur.addEvenement(new EteindreEvenement(date + robot.getTempsVidage(),
                                                      robot, incendie));
    }

    // return si le robot peut se remplir dans la case pos

    /**
     * indicate if a robot is availabe to refill its tank
     * @param pos - case where is the robot
     * @return
     */
    public boolean canSeRemplir(Case pos) {
        if (!robot.canGo(pos))
            return false;
        if (robot.getTypeRobot() == TypeRobot.DRONE)
            return pos.getNatureTerrain() == NatureTerrain.EAU;
        List<Case> neighbours = carte.getNeighbours(pos);
        for (Case neigh: neighbours) {
            if (neigh.getNatureTerrain() == NatureTerrain.EAU)
                return true;
        }
        return false;
    }

    /**
     * time to take a robot to execute certain action
     * @param i - incendie
     * @return
     */
    public double timeToGo(Incendie i) {
        Double time = pathCalculator.getTimeToCase(robot, i.getPosition());
        if (time == Double.POSITIVE_INFINITY) { // pas de chemin trouve pour aller a l'incendie
            System.out.println("Le robot " + robot + " n'a pas trouve un chemin pour se rendre a " + i);
        }
        return time;
    }

    /**
     * get if a robot is available
     * @return boolean
     */
    public boolean isAvailable() {
        if (occupied == true || robot.getCurrReservoir() <= 0)
            return false; // decliner la proposition
        return true;
    }
    public void affect(Incendie i) {
        occupied = false;
        if(robot.getPosition().equals(i.getPosition())) {
            simulateur.addEvenement(new ArrivedEvenement(simulateur.getDateSimulation(),
                                                         robot, i));
            return;
        }
        List<Case> pathCases= pathCalculator.computePath(robot, i.getPosition());
        Path path = convertInPath(pathCases);

        addPathEventsToSimulateur(i, path);
    }
    /**
     * refill the water tank of a robot
     */
    public void seRemplir() {
        System.out.println("SE REMPLIR :" + robot);
        if(canSeRemplir(robot.getPosition())) {
            int timeNeeded = robot.getTempsRemplissage();
            simulateur.addEvenement(new RemplirEvenement(simulateur.getDateSimulation()+timeNeeded, robot));
            simulateur.addEvenement(new DisponibleEvenement(simulateur.getDateSimulation()+timeNeeded, robot, chefPompier));
            return;
        }
        // chercher la case d'eau la plus pret;
        List<Case> pathCases = pathCalculator.computePathToWater(robot);
        if (pathCases == null) {
            System.out.println("[" + robot + "]" + "chemin vers l'eau non trouve");
            return;
        }
        addPathEventsToSimulateur(null, convertInPath(pathCases));
    }

    
    /** 
     * add events to the event handler
     * @param incendie - fire 
     * @param path - path
     */
    private void addPathEventsToSimulateur(Incendie incendie, Path path) {
        ArrayList<Integer> dates = path.getDates();
        ArrayList<Direction> directions = path.getNextMoves();
        System.out.println(robot);
        simulateur.addEvenement(new OccupiedEvenement(dates.get(0), robot));
        for(int i = 0; i < dates.size(); i++) {
            simulateur.addEvenement(new DeplacerEvenement(dates.get(i), robot, directions.get(i), carte));
        }
        // REVIEW : MONKEY PATCH
        if (incendie != null) {
            simulateur.addEvenement(new ArrivedEvenement(dates.get(dates.size()-1),
                                                         robot, incendie));
        } else {
            int timeNeeded = robot.getTempsRemplissage();
            simulateur.addEvenement(new RemplirEvenement(dates.get(dates.size()-1)+timeNeeded, robot));
            simulateur.addEvenement(new DisponibleEvenement(dates.get(dates.size()-1)+timeNeeded, robot, chefPompier));
        }
    }

    
    /** 
     * get the time to arrive to certain case
     * @param robot - robot
     * @param curr - actual case
     * @param date - date of the event
     * @param sizeCase - size of the case
     * @return Integer
     */
    private Integer calculateArrivalDate(Robot robot, Case curr, int date, int sizeCase) {
        if (robot.getPosition() == curr)
            return date;
        return date + (int)(sizeCase/robot.getVitesseNature(curr.getNatureTerrain()));
    }
    
    /** 
     * transform a list of cases in directions and time of arrive
     * @param pathCases - path case
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
            path.addNextMove(d, date);
            prevDate = date;
            prev = curr;
        }
        return path;
    }

}
