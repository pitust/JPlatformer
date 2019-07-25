import processing.core.*;
import java.lang.Math;

/**
 * Entity
 */
public class Entity {

    Blocks[][] cur_level;

    public Entity(Level l) {
        this.cur_level = l.getLevel();
        entityX = l.getSpawnX();
        entityY = l.getSpawnY();
    }

    public int entityX = 0;
    public int entityY = 0;
    public int velocityY = 0;
    public float velocityX = 0;
    public boolean onGround = false;

    public void init(PApplet app) {

    }

    public void draw(PApplet app) {
        velocityY -= 6;
        velocityY = Math.max(Math.min(velocityY, 49), -49);
        velocityX = Math.max(Math.min(velocityX, 49), -49);
        velocityX = (int)(velocityX / 1.1);
        
        if (Util.gridY(entityY) > 0 && Util.gridY(entityY) < cur_level.length && Util.gridX(entityX) >= 0
                && Util.gridX(entityX) < cur_level[Util.gridY(entityY)].length) {
            if (cur_level[Util.gridY(entityY - 40)][Util.gridX(entityX)] != Blocks.AIR) {
                velocityX = 0;
                entityY -= velocityY;
                return;
            }
            if (cur_level[Util.gridY(entityY)][Util.gridX(entityX)] != Blocks.AIR && velocityY <= 0) {
                if ((Util.gridX(entityX) + 1 < cur_level[Util.gridY(entityY) - 1].length && cur_level[Util.gridY(entityY) - 1][Util.gridX(entityX) + 1] != Blocks.AIR) || cur_level[Util.gridY(entityY) - 1][Util.gridX(entityX)] != Blocks.AIR) { 
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
}