package events;

import robots.Robot;

public abstract class Evenement implements Comparable<Evenement> {
    
    private Integer date;
    protected Integer dateExecution;
    private boolean eventDone;
    private Robot robot;
    public Evenement(Integer date, Robot robot) {
        this.date = date;
        this.eventDone=false;
        this.robot = robot;
        this.dateExecution = null;
    }
    @Override // for use priority queue instead of linked list
    public int compareTo(Evenement e) {
        if (date > e.getDate())
            return 1;
        if (date < e.getDate())
            return -1;
        return 0; // this.date == e.date;
    }
    public boolean isEventDone(){
        return this.eventDone;
    }
    public void setEventDone(){
        this.eventDone = true;
    }
    public Integer getDate(){
        return this.date;
    }
    public Integer getDateExecution(){
        return this.dateExecution;
    }

    public Robot getRobot() {
        return this.robot;
    }

    public abstract void execute();
}
