import bagel.Image;

/**
 * This class represents a cherry entity in the game. Extends the EatableEntity class.
 * Cherries can be eaten by the player to gain points.
 *
 * @author RUOXUAN XU
 */
public class Cherry extends EatableEntity {
    private final static Image CHERRY = new Image("res/cherry.png");

    /**
     * The value of each cherry in terms of points.
     */
    public final static int POINTS = 20;

    /**
     * Method that constructs a Cherry object at the specified position.
     *
     * @param initialX The initial x-coordinate of the cherry.
     * @param initialY The initial y-coordinate of the cherry.
     */
    public Cherry(int initialX, int initialY) {
        super(initialX, initialY, CHERRY, POINTS);
    }
}
