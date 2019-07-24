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
import java.util.*;

public class Main extends PApplet {
    Game g;
    public void setup() {
        // Auto-gen, use <Z>export
        
        g = new Game(this);
        g.setup();
    }

    static public void main(String[] passedArgs) {
        // It's just a stub, IDK what it does (IDK = I don't know)
        String[] appletArgs = new String[]{"--window-color=#666666", "Main"};
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