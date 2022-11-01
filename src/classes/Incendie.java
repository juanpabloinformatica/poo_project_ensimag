package classes;

import events.Evenement;

public class Incendie extends Evenement{
    private Case pos;
    private int intensite;

    // public Incendie(Case pos, int intensite){
    //     super(0);
    //     this.pos = pos;
    //     this.intensite = intensite;
    // }
    public Incendie(Case pos, int intensite,Integer date){
        super(date);
        this.pos = pos;
        this.intensite = intensite;
    }
    @Override
    public String toString() {
        return "Incendie a la " + pos.toString() + "Intensite lvl = " +
            intensite + "\n";
    }
    /*
     * implement what happens if a robot put out the fire
     * and possible events that can happen!
     */
    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }
    
}
