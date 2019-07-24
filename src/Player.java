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
 * Player
 */
public class Player extends Entity {
    public void redraw(PApplet app) {
        super.redraw(app);
        if (isWPressed && onGround) {
            velocityY = 30;
            onGround = false;
        }
        if (isAPressed) {
            velocityX -= 7;
            isPlFl = true;
        }
        if (isDPressed) {
            velocityX += 7;
            isPlFl = false;
        }

        if (isPlFl) {
            app.image(fl, entityX, entityY - 80, 50, 70);
        } else {
            app.image(fr, entityX, entityY - 80, 50, 70);
        }
    }

    public boolean isAPressed = false;
    public boolean isDPressed = false;
    public boolean isWPressed = false;
    PImage fl;
    PImage fr;
    boolean isPlFl = true;

    public void init(PApplet app) {
        fl = app.loadImage("playerLeft.png");
        fr = app.loadImage("playerRight.png");
    }

    public Player(boolean[][] level) {
        super( level);
    }
}