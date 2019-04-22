import java.awt.*;

public abstract class State {

    //I kind of want to turn this entire thing plus the MenuState and GameState into the private enum lest we used
    //to have. I'll try that later on tonight.



    private static State currentState = null;

    //setState and getState are basically the StateManager. The game class calls them to get the current state.


    public static void setState(State state){

        currentState = state;

    }



    public static State getState(){

        return currentState;

    }

    //CLASS

    protected Handler handler;



    public State(Handler handler){

        this.handler = handler;

    }



    public abstract void tick();



    public abstract void render(Graphics g);



}