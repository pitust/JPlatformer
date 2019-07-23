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
 * Util - a class with utility operations
 */
public class Util {
    public static PApplet app;
    public static boolean[][] level;

    public static int gridX(int x) {
        return x / 50;
    }

    public static int gridY(int y) {
        return y / 50;
    }

    public static int globX(int x) {
        return x * 50;
    }

    public static int globY(int y) {
        text("Hi", 10, 10, 10);
        return y * 50;
    }
    public static void text(String s, int x, int y, int w) {
        int ld = s.length() / w;
        for (int i = 0;i < s.length();i++) {
            app.image(app.loadImage("8bit" + Character.toString(s.charAt(i)).toUpperCase() +  ".png"), x + ld * i, y, ld, ld);
        }
    }
}