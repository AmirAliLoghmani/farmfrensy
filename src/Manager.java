import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Manager {


    String status = "progress";
    int currentTurn = 1;
    //int wildAnimalTime[][];
    int maxTime;
    int prizeEndingQuick;
    int currentLevel;
    int cageLevel = 0;
    int numberOfLevels;
    int initialCoins;
    int numberOfTasks;
    int chickenGoal = 0;
    int turkeyGoal = 0;
    int buffaloGoal = 0;
    int coinGoal = 0;
    int numberOfWildAnimals = 0;
    ArrayList<Product> onMapProduct = new ArrayList<>();
    ArrayList<Integer> bearArrivalTime = new ArrayList<>();
    ArrayList<Integer> lionArrivalTime = new ArrayList<>();
    ArrayList<Integer> tigerArrivalTime = new ArrayList<>();

    Player currentPlayer;
    ArrayList<WindMill> windMillslist = new ArrayList<WindMill>();
    ArrayList<FabricMaker> fabricMakerslist = new ArrayList<FabricMaker>();
    ArrayList<BearProduct> bearProductslist = new ArrayList<>();
    ArrayList<LionProduct> lionProductslist = new ArrayList<>();
    ArrayList<LionProduct> tigerProductslist = new ArrayList<>();
    ArrayList<Bakery> bakeryslist = new ArrayList<Bakery>();
    ArrayList<IceCreamMaker> iceCreamMakerslist = new ArrayList<IceCreamMaker>();
    ArrayList<Tailoring> tailoryslist = new ArrayList<Tailoring>();
    ArrayList<CartoonMilkMaker> cartoonMilkMakerslist = new ArrayList<CartoonMilkMaker>();
    ArrayList<Bear> bearslist = new ArrayList<Bear>();
    ArrayList<Lion> lionslist = new ArrayList<Lion>();
    ArrayList<Tiger> tigerslist = new ArrayList<Tiger>();
    ArrayList<Product> gaindproductslist = new ArrayList<Product>();
    ArrayList<Player> playersList = new ArrayList<Player>();
    //ArrayList<Animal> animalslist = new ArrayList<Animal>();
    ArrayList<Egg> eggslist = new ArrayList<Egg>();
    ArrayList<Milk> milkslist = new ArrayList<Milk>();
    ArrayList<Bread> breadslist = new ArrayList<Bread>();
    ArrayList<IceCream> icecreamslist = new ArrayList<IceCream>();
    ArrayList<Clothe> clotheslist = new ArrayList<Clothe>();
    ArrayList<Feather> featherslist = new ArrayList<Feather>();
    ArrayList<Flour> flourslist = new ArrayList<Flour>();
    //  ArrayList<Bread> breadlist = new ArrayList<Flour>();
    ArrayList<Fabric> fabriclist = new ArrayList<>();
    ArrayList<DomesticAnimal> domesticAnimalsList = new ArrayList<DomesticAnimal>();
    ArrayList<DefenderAnimal> defenderAnimalslist = new ArrayList<DefenderAnimal>();

    Scanner scanner = new Scanner(System.in);
    String[][] map = new String[6][6];
    Random random = new Random();
    WaterTank waterTank;
    Truck truck = new Truck();

    public void nextLevel() {

    }


    public boolean loginProcess() {
        System.out.println("enter your Username.");
        String username = scanner.nextLine();
        Player p = null;
        String pass = "";
        int tem = 0;
        int lvl = 0;
        for (Player player : playersList) {
            if (player.getUserName().equals(username)) {
                p = player;
                pass = player.getPassWord();
                lvl = player.getLevel();
                tem = 1;
            }
        }
        if (tem == 0) {
            System.out.println("no player found");
            return false;
        }
        System.out.println("enter password...");
        if (pass.equals(scanner.nextLine())) {
            currentLevel = lvl;
            currentPlayer = p;
            startPanel();
            return true;
        }
        System.out.println("wrong password");
        return false;


    }

    public boolean signUpProcess() {

        System.out.println("enter your username.");
        int tem = 0;
        String username = scanner.nextLine();
        for (Player player : playersList) {
            if (player.getUserName().equals(username)) {
                System.out.println("already existing!!!");
                tem = 1;
                return false;
                // break;
            }
        }

        System.out.println("enter the password you want to have!");
        String pass = scanner.nextLine();
        playersList.add(currentPlayer = new Player(username, pass, 1));
        // FileWriter fw = new FileWriter("users.txt");
        //BufferedWriter bw = new BufferedWriter(fw);
           /* for (Player player : playersList) {
                bw.append(player.getUserName()+" "+player.getPassWord()+player.getLevel()+"\n");
            }
                    //bw.append(username + " " + pass);
                    bw.close();*/


        for (Player player : playersList) {
            System.out.println(player.getUserName());
        }
        currentLevel = 1;
        startPanel();
        return true;
    }

    public void startPanel() {
        System.out.println("starting..........");
        try {
            if (status.equals("finished")) {
                System.out.println("dude you finished the whole game");
                return;
            }
            System.out.println("current level" + currentLevel);
            FileReader fileReader3 = new FileReader("missions.txt");
            BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
            numberOfLevels = Integer.parseInt(bufferedReader3.readLine());
            String temp;
            int tt = 0;
            while (tt == 0) {
                if ((temp = bufferedReader3.readLine()).equals("end")) {
                    System.out.println("file ended");
                    status = "finished";
                   // startPanel();
                    tt = 1;
                    return;

                    //return;
                } else if ((temp).startsWith("level")) {
                    if (Integer.parseInt(temp.split("\\s")[1]) == currentLevel)
                        tt = 1;
                }

            }

            //currentLevel = Integer.parseInt(bufferedReader3.readLine().split("\\s")[1]);
            initialCoins = Integer.parseInt(bufferedReader3.readLine());
            numberOfTasks = Integer.parseInt(bufferedReader3.readLine());
            System.out.println("nom of tasks  " + numberOfTasks);
            String goal;
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println("1");
                goal = bufferedReader3.readLine();
                if (goal.startsWith("chicken")) {
                    System.out.println("2");
                    System.out.println("chicken");
                    chickenGoal = Integer.parseInt(goal.split("\\s")[1]);
                } else if (goal.startsWith("turkey"))
                    turkeyGoal = Integer.parseInt(goal.split("\\s")[1]);
                    //  else if ((goal = bufferedReader3.readLine()).startsWith("buffalo"))
                    //    buffaloGoal = Integer.parseInt(goal.split("\\s")[1]);
                else if (goal.startsWith("coins")) {
                    System.out.println("coin");
                    System.out.println("3");
                    coinGoal = Integer.parseInt(goal.split("\\s")[1]);
                }
            }
            //System.out.println(bufferedReader3.readLine());
            numberOfWildAnimals = Integer.parseInt(bufferedReader3.readLine());
            String tem;
            for (int i = 0; i < numberOfWildAnimals; i++) {
                tem = bufferedReader3.readLine();
                if (tem.startsWith("bear"))
                    bearArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
                if (tem.startsWith("lion"))
                    lionArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
            }

            maxTime = Integer.parseInt(bufferedReader3.readLine());
            prizeEndingQuick = Integer.parseInt(bufferedReader3.readLine());

            currentPlayer.setMoney(initialCoins);
            bufferedReader3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("1 : Start\n2: Log out\n3: Settings");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    map[i][j] = "0";
                }
            }
            waterTank = new WaterTank(0);
            String input;
            //test

                    //test end
            String[] split;
            while (!((input = scanner.nextLine()).equals("exit"))) {
                split = input.split("\\s");
                if (input.startsWith("BUY")) {
                    buyAnimal(split);
                } else if (input.startsWith("WELL"))
                    wellWater();
                else if (input.startsWith("PLANT"))
                    plant(split);
                else if (input.startsWith("CAGE"))
                    putCage(split);
                else if (input.startsWith("TURN"))
                    turnTime(split);
                else if (input.startsWith("PICK UP"))
                    pickUpProperty(split);
                else if (input.startsWith("MAKE"))
                    makingBuilding(split);
                else if (input.startsWith("WORK"))
                    workingBuilding(split);
                else if (input.startsWith("TRUCK LOAD"))
                    loadingTruck(split);
                else if (input.startsWith("TRUCK GO"))
                    sendingTruck();
                if (status.equals("win")||status.equals("finished")) {
                    System.out.println("you finished the level");
                    status = "progress";
                    currentLevel++;
                    readingOrderFile();
                    saveEveryThing();
                    //startPanel();
                }
                if (status.equals("finished"))
                   // saveEveryThing();
                    return;
            }
        } else if (choice.equals("2")) {
            saveEveryThing();
            logoutProcess();
        } else if (choice.equals("3")) {
saveEveryThing();
        }
    }

    private void readingOrderFile(){
        try {
            deleteEveryThing();

            System.out.println("current level" + currentLevel);
            FileReader fileReader3 = new FileReader("missions.txt");
            BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
            numberOfLevels = Integer.parseInt(bufferedReader3.readLine());
            String temp;
            int tt = 0;
            while (tt == 0) {
                if ((temp = bufferedReader3.readLine()).equals("end")) {
                    System.out.println("file ended");
                    status = "finished";
                   // startPanel();
                    tt = 1;
                    return;

                    //return;
                } else if ((temp).startsWith("level")) {
                    if (Integer.parseInt(temp.split("\\s")[1]) == currentLevel)
                        tt = 1;
                }

            }

            //currentLevel = Integer.parseInt(bufferedReader3.readLine().split("\\s")[1]);
            initialCoins = Integer.parseInt(bufferedReader3.readLine());
            numberOfTasks = Integer.parseInt(bufferedReader3.readLine());
            System.out.println("nom of tasks  " + numberOfTasks);
            String goal;
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println("1");
                goal = bufferedReader3.readLine();
                if (goal.startsWith("chicken")) {
                    System.out.println("2");
                    System.out.println("chicken");
                    chickenGoal = Integer.parseInt(goal.split("\\s")[1]);
                } else if (goal.startsWith("turkey"))
                    turkeyGoal = Integer.parseInt(goal.split("\\s")[1]);
                    //  else if ((goal = bufferedReader3.readLine()).startsWith("buffalo"))
                    //    buffaloGoal = Integer.parseInt(goal.split("\\s")[1]);
                else if (goal.startsWith("coins")) {
                    System.out.println("coin");
                    System.out.println("3");
                    coinGoal = Integer.parseInt(goal.split("\\s")[1]);
                }
            }
            //System.out.println(bufferedReader3.readLine());
            numberOfWildAnimals = Integer.parseInt(bufferedReader3.readLine());
            String tem;
            for (int i = 0; i < numberOfWildAnimals; i++) {
                tem = bufferedReader3.readLine();
                if (tem.startsWith("bear"))
                    bearArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
                if (tem.startsWith("lion"))
                    lionArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
            }

            maxTime = Integer.parseInt(bufferedReader3.readLine());
            prizeEndingQuick = Integer.parseInt(bufferedReader3.readLine());

            currentPlayer.setMoney(initialCoins);
            bufferedReader3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteEveryThing() {
        domesticAnimalsList.clear();
        windMillslist.clear();
        fabricMakerslist.clear();
        bearProductslist.clear();
        lionslist.clear();
        bakeryslist.clear();
        iceCreamMakerslist.clear();
        tailoryslist.clear();
        cartoonMilkMakerslist.clear();
        bearslist.clear();
        lionArrivalTime.clear();
        gaindproductslist.clear();
        eggslist.clear();
        milkslist.clear();
        breadslist.clear();
        icecreamslist.clear();
        clotheslist.clear();
        featherslist.clear();
        flourslist.clear();
        fabriclist.clear();
        defenderAnimalslist.clear();

        currentPlayer.setLevel(currentLevel);


    }

    private void sendingTruck() {
        if (truck.productListTrans.size() == 0) {
            System.out.println("truck is empty !!!");
        } else {
            truck.refullingLevel = truck.neededTime;
        }

    }

    private void loadingTruck(String[] split) {
        System.out.println("gaind pros:");
        for (Product product : gaindproductslist) {
            System.out.println(product);
        }
        if (truck.productListTrans.size() > 15) {
            System.out.println("truck is full !!!");
        } else {
            String name = split[2];
            for (Product product : gaindproductslist) {
                if (product.name.equals(name)) {
                    truck.productListTrans.add(product);
                    gaindproductslist.remove(product);
                    break;
                }
            }
            for (Product product : gaindproductslist) {
                System.out.println(product.xVal + " " + product.yVal + "  " + product.getName());
            }
            for (Product productListTran : truck.productListTrans) {
                System.out.println(productListTran.xVal + " " + productListTran.yVal + "   " + productListTran.getName());
            }

        }

    }

    private void makingBuilding(String[] split) {



        if (split[1].equals("windmill")) {
            if (currentPlayer.getMoney() >= 150)
                currentPlayer.setMoney(currentPlayer.getMoney() - 150);
            else {
                System.out.println("not enough money");
                return;
            }
            windMillslist.add(new WindMill());
        }


        else if (split[1].equals("fabricMaker")) {
            if (currentPlayer.getMoney() >= 250)
                currentPlayer.setMoney(currentPlayer.getMoney() - 250);
            else {
                System.out.println("not enough money");
                return;
            }

            fabricMakerslist.add(new FabricMaker());
        }
        else if (split[1].equals("bakery")) {

            if (currentPlayer.getMoney() >= 250)
                currentPlayer.setMoney(currentPlayer.getMoney() - 250);
            else {
                System.out.println("not enough money");
                return;
            }
            bakeryslist.add(new Bakery());

        } else if (split[1].equals("tailoring")) {
            if (currentPlayer.getMoney() >= 400)
                currentPlayer.setMoney(currentPlayer.getMoney() - 400);
            else {
                System.out.println("not enough money");
                return;
            }
            tailoryslist.add(new Tailoring());

        } else if (split[1].equals("iceCreamMaker")) {
            if (currentPlayer.getMoney() >= 550)
                currentPlayer.setMoney(currentPlayer.getMoney() - 550);
            else {
                System.out.println("not enough money");
                return;
            }
            iceCreamMakerslist.add(new IceCreamMaker());

        } else if (split[1].equals("cartoonMilkMaker")) {
            if (currentPlayer.getMoney() >= 400)
                currentPlayer.setMoney(currentPlayer.getMoney() - 400);
            else {
                System.out.println("not enough money");
                return;
            }
            cartoonMilkMakerslist.add(new CartoonMilkMaker());

        }
    }

    private void workingBuilding(String[] split) {
        // windMillslist.add(new WindMill());

        if (split[1].equals("windmill")) {
            if (windMillslist.size()==0) {
                System.out.println("make the building first!");
            return;
            }
            if (windMillslist.size()==1)
            if (windMillslist.get(0).refullingLevel == 0)
                windMillslist.get(0).refullingLevel = 4 + 1;

        } else if (split[1].equals("fabricMaker")) {
            if (fabricMakerslist.size()==0){
                System.out.println("make the building first!");
            return;
            }
            if (fabricMakerslist.size()==1)
                if (fabricMakerslist.get(0).refullingLevel == 0)
                fabricMakerslist.get(0).refullingLevel = 5 + 1;

        } else if (split[1].equals("bakery")) {
            if (bakeryslist.size()==0) {
                System.out.println("make the building first!");
                return;
            }
            if (bakeryslist.size()==1) {
                System.out.println("1");
                if (bakeryslist.get(0).refullingLevel == 0){
                    bakeryslist.get(0).refullingLevel = 5 + 1;
                    System.out.println("2");
                }
            }
        } else if (split[1].equals("tailoring")) {
            if (tailoryslist.size()==0) {
                System.out.println("make the building first!");
                return;
            }
            if (tailoryslist.size()==1)
                if (tailoryslist.get(0).refullingLevel == 0)
                tailoryslist.get(0).refullingLevel = 6 + 1;

        } else if (split[1].equals("iceCreamMaker")) {
            if (iceCreamMakerslist.size()==0) {
                System.out.println("make the building first!");
                return;
            }
            if (iceCreamMakerslist.size()==1)

                if (iceCreamMakerslist.get(0).refullingLevel == 0)
                iceCreamMakerslist.get(0).refullingLevel = 7 + 1;

        } else if (split[1].equals("cartoonMilkMaker")) {
            if (cartoonMilkMakerslist.size()==0) {
                System.out.println("make the building first!");
                return;
            }
            if (cartoonMilkMakerslist.size()==1)

                if (cartoonMilkMakerslist.get(0).refullingLevel == 0)
                cartoonMilkMakerslist.get(0).refullingLevel = 6 + 1;

        }
    }

    private void pickUpProperty(String[] split) {
        int x = Integer.parseInt(split[2]);
        int y = Integer.parseInt(split[3]);

        int c = 0;
        int tem = 0;
        for (Egg egg : eggslist) {
            if (egg.xVal == x && egg.yVal == y) {
                gaindproductslist.add(new Egg(egg.xVal, egg.yVal, "egg"));

                tem = 1;
                break;
            }
            c++;
        }
        if (tem == 1)
            eggslist.remove(c);


        int c2 = 0;
        int tem2 = 0;
        for (Flour flour : flourslist) {
            if (flour.xVal == x && flour.yVal == y) {
                gaindproductslist.add(new Flour(flour.xVal, flour.yVal, "flour"));

                tem2 = 1;
                break;
            }
            c2++;
        }
        if (tem2 == 1)
            flourslist.remove(c2);


        int c3 = 0;
        int tem3 = 0;
        for (Fabric fabric : fabriclist) {
            if (fabric.xVal == x && fabric.yVal == y) {
                gaindproductslist.add(new Fabric(fabric.xVal, fabric.yVal, "fabric"));

                tem3 = 1;
                break;
            }
            c3++;
        }
        if (tem3 == 1)
            fabriclist.remove(c3);


        int c4 = 0;
        int tem4 = 0;
        for (Feather feather : featherslist) {
            if (feather.xVal == x && feather.yVal == y) {
                gaindproductslist.add(new Fabric(feather.xVal, feather.yVal, "feather"));

                tem4 = 1;
                break;
            }
            c4++;
        }
        if (tem4 == 1)
            featherslist.remove(c4);


        int c5 = 0;
        int tem5 = 0;
        for (Milk milk : milkslist) {
            if (milk.xVal == x && milk.yVal == y) {
                gaindproductslist.add(new Fabric(milk.xVal, milk.yVal, "milk"));

                tem5 = 1;
                break;
            }
            c5++;
        }
        if (tem5 == 1)
            milkslist.remove(c5);


        int c6 = 0;
        int tem6 = 0;
        for (Bread bread : breadslist) {
            if (bread.xVal == x && bread.yVal == y) {
                gaindproductslist.add(new Bread(bread.xVal, bread.yVal, "bread"));

                tem6 = 1;
                break;
            }
            c6++;
        }
        if (tem6 == 1)
            breadslist.remove(c6);


        int c7 = 0;
        int tem7 = 0;
        for (BearProduct bearProduct : bearProductslist) {
            if (bearProduct.xVal == x && bearProduct.yVal == y) {
                gaindproductslist.add(new BearProduct(bearProduct.xVal, bearProduct.yVal, "bearProduct"));

                tem7 = 1;
                break;
            }
            c7++;
        }
        if (tem7 == 1)
            bearProductslist.remove(c7);


        int c8 = 0;
        int tem8 = 0;
        for (Clothe clothe : clotheslist) {
            if (clothe.xVal == x && clothe.yVal == y) {
                gaindproductslist.add(new Clothe(clothe.xVal, clothe.yVal, "clothe"));

                tem8 = 1;
                break;
            }
            c8++;
        }
        if (tem8 == 1)
            clotheslist.remove(c8);


        int c9 = 0;
        int tem9 = 0;
        for (IceCream iceCream : icecreamslist) {
            if (iceCream.xVal == x && iceCream.yVal == y) {
                gaindproductslist.add(new IceCream(iceCream.xVal, iceCream.yVal, "iceCream"));

                tem9 = 1;
                break;
            }
            c9++;
        }
        if (tem9 == 1)
            icecreamslist.remove(c9);
        System.out.println("gained products : ");
        for (Product product : gaindproductslist) {
            System.out.println(product.xVal + " " + product.yVal + "  " + product.getName());
        }

        int c10 = 0;
        int tem10 = 0;
        for (LionProduct lionProduct : lionProductslist) {
            if (lionProduct.xVal == x && lionProduct.yVal == y) {
                gaindproductslist.add(new LionProduct(lionProduct.xVal, lionProduct.yVal, "lionproduct"));

                tem10 = 1;
                break;
            }
            c10++;
        }
        if (tem10 == 1)
            lionProductslist.remove(c10);
        System.out.println("gained products : ");
        for (Product product : gaindproductslist) {
            System.out.println(product.xVal + " " + product.yVal + "  " + product.getName());
        }


    }

    private void turnTime(String[] split) {

        //finishing
        catsCollecting();
        showGaindProducts();
        int chickencount = 0;
        int turkeycount = 0;
        int buffalocount = 0;
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (domesticAnimal.getName().equals("chicken"))
                chickencount++;
            else if (domesticAnimal.getName().equals("turkey"))
                turkeycount++;
            else if (domesticAnimal.getName().equals("buffalo"))
                buffalocount++;
        }


        if (chickencount >= chickenGoal)
            chickenGoal = -1;
        if (turkeycount >= turkeyGoal)
            turkeyGoal = -1;
        if (buffalocount >= buffaloGoal)
            buffaloGoal = -1;
        if (coinGoal <= currentPlayer.getMoney())
            coinGoal = -1;
        if (chickenGoal < 1 && turkeyGoal < 1 && buffaloGoal < 1 && coinGoal <= currentPlayer.getMoney()) {
            status = "win";
            System.out.println("wiiiiiiiiiin");
            System.out.println(currentLevel);
            currentTurn = 1;

            //int wildAnimalTime[][];
            maxTime = 0;
            prizeEndingQuick = 0;
            chickenGoal = 0;
            turkeyGoal = 0;
            buffaloGoal = 0;
            coinGoal = 0;
            return;
        }
        System.out.println("chicken goal" + chickenGoal);
        System.out.println("turkey goal" + turkeyGoal);
        System.out.println("buffalo goal" + buffaloGoal);
        System.out.println("coin goal" + coinGoal);


        currentTurn++;
        cageLevel = 0;
        putBear();
        movingBears();
        destroyingfunction();
        int sell = 0;


        if (truck.refullingLevel > 1) {
            truck.refullingLevel--;
        } else if (truck.refullingLevel == 1) {
            for (Product productListTran : truck.productListTrans) {
                sell += productListTran.price;

            }
            truck.productListTrans.clear();
            truck.refullingLevel = 0;
            System.out.println("previous money" + currentPlayer.getMoney());
            currentPlayer.setMoney(currentPlayer.getMoney() + sell);
            System.out.println("money now" + currentPlayer.getMoney());
            sell = 0;
        }
if (fabricMakerslist.size()==1) {
    System.out.println("fabric maker refulling   :   " + fabricMakerslist.get(0).refullingLevel);
    if (fabricMakerslist.get(0).refullingLevel > 1) {
        fabricMakerslist.get(0).refullingLevel--;
    } else if (fabricMakerslist.get(0).refullingLevel == 1) {
        //making product
        fabriclist.add(new Fabric(5, 0, "fabric"));
        onMapProduct.add(new Fabric(5, 0, "fabric"));
        fabricMakerslist.get(0).refullingLevel = 0;
    }
}
        if (bakeryslist.size()==1) {
            System.out.println("bakery refulling   :   " + bakeryslist.get(0).refullingLevel);
            if (bakeryslist.get(0).refullingLevel > 1) {
                bakeryslist.get(0).refullingLevel--;
            } else if (bakeryslist.get(0).refullingLevel == 1) {
                //making product
                breadslist.add(new Bread(4, 0, "bread"));
                onMapProduct.add((new Bread(4, 0, "bread")));
                bakeryslist.get(0).refullingLevel = 0;
            }
        }
        if (tailoryslist.size()==1) {
            System.out.println("tailoring refulling   :   " + tailoryslist.get(0).refullingLevel);
            if (tailoryslist.get(0).refullingLevel > 1) {
                tailoryslist.get(0).refullingLevel--;
            } else if (tailoryslist.get(0).refullingLevel == 1) {
                //making product
                clotheslist.add(new Clothe(3, 5, "clothe"));
                onMapProduct.add(new Clothe(3, 5, "clothe"));
                tailoryslist.get(0).refullingLevel = 0;
            }
        }
        if (iceCreamMakerslist.size()==1) {
            System.out.println("icecream refulling   :   " + iceCreamMakerslist.get(0).refullingLevel);
            if (iceCreamMakerslist.get(0).refullingLevel > 1) {
                iceCreamMakerslist.get(0).refullingLevel--;
            } else if (iceCreamMakerslist.get(0).refullingLevel == 1) {
                //making product
                icecreamslist.add(new IceCream(3, 0, "iceCream"));
                onMapProduct.add(new IceCream(3, 0, "iceCream"));

                iceCreamMakerslist.get(0).refullingLevel = 0;
            }
        }
        if (cartoonMilkMakerslist.size()==1) {
            System.out.println("cartoon milk maker refulling   :   " + cartoonMilkMakerslist.get(0).refullingLevel);
            if (cartoonMilkMakerslist.get(0).refullingLevel > 1) {
                cartoonMilkMakerslist.get(0).refullingLevel--;
            } else if (cartoonMilkMakerslist.get(0).refullingLevel == 1) {
                //making product
                milkslist.add(new Milk(4, 5, "milk"));
                onMapProduct.add(new Milk(4, 5, "milk"));
                cartoonMilkMakerslist.get(0).refullingLevel = 0;
            }
        }
        if (windMillslist.size()==1) {
        System.out.println("refulling   :   " + windMillslist.get(0).refullingLevel);
        if (windMillslist.get(0).refullingLevel > 1) {
            windMillslist.get(0).refullingLevel--;
        } else if (windMillslist.get(0).refullingLevel == 1) {
            //making product
            flourslist.add(new Flour(5, 5, "flour"));
            onMapProduct.add(new Flour(5, 5, "flour"));
            windMillslist.get(0).refullingLevel = 0;
        }
        }
        for (Flour flour : flourslist) {
            System.out.println(flour.xVal + "  " + flour.yVal + "  :  " + flour.name);
        }


        if (waterTank.getLevelOfRefulling() > 1)
            waterTank.setLevelOfRefulling(waterTank.getLevelOfRefulling() - 1);
        else if (waterTank.getLevelOfRefulling() == 1) {
            waterTank.setLevelOfWater(5);
            waterTank.setLevelOfRefulling(0);
        }


        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (domesticAnimal.getGeneratingProcess() != domesticAnimal.getGeneratingTime())
                domesticAnimal.setGeneratingProcess(domesticAnimal.getGeneratingProcess() + 1);
            else {
                domesticAnimal.setGeneratingProcess(0);
                animalMakingProduct(domesticAnimal);
            }
        }

        int tem2 = 0;
//removing animals...

        while (tem2 == 0) {
            tem2 = 1;
            int count2 = 0;
            for (DomesticAnimal animal : domesticAnimalsList) {
                if (animal.getHealth() == 10) {
                    tem2 = 0;
                    break;
                }
                count2++;
            }
            // System.out.println("count" + count2);
            if (tem2 == 0)
                domesticAnimalsList.remove(count2);
            // System.out.println("here");
        }

        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            domesticAnimal.setHealth(domesticAnimal.getHealth() - 10);
            int tem = random.nextInt(4);
            System.out.println(tem);


            switch (tem) {
                case 0:
                    if (domesticAnimal.xVal < 5)
                        domesticAnimal.xVal += 1;
                    else domesticAnimal.xVal -= 1;
                case 1:
                    if (domesticAnimal.xVal > 0)
                        domesticAnimal.yVal -= 1;
                    else domesticAnimal.yVal += 1;
                case 2:
                    if (domesticAnimal.yVal < 5)
                        domesticAnimal.yVal += 1;
                    else domesticAnimal.yVal -= 1;
                case 3:
                    if (domesticAnimal.yVal > 0)
                        domesticAnimal.yVal -= 1;
                    else domesticAnimal.yVal += 1;
                default:
                    break;
            }


        }
       /* for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }*/

        System.out.println("animals : ");
        for (DomesticAnimal animal : domesticAnimalsList) {
            System.out.println(animal.getName());
            System.out.println(animal.getxVal());
            System.out.println(animal.getyVal());
            System.out.println(animal.getHealth());

        }
        System.out.println("eggs");
        for (Egg egg : eggslist) {
            System.out.print(egg.xVal + "  ");
            System.out.println(egg.yVal);

        }
        System.out.println("feathers");
        for (Feather feather : featherslist) {
            System.out.println(feather.xVal);
            System.out.println(feather.yVal);

        }

        System.out.println("milks");
        for (Milk milk : milkslist) {
            System.out.println(milk.xVal);
            System.out.println(milk.yVal);

        }

        animalsEatingPlant();
        System.out.println("bearlists : ");
        for (Bear bear : bearslist) {
            System.out.println(bear.getxVal() + "  " + bear.getyVal() + "  " + bear.getName() + "  " + bear.health);
        }
        System.out.println("lionslists : ");
        for (Lion lion : lionslist) {
            System.out.println(lion.getxVal() + "  " + lion.getyVal() + "  " + lion.getName() + "  " + lion.health);
        }

//losing product
        tem2=0;
        while (tem2 == 0) {
            tem2 = 1;
            int count2 = 0;
            for (Egg egg : eggslist) {
                egg.setHealth(egg.getHealth()-1);
                if (egg.getHealth() <= 0) {
                    tem2 = 0;
                    break;
                }
                count2++;
            }
            // System.out.println("count" + count2);
            if (tem2 == 0)
                eggslist.remove(count2);
            // System.out.println("here");
        }

        tem2=0;
        while (tem2 == 0) {
            tem2 = 1;
            int count2 = 0;
            for (Feather feather : featherslist) {
                feather.setHealth(feather.getHealth()-1);
                if (feather.getHealth() <= 0) {
                    tem2 = 0;
                    break;
                }
                count2++;
            }
            // System.out.println("count" + count2);
            if (tem2 == 0)
                featherslist.remove(count2);
            // System.out.println("here");
        }
        tem2=0;
        while (tem2 == 0) {
            tem2 = 1;
            int count2 = 0;
            for (Milk milk : milkslist) {
                milk.setHealth(milk.getHealth()-1);
                if (milk.getHealth() <= 0) {
                    tem2 = 0;
                    break;
                }
                count2++;
            }
            // System.out.println("count" + count2);
            if (tem2 == 0)
                milkslist.remove(count2);
            // System.out.println("here");
        }







    }

    private void destroyingfunction() {


        int tem2 = 0;
        while (tem2 == 0) {
            tem2 = 1;
            int count2 = 0;
            for (DomesticAnimal animal : domesticAnimalsList) {
                for (Bear bear : bearslist) {
                    System.out.println("beer");
                    if (animal.xVal == bear.xVal && animal.yVal == bear.yVal) {
                        bearslist.remove(bear);
                        tem2 = 0;
                        break;

                    }

                }
                if (tem2 == 0) break;
                //    System.out.println("duuuuude");
                count2++;
            }

            if (tem2 == 0)
                domesticAnimalsList.remove(count2);


            int tem3 = 0;
            while (tem3 == 0) {
                tem3 = 1;
                int count3 = 0;
                for (DomesticAnimal animal : domesticAnimalsList) {
                    for (Lion lion : lionslist) {
                        if (animal.xVal == lion.xVal && animal.yVal == lion.yVal) {
                            lionslist.remove(lion);
                            tem3 = 0;
                            break;

                        }

                    }
                    if (tem3 == 0) break;
                    //    System.out.println("duuuuude");
                    count3++;
                }

                if (tem3 == 0)
                    domesticAnimalsList.remove(count3);


            }
            for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
                System.out.println("animals: " + domesticAnimal.getxVal() + " " + domesticAnimal.getyVal());
            }
        }

        int tem4 = 0;
        while (tem4 == 0) {
            tem4 = 1;
            int count4 = 0;
            for (Bear bear : bearslist) {
                for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
                    if (defenderAnimal.xVal == bear.xVal && defenderAnimal.yVal == bear.yVal) {
                        //defenderAnimalslist.remove(defenderAnimal);
                        System.out.println("dog died******************");
                        tem4 = 0;
                        break;

                    }

                }
                if (tem4 == 0) break;
                count4++;
            }

            if (tem4 == 0) {
                bearslist.remove(count4);
                System.out.println("bear died***************");
            }
        }



        int tem5 = 0;
        while (tem5 == 0) {
            tem5 = 1;
            int count5 = 0;
            for (Lion lion : lionslist) {
                for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
                    if (defenderAnimal.xVal == lion.xVal && defenderAnimal.yVal == lion.yVal) {
                        //defenderAnimalslist.remove(defenderAnimal);
                        System.out.println("dog died******************");
                        tem4 = 0;
                        break;

                    }

                }
                if (tem5 == 0) break;
                count5++;
            }

            if (tem5 == 0) {
                lionslist.remove(count5);
                System.out.println("lion died***************");
            }
        }


    }

    private void movingBears() {

        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            int tem = random.nextInt(4);
            System.out.println(tem);


            switch (tem) {
                case 0:
                    if (defenderAnimal.xVal < 5)
                        defenderAnimal.xVal += 1;
                    else defenderAnimal.xVal -= 1;
                case 1:
                    if (defenderAnimal.xVal > 0)
                        defenderAnimal.xVal -= 1;
                    else defenderAnimal.xVal += 1;
                case 2:
                    if (defenderAnimal.yVal < 5)
                        defenderAnimal.yVal += 1;
                    else defenderAnimal.yVal -= 1;
                case 3:
                    if (defenderAnimal.yVal > 0)
                        defenderAnimal.yVal -= 1;
                    else defenderAnimal.yVal += 1;
                default:
                    break;
            }
        }


        for (Bear bear : bearslist) {
            int tem = random.nextInt(4);
            System.out.println(tem);


            switch (tem) {
                case 0:
                    if (bear.xVal < 5)
                        bear.xVal += 1;
                    else bear.xVal -= 1;
                case 1:
                    if (bear.xVal > 0)
                        bear.xVal -= 1;
                    else bear.xVal += 1;
                case 2:
                    if (bear.yVal < 5)
                        bear.yVal += 1;
                    else bear.yVal -= 1;
                case 3:
                    if (bear.yVal > 0)
                        bear.yVal -= 1;
                    else bear.yVal += 1;
                default:
                    break;
            }
        }


        for (Lion lion : lionslist) {
            int tem = random.nextInt(4);
            System.out.println(tem);


            switch (tem) {
                case 0:
                    if (lion.xVal < 5)
                        lion.xVal += 1;
                    else lion.xVal -= 1;
                case 1:
                    if (lion.xVal > 0)
                        lion.xVal -= 1;
                    else lion.xVal += 1;
                case 2:
                    if (lion.yVal < 5)
                        lion.yVal += 1;
                    else lion.yVal -= 1;
                case 3:
                    if (lion.yVal > 0)
                        lion.yVal -= 1;
                    else lion.yVal += 1;
                default:
                    break;
            }
        }
        for (Tiger tiger : tigerslist) {

            {
                int tem = random.nextInt(4);
                System.out.println(tem);


                switch (tem) {
                    case 0:
                        if (tiger.xVal < 5)
                            tiger.xVal += 1;
                        else tiger.xVal -= 1;
                    case 1:
                        if (tiger.xVal > 0)
                            tiger.xVal -= 1;
                        else tiger.xVal += 1;
                    case 2:
                        if (tiger.yVal < 5)
                            tiger.yVal += 1;
                        else tiger.yVal -= 1;
                    case 3:
                        if (tiger.yVal > 0)
                            tiger.yVal -= 1;
                        else tiger.yVal += 1;
                    default:
                        break;
                }
            }
        }
    }

    private void putBear() {
        for (Integer integer : bearArrivalTime) {
            if (integer == currentTurn)
                bearslist.add(new Bear("bear"));
        }

        for (Integer integer : lionArrivalTime) {
            if (integer == currentTurn)
                lionslist.add(new Lion("lion"));
        }
        for (Integer integer : tigerArrivalTime) {
            if (integer == currentTurn)
                lionslist.add(new Lion("lion"));
        }
    }

    private void animalsEatingPlant() {
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (domesticAnimal.getHealth() < 50) {
                if (map[domesticAnimal.xVal][domesticAnimal.yVal].equals("p")) {
                    map[domesticAnimal.xVal][domesticAnimal.yVal] = "0";
                    domesticAnimal.setHealth(100);
                }

            }

        }
    }

    private void animalMakingProduct(DomesticAnimal domesticAnimal) {
        if (domesticAnimal.getName().equals("chicken")) {
            eggslist.add(new Egg(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "egg"));
            onMapProduct.add(new Egg(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "egg"));
        }
        else if (domesticAnimal.getName().equals("turkey")) {
            featherslist.add(new Feather(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "feather"));
            onMapProduct.add(new Egg(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "feather"));
        }
            else if (domesticAnimal.getName().equals("buffalo")) {
            milkslist.add(new Milk(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "milk"));
            onMapProduct.add(new Egg(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "milk"));
            }
    }

    private void putCage(String[] split) {
        int xval = Integer.parseInt(split[1]);
        int yval = Integer.parseInt(split[2]);
        map[xval][yval] = "cage";
        for (Bear bear : bearslist) {
            if (bear.getxVal() == xval && bear.getyVal() == yval)
                if (cageLevel == 0) {
                    if (bear.health == 1) {
                        gaindproductslist.add(makebearProduct(bear));
                        bearslist.remove(bear);
                        return;
                    } else {
                        bear.health--;
                        cageLevel = 1;
                    }
                }
        }


        for (Lion lion : lionslist) {
            if (lion.getxVal() == xval && lion.getyVal() == yval)
                if (cageLevel == 0) {
                    if (lion.health == 1) {
                        gaindproductslist.add(makelionProduct(lion));
                        lionslist.remove(lion);
                        return;
                    } else {
                        lion.health--;
                        cageLevel = 1;
                    }
                }
        }
        for (Tiger tiger : tigerslist) {
            if (tiger.getxVal() == xval && tiger.getyVal() == yval)
                if (cageLevel == 0) {
                    if (tiger.health == 1) {
                        gaindproductslist.add(maketigerProduct(tiger));
                        tigerslist.remove(tiger);
                        return;
                    } else {
                        tiger.health--;
                        cageLevel = 1;
                    }
                }
        }

    }
    private void showGaindProducts(){
        System.out.println("gaind product");
        for (Product product : gaindproductslist) {
            System.out.println(product.xVal +"  "+ product.yVal+"  "+ product.getName());
        }
    }

    private Product makebearProduct(Bear bear) {
        BearProduct bearProduct = new BearProduct(bear.xVal, bear.yVal, "bearProduct");
        onMapProduct.add(new BearProduct(bear.xVal, bear.yVal, "bearProduct"));
        return bearProduct;
    }

    private Product makelionProduct(Lion lion) {
        LionProduct lionProduct = new LionProduct(lion.xVal, lion.yVal, "lionProduct");
        onMapProduct.add(new LionProduct(lion.xVal, lion.yVal, "lionProduct"));
        return lionProduct;
    }
    private Product maketigerProduct(Tiger tiger) {
        TigerProduct tigerProduct = new TigerProduct(tiger.xVal, tiger.yVal, "tigerProduct");
        onMapProduct.add(new TigerProduct(tiger.xVal, tiger.yVal, "tigerProduct"));
        return tigerProduct;
    }

    private void buyAnimal(String[] split) {

        if (split[1].equals("dog")) {
            if (currentPlayer.getMoney() < 100) {
                System.out.println("not enough money");
                return;
            } else {
                defenderAnimalslist.add(new Dog("dog"));
            }
        }
        else if (split[1].equals("cat")){
            if (currentPlayer.getMoney() < 150) {
                System.out.println("not enough money");
                return;
            } else {
                defenderAnimalslist.add(new Cat("cat"));
            }
        }

        //if (currentPlayer.getMoney()<)
        DomesticAnimal d;
        //animalslist.add(d = new DomesticAnimal(split[1]));
        domesticAnimalsList.add(d = new DomesticAnimal(split[1]));
        if (currentPlayer.getMoney() < d.getPrice()) {
            System.out.println("not enough money");
            return;
        }
        currentPlayer.setMoney(currentPlayer.getMoney() - d.getPrice());
        if (split[1].equals("chicken"))
            map[d.xVal][d.yVal] = "c";
        else if (split[1].equals("turkey"))
            map[d.xVal][d.yVal] = "turkey";
        else if (split[1].equals("buffalo"))
            map[d.xVal][d.yVal] = "buffalo";


        System.out.println("animals : ");
        for (Animal animal : domesticAnimalsList) {
            System.out.println(animal.getName());
            System.out.println(animal.getxVal());
            System.out.println(animal.getyVal());

        }
        System.out.println("eggs");
        for (Egg egg : eggslist) {
            System.out.println(egg.xVal);
            System.out.println(egg.yVal);

        }
        System.out.println("feathers");
        for (Feather feather : featherslist) {
            System.out.println(feather.xVal);
            System.out.println(feather.yVal);

        }

        System.out.println("milks");
        for (Milk milk : milkslist) {
            System.out.println(milk.xVal);
            System.out.println(milk.yVal);

        }


    }

    private void plant(String[] split) {
        if (waterTank.getLevelOfWater() < 1) {
            System.out.println("no water");
        } else {
            waterTank.setLevelOfWater(waterTank.getLevelOfWater() - 1);
            int xval = Integer.parseInt(split[1]);
            int yval = Integer.parseInt(split[2]);
            map[xval - 1][yval - 1] = "p";
            /*for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }*/
        }
    }

    private void wellWater() {
        //waterTank.setLevelOfWater(5);
        if (waterTank.getLevelOfWater() != 0)
            System.out.println("you have still water in tank");
        else
            waterTank.setLevelOfRefulling(4);
    }

    private void logoutProcess() {
        return;
    }

    public void updateEveryThing() {
        try {
            String info = "";
            FileReader fileReader2 = new FileReader("users.txt");
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            while (((info = bufferedReader2.readLine())) != null) {
                System.out.println(info.split("\\s")[2]);
                playersList.add(new Player(info.split("\\s")[0], info.split("\\s")[1], Integer.parseInt(info.split("\\s")[2])));
            }
            bufferedReader2.close();
            for (Player player : playersList) {
                System.out.println(player.getUserName() + "  " + player.getPassWord() + " " + player.getLevel());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveEveryThing() {
        try {
            FileWriter fw = new FileWriter("users.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Player player : playersList) {
                bw.append(player.getUserName() + " " + player.getPassWord() + " " + (player.getLevel()-1) + "\n");
            }
            //bw.append(username + " " + pass);
            bw.close();

            FileWriter fw2 = new FileWriter("animals.txt");
            BufferedWriter bw2 = new BufferedWriter(fw2);
            for (Animal animal : domesticAnimalsList) {
                bw2.append(animal.getName() + " " + animal.getSpeed() + " " + animal.getxVal() + " " + animal.getyVal() + "\n");
                System.out.println(animal.getxVal());
            }
            bw2.close();

            /*for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }*/
            System.out.println("animals : ");
            for (Animal animal : domesticAnimalsList) {
                System.out.println(animal.getName());
                System.out.println(animal.getxVal());
                System.out.println(animal.getyVal());

            }
            System.out.println("eggs");
            for (Egg egg : eggslist) {
                System.out.print(egg.xVal + "  ");
                System.out.println(egg.yVal);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void catCollectingItems(Cat cat){
        removingItemsOnTheSpot(cat.xVal, cat.yVal);
    }
    private void catsCollecting(){
        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            if(defenderAnimal instanceof Cat) catCollectingItems((Cat)defenderAnimal);
        }
    }
    private void removingItemsOnTheSpot(int x,int y){
        boolean temp=false;
        int index=-1;
        while (!temp){
            for (Product product : onMapProduct) {
                if(product.xVal==x&&product.yVal==y){
                    index=onMapProduct.indexOf(product);
                    break;
                }
            }
            if(index==-1) temp=true;
            else {onMapProduct.remove(index);
                index=-1;
            }
        }
    }

}
