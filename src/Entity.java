import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap;


import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 
import java.util.*; 
/**
 * Entity
 */
public class Entity {

    PApplet app;
    boolean[][] cur_level;
    public Entity(PApplet p, boolean[][] level) {
        app = p;
        this.cur_level = level;
    }
    public int entityX = 0;
    public int entityY = 0;
    public int velocityY = 0;
    public float velocityX = 0;
    public boolean onGround = false;
    public void init() {

    }
    public void redraw() {
        velocityY--;
        entityX += (int)velocityX;
        velocityX = velocityX / 2;
        if (Util.gridY(entityY) > 0 && Util.gridY(entityY) < cur_level.length && Util.gridX(entityX) >= 0 && Util.gridX(entityX) < cur_level[Util.gridY(entityY)].length) {
            if (cur_level[Util.gridY(entityY)][Util.gridX(entityX)] && velocityY < 0) {
                velocityY=0;
                onGround = true;
            } else onGround = false;
        } else onGround = false;
        entityY -= velocityY;
    }
}