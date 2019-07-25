import processing.core.PApplet;

public enum Blocks {

    DIRT,
    TARGETFLAG,
    PORTAL,
    SPIKE,
    COIN,
    WIFI,
    MUD,
    AIR;

    public String getName() {
        switch (this) {
            case COIN:
                return "coin";
            case MUD:
                return "mud";
            case AIR:
                return "air";
            case WIFI:
                return "wifiResized";
            case SPIKE:
                return "spikeResized";
            case TARGETFLAG:
                return "targetFlag";
            case DIRT:
                return "dirt";
            default:
                return this.toString();
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
}