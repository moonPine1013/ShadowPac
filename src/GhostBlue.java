import bagel.Image;

/**
 * This class represents a blue ghost entity in the game. Extends the Ghost class.
 * Blue ghosts have a specific image,movement pattern, initial direction, and initial speed.

 * @author RUOXUAN XU
 */
public class GhostBlue extends Ghost{
    private final static Image GHOST_BLUE = new Image("res/ghostBlue.png");
    private final static int INITIAL_DIRECTION = DOWN;
    private final static int INITIAL_SPEED = 2;

    /**
     * Method that constructs a blue ghost object at the specified position.
     *
     * @param initialX The initial x-coordinate of the blue ghost.
     * @param initialY The initial y-coordinate of the blue ghost.
     */
    public GhostBlue(int initialX, int initialY) {
        super(initialX, initialY, GHOST_BLUE, INITIAL_SPEED, INITIAL_DIRECTION,false);
    }
}
