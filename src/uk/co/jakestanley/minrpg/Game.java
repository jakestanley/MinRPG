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

    public Game(String gameName){
        super(gameName);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        c = new Character("res/concept/pc");
        sky = new Rectangle(0, 0, Display.BASE_WIDTH, Display.SKY_HEIGHT); // wide as the screen
        world = new World(0,0,0,0);
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if(gameContainer.getInput().isKeyDown(Input.KEY_UP)){
//            c.moveNorth();
            world.modOffsetY(1);

        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)){
//            c.moveRight();
            world.modOffsetX(-1); // TODO set some values
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_DOWN)){
//            c.moveSouth();
            world.modOffsetY(-1);
        }

        if(gameContainer.getInput().isKeyDown(Input.KEY_LEFT)){
//            c.moveLeft();
            world.modOffsetX(1);
        }

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        graphics.scale(Main.display.getRenderScale(), Main.display.getRenderScale()); // TODO make more efficient

        graphics.setColor(Color.white);
        graphics.drawString(Items.Names.GAME_BOY, 10, 40);

        world.render(graphics);
        drawSky(graphics);
        drawCharacter(graphics);

    }

    private void drawSky(Graphics graphics){
        graphics.setColor(Color.lightGray);
        graphics.fillRect(0, 0, Main.display.getScreenWidth(), Main.display.getSkyHeight());
    }

    private void drawCharacter(Graphics graphics){
        graphics.drawImage(c.getCurrentFrame(), Main.display.getCenterTileX(), Main.display.getCenterTileY()); // TODO needs to be relative, etc
    }



}
