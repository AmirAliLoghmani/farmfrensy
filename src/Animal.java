import java.util.Random;

public class Animal {
    private String name;
    private int speed;
    protected int xVal;
    protected int yVal;
    protected Random random;
    public Animal(String name) {
        this.name = name;
        this.speed = speed;
        this.random=new Random();
        xVal=random.nextInt(6);
random.nextInt(6);
        xVal=random.nextInt(6);
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getxVal() {
        return xVal;
    }

    public int getyVal() {
        return yVal;
    }
}
