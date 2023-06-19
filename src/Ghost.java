import bagel.Image;
import bagel.util.Point;
import java.util.Random;

/**
 * The Ghost class represents a general ghost entity in the game.
 * It extends the Entity class and implements the Movable interface.
 * Ghosts in different color have movement behavior and can change direction randomly or in a predefined pattern.
 *
 * @author RUOXUAN XU
 */
public class Ghost extends Entity implements Movable {
    protected final static int UP = 0;
    protected final static int RIGHT = 1;
    protected final static int DOWN = 2;
    protected final static int LEFT = 3;
    protected final static Random random = new Random();
    protected final static int ALL_DIRECTION = 4;
    protected final static int REVERSE_DIRECTION = 2;
    private final static int STATIONARY = 0;

    private Point previousPosition;
    private Point initialPosition;
    private int direction;
    private double moveSize;
    private boolean moveRandomly;

    /**
     * The value of each ghost in terms of points in frenzy mode.
     */
    public final static int FRENZY_SCORE = 30;

    /**
     * Method that constructs a Ghost object at the specified position with
     * the given image, movement size, direction, and movement type.
     *
     * @param initialX The initial x-coordinate of the ghost.
     * @param initialY The initial y-coordinate of the ghost.
     * @param image The image representing the ghost.
     * @param moveSize The speed of the ghost.
     * @param direction The initial direction of the ghost's movement.
     * @param movement Whether the ghost moves randomly or in a reverse pattern.
     */
    public Ghost(double initialX, double initialY, Image image, double moveSize, int direction,boolean movement) {
        super(initialX, initialY, image);
        this.moveSize = moveSize;
        this.direction = direction;
        this.moveRandomly = movement;
        this.initialPosition = new Point(initialX, initialY);
    }

    /**
     * Method that updates the position of the ghost based on its current direction and move size.
     * It also calls from the super class (Entity) to update the image of the ghost.
     */
    @Override
    public void update() {
        if (direction == UP) {
            move(0, -moveSize);
        } else if (direction == DOWN) {
            move(0, moveSize);
        } else if (direction == LEFT) {
            move(-moveSize, 0);
        } else if (direction == RIGHT) {
            move(moveSize, 0);
        }
        super.update();
    }

    /**
     * Method that moves the ghost by the specified pixels in the x and y directions.
     * It updates the previous position and sets the new position of the ghost.
     *
     * @param xMove The pixels to move in the x-direction.
     * @param yMove The pixels to move in the y-direction.
     */
    @Override
    public void move(double xMove, double yMove) {
        previousPosition = getPosition();
        setPosition(new Point(getPosition().x + xMove, getPosition().y + yMove));
    }

    /**
     * Method that moves the ghost back to its previous position when collides with walls.
     */
    @Override
    public void moveBack() {
        setPosition(previousPosition);
    }

    /**
     * Method that retrieves the position of the ghost.
     *
     * @return The position of the ghost.
     */
    @Override
    public Point getPosition() {
        return super.getPosition();
    }

    /**
     * Method that sets the position of the ghost to the specified position.
     * If the ghost is stationary, it sets the position to the initial position.
     * Otherwise, it sets the position to the specified position.
     *
     * @param position The new position of the ghost.
     */
    @Override
    public void setPosition(Point position) {
        if (moveSize == STATIONARY) {
            super.setPosition(initialPosition);
        } else {
            super.setPosition(position);
        }
    }

    /**
     * Method that retrieves the speed of the ghost.
     *
     * @return The speed of the ghost.
     */
    @Override
    public double getMoveSize() {
        return moveSize;
    }

    /**
     * Method that resets the position of the ghost to the initial position.
     */
    @Override
    public void resetPosition() {
        setPosition(initialPosition);
    }

    /**
     * Method that changes the direction of the ghost when collides with wall.
     * If the ghost is set to move randomly, it selects a new random direction.
     * Otherwise, it reverses the current direction of the ghost.
     */
    public void changeDirection() {
        if(moveRandomly){
            direction = random.nextInt(ALL_DIRECTION);
        } else {
            if (direction == UP) {
                direction = DOWN;
            } else if (direction == DOWN) {
                direction = UP;
            } else if (direction == LEFT) {
                direction = RIGHT;
            } else if (direction == RIGHT) {
                direction = LEFT;
            }
        }
    }

    /**
     * Method that retrieves the current direction of the ghost.
     *
     * @return The current direction of the ghost.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Method that checks if the ghost moves randomly.
     *
     * @return True if the ghost moves randomly, false reversely.
     */
    public boolean isMoveRandomly() {
        return moveRandomly;
    }
}