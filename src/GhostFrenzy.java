import bagel.Image;

/**
 * This class represents a ghost entity in the frenzy game mode. Extends the Ghost class.
 * Frenzy ghosts have a specific image and updated speed.
 * The initial position movement pattern and direction of the original ghost are preserved.
 * In frenzy mode, the Player can eat Ghosts and earn points.

 * @author RUOXUAN XU
 */
public class GhostFrenzy extends Ghost{
    private final static Image GHOST_FRENZY = new Image("res/ghostFrenzy.png");
    private final static double SPEED_DECREASE = 0.5;

    /**
     * Method that constructs a frenzy ghost based on an existing ghost object.
     *
     * @param ghost The original ghost object to create the frenzy ghost from.
     */
    public GhostFrenzy(Ghost ghost){
        super(ghost.getPosition().x, ghost.getPosition().y, GHOST_FRENZY,
                ghost.getMoveSize() - SPEED_DECREASE, ghost.getDirection(),ghost.isMoveRandomly());
    }

    /**
     * Method that resets the position of the ghost to the initial position.
     */
    @Override
    public void resetPosition(){
        setActive(false);
        super.resetPosition();
    }
}
