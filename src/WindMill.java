import java.util.Random;

public class WindMill extends Building {
    Random random = new Random();
    public WindMill() {
        //super(name, price, neededTime);
        name="windmill";
        price = 150;
        neededTime=4;
        status=0;
        xVal = random.nextInt(6);
        yVal = random.nextInt(6);
    }
}
