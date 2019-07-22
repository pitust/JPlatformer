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
        print("HI");
        bg = loadImage("bg.png");
        a = new boolean[][] {
            // y:           < 0 >  < 1 >  < 2 >  < 3 >  < 4 >
            new boolean[] { false, false, false, false, false }, // < 0 > :x
            new boolean[] { false, false, false, false, false }, // < 1 > :x
            new boolean[] { false, false, false, false, false }, // < 2 > :x
            new boolean[] { false,  true,  true,  true, false }, // < 3 > :x
            new boolean[] { false,  true,  true, false, false }, // < 4 > :x
        };
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
    public void draw() {
        image(bg, 0, 0, width, height);
        for (int x = 0;x < 5;x++) {
            for (int y = 0;y < 5;y++) {
                if (a[y][x]) {
                    boolean isTop = (y - 1 < 0) || !a[y - 1][x];
                    boolean isBottom = (y + 1 >= 5) || !a[y + 1][x];
                    boolean isLeft = (x - 1 < 0) || !a[y][x - 1];
                    boolean isRight = (x + 1 >= 5) || !a[y][x + 1];
                    String nm = getNameForDirt(isTop, isLeft, isRight, isBottom);
                    print("X: ");
                    print(x);
                    print(" Y: ");
                    print(y);
                    print(" Name: ");
                    println(nm);
                    drawBlock(nm, x, 5 - y);
                }
            }
        }
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
