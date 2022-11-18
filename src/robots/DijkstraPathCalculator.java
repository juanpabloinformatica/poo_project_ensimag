package robots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;

import classes.Carte;
import classes.Case;
import constants.NatureTerrain;
import constants.TypeRobot;
import events.Simulateur;;

/**
 * the Dijkstra class represent the algorithm that allows
 * certain robot to find the shortest path to certain destination
 */
public class DijkstraPathCalculator extends PathCalculator {

  private int timeNeeded;
  private ArrayList<Case> shortestPath;

  /**
   * create dijkstra that receives
   * 
   * @param simulateur - simulator
   * @param carte      - map
   */
  public DijkstraPathCalculator(Carte carte) {
    super(carte);
  }

  /**
   * returns the shortest path if it exists to the Case target or null if not
   * 
   * @param r      - robot
   * @param target - destination case
   * @return ArrayList<Case>
   */
  @Override
  public List<Case> computePath(Robot r, Case target) {
    shortestPath = new ArrayList<Case>();
    timeNeeded = Integer.MAX_VALUE;
    if (dijkstra(r, target)) {
      assert (shortestPath.size() != 0);
      return shortestPath;
    }
    return null;
  }

  /**
   * returns the time needed to reach case target or infinity if it's unreachable
   * 
   * @param r
   * @param target
   * @return double
   */
  @Override
  public double getTimeToCase(Robot r, Case target) {
    shortestPath = new ArrayList<Case>();
    boolean pathFound = dijkstra(r, target);
    if (pathFound) {
      return (double) this.timeNeeded;
    }
    return Double.POSITIVE_INFINITY;
  }

  /*
   * implements the dijkstra algorithm to find the shortest path to
   * certain case if it exists or returns false if not
   */
  private boolean dijkstra(Robot robot, Case destination) {

    // this map has the case and the cost(distance+weight)
    Map<Case, Integer> costs = new HashMap<Case, Integer>();
    Set<Case> visited = new HashSet<Case>();

    // backtracker will store for each Case the previous Case in the shortest Path
    Map<Case, Case> backtracker = new HashMap<Case, Case>();
    initializeCosts(robot, costs);
    Case curr = getLowestDistance(costs, visited);
    while (curr != destination && curr != null) {
      visited.add(curr);
      for (Map.Entry<Case, Integer> couple : getAdjacentNodes(this.getCarte(), curr, robot,
          this.getCarte().getTailleCases()).entrySet()) {
        Case adjacent = couple.getKey();
        int distance = couple.getValue();
        if (!(visited.contains(adjacent))) {
          updateHashMap(costs, adjacent, distance, curr, backtracker);
        }
      }
      curr = getLowestDistance(costs, visited);
    }
    if (curr == null)
      return false;
    backtracking(robot, backtracker, shortestPath, destination);
    timeNeeded = costs.get(destination);
    return true;
  }

  /*
   * Backtracks the Cases visited from destination to source
   * and fills the shortest path
   */
  private void backtracking(Robot r, Map<Case, Case> backtracker, ArrayList<Case> shortestPath, Case destination) {
    Case previous = destination;
    while (!previous.equals(r.getPosition())) {
      shortestPath.add(0, previous);
      previous = backtracker.get(previous);
    }
    shortestPath.add(0, previous);
  }

  /*
   * Initializes the costs HashMap
   */
  private void initializeCosts(Robot r, Map<Case, Integer> costs) {
    int nbLignes = this.getCarte().getNbLignes();
    int nbColonnes = this.getCarte().getNbColonnes();
    for (int i = 0; i < nbLignes; i++) {
      for (int j = 0; j < nbColonnes; j++) {
        costs.put(this.getCarte().getCase(i, j), Integer.MAX_VALUE);
      }
    }
    costs.put(r.getPosition(), 0);
  }

  /*
   * returns the case with lowest distance that was not yet visited
   */
  private Case getLowestDistance(Map<Case, Integer> costs, Set<Case> visited) {
    int lowest = Integer.MAX_VALUE;
    Case min = null;
    for (Case cell : costs.keySet()) {
      if ((!visited.contains(cell)) && costs.get(cell) <= lowest) {
        lowest = costs.get(cell);
        min = cell;
      }
    }
    if (lowest == Integer.MAX_VALUE) {
      return null;
    }
    return min;
  }

  /*
   * get the adjacent cases of a certain case
   */
  private Map<Case, Integer> getAdjacentNodes(Carte carte, Case curr, Robot robot, int sizeCase) {
    Map<Case, Integer> adjacentNodes = new HashMap<Case, Integer>();
    int ligne = curr.getLigne();
    int colonne = curr.getColonne();
    int time = (int) (sizeCase / robot.getVitesseNature(curr.getNatureTerrain()));
    if (ligne != 0) {
      if (robot.canGo(carte.getCase(ligne - 1, colonne))) {
        adjacentNodes.put(carte.getCase(ligne - 1, colonne), time);
      }
    }
    if (ligne != carte.getNbLignes() - 1) {
      if (robot.canGo(carte.getCase(ligne + 1, colonne))) {
        adjacentNodes.put(carte.getCase(ligne + 1, colonne), time);
      }
    }
    if (colonne != 0) {
      if (robot.canGo(carte.getCase(ligne, colonne - 1))) {
        adjacentNodes.put(carte.getCase(ligne, colonne - 1), time);
      }
    }
    if (colonne != carte.getNbColonnes() - 1) {
      if (robot.canGo(carte.getCase(ligne, colonne + 1))) {
        adjacentNodes.put(carte.getCase(ligne, colonne + 1), time);
      }
    }
    return adjacentNodes;
  }

  /*
   * update the costs HashMap if we find a better cost
   */
  private void updateHashMap(Map<Case, Integer> costs, Case adjacent, int distance, Case curr,
      Map<Case, Case> backtracker) {
    int sourceDistance = costs.get(curr);
    if (sourceDistance + distance < costs.get(adjacent)) {
      costs.put(adjacent, sourceDistance + distance);
      backtracker.put(adjacent, curr);
    }
  }

  /**
   * Finds the nearest Case in which the robot can refill with water
   * 
   * @param r
   * @return ArrayList<Case>
   */
  @Override
  public List<Case> computePathToWater(Robot r) {
    shortestPath = new ArrayList<Case>();

    // this map has the case and the cost(distance+weight)
    Map<Case, Integer> costs = new HashMap<Case, Integer>();
    Set<Case> visited = new HashSet<Case>();

    // this variable will stock the path in inverse order
    Map<Case, Case> backtracker = new HashMap<Case, Case>();
    initializeCosts(r, costs);
    Case curr = getLowestDistance(costs, visited);
    while ((!r.canSeRemplir(curr)) && curr != null) {
      visited.add(curr);
      for (Map.Entry<Case, Integer> couple : getAdjacentNodes(this.getCarte(), curr, r,
          this.getCarte().getTailleCases()).entrySet()) {
        Case adjacent = couple.getKey();
        int distance = couple.getValue();
        if (!(visited.contains(adjacent))) {
          updateHashMap(costs, adjacent, distance, curr, backtracker);
        }
      }
      curr = getLowestDistance(costs, visited);
    }
    if (curr == null)
      return null;
    backtracking(r, backtracker, shortestPath, curr);
    return shortestPath;
  }
}
