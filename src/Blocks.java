import processing.core.PApplet;

public enum Blocks {

    DIRT,
    TARGETFLAG,
    PORTAL,
    SPIKE,
    WIFI,
    MUD,
    AIR;

    public String getName() {
        switch (this) {
            case MUD:
                return "mud.png";
            case AIR:
                return "air.png";
            case WIFI:
                return "wifiResized.png";
            case SPIKE:
                return "spikeResized.png";
            case TARGETFLAG:
                return "targetFlag.png";
            default:
                return null;
        }
    }


    public String getNameForDirt(boolean isBottom, boolean isLeft, boolean isRight, boolean isTop) {
        if (this == DIRT) {
            String dirt = "dirt";
            if (isTop) {
                dirt = "grass";
            }
            if (isLeft && !isRight) {
                dirt += "L";
            }
            if (isRight && !isLeft) {
                dirt += "R";
            }
            if (isBottom) {
                dirt += "B";
            }
            return dirt;
        } else
            return null;
    }

    public Frames getFrames(PApplet app) {
        switch (this) {
            case PORTAL:
                return new Frames(app, new String[] { "portal0.png", "portal1.png", "portal2.png", "portal3.png", "portal4.png",
                        "portal5.png" });
                default:
                    return null;
        }
    }
}