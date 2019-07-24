import processing.core.PApplet;

public class Goal {
    //Attributes
    private int posX;
    private int posY;
    //Constructor
    public Goal (int Posx, int Posy){
        posX = Posx;
        posY = Posy;
    }
    //Methods
    public void draw(PApplet app)
    {
        app.image(app.loadImage("targetFlag.png"), Util.globX(posX), Util.globY(posY), 50, 50);
    }
}
