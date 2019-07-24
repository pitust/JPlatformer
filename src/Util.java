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
    public static Blocks[][] level;
    public static boolean isDead = false;
    public static EightBitText EBT;
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
        return y * 50;
    }
    public static void text(String s, int x, int y, int w) {
        PImage[] EBTtxt = EBT.eBitTxt(s.toUpperCase());
        for(int i = 0; i<EBTtxt.length; i++)
        {
            if(EBTtxt[i] != null) {
                app.image(EBTtxt[i], i * w + x, y, w, w);
            }
        }
    }
}