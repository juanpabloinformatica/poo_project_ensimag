package events;

import classes.Carte;
import classes.Case;
import constants.Direction;
import constants.NatureTerrain;
import constants.TypeRobot;
import robots.Robot;
import robots.Robot;

/**
 * the class deplacerEvenement represent the movement of a robot
 */
public class DeplacerEvenement extends Evenement {
    private Direction direction;
    private Carte carte;
    private Robot robot;

    /**
     * create the movement event, receives the date, the robot logic the direction and the map
     * @param date - the date of the event
     * @param robot - the robot logic
     * @param direction - direction 
     * @param carte - map of the simulation
     */
    public DeplacerEvenement(Integer date, Robot robot,
                             Direction direction, Carte carte) {
        super(date, robot);
        this.direction = direction;
        this.carte = carte;
        this.robot = robot;

    }

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
        }
        if ( i < 0 || i > carte.getNbLignes()) {
            throw new Exception("i out of bounds");
        } else if ( j < 0 || j > carte.getNbLignes()) {
            throw new Exception("j out of bounds");
        }
        return carte.getCase(i,j);
    }
    
    /**
     * perform the movement of a robot
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
