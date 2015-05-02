package uk.co.jakestanley.minrpg;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import uk.co.jakestanley.minrpg.characters.Character;
import uk.co.jakestanley.minrpg.regions.World;
import uk.co.jakestanley.minrpg.values.Items;
import uk.co.jakestanley.minrpg.values.Display;

/**
 * Created by stanners on 11/04/2015.
 */
public class Game extends BasicGame {

    private Character c;
    private Rectangle sky;
    private World world;
    private float shade;
    private boolean polarity;



    public Game(String gameName){
        super(gameName);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        c = new Character("res/concept/pc");
        sky = new Rectangle(0, 0, Display.BASE_WIDTH, Display.SKY_HEIGHT); // wide as the screen
        world = new World(Display.START_CHUNK_X, Display.START_CHUNK_Y); // should start at the centre chunk. only for prototyping now, will change later
        shade = 0.01F;
        polarity = true;
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

        if(gameContainer.getInput().isKeyDown(Input.KEY_UP)){
            world.modY(1);
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)){
            world.modX(-1);
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_DOWN)){
            world.modY(-1);
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_LEFT)){
            world.modX(1);
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.scale(Main.display.getRenderScale(), Main.display.getRenderScale()); // TODO make more efficient

        graphics.setColor(Color.white);
        graphics.drawString(Items.Names.JAMIE, 10, 40);

        drawSky(graphics);
        world.render(graphics);
        drawCharacter(graphics);

        // shading stuff. TODO needs improvement
        Color color = new Color(0, 0, 0, shade); // TODO can probably get rid of this
        graphics.setColor(color);
        graphics.fillRect(0, 0, 900, 600);

        world.renderData(graphics);
//        drawScreenBorder(graphics);

    }

    private void drawSky(Graphics graphics){
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, 0, Main.display.getScreenWidth(), Main.display.getSkyHeight());
    }

    private void drawCharacter(Graphics graphics){
//        graphics.drawImage(c.getCurrentFrame(), Main.display.getCenterTileX(), Main.display.getCenterTileY()); // TODO needs to be relative, etc
        graphics.drawImage(c.getCurrentFrame(), 0, Main.display.getSkyHeight());
    }

    private void drawScreenBorder(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.drawRect(Main.display.RENDER_INITIAL_X, 100, 50, 50);
    }



}
