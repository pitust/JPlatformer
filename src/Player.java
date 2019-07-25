import processing.core.*;

/**
 * Player
 */
public class Player extends Entity {

    public void draw(PApplet app) {
        super.draw(app);
        if (isWPressed && onGround) {
            velocityY = 30;
            onGround = false;
        }
        if (isAPressed) {
            velocityX -= 7;
            isPlayerFacingLeft = true;
        }
        if (isDPressed) {
            velocityX += 7;
            isPlayerFacingLeft = false;
        }

        if (isPlayerFacingLeft) {
            app.image(facingLeft, entityX, entityY - 70, 50, 70);
        } else {
            app.image(facingRight, entityX, entityY - 70, 50, 70);
        }
    }

    public boolean isAPressed = false;
    public boolean isDPressed = false;
    public boolean isWPressed = false;
    PImage facingLeft;
    PImage facingRight;
    boolean isPlayerFacingLeft = true;

    public void init(PApplet app) {
        facingLeft = app.loadImage("playerLeft.png");
        facingRight = app.loadImage("playerRight.png");
    }

    public Player(Level level) {
        super(level);
    }
}