/**
 * The Movable interface represents an entity that can be moved within the game.
 * Player class and Ghost class implement the Movable interface.
 * Implementing classes need provide functionality for movement features.
 *
 * @author RUOXUAN XU
 */
public interface Movable {

    /**
     * Method that moves the movable entity by the specified pixels in the x and y directions.
     *
     * @param xMove The pixels to move in the x-direction.
     * @param yMove The pixels to move in the y-direction.
     */
    void move(double xMove, double yMove);

    /**
     * Method that moves the movable entity back to its previous position when collides with walls.
     */
    void moveBack();

    /**
     * Method that resets the position of the movable entity to the initial position.
     */
    void resetPosition();

    /**
     * Method that retrieves the speed of the movable entity.
     */
    double getMoveSize();
}
