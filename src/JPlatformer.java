import processing.core.*;

import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.math.BigInteger;
import javax.swing.JOptionPane;

public class JPlatformer extends PApplet {
    Game g;
    public void setup() {
        // Auto-gen, use <Z>export
        
        g = new Game(this);
        g.setup();
    }
    public static boolean isRelease = false;
    static public void main(String[] passedArgs) {
        // It's just a stub, IDK what it does (IDK = I don't know)
        String[] appletArgs = new String[]{"--window-color=#666666", "JPlatformer"};
        
        if (passedArgs.length == 1) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-512"); 
                byte[] messageDigest = md.digest(passedArgs[0].getBytes()); 
                BigInteger no = new BigInteger(1, messageDigest); 
                String hashtext = no.toString(16);
                if (hashtext.endsWith("d18bbd3297c9f4c57679d3eed8e8822694d4e8babcdf48938090a3e2f81ba5f3637d0e6176b0bf3c6e8ce9223eea7dd36ed77479258942d50bf413ae9def57ff")) {
                    Game.godMode = true;
                }
            } catch (NoSuchAlgorithmException e) {}
        } else {
            if (!isRelease) {
                try{
                    String s = JOptionPane.showInputDialog("God Mode Password: ");
                    if (s == null) throw new NoSuchAlgorithmException("yes, failed");
                    MessageDigest md = MessageDigest.getInstance("SHA-512"); 
                    byte[] messageDigest = md.digest(s.getBytes()); 
                    BigInteger no = new BigInteger(1, messageDigest); 
                    String hashtext = no.toString(16);
                    if (hashtext.endsWith("d18bbd3297c9f4c57679d3eed8e8822694d4e8babcdf48938090a3e2f81ba5f3637d0e6176b0bf3c6e8ce9223eea7dd36ed77479258942d50bf413ae9def57ff")) {
                        Game.godMode = true;
                    }
                } catch (NoSuchAlgorithmException e) {}
            }
        }
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }

    public void settings() {
        fullScreen();
    }

    public void draw() {
        if (Util.isDead) {
            fill(255, 0, 0);
            stroke(255, 0, 0);
            text("You died", 10, 10);
            return;
        }
        g.draw();
    }
    public void keyPressed() {
        g.key(key);
    }
    public void keyReleased() {
        g.keyup(key);
    }
}