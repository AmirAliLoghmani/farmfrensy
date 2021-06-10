import java.util.Random;

public class DomesticAnimal extends Animal {
    private int price;
    private final int TURKEYPRICE = 200;
    private final int CHICKENPRICE = 100;
    private final int BUFFALOPRICE = 400;
    private final int CHICKENGENERATETIME = 2;
    private final int TURKEYGENERATETIME = 3;
    private final int BUFFALOGENERATETIME = 5;
    private int health;
    private int generatingProcess;
    private int generatingTime;


    private Random random = new Random();

    public int getPrice() {
        return price;
    }

    public void setGeneratingProcess(int generatingProcess) {
        this.generatingProcess = generatingProcess;
    }

    public int getGeneratingProcess() {
        return generatingProcess;
    }

    public int getGeneratingTime() {
        return generatingTime;
    }

    public void setGeneratingTime(int generatingTime) {
        this.generatingTime = generatingTime;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public DomesticAnimal(String name) {
        super(name);
        this.xVal = random.nextInt(5);
        this.yVal = random.nextInt(5);
        this.health = 100;
        this.generatingProcess=0;

        if (name.equals("chicken")){
            this.price = CHICKENPRICE;
            this.generatingTime = CHICKENGENERATETIME;
        }
        else if (name.equals("turkey")){
            this.price = TURKEYPRICE;
            this.generatingTime = TURKEYGENERATETIME;
        }
        if (name.equals("buffalo")){
            this.price = BUFFALOPRICE;
            this.generatingTime = BUFFALOGENERATETIME;
        }

    }


}
