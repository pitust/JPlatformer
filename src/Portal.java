import processing.core.*;

/**
 * Portal
 */
public class Portal {
    int xA, xB, yA, yB;
    Frames f0, f1;
    Player p;
    public Portal(int x0, int y0, int x1, int y1, Player p) {
        xA = x0;
        xB = x1;
        yA = y0;
        yB = y1;
        this.p = p;
    }
    public void init(PApplet app) {
        f0 = new Frames(app, new String[] { "portal0.png", "portal1.png", "portal2.png", "portal3.png",
        "portal4.png", "portal5.png" });
        f1 = new Frames(app, new String[] { "portal5.png", "portal4.png", "portal3.png", "portal2.png",
        "portal1.png", "portal0.png" });
    }
    public void draw(PApplet app) {
        f0.draw(xA, yA, 50, 50);
        f1.draw(xB, yB, 50, 50);
        if (p.entityX > xA - 10 && p.entityY > yA - 10 && p.entityX < xA + 70 && p.entityY < yA + 70) {
            p.velocityX = 0;
            p.velocityY = 0;
            p.entityX = xB;
            p.entityY = yB + 50;
        }
    }
}