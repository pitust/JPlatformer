import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;
import java.util.Map;

public class EightBitText {
    //Attributes
    private Map<String, PImage> Letters;

    //Constructor
    public EightBitText(PApplet app) {
        Letters = new HashMap<>();
        Letters.put("A", app.loadImage("8bitA.png"));
        Letters.put("B", app.loadImage("8bitB.png"));
        Letters.put("C", app.loadImage("8bitC.png"));
        Letters.put("D", app.loadImage("8bitD.png"));
        Letters.put("E", app.loadImage("8bitE.png"));
        Letters.put("F", app.loadImage("8bitF.png"));
        Letters.put("G", app.loadImage("8bitG.png"));
        Letters.put("H", app.loadImage("8bitH.png"));
        Letters.put("I", app.loadImage("8bitI.png"));
        Letters.put("J", app.loadImage("8bitJ.png"));
        Letters.put("K", app.loadImage("8bitK.png"));
        Letters.put("L", app.loadImage("8bitL.png"));
        Letters.put("M", app.loadImage("8bitM.png"));
        Letters.put("N", app.loadImage("8bitN.png"));
        Letters.put("O", app.loadImage("8bitO.png"));
        Letters.put("P", app.loadImage("8bitP.png"));
        Letters.put("Q", app.loadImage("8bitQ.png"));
        Letters.put("R", app.loadImage("8bitR.png"));
        Letters.put("S", app.loadImage("8bitS.png"));
        Letters.put("T", app.loadImage("8bitT.png"));
        Letters.put("U", app.loadImage("8bitU.png"));
        Letters.put("V", app.loadImage("8bitV.png"));
        Letters.put("W", app.loadImage("8bitW.png"));
        Letters.put("X", app.loadImage("8bitX.png"));
        Letters.put("Y", app.loadImage("8bitY.png"));
        Letters.put("Z", app.loadImage("8bitZ.png"));
        Letters.put("0", app.loadImage("8bit0.png"));
        Letters.put("1", app.loadImage("8bit1.png"));
        Letters.put("2", app.loadImage("8bit2.png"));
        Letters.put("3", app.loadImage("8bit3.png"));
        Letters.put("4", app.loadImage("8bit4.png"));
        Letters.put("5", app.loadImage("8bit5.png"));
        Letters.put("6", app.loadImage("8bit6.png"));
        Letters.put("7", app.loadImage("8bit7.png"));
        Letters.put("8", app.loadImage("8bit8.png"));
        Letters.put("9", app.loadImage("8bit9.png"));
        Letters.put("©", app.loadImage("8bitCopyright.png"));
        Letters.put("×", app.loadImage("8bitCross.png"));
        Letters.put("!", app.loadImage("8bitExclamationMark.png"));
        Letters.put("-", app.loadImage("8bitMinusSign.png"));
    }

    //Methods
    public PImage[] eBitTxt(String letters) {
        letters = letters.toUpperCase();
        PImage[] PIm = new PImage[letters.length()];
        for (int i = 0; i < letters.length(); i++) {
            PIm[i] = this.printLetter(letters.charAt(i));
        }
        return PIm;
    }

    private PImage printLetter(char pLetter) {
        return Letters.get(pLetter + "");
    }
}