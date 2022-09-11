

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.MenuAnimation;
import animation.ShowHiScoresTask;
import animation.Task;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import filereaders.LevelSpecificationReader;
import filereaders.LevelsSetsReader;
import filereaders.MenuSelection;
import game.GameFlow;

/**The Game.
 *
 * @author Hadas Neuman
 *
 */
public class Ass6Game {

    private int screenWidth = 800;
    private int screenHeight = 600;

    /**Runs the game.
     * @param args is string arguments from the user
     */
    public static void main(String[] args) {

        Ass6Game ass6game = new Ass6Game();
        String fileName = null;

        if (args.length > 0) {
            fileName = args[0];
            System.out.println("User Input");
        } else {
            //fileName = "resources/level_sets.txt";
            fileName = "level_sets.txt";
            System.out.println("Default level set file");

        }


        try {
            ass6game.showMenu(fileName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**Shows the menu.
     * @param fileName is the file name
     * @throws IOException if can't open the high score table file
     */
    public void showMenu(String fileName) throws IOException {

        GUI gui = new GUI("Arkanoid", this.screenWidth, this.screenHeight);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);

        File sets = new File(fileName);
        //System.out.println("sets: " + sets.getAbsolutePath());

        GameFlow gf = new GameFlow(gui);

        LevelSpecificationReader lsr = new LevelSpecificationReader();

        //Creating the tasks
        MenuAnimation<Task<Void>> menu = new MenuAnimation<Task<Void>>("Main Menu", keyboard);
        MenuAnimation<Task<Void>> submenu = new MenuAnimation<Task<Void>>("Sub Menu", keyboard);

        Task<Void> quit = new Task<Void>() {
            public Void run() {
                System.exit(0);
                return null;
            }
        };

        Task<Void> runSubMenu = new Task<Void>() {
            //boolean shouldPlayGame = false;
            public Void run() {
                Task<Void> task = null;

                while (task == null) {
                    runner.run(submenu);

                    task = submenu.getStatus();
                    if (!(task == null)) {
                        task.run();
                        submenu.setStop(false);
                    }
                }
                return null;
            }
        };

        // Reading the level sets
        LevelsSetsReader<Task> setReader = new LevelsSetsReader<Task>();
        List<MenuSelection<Task>> levelsSet = null;

        try {
            InputStreamReader is = new InputStreamReader(
                    ClassLoader.getSystemClassLoader().
                    getResourceAsStream(fileName));
            levelsSet = setReader.fromReader(is, gui);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(fileName + " was not found");
            e.printStackTrace();
        }
        /* */

        for (MenuSelection<?> ms : levelsSet) {
            submenu.addSelection(ms.getKey(), ms.getMessage(), (Task<Void>) ms.getReturnVal());
        }


        Animation a = new HighScoresAnimation(gf.getHst());
        Animation ak = new KeyPressStoppableAnimation(keyboard, " ", a);

        menu.addSelection("s", "Start Game", runSubMenu);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(runner, ak));
        //menu.addSelection("h", "High Scores", new ShowHiScoresTask(runner, gf.getHst(), keyboard));
        menu.addSelection("q", "Quit", quit);

        while (true) {
            runner.run(menu);

            Task<Void> task = menu.getStatus();
            if (!(task == null)) {
                task.run();
                menu.setStop(false);
            }
        }


        //gui.close();


    }
}
