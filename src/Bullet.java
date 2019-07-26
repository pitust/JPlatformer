public class Bullet {
    //Attributes
    private int posX;
    private int posY;
    private int Speed;
    private int gravityAttraction;
    private String Type;
    private int infDamage;
    //Constructor
    public Bullet(int pX, int pY, int sPd, String type, int dam)
    {
        this.posX = pX;
        this.posY = pY;
        this.Speed = sPd;
        this.Type = type;
        this.infDamage = dam;
    }
    //Methods

}
