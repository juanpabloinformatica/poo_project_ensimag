package events;

import robots.Robot;
import robots.Robot;

/**
 * the Evenement class represent the possible actions that a robot can has 
 * in the simulation 
 */
public abstract class Evenement implements Comparable<Evenement> {
    
    private Integer date;
    protected Integer dateExecution;
    private boolean eventDone;
    private Robot robot;

    /**
     * create a event which receive a date and robot
     * @param date - date of the event
     * @param robot - logic of the robots
     */
    public Evenement(Integer date, Robot robot) {
        this.date = date;
        this.eventDone=false;
        this.robot = robot;
        this.dateExecution = null;
    }

    
    /** 
     * compare the dates of the events in the simulation
     * @param e
     * @return int
     */
    @Override // for use priority queue instead of linked list
    public int compareTo(Evenement e) {
        if (date > e.getDate())
            return 1;
        if (date < e.getDate())
            return -1;
        return 0; // this.date == e.date;
    }
    
    /** 
     * get is certain event was done
     * @return boolean
     */
    public boolean isEventDone(){
        return this.eventDone;
    }
    /**
     * set the event as done
     */
    public void setEventDone(){
        this.eventDone = true;
    }
    
    /** 
     * get the date of the event
     * @return Integer
     */
    public Integer getDate(){
        return this.date;
    }
    
    /** 
     * get the date when the event will be executed
     * @return Integer
     */
    public Integer getDateExecution(){
        return this.dateExecution;
    }

    
    /**  get the robot logic
     * @return Robot
     */
    public Robot getRobot() {
        return this.robot;
    }

    /**
     * apply the events in each event class
     */
    public abstract void execute();
}
