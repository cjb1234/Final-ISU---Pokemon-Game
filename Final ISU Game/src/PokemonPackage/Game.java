/**
 * Don't forget to change the directory for the images
 * Also I don't understand how delta works
 * */

package PokemonPackage;

import States.GameState;
import States.MenuState;
import States.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{
    //Initializes the variables
    private Display display;
    public  Thread thread;
    private BufferStrategy bs;
    private Graphics g;

    private int width, height;
    private String title;
    private boolean running;

    //States
    private State gameState;
    private State menuState;

    //Game Constructor
    public Game(String title, int width, int height){
        //Sets the variable of this class (purple variables) from the values of the constructor (white variables)
        this.width = width;
        this.height = height;
        this.title = title;

        //Sets running to false
        running = false;

        //Runs the start method
        start();
    }

    //Method for initializing stuff
    public void initialization(){

        //Creates the new display with the needed variables
        display = new Display(title, width, height);
        Assets.initialization();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        State.setState(gameState);
    }

    //Method for updates
    public void update(){
        if(State.getState() != null){
            State.getState().update();
        }
    }

    //Method for rendering
    public void render(){
        /*Buffer Strategy*/
        bs = display.getCanvas().getBufferStrategy();
        //If the bs is null (aka no buffer strategy) it creates a new buffer strategy with triple buffering
        if(bs == null){
            display.getCanvas().createBufferStrategy(2);
            return;     //After creating buffer strategy it returns out of the if statement
        }
        g = bs.getDrawGraphics();       //Creates the graphics with the buffer

        //Clear Screen
        //Gets rid of flickering by adding a clear rectangle that is the same size as the canvas
        g.clearRect(0, 0, width, height);

        //Drawing Stuff
        if(State.getState() != null){
            State.getState().render(g);
        }

        //After Drawing Stuff
        g.dispose();     //Disposes the graphic g
        bs.show();      //Shows the buffer strategy
    }

    //Runnable that runs the update and render methods at 60fps
    public void run(){
        //Runs the initialization method
        initialization();

        //Initializes the variables
        int fps = 60;       //Sets fps to 60; this will be the frames per second
        int oneSecond = 1000000000;     //Sets what one nanosecond is equal to
        int updatesAndFrames = 0;       //Sets the number of updates and frames to 0
        double timePerUpdate = oneSecond / fps;     //Time it has to update to update and render at 60fps
        double delta = 0;
        long currentTime;       //The current time
        long timePassed;        //Time between current and last time
        long lastTime = System.nanoTime();      //Sets last time to the nanoTime (aka nanoseconds) of the system
        long fpsTimer = 0;      //Sets the fpsTimer to 0; timer that goes up to one for fps counter

        //While running is equal to true it does this
        while(running){
            currentTime = System.nanoTime();        //Gets the current time of the system in nanoseconds
            timePassed = currentTime - lastTime;    //Gets the time passed by subtracting the lastTime from the currentTime
            delta += timePassed / timePerUpdate;     //Gets delta by divided the timePassed by the timePerUpdate; used to see if the program must update and render or not
            fpsTimer += timePassed;         //Adds the timePassed to the fpsTimer
            lastTime = currentTime;     //Sets lastTime to the current time

            //If delta is greater than or equal to 1
            if(delta >= 1){
                //Runs the update and render methods
                update();
                render();

                updatesAndFrames++;     //Adds one to updatesAndFrames
                delta--;        //Subtracts one from delta
            }

            //If fpsTimer is greater than or equal to oneNanoSecond
            if(fpsTimer >= oneSecond){
                //Prints the updates and frames per second (should be around 60)
                System.out.println("Updates and Frames per Second: " + updatesAndFrames);
                updatesAndFrames = 0;       //Resets the updates and frames variable
                fpsTimer = 0;      //Resets the timer
            }
        }
        stop();
    }

    //Method that starts the thread
    public void start(){
        //Checks if the program is not running; if running equals true
        if(!running) {
            //If the program is not running set running to true, create the new Thread, and start the thread with thread.start();
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    //Method that stops the thread
    public void stop(){
        //Checks if the program is running; if running equals true
        if(running) {
            //If running equals true it stops the thread with thread.join; must have try catch for some reason
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //Runs a new game; has the value of String title and int width and height; these variable are used in the Game and Display constructor
        new Game("Pokemon", 512, 512 );
    }
}