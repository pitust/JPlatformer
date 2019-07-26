import processing.core.PApplet;
import processing.core.PImage;

public class Weapon {
    //Attributes
    private PImage Wimage;
    private int damage;
    private String type;
    //File Wsound;
    //Constructor
    public Weapon(String Type, PImage image, int str/*, File sound*/)
    {
        this.type = Type;
        this.Wimage = image;
        this.damage = str;
        //this.Wsound = sound;
    }
    //Methods
    public void attack(Entity target, Entity owner, PApplet app)
    {
        if(type == "sword")
        {
            if(target.entityX > owner.entityX + 20 && target.entityY > owner.entityY - 5 && target.entityY < owner.entityY + 5)
            {
                target.entityHP = target.entityHP - this.damage;
            }
        }
        else if(type == "pistol")
        {
            if(owner.entityY > target.entityY && target.entityY > owner.entityY - owner.entityH || target.entityY > owner.entityY && owner.entityY > target.entityY - target.entityH)
            {
                target.entityHP = target.entityHP - this.damage;
            }
        }
        else if(type == "rocketLauncher")
        {
            Missile bill = new Missile(owner.entityX + 51, owner.entityY,20, "Rocket", damage, app);
        }
    }
}
