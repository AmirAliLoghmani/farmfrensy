public class Product {
    protected int xVal;
    protected int yVal;
    protected String name;
    protected int price;
    protected int health;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Product(int xVal, int yVal, String name) {
        this.xVal = xVal;
        this.yVal = yVal;
        this.name = name;
    }
}
