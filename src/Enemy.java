import processing.core.*;

public class Enemy extends Entity {

    public void redraw(PApplet app) {
        super.draw(app);
        if (isWPressed && onGround) {
            velocityY = 30;
            onGround = false;
        }
        if (isAPressed) {
            velocityX -= 7;
            isPlFl = true;
        }
        if (isDPressed) {
            velocityX += 7;
            isPlFl = false;
        }

        if (isPlFl) {
            app.image(fl, entityX, entityY - 80, 50, 70);
        } else {
            app.image(fr, entityX, entityY - 80, 50, 70);
        }
    }

    public boolean isAPressed = false;
    public boolean isDPressed = false;
    public boolean isWPressed = false;
    PImage fl;
    PImage fr;
    boolean isPlFl = true;

    public void init(PApplet app) {
        fl = app.loadImage("playerLeft.png");
        fr = app.loadImage("playerRight.png");
    }

    public Enemy(Blocks[][] level) {
        super(level);
    }






}
