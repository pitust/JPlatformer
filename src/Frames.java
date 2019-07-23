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
/**
 * Frames - a class describing animation
 */
public class Frames {
    PApplet app;
    int len;
    int cf = 0;
    PImage[] img;
    public Frames(PApplet app_, String[] names) {
        this.app = app_;
        len = names.length;
        img = new PImage[len];
        for (int i = 0;i < len;i++) {
            this.img[i] = app.loadImage(names[i]);
        }
    }
    public void draw(int x, int y, int w, int h) {
        app.image(img[cf++], x, y, w, h);
        cf = cf % len;
    }
    public void draw(int x, int y) {
        app.image(img[cf++], x, y);
        cf = cf % len;
    }
}