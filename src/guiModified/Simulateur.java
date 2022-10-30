package guiModified;

import java.util.Iterator;
import gui.GUISimulator;
import gui.Simulable;

public class Simulateur implements Simulable {
    private GUISimulator gui;

    private int x;

    /** Ordonnée courante de l'invader (bord supérieur) */
    private int y;

    /** Itérateur sur les abcisses de l'invader au cours du temps */
    private Iterator<Integer> xIterator;

    /** Itérateur sur les ordonnées de l'invader au cours du temps */
    private Iterator<Integer> yIterator;
    
    @Override
    public void next() {
        if (this.xIterator.hasNext())
            this.x = this.xIterator.next();		
        if (this.yIterator.hasNext())
            this.y = this.yIterator.next();		
        draw();
    }

    @Override
    public void restart() {
        planCoordinates();
        draw();
    }

    private void draw() {
    }

    private void planCoordinates() {
    }

    
}
