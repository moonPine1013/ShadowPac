import bagel.Image;

/**
 * The abstract class EatableEntity represents an entity in the game that can be eaten by the player.
 * It extends the Entity class.
 * This class provides common attributes and methods for all eatable entities.
 *
 * @author RUOXUAN XU
 */
abstract public class EatableEntity extends Entity {
    private int points;

    /**
     * Method that constructs an EatableEntity object at the specified position with the given image and points value.
     *
     * @param initialX The initial x-coordinate of the entity.
     * @param initialY The initial y-coordinate of the entity.
     * @param image The image representing the entity.
     * @param points The points value associated with the entity.
     */
    public EatableEntity(int initialX, int initialY, Image image, int points) {
        super(initialX, initialY, image);
        this.points = points;
    }

    /**
     * Method that retrieves the points value associated with the entity.
     *
     * @return The points value of the entity.
     */
    public int getPoints(){
        return points;
    }

    /**
     * Method that sets the entity as inactive when it is eaten.
     * This method is expected to be overridden by subclasses for additional functionality.
     */
    public void isEaten(){
        setActive(false);
    }
}
