import java.util.Random;

public class Bear extends WildAnimal {
    private Random random = new Random();
    public Bear(String name) {
        super(name);
        this.xVal = random.nextInt(5);
        this.yVal = random.nextInt(5);
        this.health=4;

    }
}
