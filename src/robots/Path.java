package robots;

import java.util.ArrayList;

import constants.Direction;

/**
 * the class Path  represents the association of the movements and the date when they are done
 * 
 * */
public class Path {
    ArrayList<Direction> nextMoves;
    ArrayList<Integer> dates;

    /**
     * create a path 
     */
    public Path() {
        nextMoves = new ArrayList<Direction>();
        dates = new ArrayList<Integer>();
    }

    
    /** 
     * add the following move in the list
     * @param d - direction
     * @param date - date of path
     */
    public void addNextMove(Direction d, Integer date) {
        nextMoves.add(d);
        dates.add(date);
    }

    
    /** 
     * get the list of future moves
     * @return ArrayList<Direction>
     */
    public ArrayList<Direction> getNextMoves() {
        return nextMoves;
    }

    
    /** 
     * get the list of dates of moves
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getDates() {
        return dates;
    }
}
