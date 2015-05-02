package uk.co.jakestanley.minrpg;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jakestanley.minrpg.characters.Player;
import uk.co.jakestanley.minrpg.regions.World;
import uk.co.jakestanley.minrpg.values.Display;
import uk.co.jakestanley.minrpg.values.GameConstants;
import uk.co.jakestanley.minrpg.values.Items;

/**
 * Created by stanners on 11/04/2015.
 */
public class Game extends BasicGame {

    private Player player;
    private Rectangle sky;
    private World world;
    private float shade;
    private boolean polarity;



    public Game(String gameName){
        super(gameName);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        player = new Player(GameConstants.NEW_CHARACTER_WORLD_X, GameConstants.NEW_CHARACTER_WORLD_Y);
        sky = new Rectangle(0, 0, Display.BASE_WIDTH, Display.SKY_HEIGHT); // wide as the screen
        world = new World(Display.START_CHUNK_X, Display.START_CHUNK_Y); // should start at the centre chunk. only for prototyping now, will change later
        shade = 0.01F;
        polarity = true;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        if(gameContainer.getInput().isKeyDown(Input.KEY_UP) || gameContainer.getInput().isKeyDown(Input.KEY_W)){
            world.modY(1);
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_RIGHT) || gameContainer.getInput().isKeyDown(Input.KEY_D)){
            world.modX(-1);
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_DOWN) || gameContainer.getInput().isKeyDown(Input.KEY_S)){
            world.modY(-1);
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_LEFT) || gameContainer.getInput().isKeyDown(Input.KEY_A)){
            world.modX(1);
        }

//        if(gameContainer.getInput().isKeyDown(Input.KEY_E)){
            // If interact key pressed
//        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_E)){
            System.out.println("Interact key pressed");
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        // SET GRAPHICS SCALE TO DRAW GAME ELEMENTS
        float scaleUp = Display.DEFAULT_SCALE;
        graphics.scale(scaleUp, scaleUp);

        graphics.setColor(Color.white);
        graphics.drawString(Items.Names.JAMIE, 10, 40);

        world.render(graphics);
        drawSky(graphics); // TODO enhance
        player.render(graphics);


        // render player




        // shading stuff. TODO needs improvement
        Color color = new Color(0, 0, 0, shade); // TODO can probably get rid of this
        graphics.setColor(color);
        graphics.fillRect(0, 0, 900, 600);

        // DRAW DEBUG BOXES
        drawScreenBorder(graphics);

        // SET GRAPHICS SCALE TO DRAW DEBUG STRINGS
        float scaleDown = 0.25F;
        graphics.scale(scaleDown, scaleDown);

        // DRAW DEBUG STRINGS
        world.renderData(graphics);

    }

    private void drawSky(Graphics graphics){
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, 0, Display.BASE_WIDTH, Display.SKY_HEIGHT  );
    }

    private void drawCharacters(Graphics graphics){ // TODO this
        player.render(graphics);
    }

    private void drawScreenBorder(Graphics graphics){
        graphics.setColor(Color.red); // TODO sort
        graphics.drawRect(0, 0, 49, 49);
    }



}
