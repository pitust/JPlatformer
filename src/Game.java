import processing.core.*;

/**
 * Game
 */
public class Game {
    PApplet app;
    public static boolean[][] level;
    PImage background;
    PImage facingLeft;
    PImage facingRight;
    Player player;
    Frames portal;
    Goal flag;
    EightBitText EBT;

    public Game(PApplet app) {
        this.app = app;
        this.EBT = new EightBitText(this.app);
        this.flag = new Goal(50, 300);
    }

    boolean nonce = false;

    public void setup() {
        background = app.loadImage("bg.png");
        facingLeft = app.loadImage("playerLeft.png");
        facingRight = app.loadImage("playerRight.png");
        portal = new Frames(app, new String[]{"portal0.png", "portal1.png", "portal2.png", "portal3.png", "portal4.png",
                "portal5.png"});
        // Auto-gen, use <Z>export

        level = Level.DEFAULT.getLevel();

        player = new Player(level);
        player.init(app);
        app.frameRate(60);
        Util.app = app;
        Util.level = level;
        Util.EBT = EBT;
    }

    void keyup(char c) {
        if (c == 'w')
            player.isWPressed = false;
        if (c == 'd')
            player.isDPressed = false;
        if (c == 'a')
            player.isAPressed = false;
    }

    void key(char c) {
        if (c == 'w')
            player.isWPressed = true;
        if (c == 'd')
            player.isDPressed = true;
        if (c == 'a')
            player.isAPressed = true;
        if (c == 'q') {
            player.entityX = app.mouseX;
            player.entityY = app.mouseY;
        }
        if (c == 'r')
            level = Level.DEFAULT.getLevel();
        if (c == 'z') {
            PApplet.print("new boolean[][] {");
            for (int i = 0; i < level.length; i++) {
                PApplet.print("new boolean[] {");
                for (int j = 0; j < level[i].length; j++) {
                    PApplet.print(level[i][j]);
                    if (j + 1 != level[i].length)
                        PApplet.print(",");
                }
                PApplet.print("}");
                if (i + 1 != level.length)
                    PApplet.print(",");
            }
            PApplet.print("}");
        }
        if (c == 'x') {
            PApplet.print("new boolean[][] {");
            for (int i = 0; i < 38; i++) {
                PApplet.print("new boolean[] {");
                for (int j = 0; j < 21; j++) {
                    PApplet.print("false");
                    if (j + 1 != 21)
                        PApplet.print(",");
                }
                PApplet.print("}");
                if (i + 1 != 38)
                    PApplet.print(",");
            }
            PApplet.print("}");
        }
    }

    void draw() {
        app.image(background, 0, 0, app.width, app.height);
        for (int y = 0; y < 21 && y < level.length; y++) {
            for (int x = 0; x < 38 && x < level[y].length; x++) {
                if (level[y][x]) {
                    boolean isTop = (y - 1 < 0) || !level[y - 1][x];
                    boolean isBottom = (y + 1 >= level.length) || !level[y + 1][x];
                    boolean isLeft = (x - 1 < 0) || !level[y][x - 1];
                    boolean isRight = (x + 1 >= level[y].length) || !level[y][x + 1];
                    String nm = Blocks.DIRT.getNameForDirt(isBottom, isLeft, isRight, isTop);
                    drawBlock(nm, x, y);
                }
            }
        }
        //f.draw(30, 30, 50, 50);
        Util.text("Use WAD keys to move", 20, 20, 10);


        player.draw(app);
        int xa = Util.globX(Util.gridX(app.mouseX));
        int ya = Util.globY(Util.gridY(app.mouseY));
        app.stroke(0);
        app.fill(0, 0, 0, 0);
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
}