package game;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Paddle;
import graphics.Point;
import indicators.LevelIndicator;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;


/**
 * Game class.
 *
 * @author Hadas Neuman
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private int screenWidth;
    private int screenHeight;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation li;
    private Paddle paddle;


    /**A constructor.
     *
     * @param ar is the animation runner
     * @param ks is the Keyboard Sensor
     * @param gui is the graphic user interface
     * @param score is the score counter
     * @param lives is the lives counter
     * @param li is the level information
     */
    public GameLevel(AnimationRunner ar, KeyboardSensor ks,
            GUI gui, Counter score, Counter lives, LevelInformation li) {
        this.runner = ar;
        this.keyboard = ks;
        this.environment = new GameEnvironment();
        this.screenHeight = 600;
        this.screenWidth = 800;
        this.gui = gui;
        this.blocksCounter = new Counter(li.numberOfBlocksToRemove());
        this.ballsCounter = new Counter(0);
        this.score = score;
        this.lives = lives;
        this.sprites = new SpriteCollection();;
        this.running = true;
        this.li = li;
    }

    /**
     * Add a Collidable to the collidable list.
     *
     *@param c is a collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a Sprite to the sprites list.
     *
     *@param s is a sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove a Collidable from the collidable list.
     *
     *@param c is a collidable to add
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove a Sprite from the sprites list.
     *
     *@param s is a sprite to add
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball and Paddle and add them to the game.
     *
     */
    public void initialize() {

        //Adding the background
        this.sprites.addSprite(this.li.getBackground());

        BlockRemover blockR = new BlockRemover(this, this.blocksCounter);
        BallRemover ballR = new BallRemover(this, this.ballsCounter);

        ScoreTrackingListener stl = new ScoreTrackingListener(this, this.score);

        //Creating the score indicator
        ScoreIndicator si = new ScoreIndicator(this.score);
        si.addToGame(this);


        //Creating the lives indicator
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        livesIndicator.addToGame(this);

        //Creating the level indicator
        LevelIndicator leveli = new LevelIndicator(this.li.levelName());
        leveli.addToGame(this);

        int borderWidth = 10;


        //make the top border
        Point blockP = new Point(0, 30);
        java.awt.Color[] bColors = {java.awt.Color.gray};
        Block block = new Block(blockP, screenWidth, borderWidth, bColors, 0);
        block.addToGame(this);

        //make the right border
        blockP = new Point((screenWidth - borderWidth), borderWidth + 20);
        block = new Block(blockP, borderWidth, screenHeight - borderWidth, bColors, 0);
        block.addToGame(this);

        //make the bottom border
        //***The end-game region***
        blockP = new Point(borderWidth, screenHeight);
        block = new Block(blockP, (double) screenWidth - borderWidth * 2, borderWidth, bColors, 0);
        block.addHitListener(ballR);
        block.addToGame(this);

        //make the left border
        blockP = new Point(0, borderWidth + 20);
        block = new Block(blockP, 10, screenHeight - borderWidth, bColors, 0);
        block.addToGame(this);

        // Adding the blocks to the game
        for (Block b: this.li.blocks()) {
            Block tempBlock = new Block(b);
            tempBlock.addToGame(this);
            tempBlock.addHitListener(blockR);
            tempBlock.addHitListener(stl);
        }

        //Creating the paddle
        this.paddle = new Paddle(this.gui, this.screenWidth, this.screenHeight,
                this.li.paddleWidth(), this.li.paddleSpeed());
    }

    /**Sets the blocks counter.
     *
     * @param counter is the block counter
     */
    public void setBlockCounter(Counter counter) {
        this.blocksCounter = counter;
    }

    /**Sets the score counter.
     *
     * @param counter is the score counter
     */
    public void setScore(Counter counter) {
        this.score = counter;
    }

    /**Sets the balls counter.
     *
     * @param counter is the balls counter
     */
    public void setBallCounter(Counter counter) {
        this.ballsCounter = counter;
    }

    /**
     * Run the game -- start the animation loop.
     *
     */
    public void run() {
        while ((this.lives.getValue() > 0) && (this.blocksCounter.getValue() > 0)) {
            playOneTurn();
        }
        gui.close();
        return;
    }


    /**
     * Runs one turn of the game.
     */
    public void playOneTurn() {

        this.paddle = new Paddle(this.gui, this.screenWidth, this.screenHeight,
                this.li.paddleWidth(), this.li.paddleSpeed());
        this.paddle.addToGame(this);
        this.createBallsOnTopOfPaddle(); // or a similar method

        this.running = true;

        // use the runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));

        this.runner.run(this);

        //removing the paddle
        paddle.removeFromGame(this);
    }

    /**Creates balls according to the level information.
     *
     */
    private void createBallsOnTopOfPaddle() {

        BallRemover ballR = new BallRemover(this, this.ballsCounter);

        for (Velocity vel: this.li.initialBallVelocities()) {
            Point center = new Point((this.screenWidth / 2), (this.screenHeight - 30));
            Ball ball = new Ball(center, 5, java.awt.Color.white, vel, this.environment);
            ball.addToGame(this);
            this.ballsCounter.increase(1);
            ballR.setCounter(this.ballsCounter);
        }
    }

    /**Draws one frame.
     *
     * @param d is the drawing surface.
     * @param dt is the difference in time
     */
    public void doOneFrame(DrawSurface d, double dt) {

        // drawing the background
        d.setColor(java.awt.Color.decode("35"));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //pause?
        if (this.keyboard.isPressed("p")) {
            Animation a = new PauseScreen();
            Animation ak = new KeyPressStoppableAnimation(this.keyboard, " ", a);
            this.runner.run(ak);
            //this.runner.run(new PauseScreen(this.keyboard));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        if (this.blocksCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }

        if (this.ballsCounter.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
        }
    }

    /**Gets the block counter.
     *
     * @return the blocks counter.
     */
    public Counter getBlockCounter() {
        return this.blocksCounter;
    }

    /**Indicates is the game should be stopped.
     *
     * @return true is the game should be stopped, or false otherwise
     */
    public boolean shouldStop() {

        return (!(this.running));
    }
}