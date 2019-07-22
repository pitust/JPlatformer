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
    boolean[][] a;
    boolean b = true;
    int plx = 0;
    int ply = 0;
    int vely = 0;
    float velx = 0;
    boolean isA = false;
    boolean isD = false;
    boolean isW = false;
    public void keyPressed() {
        if (key == 'w') isW = true;
        if (key == 'd') isD = true;
        if (key == 'a') isA = true;
    }
    public void keyReleased() {
        if (key == 'w') isW = false;
        if (key == 'd') isD = false;
        if (key == 'a') isA = false;
    }
    public void draw() {
        if (isW && b) {
            vely=15;
            b = false;
        }
        if (isA) {
            velx-=7;
        }
        if (isD) {
            velx+=7;
        }
        image(bg, 0, 0, width, height);
        vely--;
        plx += (int)velx;
        velx = velx / 2;
        int pvy = height - ply + 100;
        if (pvy / 50 > 0 && pvy / 50 < a.length && plx / 50 >= 0 && plx / 50 < a[pvy / 50].length) {
            if (a[(pvy / 50) - 1][plx / 50] && vely < 0) {
                vely=0;
                b = true;
                ply -= 25;
                ply = ply / 50;
                ply = ply * 50;
                ply += 32;
            }
        }
        ply -= vely;
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
        fill(0);
        circle(plx, ply + 25, 50);
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
