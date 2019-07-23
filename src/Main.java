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

    Player p;
    Frames f;

    public void setup() {
        bg = loadImage("bg.png");
        fl = loadImage("playerLeft.png");
        fr = loadImage("playerRight.png");
        f = new Frames(this, new String[]{
                "portal0.png",
                "portal1.png",
                "portal2.png",
                "portal3.png",
                "portal4.png",
                "portal5.png"
        });
        // Auto-gen, use <Z>export
        a = new boolean[][]{new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}};
        p = new Player(this, a);
        p.init();
        println(width / 50);
        println(height / 50);
        frameRate(60);
        Util.app = this;
        Util.level = a;
        println(Util.globX(Util.gridX(100)));
    }

    static public void main(String[] passedArgs) {
        // It's just a stub, IDK what it does (IDK = I dont know)
        String[] appletArgs = new String[]{"--window-color=#666666", "Main"};
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

    public int player_x = 0;
    public int player_y = 0;
    public int velocity_y = 0;
    public float velocity_x = 0;

    boolean nonce = false;

    public void keyPressed() {
        if (key == 'w')
            p.isWPressed = true;
        if (key == 'd')
            p.isDPressed = true;
        if (key == 'a')
            p.isAPressed = true;
        if (key == 'q') {
            p.entityX = mouseX;
            p.entityY = mouseY;
        }
        if (key == 'z') {
            print("new boolean[][] {");
            for (int i = 0; i < a.length; i++) {
                print("new boolean[] {");
                for (int j = 0; j < a[i].length; j++) {
                    print(a[i][j]);
                    if (j + 1 != a[i].length) print(",");
                }
                print("}");
                if (i + 1 != a.length) print(",");
            }
            print("}");
        }
        if (key == 'x') {
            print("new boolean[][] {");
            for (int i = 0; i < 38; i++) {
                print("new boolean[] {");
                for (int j = 0; j < 21; j++) {
                    print("false");
                    if (j + 1 != 21) print(",");
                }
                print("}");
                if (i + 1 != 38) print(",");
            }
            print("}");
        }
    }

    public void keyReleased() {
        if (key == 'w')
            p.isWPressed = false;
        if (key == 'd')
            p.isDPressed = false;
        if (key == 'a')
            p.isAPressed = false;
    }

    public void draw() {
        image(bg, 0, 0, width, height);
        for (int y = 0; y < 21 && y < a.length; y++) {
            for (int x = 0; x < 38 && x < a[y].length; x++) {
                if (a[y][x]) {
                    boolean isTop = (y - 1 < 0) || !a[y - 1][x];
                    boolean isBottom = (y + 1 >= a.length) || !a[y + 1][x];
                    boolean isLeft = (x - 1 < 0) || !a[y][x - 1];
                    boolean isRight = (x + 1 >= a[y].length) || !a[y][x + 1];
                    String nm = getNameForDirt(isBottom, isLeft, isRight, isTop);
                    drawBlock(nm, x, y);
                }
            }
        }
        f.draw(30, 30, 50, 50);
        p.redraw();
        int xa = Util.globX(Util.gridX(mouseX));
        int ya = Util.globY(Util.gridY(mouseY));
        stroke(0);
        fill(0, 0, 0, 0);
        rect(xa, ya, 50, 50);
        if (mousePressed && !nonce) {
            ya = Util.gridY(mouseY);
            xa = Util.gridX(mouseX);
            if (ya >= 0 && ya < a.length && xa >= 0 && xa < a[ya].length)
                a[ya][xa] = !a[ya][xa];
            nonce = true;
        } else
            nonce = mousePressed;
    }

    public void drawBlock(String name, int x, int y) {
        image(loadImage(name + ".png"), Util.globX(x), Util.globY(y), 50, 50);
    }

    public String getNameForDirt(boolean isBottom, boolean isLeft, boolean isRight, boolean isTop) {
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