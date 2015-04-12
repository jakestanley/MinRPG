package uk.co.jakestanley.minrpg.characters;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by stanners on 11/04/2015.
 */
public class Character {

    private Image   stillUp, stillDown, stillLeft, stillRight,
                    walkUp1, walkUp2, walkDown1, walkDown2,
                    walkLeft1, walkLeft2, walkRight1, walkRight2, test;

    private int facing;

    private float x, y;

    private boolean testing;

    public Character(String resPath){ // testing
        this.testing = true;
        this.x = 50;
        this.y = 50;
        try {
            String imagePath = resPath + "/test.png";

            this.test = new Image(imagePath, false, Image.FILTER_NEAREST);
        } catch (SlickException e){
            e.printStackTrace(); // TODO handle
        }

    }

    public Character(String resPath, int facing){

        this.facing = facing;
        this.testing = false;

        try {
            stillUp     = new Image(resPath + "/stillUp.png", false, Image.FILTER_NEAREST);
            stillDown   = new Image(resPath + "/stillDown.png", false, Image.FILTER_NEAREST);
            stillLeft   = new Image(resPath + "/stillLeft.png", false, Image.FILTER_NEAREST);
            stillRight  = new Image(resPath + "/stillRight.png", false, Image.FILTER_NEAREST);
            walkUp1     = new Image(resPath + "/walkUp1.png", false, Image.FILTER_NEAREST);
            walkUp2     = new Image(resPath + "/walkUp2.png", false, Image.FILTER_NEAREST);
            walkDown1   = new Image(resPath + "/walkDown1.png", false, Image.FILTER_NEAREST);
            walkDown2   = new Image(resPath + "/walkDown2.png", false, Image.FILTER_NEAREST);
            walkLeft1   = new Image(resPath + "/walkLeft1.png", false, Image.FILTER_NEAREST);
            walkLeft2   = new Image(resPath + "/walkLeft2.png", false, Image.FILTER_NEAREST);
            walkRight1  = new Image(resPath + "/walkRight1.png", false, Image.FILTER_NEAREST);
            walkRight2  = new Image(resPath + "/walkRight2.png", false, Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace(); // TODO handle
        }

    }

    public Image getCurrentFrame(){
        return test;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void moveLeft(){
        x--;
    }

    public void moveRight(){
        x++;
    }

    public void moveNorth(){
        y--;
    }

    public void moveSouth(){
        y++;
    }

}
