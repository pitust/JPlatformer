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

public class Main extends PApplet {

    public void setup() {
        bg = loadImage("bg.png");
        fl = loadImage("playerLeft.png");
        fr = loadImage("playerRight.png");
        a = new boolean[][] {
            // y:           < 0 >  < 1 >  < 2 >  < 3 >  < 4 >
            new boolean[] { false, false, false, false, false }, // < 0 > :x
            new boolean[] { false, false, false, false, false }, // < 1 > :x
            new boolean[] { false, false, false, false, false }, // < 2 > :x
            new boolean[] { false,  true,  true,  true, false }, // < 3 > :x
            new boolean[] { false, false, false, false, false }, // < 4 > :x
        };
        println(width/50);
        println(height/50);
    }
    static public void main(String[] passedArgs) {
      // It's just a stub, IDK what it does (IDK = I dont know)
        String[] appletArgs = new String[] { "--window-color=#666666", "Main" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
    public void settings() {
        fullScreen();
    }
    PImage bg;
    PImage fl;
    PImage fr;
    boolean[][] a;
    boolean b = true;
    int player_x = 0;
    int player_y = 0;
    int velocity_y = 0;
    float velocity_x = 0;
    boolean isAPressed = false;
    boolean isDPressed = false;
    boolean isWPressed = false;
    boolean isPlFl = true;
    public void keyPressed() {
        if (key == 'w') isWPressed = true;
        if (key == 'd') isDPressed = true;
        if (key == 'a') isAPressed = true;
    }
    public void keyReleased() {
        if (key == 'w') isWPressed = false;
        if (key == 'd') isDPressed = false;
        if (key == 'a') isAPressed = false;
    }
    public void draw() {
        if (isWPressed && b) {
            velocity_y=15;
            b = false;
        }
        if (isAPressed) {
            velocity_x-=7;
        }
        if (isDPressed) {
            velocity_x+=7;
        }
        image(bg, 0, 0, width, height);
        velocity_y--;
        player_x += (int)velocity_x;
        velocity_x = velocity_x / 2;
        int pvy = height - player_y + 100;
        if (pvy / 50 > 0 && pvy / 50 < a.length && player_x / 50 >= 0 && player_x / 50 < a[pvy / 50].length) {
            if (a[(pvy / 50) - 1][player_x / 50] && velocity_y < 0) {
                velocity_y=0;
                b = true;
                player_y -= 25;
                player_y = player_y / 50;
                player_y = player_y * 50;
                player_y += 32;
            }
        }
        player_y -= velocity_y;
        for (int x = 0;x < 38 && x < a.length;x++) {
            for (int y = 0;y < 21 && y < a[x].length;y++) {
                if (a[y][x]) {
                    boolean isTop = (y - 1 < 0) || !a[y - 1][x];
                    boolean isBottom = (y + 1 >= 21) || !a[y + 1][x];
                    boolean isLeft = (x - 1 < 0) || !a[y][x - 1];
                    boolean isRight = (x + 1 >= 38) || !a[y][x + 1];
                    String nm = getNameForDirt(isTop, isLeft, isRight, isBottom);
                    drawBlock(nm, x, 5 - y);
                }
            }
        }
        if (isPlFl) {
            
        } else {
            
        }
        fill(0);
        circle(player_x, player_y + 25, 50);
    }
    public void drawBlock(String name, int x, int y) {
        image(loadImage(name + ".png"), x*50, height - y*50, 50, 50);
    }
    public String getNameForDirt(boolean isTop, boolean isLeft, boolean isRight, boolean isBottom) {
        String s = "dirt";
        if (isTop) {
            s = "grass";
        }
        if (isLeft && !isRight) {
            s += "L";
        }
        if (isRight && !isLeft) {
            s += "R";
        }
        if (isBottom) {
            s += "B";
        }
        return s;
    }
}
