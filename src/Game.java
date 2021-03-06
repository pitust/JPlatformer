import processing.core.*;

import java.io.File;

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
    Portal portal;
    Goal flag;
    boolean player_won;
    File BgMusic;
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
        //Just try to find a way to make the sound work
        BgMusic = new File("data/Background_basic_music.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(BgMusic);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        // Auto-gen, use <Z>export
        Level l = Level.DEFAULT;
        Util.level = l.getLevel();

        player = new Player(l);
        portal = new Portal(100, 150, 300, 150, player);
        e = new Enemy(l, player);
        player.init(app);
        portal.init(app);
        e.init(app);
        app.frameRate(60);
        Util.app = app;
        Util.level = level;
        player_won = false;
    }
    public boolean check_collision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2)
    {
        int bX1 = x1 + w1;
        int bY1 = y1 - h1;
        int bX2 = x2 + w2;
        int bY2 = y2 - h2;
        if(x1<=x2 && x2 <= bX1 || x1 <= bX2 && bX2 <= bX1 || x2 <= x1 && x1 <= bX2 || x2 <= bX1 && bX1 <= bX2) //Check vertical collision
        {
            if(y1<=y2 && y2 <= bY1 || y1 <= bY2 && bY2 <= bY1 || y2 <= y1 && y1 <= bY2 || y2 <= bY1 && bY1 <= bY2) //Check horizontal collision
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
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
        if (c == 'c' && godMode) {
            portal.xA = Util.globX(Util.gridX(app.mouseX));
            portal.yA = Util.globY(Util.gridY(app.mouseY));
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
        if (this.player_won) {
            //Test
            background = app.loadImage("Desert.png");
            app.image(background, 0, 0, app.width, app.height);
            player.entityX = 100;
            player.entityY = 200;
        }
        else {
            app.image(background, 0, 0, app.width, app.height);
            if (godMode && isRelease) {
                app.image(app.loadImage(cursorType().getName() + ".png"), app.width - 50, app.height - 50, 50, 50);
                String cursorName = cursorType().getName().replaceAll("([A-Z])", " $1").toUpperCase();
                EightBitText.text(cursorName, app.width - (16 * cursorName.length()) - 50, app.height - 35, 15);
            } else if (!godMode && isRelease) {
                String cursorName = String.valueOf(app.mouseX) + "\bcros" + String.valueOf(app.mouseY) + "  " + cursorType().getName().replaceAll("([A-Z])", " $1").toUpperCase();
                EightBitText.text(cursorName, app.width - (16 * cursorName.length()) - 50, app.height - 35, 15);
            } else if (godMode) {
                app.image(app.loadImage(cursorType().getName() + ".png"), app.width - 50, app.height - 50, 50, 50);
                String cursorName = String.valueOf(app.mouseX) + "\bcros" + String.valueOf(app.mouseY) + "  " + cursorType().getName().replaceAll("([A-Z])", " $1").toUpperCase();
                EightBitText.text(cursorName, app.width - (16 * cursorName.length()) - 50, app.height - 35, 15);
            }
            for (int y = 0; y < 21 && y < Util.level.length; y++) {
                for (int x = 0; x < 38 && x < Util.level[y].length; x++) {
                    if (Util.level[y][x] != Blocks.AIR && Util.level[y][x] == Blocks.DIRT) {
                        boolean isTop = (y - 1 < 0) || Util.level[y - 1][x] != Blocks.DIRT;
                        boolean isBottom = (y + 1 >= Util.level.length) || Util.level[y + 1][x] != Blocks.DIRT;
                        boolean isLeft = (x - 1 < 0) || Util.level[y][x - 1] != Blocks.DIRT;
                        boolean isRight = (x + 1 >= Util.level[y].length) || Util.level[y][x + 1] != Blocks.DIRT;
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
            if (flag.check_goal(player.entityX, player.entityY)) {
                //this.player_won = true;
            }
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
        }

    void drawBlock(String name, int x, int y) {
        app.image(app.loadImage(name + ".png"), Util.globX(x), Util.globY(y), 50, 50);
    }
}