import bagel.Image;

/**
 * This class represents the pellet entity in the game. Extends the EatableEntity class.
 * Pellet can be eaten by the player to switch the game to frenzy mode.
 * Pellet does not have extra point value.
 *
 * @author RUOXUAN XU
 */
public class Pellet extends EatableEntity{
    private final static Image PELLET = new Image("res/pellet.png");

    /**
     * The value of the pellet in terms of points.
     */
    public final static int POINTS = 0;

    /**
     * Method that constructs the pellet object at the specified position.
     *
     * @param initialX The initial x-coordinate of the pellet.
     * @param initialY The initial y-coordinate of the pellet.
     */
    public Pellet(int initialX, int initialY) {
        super(initialX, initialY, PELLET, POINTS);
    }

    /**
     * This method overrides the isEaten method of the EatableEntity superclass.
     * It handles common behavior when the pellet is eaten by the player.
     */
    @Override
    public void isEaten(){
        super.isEaten();
    }
}
