import bagel.Image;

/**
 * This class represents a red ghost entity in the game. Extends the Ghost class.
 * Red ghosts have a specific image,movement pattern, initial direction, and initial speed.

 * @author RUOXUAN XU
 */
public class GhostRed extends Ghost{
    private final static Image GHOST_RED = new Image("res/ghostRed.png");
    private final static int INITIAL_SPEED = 1;

    /**
     * Method that constructs a red ghost object at the specified position.
     *
     * @param initialX The initial x-coordinate of the red ghost.
     * @param initialY The initial y-coordinate of the red ghost.
     */
    public GhostRed(int initialX, int initialY) {
        super(initialX, initialY, GHOST_RED, INITIAL_SPEED, RIGHT,false);
    }

    /**
     * Method that constructs a red ghost object at the specified position and specific speed.
     *
     * @param initialX The initial x-coordinate of the red ghost.
     * @param initialY The initial y-coordinate of the red ghost.
     * @param moveSize The speed of the red ghost.
     */
    public GhostRed(int initialX, int initialY, int moveSize) {
        super(initialX, initialY, GHOST_RED, moveSize, RIGHT,false);
    }
}


