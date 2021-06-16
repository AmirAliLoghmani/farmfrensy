import java.util.Random;

public class Lion extends WildAnimal{
    private final int MAX_HEALTH=3;
    Random random = new Random();
    public Lion(String name) {
        super(name);
        this.xVal = random.nextInt(5);
        this.yVal = random.nextInt(5);
        this.health=3;
    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }
}
