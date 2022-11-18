package robots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.PriorityQueue;

import classes.Carte;
import classes.Case;
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
  public DijkstraPathCalculator(Simulateur simulateur, Carte carte) {
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
    int nbLignes = this.getCarte().getNbLignes();
    int nbColonnes = this.getCarte().getNbColonnes();
    PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(nbLignes * nbColonnes,new Node());
    priorityQueue.add(new Node(robot.getPosition(), 0));
    initializeCosts(robot, costs, nbLignes, nbColonnes);
    Node currNode = priorityQueue.remove();
    while (currNode.getCurr() != destination) {
      visited.add(currNode.getCurr());
      for (Map.Entry<Case, Integer> couple : getAdjacentNodes(this.getCarte(), currNode.getCurr(), robot,
          this.getCarte().getTailleCases()).entrySet()) {
        Case adjacent = couple.getKey();
        int distance = couple.getValue();
        if (!(visited.contains(adjacent))) {
          updateCosts(costs, priorityQueue, adjacent, distance, currNode.getCurr(), backtracker);
        }
      }
      if (priorityQueue.isEmpty()) break;
      currNode = priorityQueue.remove();
    }
    if (currNode.getCurr() != destination) return false;
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
  private void initializeCosts(Robot r, Map<Case, Integer> costs, int nbLignes, int nbColonnes) {
    for (int i = 0; i < nbLignes; i++) {
      for (int j = 0; j < nbColonnes; j++) {
        costs.put(this.getCarte().getCase(i, j), Integer.MAX_VALUE);
      }
    }
    costs.put(r.getPosition(), 0);
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
   * update the costs HashMap if we find a better cost and adds to priorityqueue
   */
  private void updateCosts(Map<Case, Integer> costs, PriorityQueue<Node> priorityQueue, Case adjacent, int distance, Case curr, Map<Case, Case> backtracker) {
    int sourceDistance = costs.get(curr);
    if (sourceDistance + distance < costs.get(adjacent)) {
      costs.put(adjacent, sourceDistance + distance);
      priorityQueue.add(new Node(adjacent, sourceDistance + distance));
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
    int nbLignes = this.getCarte().getNbLignes();
    int nbColonnes = this.getCarte().getNbColonnes();
    // the priority queue will be used to retrieve nearest Cases everytime
    PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(nbLignes * nbColonnes,new Node());
    priorityQueue.add(new Node(r.getPosition(), 0));
    initializeCosts(r, costs, nbLignes, nbColonnes);
    Node currNode = priorityQueue.remove();
    initializeCosts(r, costs, nbLignes, nbColonnes);
    while ((!r.canSeRemplir(currNode.getCurr())) && currNode.getCurr() != null) {
      visited.add(currNode.getCurr());
      for (Map.Entry<Case, Integer> couple : getAdjacentNodes(this.getCarte(), currNode.getCurr(), r,
          this.getCarte().getTailleCases()).entrySet()) {
        Case adjacent = couple.getKey();
        int distance = couple.getValue();
        if (!(visited.contains(adjacent))) {
          updateCosts(costs, priorityQueue, adjacent, distance, currNode.getCurr(), backtracker);
        }
      }
      if (priorityQueue.isEmpty()) break;
      currNode = priorityQueue.remove();
    }
    if (!r.canSeRemplir(currNode.getCurr())) return null;
    backtracking(r, backtracker, shortestPath, currNode.getCurr());
    return shortestPath;
  }
}
