import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Main extends PApplet {

    public void setup() {
        print("HI");
        rect(0, 0, width, height);
    }
    static public void main(String[] passedArgs) {
      // It's just a stub, IDK what it does (IDK = I dont know)
        String[] appletArgs = new String[] { "--window-color=#666666", "Main" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
    public void settings() {
        fullScreen();
    }
}
