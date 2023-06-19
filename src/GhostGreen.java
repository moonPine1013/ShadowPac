import bagel.Image;

/**
 * This class represents a green ghost entity in the game. Extends the Ghost class.
 * Green ghosts have a specific image,movement pattern, initial direction, and initial speed.

 * @author RUOXUAN XU
 */
public class GhostGreen extends Ghost{
    private final static Image GHOST_GREEN = new Image("res/ghostGreen.png");
    private final static int INITIAL_SPEED = 4;
    private final static int INDEXING= 1;

    /**
     * Method that constructs a green ghost object at the specified position.
     *
     * @param initialX The initial x-coordinate of the green ghost.
     * @param initialY The initial y-coordinate of the green ghost.
     */
    public GhostGreen(int initialX, int initialY) {
        super(initialX, initialY, GHOST_GREEN, INITIAL_SPEED,
                random.nextInt(REVERSE_DIRECTION) + INDEXING,false);
    }
}
