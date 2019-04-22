
import javax.swing.*;
import java.awt.Graphics;


    //To Do for Me: Turn this into our Menu.

public class MenuState extends State {


    private UIManager uiManager;


    public MenuState(Handler handler) {

        super(handler);

        //the UI manager and the button were stuff from the tutorial of making a 3D button to click on to do start.
        //However, I don't like this UI Button or this Menu system, so I'm going to be redesigning it.

        uiManager = new UIManager(handler);

        handler.getMouseManager().setUIManager(uiManager);


        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {

            @Override

            public void onClick() {

                handler.getMouseManager().setUIManager(null);

                State.setState(handler.getGame().gameState);

            }

        }));

    }


    @Override

    public void tick() {

        uiManager.tick();


        // Temporarily just go directly to the GameState, skip the menu state!

        handler.getMouseManager().setUIManager(null);

        State.setState(handler.getGame().gameState);

    }


    @Override

    public void render(Graphics g) {

        uiManager.render(g);

    }
}