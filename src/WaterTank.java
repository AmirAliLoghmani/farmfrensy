public class WaterTank extends Building{
    private int levelOfWater;
    private final int MAX_LEVEL_OFF_WATER=5;
    private int levelOfRefulling;
    public WaterTank(int levelOfWater) {
        this.levelOfWater = levelOfWater;
        this.levelOfRefulling=0;
    }

    public int getLevelOfRefulling() {
        return levelOfRefulling;
    }

    public void setLevelOfRefulling(int levelOfRefulling) {
        this.levelOfRefulling = levelOfRefulling;
    }

    public int getLevelOfWater() {
        return levelOfWater;
    }

    public int getMAX_LEVEL_OFF_WATER() {
        return MAX_LEVEL_OFF_WATER;
    }

    public void setLevelOfWater(int levelOfWater) {
        this.levelOfWater = levelOfWater;
    }
}
