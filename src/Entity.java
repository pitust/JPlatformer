import processing.core.*;
import java.lang.Math;

/**
 * Entity
 */
public class Entity {


    public Entity(Level l) {
        entityX = l.getSpawnX();
        entityY = l.getSpawnY();
    }

    public int entityX = 0;
    public int entityY = 0;
    public int velocityY = 0;
    public float velocityX = 0;
    public boolean onGround = false;
    public int entityHP;

    public void init(PApplet app) {

    }

    public void draw(PApplet app) {
        velocityY -= 6;
        velocityY = Math.max(Math.min(velocityY, 49), -49);
        velocityX = Math.max(Math.min(velocityX, 49), -49);
        velocityX = (int)(velocityX / 1.1);
        
        if (Util.gridY(entityY) > 0 && Util.gridY(entityY) < Util.level.length && Util.gridX(entityX) >= 0
                && Util.gridX(entityX) < Util.level[Util.gridY(entityY)].length) {
            if (Util.level[Util.gridY(entityY - 40)][Util.gridX(entityX)] == Blocks.DIRT) {
                velocityX = 0;
                entityY -= velocityY;
                return;
            }
            if (Util.level[Util.gridY(entityY)][Util.gridX(entityX)] == Blocks.DIRT && velocityY <= 0) {
                if ((Util.gridX(entityX) + 1 < Util.level[Util.gridY(entityY) - 1].length && Util.level[Util.gridY(entityY) - 1][Util.gridX(entityX) + 1] == Blocks.DIRT) || Util.level[Util.gridY(entityY) - 1][Util.gridX(entityX)] == Blocks.DIRT) { 
                    int i = (int)(Math.abs(velocityX) / velocityX);
                    entityX -= velocityX + i * 2;
                    velocityX = 0;
                }
                velocityY = 0;
                onGround = true;
                velocityX *= 4;
                velocityX /= 5;
                entityY = Util.globY(Util.gridY(entityY));
            } else
                onGround = false;
        } else {
            onGround = false;
        }
        entityY -= velocityY;
        entityX += (int) velocityX;
    }
    public void showRange(PApplet app) {
        int topVelY = velocityY;
        if (onGround) topVelY += 30;
        int airTimeY = (int)((float)topVelY / 6.6);
        int yAvg = topVelY / 2;
        int l = airTimeY * yAvg;
        int yCan = l;
        
        int howHigh = yCan;
        int airTime = howHigh / 6;
        int howFarATick = (int)(velocityX + (airTime * 6 / 1.1));
        int xCan =  airTime * howFarATick;
        app.fill(255, 0, 0, 10);
        app.stroke(255, 0, 0, 40);
        app.ellipse(entityX, entityY, xCan, yCan * 2);

    }
}