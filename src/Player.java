import bagel.*;
import bagel.util.Point;

/**
 * The Player class represents a pacman entity in the game.
 * It extends the Entity class and implements the Movable interface.
 * The player can move, collect points, and interact with other entities in the game.
 * The player can move in different directions and has specific iterative images for animation.
 * The player's lives, score, and frenzy mode status are tracked and updated.
 *
 * @author RUOXUAN XU
 */
public class Player extends Entity implements Movable {
    private final static String PAC = "res/pac.png";
    private final static String PAC_OPEN = "res/pacOpen.png";
    private final static Image HEART = new Image("res/heart.png");
    private final static double MOVE_SIZE = 3;
    private final static double SPEED_INCREASE = 1;
    private final static int MAX_LIVES = 3;
    private final static int SWITCH_FRAME = 15;
    private final static int FONT_SIZE = 20;
    private final static String SCORE_STRING = "SCORE ";
    private final static int SCORE_X = 25;
    private final static int SCORE_Y = 25;
    private final static int LIVES_X = 900;
    private final static int LIVES_Y = 10;
    private final static int LIVES_OFFSET = 30;
    private final Font FONT = new Font("res/FSO8BITR.ttf", FONT_SIZE);
    private final Point startingPosition;

    private DrawOptions rotator = new DrawOptions();
    private int counter;
    private int score;
    private int lives;
    private Point position;
    private Point prevPosition;
    private Image currentImage;
    private boolean isOpen = false;
    private boolean isFrenzyMode;

    /**
     * Method that constructs a Player object at the specified position.
     *
     * @param initialX The initial x-coordinate of the player.
     * @param initialY The initial y-coordinate of the player.
     */
    public Player(double initialX, double initialY) {
        super(initialX, initialY, new Image(PAC));

        this.position = new Point(initialX, initialY);
        this.startingPosition = position;
        this.currentImage = new Image(PAC);
        this.counter = SWITCH_FRAME;
        this.lives = MAX_LIVES;
        this.score = 0;
    }

    /**
     * Method that updates the state of the player based on the user input and game object interactions.
     * This method is called each frame to update the player's position, animation, collisions, score, and lives.
     *
     * @param input The input object that handles user input.
     * @param gameObject The game object instance for collision detection and interaction.
     */
    public void update(Input input, ShadowPac gameObject){
        counter--;
        if (input.isDown(Keys.UP)){
            move(0, -getMoveSize());
            rotator.setRotation(-Math.PI/2);
        } else if (input.isDown(Keys.DOWN)){
            move(0, getMoveSize());
            rotator.setRotation(Math.PI/2);
        } else if (input.isDown(Keys.LEFT)){
            move(-getMoveSize(),0);
            rotator.setRotation(Math.PI);
        } else if (input.isDown(Keys.RIGHT)) {
            move(getMoveSize(),0);
            rotator.setRotation(0);
        }
        if (counter == 0) {
            if (isOpen) {
                currentImage = new Image(PAC);
                isOpen = false;
            } else {
                currentImage = new Image(PAC_OPEN);
                isOpen = true;
            }
            counter = SWITCH_FRAME;
        }
        currentImage.drawFromTopLeft(position.x, position.y, rotator);
        gameObject.checkCollisions(this);
        renderScore();
        renderLives();
    }

    /**
     * Method that moves the player by the specified pixels in the x and y directions.
     * It updates the previous position and sets the new position of the player.
     *
     * @param xMove The pixels to move in the x-direction.
     * @param yMove The pixels to move in the y-direction.
     */
    @Override
    public void move(double xMove, double yMove){
        prevPosition = position;
        position = new Point(position.x + xMove, position.y + yMove);
    }

    /**
     * Method that renders the player's score.
     */
    private void renderScore(){
        FONT.drawString(SCORE_STRING + score, SCORE_X, SCORE_Y);
    }

    /**
     * Method that renders the player's lives.
     */
    private void renderLives(){
        for (int i = 0; i < lives; i++){
            HEART.drawFromTopLeft(LIVES_X + (LIVES_OFFSET*i), LIVES_Y);
        }
    }

    /**
     * Method that resets the player's position to the starting location.
     */
    @Override
    public void resetPosition(){
        position = startingPosition;
        currentImage = new Image(PAC);
        rotator.setRotation(0);
    }

    /**
     * Method that moves the player back to its previous position when collides with walls.
     */
    @Override
    public void moveBack(){
        position = prevPosition;
    }

    /**
     * Method that checks if the player has 0 lives.
     */
    public boolean isDead() {
        return lives == 0;
    }

    /**
     * Method that checks if the player has reached the target score.
     *
     * @param target The target score required to win each level of the game.
     */
    public boolean reachedScore(int target){
        return score == target;
    }

    /**
     * Method that increments the player's score by the specified amount.
     *
     * @param score The amount to increment the score by.
     */
    public void incrementScore(int score) {
        this.score += score;
    }

    /**
     * Method that reduces the player's remaining lives
     * by one when collides with the ghost in normal game mode.
     */
    public void reduceLives() {
        lives--;
    }

    /**
     * Method that returns the current position of the player.
     *
     * @return The current position of the player.
     */
    @Override
    public Point getPosition() {
        return position;
    }

    /**
     * Method that sets the position of the player to the specified position.
     *
     * @param position The new position for the player.
     */
    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Method that returns the current image of the player.
     *
     * @return The current image of the player.
     */
    public Image getCurrentImage() {
        return currentImage;
    }

    /**
     * Method that returns the size of the player's movement.
     * If the player is in frenzy mode, the movement size is increased by one.
     *
     * @return The size of the player's movement.
     */
    public double getMoveSize() {
        if (isFrenzyMode) {
            return MOVE_SIZE + SPEED_INCREASE;
        }
        return MOVE_SIZE;
    }

    /**
     * Method that sets the frenzy mode of the player.
     * @param isFrenzyMode True if the player is in frenzy mode, otherwise not.
     */
    public void setFrenzyMode(boolean isFrenzyMode) {
        this.isFrenzyMode = isFrenzyMode;
    }
}
