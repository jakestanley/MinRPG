package uk.co.jakestanley.minrpg;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jakestanley.minrpg.characters.Player;
import uk.co.jakestanley.minrpg.regions.World;
import uk.co.jakestanley.minrpg.values.Display;
import uk.co.jakestanley.minrpg.values.GameConstants;
import uk.co.jakestanley.minrpg.values.Items;

import java.util.ArrayList;

/**
 * Created by stanners on 11/04/2015.
 */
public class Game extends BasicGame {

    private Player player;
    private Rectangle sky;
    private World world;
    private float shade;
    private boolean polarity;

    // bounding box stuff
//    private Rectangle playerBoundingBox;
    private Line playerBoundingNorth, playerBoundingEast, playerBoundingSouth, playerBoundingWest;
    private ArrayList<Rectangle> boundingBoxes;


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

        // getting necessary collision detection information for the first time
//        playerBoundingBox = player.getBoundingBox();

        // get bounding lines
        playerBoundingNorth = player.getBoundingNorth();
        playerBoundingEast  = player.getBoundingEast();
        playerBoundingSouth = player.getBoundingSouth();
        playerBoundingWest  = player.getBoundingWest();

        // get all "visible" bounding boxes
        boundingBoxes = world.getBoundingBoxes();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        boolean canMoveNorth = true;
        boolean canMoveEast = true;
        boolean canMoveSouth = true;
        boolean canMoveWest = true;

        for(Rectangle box : boundingBoxes){
            if(box.intersects(playerBoundingNorth)){
                canMoveNorth = false;
            }
            if(box.intersects(playerBoundingEast)){
                canMoveEast = false;
            }
            if(box.intersects(playerBoundingSouth)){
                canMoveSouth = false;
            }
            if(box.intersects(playerBoundingWest)){
                canMoveWest = false;
            }
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_UP) || gameContainer.getInput().isKeyDown(Input.KEY_W)){
            if(canMoveNorth){
                world.modY(1);
            }
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_RIGHT) || gameContainer.getInput().isKeyDown(Input.KEY_D)){
            if(canMoveEast){
                world.modX(-1);
            }
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_DOWN) || gameContainer.getInput().isKeyDown(Input.KEY_S)){
            if(canMoveSouth){
                world.modY(-1);
            }
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_LEFT) || gameContainer.getInput().isKeyDown(Input.KEY_A)){
            if(canMoveWest){
                world.modX(1);
            }
        }

        if(gameContainer.getInput().isKeyPressed(Input.KEY_E)){
            System.out.println("Interact key pressed");
        }

        // get new collision detection information
//        playerBoundingBox = player.getBoundingBox();

        // get bounding lines
        playerBoundingNorth = player.getBoundingNorth();
        playerBoundingEast  = player.getBoundingEast();
        playerBoundingSouth = player.getBoundingSouth();
        playerBoundingWest  = player.getBoundingWest();
        boundingBoxes = world.getBoundingBoxes();

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

        // render player
        player.render(graphics);
        graphics.setColor(Color.red);

        // shading stuff. TODO needs improvement
        Color color = new Color(0, 0, 0, shade); // TODO can probably get rid of this
        graphics.setColor(color);
        graphics.fillRect(0, 0, 900, 600);

        // DRAW BOUNDING BOXES
//        drawScreenBorder(graphics);
        drawBoundingBoxes(graphics);

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

    private void drawBoundingBoxes(Graphics graphics){
        // render player collision box in yellow
        graphics.setColor(Color.yellow);
//        graphics.draw(playerBoundingBox);
        graphics.draw(playerBoundingNorth);
        graphics.draw(playerBoundingEast);
        graphics.draw(playerBoundingSouth);
        graphics.draw(playerBoundingWest);

        // render other collision boxes in white
        graphics.setColor(Color.white);
        for(Rectangle boundingBox : boundingBoxes){
            graphics.draw(boundingBox);
        }
    }

}
