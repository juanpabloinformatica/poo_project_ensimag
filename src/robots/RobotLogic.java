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

public class RobotLogic {
    private Robot robot;
    private static ChefPompier chefPompier;
    private static Simulateur simulateur;
    private static Carte carte;
    private static PathCalculator pathCalculator;
    private boolean occupied;

    public RobotLogic(Robot robot) {
        this.robot = robot;
    }
    public Robot getRobot() {
        return robot;
    }
    public static void InitialiateStaticVariables(Simulateur simulateur,
                                                  Carte carte,
                                                  PathCalculator pathCalculator,
                                                  ChefPompier chefPompier) {
        RobotLogic.simulateur = simulateur;
        RobotLogic.carte = carte;
        RobotLogic.pathCalculator= pathCalculator;
        RobotLogic.chefPompier = chefPompier;
    }

    public void eteindreIncendie(Integer date, Incendie incendie) {
        // incendie a ete eteint entre temps
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, this, chefPompier));
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
            simulateur.addEvenement(new DisponibleEvenement(date, this,
                                                            chefPompier));
            return;
        }
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void arrivedToIncendie(Integer date, Incendie incendie) {
        if (incendie.getIntensite() <= 0) {
            simulateur.addEvenement(new DisponibleEvenement(date, this,
                                                            chefPompier));
            return;
        }
        simulateur.addEvenement(new EteindreEvenement(date + robot.getTempsVidage(),
                                                      this, incendie));
    }

    // return si le robot peut se remplir dans la case pos
    public boolean canSeRemplir(Case pos) {
        if (!robot.canGo(pos))
            return false;
        if (robot.getTypeRobot() == TypeRobot.DRONE
            && pos.getNatureTerrain() == NatureTerrain.EAU)
            return true;
        List<Case> neighbours = carte.getNeighbours(pos);
        for (Case neigh: neighbours) {
            if (neigh.getNatureTerrain() == NatureTerrain.EAU)
                return true;
        }
        return false;
    }
    public double timeToGo(Incendie i) {
        return pathCalculator.getTimeToCase(robot, i.getPosition());
    }

    public boolean isAvailable() {
        if (occupied == true || robot.getCurrReservoir() <= 0)
            return false; // decliner la proposition
        return true;
    }
    public void affect(Incendie i) {
        occupied = false;
        if(robot.getPosition().equals(i.getPosition())) {
            simulateur.addEvenement(new ArrivedEvenement(simulateur.getDateSimulation(),
                                                         this, i));
            return;
        }
        List<Case> pathCases= pathCalculator.computePath(robot, i.getPosition());
        if (pathCases == null) {
            System.out.println("Le robot " + this.getRobot() + " n'a pas trouve un chemin pour se rendre a " + i);
            // pas de chemin trouve pour aller a l'incendie on
                          // decline la proposition
        }
        // if (pathCases.size() == 0) {
        //     System.out.println("ZEROOOOO :(");
        //     return false; // pas de chemin trouve pour aller a l'incendie on
        // }
        System.out.println("pos : " + robot.getPosition() + " destination : " + i);
        System.out.println("pathCases ???? -> " + pathCases);
        Path path = convertInPath(pathCases);
        System.out.println("dates ???? -> " + path.getDates());
        System.out.println("nextMoves???? -> " + path.getNextMoves());

        addPathEventsToSimulateur(i, path);
    }

    public void seRemplir() {
        System.out.println("SE REMPLIR :" + robot);
        if(canSeRemplir(robot.getPosition())) {
            int timeNeeded = robot.getTempsRemplissage();
            simulateur.addEvenement(new RemplirEvenement(simulateur.getDateSimulation()+timeNeeded, this));
            simulateur.addEvenement(new DisponibleEvenement(simulateur.getDateSimulation()+timeNeeded, this, chefPompier));
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
            simulateur.addEvenement(new DisponibleEvenement(dates.get(dates.size()-1)+timeNeeded, this, chefPompier));
        }
    }

    private Integer calculateArrivalDate(Robot robot, Case curr, int date, int sizeCase) {
        if (robot.getPosition() == curr)
            return date;
        return date + (int)(sizeCase/robot.getVitesseNature(curr.getNatureTerrain()));
    }
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
