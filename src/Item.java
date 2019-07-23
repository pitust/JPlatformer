import processing.core.PApplet;
import processing.core.PImage;

public class Item {
    int x;
    int y;
    String texturename;
    void draw(PApplet applet) {
        PImage texture = applet.loadImage(texturename);
        applet.image(texture, x, y);
    }
    public Item(int xpos, int ypos, String name) {
        x = xpos;
        y = ypos;
        texturename = name;
    }
}