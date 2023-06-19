import bagel.*;
import bagel.util.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SWEN20003 Project 2B, Semester 1, 2023
 * The ShadowPac class represents the main game class for the Shadow Pac game.
 * It extends the AbstractGame class and manages the game's logic, rendering,
 * and user input handling.
 *
 * @author RUOXUAN XU
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final static String WORLD_FILE_0 = "res/level0.csv";
    private final static String WORLD_FILE_1 = "res/level1.csv";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");
    private final static int TITLE_FONT_SIZE = 64;
    private final static int INSTRUCTION_FONT_SIZE_0 = 24;
    private final static int INSTRUCTION_FONT_SIZE_1 = 40;
    private final static int TITLE_X = 260;
    private final static int TITLE_Y = 250;
    private final static int INS_X_1 = 200;
    private final static int INS_Y_1 = 350;
    private final static int INS_X_OFFSET = 60;
    private final static int INS_Y_OFFSET = 190;
    private final static String INSTRUCTION_MESSAGE_0 = "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE";
    private final static String INSTRUCTION_MESSAGE_1 =
            "PRESS SPACE TO START\nUSE ARROW KEYS TO MOVE\nEAT THE PELLET TO ATTACK";
    private final static String END_MESSAGE = "GAME OVER!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LEVEL_COMPLETE = "LEVEL COMPLETE!";
    private final Font TITLE_FONT = new Font("res/FSO8BITR.ttf", TITLE_FONT_SIZE);
    private final Font INSTRUCTION_FONT_0 = new Font("res/FSO8BITR.ttf", INSTRUCTION_FONT_SIZE_0);
    private final Font INSTRUCTION_FONT_1 = new Font("res/FSO8BITR.ttf", INSTRUCTION_FONT_SIZE_1);
    private final static int LEVEL_0 = 0;
    private final static int LEVEL_1 = 1;
    private final static int FRENZY_INTERVAL = 1000;
    private final static int LEVEL_INTERVAL = 300;
    private final static int WIN_SCORE_0 = 1210;
    private final static int WIN_SCORE_1 = 800;

    private ArrayList<Wall> walls;
    private ArrayList<EatableEntity> eatableEntities;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Ghost> ghostsFrenzy;
    private Player player;
    private Pellet pellet;
    private boolean hasStarted;
    private boolean gameOver;
    private boolean playerWin;
    private boolean isFrenzyMode;
    private int level;
    private int frenzyCounter = FRENZY_INTERVAL;
    private int levelCounter = LEVEL_INTERVAL;

    /**
     * Method that constructs a new instance of the ShadowPac class.
     * It initializes the game window with the specified width, height, and title.
     * The game starts at level 0, and the game will be reset to specific status.
     */
    public ShadowPac(){
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        level = LEVEL_0;
        resetGame();
    }

    /**
     * Method that resets the game state to prepare for a new game.
     * It sets the game status flags to their initial values, initializes the lists
     * for walls, eatable entities, ghosts, and frenzy ghosts, and reads the level's
     * CSV file based on the current level.
     */
    private void resetGame(){
        hasStarted = false;
        gameOver = false;
        playerWin = false;
        isFrenzyMode = false;

        walls = new ArrayList<>();
        eatableEntities = new ArrayList<>();
        ghosts = new ArrayList<>();
        ghostsFrenzy = new ArrayList<>();
        if(level == LEVEL_0) {
           readCSV(WORLD_FILE_0);
        }else{
           readCSV(WORLD_FILE_1);
        }
    }

    /**
     * Method used to read csv file and create objects for specific entity.
     * Entities with varying quantities are stored in an ArrayList.
     *
     * @param filename The name of the CSV file to be read to start the particular game level.
     */
    private void readCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line;

            while((line = reader.readLine()) != null){
                String[] sections = line.split(",");
                switch (sections[0]) {
                    case "Player":
                        player = new Player(Integer.parseInt(sections[1]), Integer.parseInt(sections[2]));
                        break;
                    case "Ghost":
                        ghosts.add(new GhostRed(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2]),0));
                        break;
                    case "GhostRed":
                        ghosts.add(new GhostRed(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "GhostBlue":
                        ghosts.add(new GhostBlue(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "GhostGreen":
                        ghosts.add(new GhostGreen(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "GhostPink":
                        ghosts.add(new GhostPink(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "Dot":
                        eatableEntities.add(new Dot(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "Wall":
                        walls.add(new Wall(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "Cherry":
                        eatableEntities.add(new Cherry(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2])));
                        break;
                    case "Pellet":
                        pellet = new Pellet(Integer.parseInt(sections[1]),
                                Integer.parseInt(sections[2]));
                        break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * This is the main method for running and playing the Pacman game.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }

    /**
     * Method that updates the game state based on the user input.
     *
     * @param input The input object containing user input.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }
        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        if(!hasStarted){
            drawStartScreen();
            if (input.wasPressed(Keys.SPACE)){
                hasStarted = true;
            }
        } else if (gameOver){
            drawMessage(END_MESSAGE);
        } else if (playerWin) {
            updateLevel();
        } else {
            updateGame(input);
        }
    }

    /**
     * Method that updates the game state during regular gameplay.
     * It tracks the frenzy game mode and winning status of the player.
     *
     * @param input The input object containing user input.
     */
    private void updateGame(Input input) {
        player.update(input, this);
        updateWalls();
        updateBonus();

        if (isFrenzyMode) {
            if (frenzyCounter == 0) {
                frenzyModeEnd();
            } else {
                frenzyCounter--;
                updateGhosts(ghostsFrenzy);
            }
        } else{
            updateGhosts(ghosts);
        }

        if (player.isDead()) {
            gameOver = true;
        } else{
            if (level == LEVEL_0 && player.reachedScore(WIN_SCORE_0)) {
                playerWin = true;
            } else if (level == LEVEL_1 && player.reachedScore(WIN_SCORE_1)) {
                playerWin = true;
            }
        }
    }

    /**
     * Method that updates the game state when transitioning to a new level.
     */
    private void updateLevel() {
        if (level == LEVEL_1) {
            drawMessage(WIN_MESSAGE);
        } else {
            drawMessage(LEVEL_COMPLETE);
            if (levelCounter == 0) {
                level++;
                resetGame();
            } else {
                levelCounter--;
            }
        }
    }

    /**
     * Method that updates the eatable bonus entities in the game.
     */
    private void updateBonus() {
        for (EatableEntity current : eatableEntities) {
            current.update();
        }

        if (pellet != null) {
            pellet.update();
        }
    }

    /**
     * Method that updates the walls in the game.
     */
    private void updateWalls() {
        for (Wall current : walls) {
            current.update();
        }
    }

    /**
     * Method that updates the ghosts in the game.
     *
     @param ghosts The Arraylist of ghosts to update.
     */
    private void updateGhosts(ArrayList<Ghost> ghosts) {
        for (Ghost current : ghosts) {
            current.update();
        }
    }

    /**
     * Method that checks for collisions between the player and the other entities, and performs
     * corresponding actions in normal and frenzy mode respectively.
     *
     * @param player The player entity in the game.
     */
    public void checkCollisions(Player player){
        Rectangle playerBox = new Rectangle(player.getPosition(), player.getCurrentImage().getWidth(),
                player.getCurrentImage().getHeight());

        if(pellet != null){
            Rectangle pelletBox = pellet.getBoundingBox();
            if(pelletBox.intersects(playerBox) && pellet.isActive()){
                pellet.isEaten();
                frenzyModeStart();
            }
        }

        if (isFrenzyMode) {
            checkGhostCollision(ghostsFrenzy, playerBox);
        } else {
            checkGhostCollision(ghosts, playerBox);
        }

        for (EatableEntity current: eatableEntities){
            Rectangle dotBox = current.getBoundingBox();
            if (current.isActive() && playerBox.intersects(dotBox)) {
                player.incrementScore(current.getPoints());
                current.isEaten();
            }
        }

        for (Wall current: walls){
            Rectangle wallBox = current.getBoundingBox();
            if (playerBox.intersects(wallBox)){
                player.moveBack();
            }
        }
    }

    /**
     * Method that checks for collisions between the ghost and the other entities, and performs
     * corresponding actions in normal and frenzy mode respectively.
     *
     * @param ghosts The Arraylist of ghosts in the game.
     * @param playerBox The rectangle that encloses the player's image.
     */
    private void checkGhostCollision(ArrayList<Ghost> ghosts, Rectangle playerBox){
        for (Ghost current: ghosts) {
            Rectangle ghostBox = current.getBoundingBox();
            if (playerBox.intersects(ghostBox)) {
                if(isFrenzyMode){
                    if(current.isActive()) {
                        player.incrementScore(Ghost.FRENZY_SCORE);
                    }
                }else {
                    player.reduceLives();
                    player.resetPosition();
                }
                current.resetPosition();
            }
            for (Wall wall : walls) {
                Rectangle wallBox = wall.getBoundingBox();
                if (ghostBox.intersects(wallBox)) {
                    current.changeDirection();
                    current.moveBack();
                }
            }
        }
    }

    /**
     * Method that starts the frenzy mode in the game.
     * During frenzy mode, the player and ghosts exhibit special behavior.
     */
    private void frenzyModeStart(){
        isFrenzyMode = true;
        player.setFrenzyMode(true);
        for(Ghost ghost: ghosts){
            ghostsFrenzy.add(new GhostFrenzy(ghost));
        }
    }

    /**
     * Method that ends the frenzy mode in the game.
     * After ending frenzy mode, the game returns to regular gameplay.
     * The frenzy ghosts are reset to their initial positions.
     */
    private void frenzyModeEnd(){
        this.isFrenzyMode = false;
        player.setFrenzyMode(false);
        for(Ghost current: ghosts){
            current.resetPosition();
        }
    }

    /**
     * Method used to draw the start screen title
     * and instructions for each level of the game.
     */
    private void drawStartScreen(){
        if(level == LEVEL_0) {
            TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
            INSTRUCTION_FONT_0.drawString(INSTRUCTION_MESSAGE_0, TITLE_X + INS_X_OFFSET, TITLE_Y + INS_Y_OFFSET);
        } else{
            INSTRUCTION_FONT_1.drawString(INSTRUCTION_MESSAGE_1, INS_X_1, INS_Y_1);
        }
    }

    /**
     * Method used to draw end screen messages.
     *
     * @param message This is the string text which displays on the screen.
     */
    private void drawMessage(String message){
        TITLE_FONT.drawString(message, (Window.getWidth()/2.0 - (TITLE_FONT.getWidth(message)/2.0)),
                (Window.getHeight()/2.0 + (TITLE_FONT_SIZE/2.0)));
    }
}