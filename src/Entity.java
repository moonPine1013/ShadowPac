import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * The abstract class Entity represents a generic entity in the game.
 * It provides common attributes and methods for game entities.
 * Subclasses of Entity are expected to provide specific functionality.
 *
 * @author RUOXUAN XU
 */
abstract public class Entity {
    private Image image;
    private Point position;
    private boolean isActive;

    /**
     * Method that constructs an Entity object at the specified position with the given image.
     * All the entities are active initially as they exist on the screen.
     *
     * @param initialX The initial x-coordinate of the entity.
     * @param initialY The initial y-coordinate of the entity.
     * @param image The image representing the entity.
     */
    public Entity(double initialX, double initialY, Image image) {
        this.position = new Point(initialX, initialY);
        this.image = image;
        this.isActive = true;
    }

    /**
     * Method that updates the position and image of the entity on the screen.
     * If the entity is active, it draws the image at its current position.
     */
    public void update() {
        if (isActive()){
            image.drawFromTopLeft(this.position.x, this.position.y);
        }
    }

    /**
     * Method that returns the bounding box of the entity.
     * The bounding box is a rectangle that encloses the entity's image.
     *
     * @return The bounding box of the entity.
     */
    public Rectangle getBoundingBox(){
        return new Rectangle(position, image.getWidth(), image.getHeight());
    }

    /**
     * Method that retrieves the position of the entity.
     *
     * @return The position of the entity.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Method that sets the position of the entity.
     *
     * @param position The new position of the entity.
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Method that checks whether the entity is currently on the screen.
     *
     * @return True if the entity is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Method that sets the active state of the entity.
     *
     * @param active The new active state of the entity.
     */
    public void setActive(boolean active) {
        isActive = active;
    }
}
