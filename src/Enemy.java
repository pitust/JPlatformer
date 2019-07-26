import processing.core.*;

public class Enemy extends Entity {

    public void draw(PApplet app) {
        super.draw(app);
        if (isWPressed && onGround) {
            velocityY = 30;
            onGround = false;
        }
        if (isAPressed) {
            velocityX -= 7;
            isEnemiFacingLeft = true;
        }
        if (isDPressed) {
            velocityX += 7;
            isEnemiFacingLeft = false;
        }

        if (isEnemiFacingLeft) {
            app.image(facingLeft, entityX, entityY - 70, 50, 70);
        } else {
            app.image(facingRight, entityX, entityY - 70, 50, 70);
        }
        velocityX += dir() * 12;
        if (onGround && Util.gridY(entityY) < Util.level.length && Util.gridX(entityX + (int)velocityX) < Util.level[Util.gridY(entityY)].length && (Util.level[Util.gridY(entityY)][Util.gridX(entityX + (int)velocityX)] == Blocks.AIR || Util.level[Util.gridY(entityY) - 1][Util.gridX(entityX + (int)velocityX + 20)] == Blocks.DIRT)) {
            velocityY = 30;
            onGround = false;
        }
    }

    public boolean isAPressed = false;
    public boolean isDPressed = false;
    public boolean isWPressed = false;
    PImage facingLeft;
    PImage facingRight;
    boolean isEnemiFacingLeft = true;
    Player tgd;

    public void init(PApplet app) {
        facingLeft = app.loadImage("enemiLeft.png");
        facingRight = app.loadImage("enemiRight.png");
    }

    public Enemy(Level level, Player target) {
        super(level);
        tgd = target;
    }

    private int dir() {
        if (tgd.entityX > entityX) return 1;
        return -1;
    }
}
