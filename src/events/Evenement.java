package events;

import robots.Robot;
import robots.RobotLogic;

/**
 * the Evenement class represent the possible actions that a robot can has 
 * in the simulation 
 */
public abstract class Evenement implements Comparable<Evenement> {
    
    private Integer date;
    protected Integer dateExecution;
    private boolean eventDone;
    private RobotLogic robotLogic;

    /**
     * create a event which receive a date and robotLogic 
     * @param date - date of the event
     * @param robotL - logic of the robots
     */
    public Evenement(Integer date, RobotLogic robotL) {
        this.date = date;
        this.eventDone=false;
        this.robotLogic = robotL;
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
     * @return RobotLogic
     */
    public RobotLogic getRobotLogic() {
        return this.robotLogic;
    }

    /**
     * apply the events in each event class
     */
    public abstract void execute();
}
