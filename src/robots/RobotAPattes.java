package robots;

public class RobotAPattes extends Robot {
    private double vitesse;
    public RobotAPattes() {
        this.vitesse = 30;
        this.setReservoir(Integer.MAX_VALUE);
        this.setTempsRemplissage(Integer.MAX_VALUE); // 30 min * 60 sec
        this.setVidage(10, 1);
    }

    @Override
    public void setPosition(Case pos) {
        if (pos.getNatureTerrain() != EAU)
            this.position = pos;
        // REVIEW: throw error?
    }
    public double getVitesse() {
        NatureTerrain natureTerrain = this.getPosition().getNatureTerrain();
        if (natureTerrain == ROCHER)
            return 10;
        return this.vitesse;
    }
}
