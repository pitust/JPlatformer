public enum Blocks {

    DIRT,
    GOALFLAG,
    PORTAL,
    SPIKE,
    WIFI,
    AIR;

    public String getName() {
        switch (this) {
            case AIR:
                return "air.png";
            case WIFI:
                break;
            case PORTAL:
                break;
            case SPIKE:
                break;
            case GOALFLAG:
                break;
            default:
                return null;
        }
        return null;
    }


    public String getNameForDirt(boolean isBottom, boolean isLeft, boolean isRight, boolean isTop) {
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
    }

}