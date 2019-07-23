import processing.core.*;

import java.util.ArrayList;

/**
 * Game
 */
public class Game {
    PApplet app;
    boolean[][] level;
    PImage bg;
    PImage fl;
    PImage fr;
    Player p;
    Frames f;
    boolean b = true;
    public Game(PApplet app) {
        this.app = app;
    }
    boolean nonce = false;
    ArrayList<Item> items = new ArrayList<Item>();
    public void setup() {
        bg = app.loadImage("bg.png");
        fl = app.loadImage("playerLeft.png");
        fr = app.loadImage("playerRight.png");
        f = new Frames(app, new String[] { "portal0.png", "portal1.png", "portal2.png", "portal3.png", "portal4.png",
                "portal5.png" });
        // Auto-gen, use <Z>export
        // TODO: add a auto/Levels.java file
        items.add(new Coin(10, 10));
        level = new boolean[][] {
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false },
                new boolean[] { false, false, false, false, false, false, false, false, false, false, false, false,
                        false, false, false, false, false, false, false, false, false } };
        p = new Player(level);
        p.init(app);
        app.println(app.width / 50);
        app.println(app.height / 50);
        app.frameRate(60);
        Util.app = app;
        Util.level = level;
    }
    void keyup(char c) {
        if (c == 'w')
            p.isWPressed = false;
        if (c == 'd')
            p.isDPressed = false;
        if (c == 'a')
            p.isAPressed = false;
    }
    void key(char c) {
        if (c == 'w')
            p.isWPressed = true;
        if (c == 'd')
            p.isDPressed = true;
        if (c == 'a')
            p.isAPressed = true;
        if (c == 'q') {
            p.entityX = app.mouseX;
            p.entityY = app.mouseY;
        }
        if (c == 'z') {
            app.print("new boolean[][] {");
            for (int i = 0; i < level.length; i++) {
                app.print("new boolean[] {");
                for (int j = 0; j < level[i].length; j++) {
                    app.print(level[i][j]);
                    if (j + 1 != level[i].length)
                        app.print(",");
                }
                app.print("}");
                if (i + 1 != level.length)
                    app.print(",");
            }
            app.print("}");
        }
        if (c == 'x') {
            app.print("new boolean[][] {");
            for (int i = 0; i < 38; i++) {
                app.print("new boolean[] {");
                for (int j = 0; j < 21; j++) {
                    app.print("false");
                    if (j + 1 != 21)
                        app.print(",");
                }
                app.print("}");
                if (i + 1 != 38)
                    app.print(",");
            }
            app.print("}");
        }
    }
    void draw() {
        app.image(bg, 0, 0, app.width,app. height);
        for (int y = 0; y < 21 && y < level.length; y++) {
            for (int x = 0; x < 38 && x < level[y].length; x++) {
                if (level[y][x]) {
                    boolean isTop = (y - 1 < 0) || !level[y - 1][x];
                    boolean isBottom = (y + 1 >= level.length) || !level[y + 1][x];
                    boolean isLeft = (x - 1 < 0) || !level[y][x - 1];
                    boolean isRight = (x + 1 >= level[y].length) || !level[y][x + 1];
                    String nm = getNameForDirt(isBottom, isLeft, isRight, isTop);
                    drawBlock(nm, x, y);
                }
            }
        }
        for(int i = 0; i < items.size();i++){
            items.get(i).draw(app);
        }
        f.draw(30, 30, 50, 50);
        p.draw(app);
        int xa = Util.globX(Util.gridX(app.mouseX));
        int ya = Util.globY(Util.gridY(app.mouseY));
        app.stroke(0);
        app.fill(0,0,0,0);
        app.rect(xa, ya, 50, 50);
        if (app.mousePressed && !nonce) {
            ya = Util.gridY(app.mouseY);
            xa = Util.gridX(app.mouseX);
            if (ya >= 0 && ya < level.length && xa >= 0 && xa < level[ya].length)
                level[ya][xa] = !level[ya][xa];
            nonce = true;
        } else
            nonce = app.mousePressed;
    }
    void drawBlock(String name, int x, int y) {
        app.image(app.loadImage(name + ".png"), Util.globX(x), Util.globY(y), 50, 50);
    }
    String getNameForDirt(boolean isBottom, boolean isLeft, boolean isRight, boolean isTop) {
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