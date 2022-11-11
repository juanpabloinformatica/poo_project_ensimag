package events;


public abstract class Evenement {
    
    private Integer date;
    private boolean eventDone;
    public Evenement(Integer date) {
        this.date = date;
        this.eventDone=false;
    }
    public boolean getEventDone(){
        return this.eventDone;
    }
    public void setEventDone(boolean done){
        this.eventDone = done;
    }
    public Integer getDate(){
        return this.date;
    }
    
    public abstract void execute() throws Exception;
}
