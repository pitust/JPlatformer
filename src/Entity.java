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

    boolean[][] cur_level;

    public Entity(boolean[][] level) {
        this.cur_level = level;
    }

    public int entityX = 0;
    public int entityY = 0;
    public int velocityY = 0;
    public float velocityX = 0;
    public boolean onGround = false;

    public void init(PApplet app) {

    }

    public void redraw(PApplet app) {
        velocityY -= 3;
        velocityY = Math.max(Math.min(velocityY, 49), -49);
        velocityX = Math.max(Math.min(velocityX, 49), -49);
        velocityX = (int)(velocityX / 1.1);
        entityX += (int) velocityX;
        
        if (Util.gridY(entityY) > 0 && Util.gridY(entityY) < cur_level.length && Util.gridX(entityX) >= 0
                && Util.gridX(entityX) < cur_level[Util.gridY(entityY)].length) {
            if (cur_level[Util.gridY(entityY)][Util.gridX(entityX)] && velocityY <= 0) {
                if (cur_level[Util.gridY(entityY) - 1][Util.gridX(entityX) + 1] || cur_level[Util.gridY(entityY) - 1][Util.gridX(entityX)]) {
                    int i = (int)(Math.abs(velocityX) / velocityX);
                    entityX -= velocityX + i * 2;
                    velocityX = 0;
                }
                if (cur_level[Util.gridY(entityY) - 1][Util.gridX(entityX)]) {
                    Util.isDead = true;
                }
                velocityY = 0;
                onGround = true;
                velocityX *= 2;
                velocityX /= 3;
                entityY = Util.globY(Util.gridY(entityY));
            } else
                onGround = false;
        } else {
            onGround = false;
        }
        entityY -= velocityY;
        
    }
}