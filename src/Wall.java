import bagel.Image;

/**
 * This class represents a wall entity in the game. Extends the Entity class.
 * Walls cannot be overlapped by any entity.
 *
 * @author RUOXUAN XU
 */
public class Wall extends Entity {
    private final static Image WALL = new Image("res/wall.png");

    /**
     * Method that constructs a Wall object at the specified position.
     *
     * @param initialX The initial x-coordinate of the wall.
     * @param initialY The initial y-coordinate of the wall.
     */
    public Wall(int initialX, int initialY) {
        super(initialX, initialY, WALL);
    }
}
