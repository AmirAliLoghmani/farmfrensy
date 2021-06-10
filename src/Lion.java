import java.util.Random;

public class Lion extends WildAnimal{
    Random random = new Random();
    public Lion(String name) {
        super(name);
        this.xVal = random.nextInt(5);
        this.yVal = random.nextInt(5);
        this.health=3;
    }
}
