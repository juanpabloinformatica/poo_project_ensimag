package events;

import robots.Robot;

public abstract class Evenement {
    
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
    public boolean getEventDone(){
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


    public abstract void execute() throws Exception;

    public abstract void computeDateExecution(int date);
}
