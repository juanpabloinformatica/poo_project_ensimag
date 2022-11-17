package robots;

import java.util.ArrayList;

import constants.Direction;

/*
 * Associe les nouveaux mouvements et la date a la quelle ils sont fait
 * (i.e date d'arrivee a la case)
 * */
public class Path {
    ArrayList<Direction> nextMoves;
    ArrayList<Integer> dates;

    public Path() {
        nextMoves = new ArrayList<Direction>();
        dates = new ArrayList<Integer>();
    }

    
    /** 
     * @param d
     * @param date
     */
    public void addNextMove(Direction d, Integer date) {
        nextMoves.add(d);
        dates.add(date);
    }

    
    /** 
     * @return ArrayList<Direction>
     */
    public ArrayList<Direction> getNextMoves() {
        return nextMoves;
    }

    
    /** 
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getDates() {
        return dates;
    }
}
