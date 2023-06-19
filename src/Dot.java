import bagel.Image;

/**
 * This class represents a dot entity in the game. Extends the EatableEntity class.
 * Dots can be eaten by the player to gain points.
 *
 * @author RUOXUAN XU
 */
public class Dot extends EatableEntity{
    private final static Image DOT = new Image("res/dot.png");

    /**
     * The value of each dot in terms of points.
     */
    public final static int POINTS = 10;

    /**
     * Method that constructs a Dot object at the specified position.
     *
     * @param initialX The initial x-coordinate of the dot.
     * @param initialY The initial y-coordinate of the dot.
     */
    public Dot(int initialX, int initialY){
        super(initialX, initialY, DOT, POINTS);
    }
}
