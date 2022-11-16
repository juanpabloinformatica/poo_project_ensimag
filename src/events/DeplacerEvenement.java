package events;

import classes.Carte;
import classes.Case;
import constants.Direction;
import constants.NatureTerrain;
import constants.TypeRobot;
import robots.Robot;
import robots.RobotLogic;

public class DeplacerEvenement extends Evenement {
    private Direction direction;
    private Carte carte;
    private Robot robot;

    public DeplacerEvenement(Integer date, RobotLogic robotLogic,
                             Direction direction, Carte carte) {
        super(date, robotLogic);
        this.direction = direction;
        this.carte = carte;
        this.robot = getRobotLogic().getRobot();

    }


    /*
     * Vérfie que la direction saisi est dans les limites de la carte
     * Si elle est dans les limites elle envoie la Case
     * Sinon elle envoie une exception
     */
    private Case checkMapLimits() throws Exception {
        Case pos = robot.getPosition();
        int i = pos.getLigne();
        int j = pos.getColonne();
        switch (direction) {
            case NORD:
                i -= 1;
                break;
            case SUD:
                i += 1;
                break;
            case EST:
                j += 1;
                break;
            case OUEST:
                j -= 1;
                break;
            default:
                // TODO throw exception type
        }
        if ( i < 0 || i > carte.getNbLignes()) {
            throw new Exception("i out of bounds");
        } else if ( j < 0 || j > carte.getNbLignes()) {
            throw new Exception("j out of bounds");
        }
        return carte.getCase(i,j);
    }
    /*
     * Deplacer le robot
     */
    @Override
    public void execute() {
        try {
            Case nextPos = checkMapLimits();
            TypeRobot typeR = robot.getTypeRobot();
            NatureTerrain natureNext = nextPos.getNatureTerrain();
            switch (typeR) {
                case DRONE:
                    // peut se deplacer par tout
                    break;
                case ROUES:
                    if (natureNext != NatureTerrain.HABITAT && natureNext != NatureTerrain.TERRAIN_LIBRE) {
                        throw new Exception("le robot a roues ne peut se deplacer que sur du terrain_libre ou habitat");
                    }
                    break;
                case PATTES:
                    if (natureNext == NatureTerrain.EAU)
                        throw new Exception("Le robot a pattes ne peut se rendre de l'eau");
                    break;
                case CHENILLES:
                    if (natureNext == NatureTerrain.EAU || natureNext == NatureTerrain.ROCHE)
                        throw new Exception("Le robot a chenilles ne peut pas se rendre sur de l'eau ni sur du rocher");
                    break;
            }
            robot.setPosition(nextPos);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    
}