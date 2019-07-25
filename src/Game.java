import processing.core.*;

/**
 * Game
 */
public class Game {
    PApplet app;
    public static Blocks[][] level;
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
    int cursorType = 0;
    static public boolean godMode = false;
    // This is actually downstream from JPlatformer.java
    public static boolean isRelease;
    private Blocks cursorType() {
        cursorType = cursorType % 6;
        switch (cursorType) {
        case 0:
            return Blocks.DIRT;

        case 1:
            return Blocks.WIFI;

        case 2:
            return Blocks.COIN;

        case 3:
            return Blocks.MUD;

        case 4:
            return Blocks.SPIKE;

        case 5:
            return Blocks.TARGETFLAG;

        default:
            return Blocks.DIRT;
        }
    }
    Enemy e;
    public void setup() {
        background = app.loadImage("bg.png");
        facingLeft = app.loadImage("playerLeft.png");
        facingRight = app.loadImage("playerRight.png");
        portal = new Frames(app, new String[] { "portal0.png", "portal1.png", "portal2.png", "portal3.png",
                "portal4.png", "portal5.png" });
        // Auto-gen, use <Z>export
        Level l = Level.DEFAULT;
        Util.level = l.getLevel();

        player = new Player(l);
        e = new Enemy(l, player);
        player.init(app);
        e.init(app);
        app.frameRate(60);
        Util.app = app;
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
        if (c == 'q' && godMode) {
            player.entityX = app.mouseX;
            player.entityY = app.mouseY;
        }
        if (c == 'r') {
            Util.level = Level.DEFAULT.getLevel();
            player.entityX = Level.DEFAULT.getSpawnX();
            player.entityY = Level.DEFAULT.getSpawnY();
        }
        if (c == 'f' && godMode) {
            // Goal g = new Goal(app.mouseX, app.mouseY);
        }
        if (c == 'z' && godMode) {
            PApplet.print("new Blocks[][] {");
            for (int i = 0; i < Util.level.length; i++) {
                PApplet.print("new Blocks[] {");
                for (int j = 0; j < Util.level[i].length; j++) {
                    PApplet.print(Util.level[i][j]);
                    if (j + 1 != Util.level[i].length)
                        PApplet.print(",");
                }
                PApplet.print("}");
                if (i + 1 != Util.level.length)
                    PApplet.print(",");
            }
            PApplet.print("}");
        }
        if (c == 'x' && godMode) {
            PApplet.print("new Blocks[][] {");
            for (int i = 0; i < 38; i++) {
                PApplet.print("new Blocks[] {");
                for (int j = 0; j < 21; j++) {
                    PApplet.print("Blocks.AIR");
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
        if (godMode && isRelease) {
            app.image(app.loadImage(cursorType().getName() + ".png"), app.width - 50, app.height - 50, 50, 50);
            String cursorName = cursorType().getName().replaceAll("([A-Z])", " $1").toUpperCase();
            EightBitText.text(cursorName, app.width - (16 * cursorName.length()) - 50, app.height - 35, 15);
        } else if (!godMode && isRelease) {
            String cursorName = String.valueOf(app.mouseX) + "\bcros" + String.valueOf(app.mouseY) +  "  " + cursorType().getName().replaceAll("([A-Z])", " $1").toUpperCase();
            EightBitText.text(cursorName, app.width - (16 * cursorName.length()) - 50, app.height - 35, 15);
        } else if (godMode) {
            app.image(app.loadImage(cursorType().getName() + ".png"), app.width - 50, app.height - 50, 50, 50);
            String cursorName = String.valueOf(app.mouseX) + "\bcros" + String.valueOf(app.mouseY) +  "  " + cursorType().getName().replaceAll("([A-Z])", " $1").toUpperCase();
            EightBitText.text(cursorName, app.width - (16 * cursorName.length()) - 50, app.height - 35, 15);
        }
        for (int y = 0; y < 21 && y < Util.level.length; y++) {
            for (int x = 0; x < 38 && x < Util.level[y].length; x++) {
                if (Util.level[y][x] != Blocks.AIR && Util.level[y][x] == Blocks.DIRT) {
                    boolean isTop = (y - 1 < 0) || Util.level[y - 1][x] == Blocks.AIR;
                    boolean isBottom = (y + 1 >= Util.level.length) || Util.level[y + 1][x] == Blocks.AIR;
                    boolean isLeft = (x - 1 < 0) || Util.level[y][x - 1] == Blocks.AIR;
                    boolean isRight = (x + 1 >= Util.level[y].length) || Util.level[y][x + 1] == Blocks.AIR;
                    String nm = Blocks.DIRT.getNameForDirt(isBottom, isLeft, isRight, isTop);
                    drawBlock(nm, x, y);
                } else if (Util.level[y][x] != Blocks.AIR) {
                    drawBlock(Util.level[y][x].getName(), x, y);
                }
            }
        }
        // f.draw(30, 30, 50, 50);
        EightBitText.text("Use WAD keys to move", 20, 20, 10);

        player.draw(app);
        e.draw(app);
        int xa = Util.globX(Util.gridX(app.mouseX));
        int ya = Util.globY(Util.gridY(app.mouseY));
        app.stroke(0);
        app.fill(0, 0, 0, 0);
        app.rect(xa, ya, 50, 50);
        if (app.mousePressed && !nonce && godMode) {
            if (app.mouseButton == PApplet.RIGHT) {
                cursorType += 1;
            } else {
                ya = Util.gridY(app.mouseY);
                xa = Util.gridX(app.mouseX);
                if (ya >= 0 && ya < Util.level.length && xa >= 0 && xa < Util.level[ya].length)
                    Util.level[ya][xa] = Util.level[ya][xa] == Blocks.AIR ? cursorType() : Blocks.AIR;
            }
            nonce = true;
        } else
            nonce = app.mousePressed;
    }

    void drawBlock(String name, int x, int y) {
        app.image(app.loadImage(name + ".png"), Util.globX(x), Util.globY(y), 50, 50);
    }
}