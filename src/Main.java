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
import java.util; 

public class Main extends PApplet {

    public void setup() {
        print("HI");
        I0 = loadImage("1.png");
        bg = loadImage("bg.png");
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
    PImage I0;
    PImage bg;
    int x = 0;
    int y = 0;
    public void draw() {
        image(bg, 0, 0, width, height);
        image(I0, x*50, height - y*50, 50, 50);
        y += 1;
    }
}
