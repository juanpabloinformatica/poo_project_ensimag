package robots;

public class RobotAChenilles extends Robot {
    private double vitesse;
    public RobotAChenilles(int vitesse) {
        if (vitesse == -1 || vitesse > 80) {
            this.vitesse = 60; // vitesse par défaut
        } else {
            this.vitesse = vitesse;
        }
        this.setReservoir(2000);
        this.setTempsRemplissage(10*60); // 10 min * 60 sec
        this.setVidage(100, 8);
    }

    @Override
    public void setPosition(Case pos) {
        NatureTerrain nT = pos.getNatureTerrain();
        if (nt != EAU && NT != ROCHER) {
            this.position = pos;
        } else {
            // REVIEW: throw error?
        }
    }

    @Override
    public double getVitesse() {
        if (nt == FORET)
            return  this.vitesse / 2;
        return this.vitesse;

    }
}
