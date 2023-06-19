import bagel.Image;

/**
 * This class represents a pink ghost entity in the game. Extends the Ghost class.
 * Pink ghosts have a specific image,movement pattern, initial direction, and initial speed.

 * @author RUOXUAN XU
 */
public class GhostPink extends Ghost{
    private final static Image GHOST_PINK = new Image("res/ghostPink.png");
    private final static int INITIAL_SPEED = 3;

    /**
     * Method that constructs a pink ghost object at the specified position.
     *
     * @param initialX The initial x-coordinate of the pink ghost.
     * @param initialY The initial y-coordinate of the pink ghost.
     */
    public GhostPink(int initialX, int initialY) {
        super(initialX, initialY, GHOST_PINK, INITIAL_SPEED, random.nextInt(ALL_DIRECTION),true);
    }
}
