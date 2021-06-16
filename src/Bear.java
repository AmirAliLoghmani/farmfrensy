import java.util.Random;

public class Bear extends WildAnimal {
    private final int MAX_HEALTH=4;
    private Random random = new Random();
    public Bear(String name) {
        super(name);
        this.xVal = random.nextInt(5);
        this.yVal = random.nextInt(5);
        this.health=4;

    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }
}
