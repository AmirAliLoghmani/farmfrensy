import javax.swing.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Manager {


    String status = "progress";
    int leftPrize=0;
    int currentTurn = 1;
    //int wildAnimalTime[][];
    int maxTime;
    int prizeEndingQuick;
    int currentLevel;
    //int lastLevelThatIsFinished;
    int cageLevel = 0;
    int numberOfLevels;
    int initialCoins;
    int numberOfTasks;
    int chickenGoal = -1;
    int tigerGoal = -1;
    int turkeyGoal = -1;
    int buffaloGoal = -1;
    int coinGoal = -1;
    int numberOfWildAnimals = 0;
    Log log;
    ArrayList<Product> onMapProduct = new ArrayList<>();
    ArrayList<Integer> bearArrivalTime = new ArrayList<>();
    ArrayList<Integer> lionArrivalTime = new ArrayList<>();
    ArrayList<Integer> tigerArrivalTime = new ArrayList<>();

    Player currentPlayer;
    ArrayList<WindMill> windMillslist = new ArrayList<WindMill>();
    ArrayList<FabricMaker> fabricMakerslist = new ArrayList<FabricMaker>();
    ArrayList<BearProduct> bearProductslist = new ArrayList<>();
    ArrayList<LionProduct> lionProductslist = new ArrayList<>();
    ArrayList<TigerProduct> tigerProductslist = new ArrayList<>();
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
    ArrayList<CartoonMilk> cartoonMilkList = new ArrayList<>();
    ArrayList<Clothe> clotheslist = new ArrayList<Clothe>();
    ArrayList<Feather> featherslist = new ArrayList<Feather>();
    ArrayList<Flour> flourslist = new ArrayList<Flour>();
    //  ArrayList<Bread> breadlist = new ArrayList<Flour>();
    ArrayList<Fabric> fabriclist = new ArrayList<>();
    ArrayList<DomesticAnimal> domesticAnimalsList = new ArrayList<DomesticAnimal>();
    ArrayList<DefenderAnimal> defenderAnimalslist = new ArrayList<DefenderAnimal>();
    ArrayList<WildAnimal> wildAnimalsList = new ArrayList<>();

    //String[][] map = new String[6][6];
    int[][] grassMap = new int[6][6];
    Random random = new Random();
    WaterTank waterTank;
    Truck truck = new Truck();

    public void nextLevel() {

    }

    public void deleteEveryThing() {
        domesticAnimalsList.clear();
        onMapProduct.clear();
        windMillslist.clear();
        fabricMakerslist.clear();
        bearProductslist.clear();
        tigerProductslist.clear();
        lionProductslist.clear();
        lionslist.clear();
        bearslist.clear();
        tigerslist.clear();
        bakeryslist.clear();
        iceCreamMakerslist.clear();
        tailoryslist.clear();
        cartoonMilkMakerslist.clear();
        bearslist.clear();
        lionArrivalTime.clear();
        bearArrivalTime.clear();
        tigerArrivalTime.clear();
        gaindproductslist.clear();
        eggslist.clear();
        milkslist.clear();
        cartoonMilkList.clear();
        breadslist.clear();
        icecreamslist.clear();
        clotheslist.clear();
        featherslist.clear();
        flourslist.clear();
        fabriclist.clear();
        defenderAnimalslist.clear();
        if(currentLevel>currentPlayer.getLevel())
            currentPlayer.setLevel(currentLevel);


    }

    public void sendingTruck() {
        if (truck.productListTrans.size() == 0) {
            Messages.truckSentFailedMessage();
            log.errorLog(currentPlayer.getUserName(), "truck sending failed.Truck storage is empty");

        } else {
            if (truck.refullingLevel == 0) {

                truck.refullingLevel = truck.neededTime;
                Messages.truckSentMessage();
                log.infoLog(currentPlayer.getUserName(), "Truck  was sent successfully");
            } else {
                Messages.truckIsBusyMessage();
                log.errorLog(currentPlayer.getUserName(), "Truck sending failed .Truck is at work now ");
            }
        }

    }

    public void makingBuilding(String[] split) {


        if (split[1].equals("windmill")) {
            if (currentPlayer.getMoney() >= 150)
                currentPlayer.setMoney(currentPlayer.getMoney() - 150);
            else {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                return;
            }
            windMillslist.add(new WindMill());
            Messages.buildingAddedSuccessfullyMessage();
            log.infoLog(currentPlayer.getUserName(), "building added successfully");
        } else if (split[1].equals("fabricMaker")) {
            if (currentPlayer.getMoney() >= 250)
                currentPlayer.setMoney(currentPlayer.getMoney() - 250);
            else {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                return;
            }
            Messages.buildingAddedSuccessfullyMessage();
            log.infoLog(currentPlayer.getUserName(), "building added successfully");
            fabricMakerslist.add(new FabricMaker());
        } else if (split[1].equals("bakery")) {

            if (currentPlayer.getMoney() >= 250)
                currentPlayer.setMoney(currentPlayer.getMoney() - 250);
            else {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                return;
            }
            Messages.buildingAddedSuccessfullyMessage();
            log.infoLog(currentPlayer.getUserName(), "building added successfully");
            bakeryslist.add(new Bakery());

        } else if (split[1].equals("tailoring")) {
            if (currentPlayer.getMoney() >= 400)
                currentPlayer.setMoney(currentPlayer.getMoney() - 400);
            else {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                return;
            }
            Messages.buildingAddedSuccessfullyMessage();
            log.infoLog(currentPlayer.getUserName(), "building added successfully");
            tailoryslist.add(new Tailoring());

        } else if (split[1].equals("iceCreamMaker")) {
            if (currentPlayer.getMoney() >= 550)
                currentPlayer.setMoney(currentPlayer.getMoney() - 550);
            else {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                return;
            }
            Messages.buildingAddedSuccessfullyMessage();
            log.infoLog(currentPlayer.getUserName(), "building added successfully");
            iceCreamMakerslist.add(new IceCreamMaker());

        } else if (split[1].equals("cartoonMilkMaker")) {
            if (currentPlayer.getMoney() >= 400)
                currentPlayer.setMoney(currentPlayer.getMoney() - 400);
            else {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                return;
            }
            Messages.buildingAddedSuccessfullyMessage();
            log.infoLog(currentPlayer.getUserName(), "building added successfully");
            cartoonMilkMakerslist.add(new CartoonMilkMaker());

        }
    }

    public void workingBuilding(String[] split) {
        // windMillslist.add(new WindMill());
        int done = 0;
        if (split[1].equals("windmill")) {
            if (windMillslist.size() == 0) {


            }
            if (windMillslist.size() == 1) {
                done++;
                if (windMillslist.get(0).refullingLevel == 0) {
                    done++;
                    windMillslist.get(0).refullingLevel = 4 + 1;
                }
            }

        } else if (split[1].equals("fabricMaker")) {
            if (fabricMakerslist.size() == 0) {

            }
            if (fabricMakerslist.size() == 1) {
                done++;
                if (fabricMakerslist.get(0).refullingLevel == 0) {
                    done++;
                    fabricMakerslist.get(0).refullingLevel = 5 + 1;
                }
            }

        } else if (split[1].equals("bakery")) {
            if (bakeryslist.size() == 0) {

            }
            if (bakeryslist.size() == 1) {
                {
                    done++;
                    if (bakeryslist.get(0).refullingLevel == 0) {
                        done++;
                        bakeryslist.get(0).refullingLevel = 5 + 1;

                    }
                }
            }
        } else if (split[1].equals("tailoring")) {
            if (tailoryslist.size() == 0) {


            }
            if (tailoryslist.size() == 1) {
                done++;
                if (tailoryslist.get(0).refullingLevel == 0) {
                    done++;
                    tailoryslist.get(0).refullingLevel = 6 + 1;
                }

            }
        } else if (split[1].equals("iceCreamMaker")) {
            if (iceCreamMakerslist.size() == 0) {


            }
            if (iceCreamMakerslist.size() == 1) {
                done++;
                if (iceCreamMakerslist.get(0).refullingLevel == 0) {
                    done++;
                    iceCreamMakerslist.get(0).refullingLevel = 7 + 1;
                }
            }
        } else if (split[1].equals("cartoonMilkMaker")) {
            if (cartoonMilkMakerslist.size() == 0) {

            }
            if (cartoonMilkMakerslist.size() == 1) {
                done++;
                if (cartoonMilkMakerslist.get(0).refullingLevel == 0) {
                    done++;
                    cartoonMilkMakerslist.get(0).refullingLevel = 6 + 1;
                }
            }
        } else {
            Messages.invalidCommandMessage();
            log.errorLog(currentPlayer.getUserName(), "invalid command");
            return;
        }
        if (done == 0) {
            Messages.buildFirstMessage();
            log.errorLog(currentPlayer.getUserName(), "work failed. building should be constructed first");
        } else if (done == 1) {
            log.errorLog(currentPlayer.getUserName(), "building is already working");
            Messages.buildingIsAlreadyWorkingMessage();
        } else {
            log.infoLog(currentPlayer.getUserName(), "work started");
            Messages.workStartedMessage();
        }
    }

    private void workingTwoTimesFaster(String name) {
        boolean done = false;
        if (name.equals("windmill")) {
            if (windMillslist.get(0).refullingLevel == 0) {
                done = true;
                windMillslist.get(0).refullingLevel = (windMillslist.get(0).neededTime) / 2 + 1;
            }


        } else if (name.equals("fabricMaker")) {

            if (fabricMakerslist.get(0).refullingLevel == 0) {
                done = true;
                fabricMakerslist.get(0).refullingLevel = (fabricMakerslist.get(0).neededTime) / 2 + 1;
            }

        } else if (name.equals("bakery")) {
            if (bakeryslist.get(0).refullingLevel == 0) {
                done = true;
                bakeryslist.get(0).refullingLevel = (bakeryslist.get(0).neededTime) / 2 + 1;
            }


        } else if (name.equals("tailoring")) {


            if (tailoryslist.get(0).refullingLevel == 0) {
                done = true;
                tailoryslist.get(0).refullingLevel = (tailoryslist.get(0).neededTime) / 2 + 1;
            }


        } else if (name.equals("iceCreamMaker")) {


            if (iceCreamMakerslist.get(0).refullingLevel == 0) {
                done = true;
                iceCreamMakerslist.get(0).refullingLevel = (iceCreamMakerslist.get(0).neededTime) / 2 + 1;
            }


        } else if (name.equals("cartoonMilkMaker")) {


            if (cartoonMilkMakerslist.get(0).refullingLevel == 0) {
                done = true;
                cartoonMilkMakerslist.get(0).refullingLevel = (cartoonMilkMakerslist.get(0).neededTime) / 2 + 1;
            }


        } else {
            Messages.invalidCommandMessage();
            log.errorLog(currentPlayer.getUserName(), "invalid command");
            return;
        }
        if (done) {
            Messages.WorkWithTwoTimesOfNormalSpeedMessage();
            log.infoLog(currentPlayer.getUserName(), "building set to work two times faster");
        } else {
            log.errorLog(currentPlayer.getUserName(), "building is already working");
            Messages.buildingIsAlreadyWorkingMessage();
        }

    }

    public void pickUpProperty(String[] split) {
        int x = Integer.parseInt(split[2]);
        int y = Integer.parseInt(split[3]);
        int c = 0;
        int tem = 0;
        boolean picked = false;

        // for (int i = 0; i < 3; i++) {


        for (Egg egg : eggslist) {
            //System.out.println("hi");
            if (egg.xVal == x && egg.yVal == y && onMapProduct.contains(egg) && gainedProductFreeSpace() >= 1) {
                gaindproductslist.add(new Egg(egg.xVal, egg.yVal, "egg"));

                c = eggslist.indexOf(egg);
                onMapProduct.remove(egg);
                picked = true;

                tem = 1;
                break;
            }

        }
        if (tem == 1)
            eggslist.remove(c);


        c = 0;
        tem = 0;
        for (Flour flour : flourslist) {
            if (flour.xVal == x && flour.yVal == y && onMapProduct.contains(flour) && gainedProductFreeSpace() >= 2) {
                gaindproductslist.add(new Flour(flour.xVal, flour.yVal, "flour"));
                picked = true;
                tem = 1;
                onMapProduct.remove(flour);
                c = flourslist.indexOf(flour);
                break;
            }

        }
        if (tem == 1)
            flourslist.remove(c);


        c = 0;
        tem = 0;
        for (Fabric fabric : fabriclist) {
            if (fabric.xVal == x && fabric.yVal == y && onMapProduct.contains(fabric) && gainedProductFreeSpace() >= 2) {
                gaindproductslist.add(new Fabric(fabric.xVal, fabric.yVal, "fabric"));
                c = fabriclist.indexOf(fabric);
                onMapProduct.remove(fabric);
                onMapProduct.remove(fabric);
                tem = 1;
                picked = true;
                break;
            }

        }
        if (tem == 1)
            fabriclist.remove(c);


        c = 0;
        tem = 0;
        for (Feather feather : featherslist) {
            if (feather.xVal == x && feather.yVal == y && onMapProduct.contains(feather) && gainedProductFreeSpace() >= 1) {
                gaindproductslist.add(new Fabric(feather.xVal, feather.yVal, "feather"));
                c = featherslist.indexOf(feather);
                onMapProduct.remove(feather);
                picked = true;
                tem = 1;
                break;
            }

        }
        if (tem == 1)
            featherslist.remove(c);


        c = 0;
        tem = 0;
        for (Milk milk : milkslist) {
            if (milk.xVal == x && milk.yVal == y && onMapProduct.contains(milk) && gainedProductFreeSpace() >= 1) {
                gaindproductslist.add(new Fabric(milk.xVal, milk.yVal, "milk"));
                c = milkslist.indexOf(milk);
                onMapProduct.remove(milk);
                tem = 1;
                picked = true;
                break;
            }

        }
        if (tem == 1)
            milkslist.remove(c);


        c = 0;
        tem = 0;
        for (Bread bread : breadslist) {
            if (bread.xVal == x && bread.yVal == y && onMapProduct.contains(bread) && gainedProductFreeSpace() >= 4) {
                gaindproductslist.add(new Bread(bread.xVal, bread.yVal, "bread"));
                c = breadslist.indexOf(bread);
                onMapProduct.remove(bread);
                tem = 1;
                picked = true;
                break;
            }

        }
        if (tem == 1)
            breadslist.remove(c);


        c = 0;
        tem = 0;
        for (BearProduct bearProduct : bearProductslist) {
            if (!onMapProduct.contains(bearProduct))
                System.out.println("hey");
            if (bearProduct.xVal == x && bearProduct.yVal == y && onMapProduct.contains(bearProduct) && gainedProductFreeSpace() >= 15) {
                System.out.println("hey 2**************************************");
                gaindproductslist.add(new BearProduct(bearProduct.xVal, bearProduct.yVal, "bearProduct"));
                c = bearProductslist.indexOf(bearProduct);
                onMapProduct.remove(bearProduct);
                tem = 1;
                picked = true;
                break;
            }

        }
        if (tem == 1)
            bearProductslist.remove(c);


        c = 0;
        tem = 0;
        for (Clothe clothe : clotheslist) {
            if (clothe.xVal == x && clothe.yVal == y && onMapProduct.contains(clothe) && gainedProductFreeSpace() >= 4) {
                gaindproductslist.add(new Clothe(clothe.xVal, clothe.yVal, "clothe"));
                c = clotheslist.indexOf(clothe);
                onMapProduct.remove(clothe);
                picked = true;
                tem = 1;
                break;
            }

        }
        if (tem == 1)
            clotheslist.remove(c);


        c = 0;
        tem = 0;
        for (IceCream iceCream : icecreamslist) {
            if (iceCream.xVal == x && iceCream.yVal == y && onMapProduct.contains(iceCream) && gainedProductFreeSpace() >= 4) {
                gaindproductslist.add(new IceCream(iceCream.xVal, iceCream.yVal, "iceCream"));
                c = icecreamslist.indexOf(iceCream);
                onMapProduct.remove(iceCream);
                picked = true;
                tem = 1;
                break;
            }

        }
        if (tem == 1)
            cartoonMilkList.remove(c);
        c = 0;
        tem = 0;
        for (CartoonMilk cartoonMilk : cartoonMilkList) {
            if (cartoonMilk.xVal == x && cartoonMilk.yVal == y && onMapProduct.contains(cartoonMilk) && gainedProductFreeSpace() >= 2) {
                gaindproductslist.add(new IceCream(cartoonMilk.xVal, cartoonMilk.yVal, "cartoonMilk"));
                c = icecreamslist.indexOf(cartoonMilk);
                onMapProduct.remove(cartoonMilk);
                picked = true;
                tem = 1;
                break;
            }

        }
        if (tem == 1)
            cartoonMilkList.remove(c);


        c = 0;
        tem = 0;
        for (LionProduct lionProduct : lionProductslist) {
            if (lionProduct.xVal == x && lionProduct.yVal == y && onMapProduct.contains(lionProduct) && gainedProductFreeSpace() >= 15) {
                gaindproductslist.add(new LionProduct(lionProduct.xVal, lionProduct.yVal, "lionproduct"));
                c = lionProductslist.indexOf(lionProduct);
                onMapProduct.remove(lionProduct);
                picked = true;
                tem = 1;
                break;
            }

        }
        if (tem == 1)
            lionProductslist.remove(c);

        c = 0;
        tem = 0;
        for (TigerProduct tigerProduct : tigerProductslist) {
            if (tigerProduct.xVal == x && tigerProduct.yVal == y && onMapProduct.contains(tigerProduct) && gainedProductFreeSpace() >= 15) {
                gaindproductslist.add(new LionProduct(tigerProduct.xVal, tigerProduct.yVal, "tigerproduct"));
                onMapProduct.remove(tigerProduct);
                c = tigerProductslist.indexOf(tigerProduct);
                picked = true;
                tem = 1;
                break;
            }

        }
        if (tem == 1)
            tigerProductslist.remove(c);

        if (picked&&!split[1].equalsIgnoreCase("no")) {
            Messages.itemPickedMessage();
            log.infoLog(currentPlayer.getUserName(), "some items were picked");
        } else if (!split[1].equalsIgnoreCase("no")){
            Messages.noItemPickedMessage();
            log.errorLog(currentPlayer.getUserName(), "no item was picked");
        }

        showGaindProducts();


    }//}

    public void buyAnimal(String[] split) {

        if (split[1].equals("dog")) {
            if (currentPlayer.getMoney() < 100) {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                return;
            } else {
                log.infoLog(currentPlayer.getUserName(), "dog added successfully");
                Messages.animalBuyingSuccessfulMessage();
                defenderAnimalslist.add(new Dog("dog"));
                currentPlayer.setMoney(currentPlayer.getMoney() - 100);
            }
        } else if (split[1].equals("cat")) {
            if (currentPlayer.getMoney() < 150) {
                Messages.notEnoughMoneyMessage();
                log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                System.out.println("not enough money");
                return;
            }
            else {
                log.infoLog(currentPlayer.getUserName(), "cat added successfully");
                Messages.animalBuyingSuccessfulMessage();
                defenderAnimalslist.add(new Cat("cat"));
                currentPlayer.setMoney(currentPlayer.getMoney() - 150);
            }
        }


        else {
            DomesticAnimal d;

            if (split[1].equalsIgnoreCase("chicken") || split[1].equalsIgnoreCase("turkey") || split[1].equalsIgnoreCase("buffalo")) {
                {
                    domesticAnimalsList.add(d = new DomesticAnimal(split[1]));

                }

                if (currentPlayer.getMoney() < d.getPrice()) {
                    System.out.println("not enough money");
                    Messages.notEnoughMoneyMessage();
                    log.errorLog(currentPlayer.getUserName(), "buy failed due to lack of money");
                    domesticAnimalsList.remove(d);
                    return;
                }
                log.infoLog(currentPlayer.getUserName(), split[1] + "  added successfully");
                Messages.animalBuyingSuccessfulMessage();
                currentPlayer.setMoney(currentPlayer.getMoney() - d.getPrice());
            } else {
                log.errorLog(currentPlayer.getUserName(), "buying failed.This item does not exist");
                Messages.thisItemDoesNotExistMessage();
            }

        }
    }

    public void plant(String[] split) {
        if (waterTank.getLevelOfWater() < 1) {
            Messages.noWaterMessage();
            log.errorLog(currentPlayer.getUserName(), "planting failed.Tank is empty");

        } else {
            Messages.plantSuccessfulMessage();
            log.infoLog(currentPlayer.getUserName(), "plant successful");
            waterTank.setLevelOfWater(waterTank.getLevelOfWater() - 1);
            int xval = Integer.parseInt(split[1]);
            int yval = Integer.parseInt(split[2]);
            grassMap[xval][yval] += 1;
            /*for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }*/
        }
    }

    public void loadingTruck(String[] split) {
        int index = -1;
        for (Product product : gaindproductslist) {
            if (product.name.equals(split[2])) {
                //truck.productListTrans.add(product);
                //gaindproductslist.remove(product);
                index = gaindproductslist.indexOf(product);
                break;
            }
        }
        if (index != -1) {
            if (truckEmptySpace() >= gaindproductslist.get(index).size) {
                truck.productListTrans.add(gaindproductslist.get(index));
                gaindproductslist.remove(index);
            } else {
                log.errorLog(currentPlayer.getUserName(), "truck loading failed.Truck is full");
                Messages.truckIsFullMessage();
            }
        } else {
            log.errorLog(currentPlayer.getUserName(), "load failed .This Item Does Not Exist");
            Messages.thisItemDoesNotExistMessage();
        }


    }

    private void showGaindProducts() {
        System.out.println("gaind product");
        log.infoLog(currentPlayer.getUserName(), "Products on the storage were displayed");
        for (Product product : gaindproductslist) {
            System.out.println(product.xVal + "  " + product.yVal + "  " + product.getName());
        }
    }

    public void wellWater() {
        //waterTank.setLevelOfWater(5);
        if (waterTank.getLevelOfWater() != 0) {
            log.errorLog(currentPlayer.getUserName(), "well failed.There is still water in tank");
            Messages.thereIsStillWaterMessage();
        } else {
            log.infoLog(currentPlayer.getUserName(), "well successful");
            waterTank.setLevelOfRefulling(4);
            System.out.println("welled");
        }
    }

    public void unloadTruck(String split[]) {
        int index = -1;
        for (Product productTran : truck.productListTrans) {
            if (productTran.name.equalsIgnoreCase(split[2])) {
                index = truck.productListTrans.indexOf(productTran);
                gaindproductslist.add(productTran);
                break;

            }
        }
        if (index != -1) {
            truck.productListTrans.remove(index);
            Messages.truckUnloadedSuccessfullyMessage(split[2]);
            log.infoLog(currentPlayer.getUserName(), "Truck unloaded successfully");
        } else {
            log.errorLog(currentPlayer.getUserName(), "unload failed.This item does not exist");
            Messages.thisItemDoesNotExistMessage();
        }
    }

    private void workingAndMakingTwoProduct(String name) {
        String split[]=new String[2];
        split[1]=name;
        if (name.equalsIgnoreCase("icecreammaker")) {
            log.infoLog(currentPlayer.getUserName(), "create two product instead of one");
            Messages.createTwoProductMessage();
            iceCreamMakerslist.get(0).twoProduct = true;
            workingBuilding(split);
        } else if (name.equalsIgnoreCase("cartoonmilkmaker")) {
            log.infoLog(currentPlayer.getUserName(), "create two product instead of one");
            Messages.createTwoProductMessage();
            cartoonMilkMakerslist.get(0).twoProduct = true;
            workingBuilding(split);
        } else if (name.equalsIgnoreCase("tailoring")) {
            log.infoLog(currentPlayer.getUserName(), "create two product instead of one");
            Messages.createTwoProductMessage();
            tailoryslist.get(0).twoProduct = true;
            workingBuilding(split);
        } else if (name.equalsIgnoreCase("windmill")) {
            log.infoLog(currentPlayer.getUserName(), "create two product instead of one");
            Messages.createTwoProductMessage();
            windMillslist.get(0).twoProduct = true;
            workingBuilding(split);
        } else if (name.equalsIgnoreCase("bakery")) {
            log.infoLog(currentPlayer.getUserName(), "create two product instead of one");
            Messages.createTwoProductMessage();
            bakeryslist.get(0).twoProduct = true;
            workingBuilding(split);
        } else if (name.equalsIgnoreCase("fabricmaker")) {
            log.infoLog(currentPlayer.getUserName(), "create two product instead of one");
            Messages.createTwoProductMessage();
            fabricMakerslist.get(0).twoProduct = true;
            workingBuilding(split);
        } else {
            log.errorLog(currentPlayer.getUserName(), "invalid command");
            Messages.invalidCommandMessage();
        }
    }

    public void updateEveryThing() {
        try {
            String info = "";
            FileReader fileReader2 = new FileReader("users.txt");
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
            while (((info = bufferedReader2.readLine())) != null) {
                //    System.out.println(info.split("\\s")[2]);
                playersList.add(new Player(info.split("\\s")[0], info.split("\\s")[1], Integer.parseInt(info.split("\\s")[2])));
            }
            bufferedReader2.close();
           /* for (Player player : playersList) {
                System.out.println(player.getUserName() + "  " + player.getPassWord() + " " + player.getLevel());
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int truckEmptySpace() {
        int volume = 0;
        for (Product productListTran : truck.productListTrans) {
            volume += productListTran.size;
        }
        return (30 - volume);
    }

    public int gainedProductFreeSpace() {
        int size = 0;
        for (Product product : gaindproductslist) {
            size += product.size;
        }

        return (30 - size);
    }

    public Manager() {
        this.log = new Log();
    }

    private boolean isItUpdated(String name) {
        if (name.equalsIgnoreCase("icecreammaker")) {
            if (iceCreamMakerslist.size() == 1) {
                if (iceCreamMakerslist.get(0).updated) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (name.equalsIgnoreCase("cartoonmilkmaker")) {
            if (cartoonMilkMakerslist.size() == 1) {
                if (cartoonMilkMakerslist.get(0).updated) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (name.equalsIgnoreCase("tailoring")) {
            if (tailoryslist.size() == 1) {
                if (!tailoryslist.get(0).updated) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (name.equalsIgnoreCase("windmill")) {
            if (windMillslist.size() == 1) {
                if (windMillslist.get(0).updated) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (name.equalsIgnoreCase("bakery")) {
            if (bakeryslist.size() == 1) {
                if (bakeryslist.get(0).updated) {
                    return true;
                }
                return false;
            }
            return false;
        }
        if (name.equalsIgnoreCase("fabricmaker")) {
            if (fabricMakerslist.size() == 1) {
                if (fabricMakerslist.get(0).updated) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void workingBuildingWithUpdate(String split[]) {
        if (split[2].equalsIgnoreCase("1") && isItUpdated(split[1])) workingTwoTimesFaster(split[1]);
        else if (split[2].equalsIgnoreCase("2") && isItUpdated(split[1])) workingAndMakingTwoProduct(split[1]);
        else {
            Messages.invalidCommandMessage();
            log.errorLog(currentPlayer.getUserName(), "invalid input");
        }
    }


    public void turnTimes(String split[]) {
        int n = Integer.parseInt(split[1]);
        for (int i = 0; i < n; i++) {
            turnTime();
        }
    }

    int chickencount = 0;
    int tigercount = 0;
    int turkeycount = 0;
    int buffalocount = 0;

    private void turnTime() {
        System.out.println("***********************");
        System.out.println("current money :  " + currentPlayer.getMoney());
        System.out.println("turns passed :  " + currentTurn);
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                System.out.print(grassMap[i][j]);
            }
            System.out.println();
        }


        //finishing
        productTimePass();
        removingDecayedItems();
        catsMoveWisely();
        domesticsMoveWisely();
        catsCollecting();
        wildsCageLevelDecrease();
        showGaindProducts();
        chickencount = 0;
        tigercount=0;
        turkeycount = 0;
        buffalocount = 0;
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (domesticAnimal.getName().equals("chicken"))
                chickencount++;
            else if (domesticAnimal.getName().equals("turkey"))
                turkeycount++;
            else if (domesticAnimal.getName().equals("buffalo"))
                buffalocount++;
        }
        for (Product product : gaindproductslist) {
            if (product.getName().equals("tigerproduct"))
                tigercount++;
        }
        //tigercount=tigerProductslist.size();
        //System.out.println("tigerProductslist"+tigerProductslist);


        if (chickencount >= chickenGoal && chickenGoal!= -1)
            chickenGoal = -2;
        if (tigercount >= tigerGoal && tigerGoal!= -1)
            tigerGoal = -2;
        if (turkeycount >= turkeyGoal && turkeyGoal!=-1)
            turkeyGoal = -2;
        if (buffalocount >= buffaloGoal &&buffaloGoal!=-1)
            buffaloGoal = -2;
        if (coinGoal <= currentPlayer.getMoney() && coinGoal!=-1)
            coinGoal = -2;
        if (chickenGoal < 1 && turkeyGoal < 1 && buffaloGoal < 1 && coinGoal <= currentPlayer.getMoney()&&tigerGoal<1) {
            currentPlayer.setMoney(0);
            if (currentTurn<maxTime){
                System.out.println("prize earned");
                leftPrize=prizeEndingQuick;
            }
            status = "win";
            System.out.println("YOU WON!!!  ");
            log.infoLog(currentPlayer.getUserName(), "level finished");
            // System.out.println(currentLevel);
            currentTurn = 1;

            //int wildAnimalTime[][];
            maxTime = 0;
            prizeEndingQuick = 0;
            chickenGoal = -1;
            tigerGoal=-1;
            turkeyGoal = -1;
            buffaloGoal = -1;
            coinGoal = -1;
            return;
        }
        System.out.println("GOALS :");

        if (chickenGoal == -2)
            System.out.println("chicken "+(char)10003);
        if (tigerGoal == -2)
            System.out.println("tiger "+(char)10003);

        if (turkeyGoal == -2)
            System.out.println("turkey "+(char)10003);
        if (buffaloGoal == -2)
            System.out.println("buffalo "+(char)10003);
        if (coinGoal == -2)
            System.out.println("CoinGoal "+(char)10003);



        if (chickenGoal != -1&&chickenGoal != -2 )
            System.out.println("chicken :     catched  :  " + chickencount + "  goal  :  " + chickenGoal);
        if (tigerGoal != -1&&tigerGoal != -2 )
            System.out.println("tiger :     catched  :  " + tigercount + "  goal  :  " + tigerGoal);

        if (turkeyGoal != -1&&turkeyGoal != -2)
            System.out.println("turkey :     catched  :  " + turkeycount + "  goal  :  " + turkeyGoal);
        if (buffaloGoal != -1&&buffaloGoal != -2)
            System.out.println("buffalo :     catched  :  " + buffalocount + "  goal  :  " + buffaloGoal);
        if (coinGoal != -1&&coinGoal != -2)
            System.out.println("CoinGoal :     catched  :  " + coinGoal + "  goal  :  " + currentPlayer.getMoney());

        //System.out.println("PRODUCTS ON MAP : ");
       /* System.out.println(onMapProduct.size());
        for (Product product : onMapProduct) {
            System.out.println(product.getName());
        }*/

        // System.out.println("chicken goal" + chickenGoal);
        // System.out.println("turkey goal" + turkeyGoal);
        // System.out.println("buffalo goal" + buffaloGoal);
        // System.out.println("coin goal" + coinGoal);


        currentTurn++;
        cageLevel = 0;
        putBear();
        movingWildAndDog();
        destroyingfunction();
        justTigersMove();
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
        if (fabricMakerslist.size() == 1) {
            System.out.println("fabric maker refulling   :   " + fabricMakerslist.get(0).refullingLevel);
            if (fabricMakerslist.get(0).refullingLevel > 1) {
                fabricMakerslist.get(0).refullingLevel--;
            } else if (fabricMakerslist.get(0).refullingLevel == 1) {
                //making product
                Fabric temp = new Fabric(6, 1, "fabric");
                fabriclist.add(temp);
                onMapProduct.add(temp);
                if (fabricMakerslist.get(0).twoProduct) {
                    Fabric veryTemp = new Fabric(6, 1, "fabric");
                    fabriclist.add(veryTemp);
                    onMapProduct.add(veryTemp);
                    fabricMakerslist.get(0).twoProduct = false;
                }
                fabricMakerslist.get(0).refullingLevel = 0;
            }
        }
        if (bakeryslist.size() == 1) {
            System.out.println("bakery refulling   :   " + bakeryslist.get(0).refullingLevel);
            if (bakeryslist.get(0).refullingLevel > 1) {
                bakeryslist.get(0).refullingLevel--;
            } else if (bakeryslist.get(0).refullingLevel == 1) {
                //making product
                Bread temp = new Bread(5, 1, "bread");
                breadslist.add(temp);
                onMapProduct.add(temp);
                if (bakeryslist.get(0).twoProduct) {
                    Bread veryTemp = new Bread(5, 1, "bread");
                    breadslist.add(veryTemp);
                    onMapProduct.add(veryTemp);
                    bakeryslist.get(0).twoProduct = false;
                }
                bakeryslist.get(0).refullingLevel = 0;
            }
        }
        if (tailoryslist.size() == 1) {
            System.out.println("tailoring refulling   :   " + tailoryslist.get(0).refullingLevel);
            if (tailoryslist.get(0).refullingLevel > 1) {
                tailoryslist.get(0).refullingLevel--;
            } else if (tailoryslist.get(0).refullingLevel == 1) {
                //making product
                Clothe temp1 = new Clothe(4, 6, "clothe");
                clotheslist.add(temp1);
                onMapProduct.add(temp1);
                if (tailoryslist.get(0).twoProduct) {
                    Clothe veryTemp1 = new Clothe(4, 6, "clothe");
                    clotheslist.add(veryTemp1);
                    onMapProduct.add(veryTemp1);
                    tailoryslist.get(0).twoProduct = false;

                }
                tailoryslist.get(0).refullingLevel = 0;
            }
        }

        if (iceCreamMakerslist.size() == 1) {
            System.out.println("icecream refulling   :   " + iceCreamMakerslist.get(0).refullingLevel);
            if (iceCreamMakerslist.get(0).refullingLevel > 1) {
                iceCreamMakerslist.get(0).refullingLevel--;
            } else if (iceCreamMakerslist.get(0).refullingLevel == 1) {
                //making product
                IceCream temp3 = new IceCream(4, 1, "iceCream");
                icecreamslist.add(temp3);
                onMapProduct.add(temp3);
                if (iceCreamMakerslist.get(0).twoProduct) {
                    IceCream veryTemp3 = new IceCream(4, 1, "iceCream");
                    icecreamslist.add(veryTemp3);
                    onMapProduct.add(veryTemp3);

                    iceCreamMakerslist.get(0).twoProduct = false;
                }
                iceCreamMakerslist.get(0).refullingLevel = 0;
            }
        }
        if (cartoonMilkMakerslist.size() == 1) {
            System.out.println("cartoon milk maker refulling   :   " + cartoonMilkMakerslist.get(0).refullingLevel);
            if (cartoonMilkMakerslist.get(0).refullingLevel > 1) {
                cartoonMilkMakerslist.get(0).refullingLevel--;
            } else if (cartoonMilkMakerslist.get(0).refullingLevel == 1) {
                //making product
                CartoonMilk temp4 = new CartoonMilk(5, 6, "milk");
                cartoonMilkList.add(temp4);
                onMapProduct.add(temp4);
                if (cartoonMilkMakerslist.get(0).twoProduct) {
                    CartoonMilk veryTemp4 = new CartoonMilk(5, 6, "milk");
                    cartoonMilkList.add(veryTemp4);
                    onMapProduct.add(veryTemp4);
                    cartoonMilkMakerslist.get(0).twoProduct = false;
                }
                cartoonMilkMakerslist.get(0).refullingLevel = 0;
            }
        }
        if (windMillslist.size() == 1) {
            System.out.println("refulling   :   " + windMillslist.get(0).refullingLevel);
            if (windMillslist.get(0).refullingLevel > 1) {
                windMillslist.get(0).refullingLevel--;
            } else if (windMillslist.get(0).refullingLevel == 1) {
                //making product
                Flour temp5 = new Flour(6, 6, "flour");
                flourslist.add(temp5);
                onMapProduct.add(temp5);
                if (windMillslist.get(0).twoProduct) {
                    Flour veryTemp5 = new Flour(6, 6, "flour");
                    flourslist.add(veryTemp5);
                    onMapProduct.add(veryTemp5);
                    windMillslist.get(0).twoProduct = false;

                }
                windMillslist.get(0).refullingLevel = 0;
            }
        }
        /*for (Flour flour : flourslist) {
            System.out.println(flour.xVal + "  " + flour.yVal + "  :  " + flour.name);
        }*/


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
            //  System.out.println(tem);


            /*switch (tem) {
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
            }*/


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
        /*System.out.println("eggs");
        for (Egg egg : eggslist) {
            System.out.print(egg.xVal + "  ");
            System.out.println(egg.yVal);

        }
        System.out.println("feathers");
        for (Feather feather : featherslist) {
            System.out.print(feather.xVal);
            System.out.println(feather.yVal);

        }

        System.out.println("milks");
        for (Milk milk : milkslist) {
            System.out.print(milk.xVal);
            System.out.println(milk.yVal);

        }
        for (Bread bread : breadslist) {
            System.out.println("bread");
            System.out.print(bread.xVal + "  ");
            System.out.println(bread.yVal);
        }

        System.out.println("cartoonMilks");
        for (CartoonMilk cartoonMilk : cartoonMilkList) {
            System.out.print(cartoonMilk.xVal);
            System.out.println(cartoonMilk.yVal);

        }
        System.out.println("flours");
        for (Flour flour : flourslist) {
            System.out.print(flour.xVal);
            System.out.println(flour.yVal);

        }


        System.out.println("fabrics");
        for (Fabric fabric : fabriclist) {
            System.out.print(fabric.xVal);
            System.out.println(fabric.yVal);

        }

        System.out.println("clothes");
        for (Clothe clothe : clotheslist) {
            System.out.print(clothe.xVal);
            System.out.println(clothe.yVal);

        }

        System.out.println("iceCreams");
        for (IceCream iceCream : icecreamslist) {
            System.out.print(iceCream.xVal);
            System.out.println(iceCream.yVal);

        }
*/

        animalsEatingPlant();
        System.out.println("bearlists : ");
        for (Bear bear : bearslist) {
            System.out.println(bear.getxVal() + "  " + bear.getyVal() + "  " + bear.getName() + "  " + bear.health);
        }

        System.out.println("lionslists : ");
        for (Lion lion : lionslist) {
            System.out.println(lion.getxVal() + "  " + lion.getyVal() + "  " + lion.getName() + "  " + lion.health);
        }
        System.out.println("tigerslists : "+tigerslist.size());

        for (Tiger tiger : tigerslist) {
            System.out.println(tiger.getxVal() + "  " + tiger.getyVal() + "  " + tiger.getName() + "  " + tiger.health);

        }

        System.out.println("cats list");
        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            if(defenderAnimal instanceof Cat) System.out.println(defenderAnimal.xVal+" "+defenderAnimal.yVal);
        }
        showGaindProducts();
        System.out.println("dog");{
            for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
                if(defenderAnimal instanceof Dog){
                    if(((Dog) defenderAnimal).alive)
                        System.out.println(defenderAnimal.xVal+" "+defenderAnimal.yVal);
                }
            }
        }
        System.out.println("on map");
        for (Product product : onMapProduct) {
            System.out.println(product.xVal+" "+product.yVal+" "+product.name);
        }
//losing product
/*
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
*/

        System.out.println("***********************");
    }

    private void animalsEatingPlant() {
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            if (domesticAnimal.getHealth() < 50) {
                if (grassMap[domesticAnimal.xVal][domesticAnimal.yVal] >= 1) {
                    grassMap[domesticAnimal.xVal][domesticAnimal.yVal] -= 1;
                    domesticAnimal.setHealth(100);
                }

            }

        }
    }

    public void updateBuilding(String split[]) {
        if (split[1].equalsIgnoreCase("icecreammaker")) {
            if (iceCreamMakerslist.size() == 1) {
                if (!iceCreamMakerslist.get(0).updated) {
                    iceCreamMakerslist.get(0).updated = true;
                    Messages.updateSuccessfulMessage();
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                } else {
                    Messages.alreadyUpdatedMessage();
                    log.errorLog(currentPlayer.getUserName(), "update failed . Building is already updated");
                }
            } else {
                log.errorLog(currentPlayer.getUserName(), "update failed . Building does not exist");
                Messages.buildFirstMessage();
            }
        }
        if (split[1].equalsIgnoreCase("cartoonmilkmaker")) {
            if (cartoonMilkMakerslist.size() == 1) {
                if (!cartoonMilkMakerslist.get(0).updated) {
                    cartoonMilkMakerslist.get(0).updated = true;
                    Messages.updateSuccessfulMessage();
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                } else {
                    log.errorLog(currentPlayer.getUserName(), "update failed . Building is already updated");
                    Messages.alreadyUpdatedMessage();
                }
            } else {
                log.errorLog(currentPlayer.getUserName(), "update failed . Building does not exist");
                Messages.buildFirstMessage();
            }
        }
        if (split[1].equalsIgnoreCase("tailoring")) {
            if (tailoryslist.size() == 1) {
                if (!tailoryslist.get(0).updated) {
                    tailoryslist.get(0).updated = true;
                    Messages.updateSuccessfulMessage();
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                } else {
                    log.errorLog(currentPlayer.getUserName(), "update failed . Building is already updated");
                    Messages.alreadyUpdatedMessage();
                }
            } else {
                log.errorLog(currentPlayer.getUserName(), "update failed . Building does not exist");
                Messages.buildFirstMessage();
            }
        }
        if (split[1].equalsIgnoreCase("windmill")) {
            if (windMillslist.size() == 1) {
                if (!windMillslist.get(0).updated) {
                    windMillslist.get(0).updated = true;
                    Messages.updateSuccessfulMessage();
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                } else {
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                    Messages.alreadyUpdatedMessage();
                }
            } else {
                log.errorLog(currentPlayer.getUserName(), "update failed . Building does not exist");
                Messages.buildFirstMessage();
            }
        }
        if (split[1].equalsIgnoreCase("bakery")) {
            if (bakeryslist.size() == 1) {
                if (!bakeryslist.get(0).updated) {
                    bakeryslist.get(0).updated = true;
                    Messages.updateSuccessfulMessage();
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                } else {
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                    Messages.alreadyUpdatedMessage();
                }
            } else {
                log.errorLog(currentPlayer.getUserName(), "update failed . Building does not exist");
                Messages.buildFirstMessage();
            }
        }
        if (split[1].equalsIgnoreCase("fabricmaker")) {
            if (fabricMakerslist.size() == 1) {
                if (!fabricMakerslist.get(0).updated) {
                    fabricMakerslist.get(0).updated = true;
                    Messages.updateSuccessfulMessage();
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                } else {
                    log.infoLog(currentPlayer.getUserName(), "building updated");
                    Messages.alreadyUpdatedMessage();
                }
            } else {
                log.errorLog(currentPlayer.getUserName(), "update failed . Building does not exist");
                Messages.buildFirstMessage();
            }
        }
    }

    public void wildsCageLevelDecrease() {
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.lastCagedTurn < currentTurn) {
                if ((wildAnimal instanceof Tiger && wildAnimal.health < 3) || (wildAnimal instanceof Lion && wildAnimal.health < 3) || (wildAnimal instanceof Bear && wildAnimal.health < 4)) {
                    wildAnimal.health += 1;
                    wildAnimal.lastCagedTurn = currentTurn;
                }
            }
        }
    }

    public void putCage(String[] split) {
        int xval = Integer.parseInt(split[1]);
        int yval = Integer.parseInt(split[2]);
        int index = -1;
        // map[xval][yval] = "cage";
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if (wildAnimal.xVal == xval && wildAnimal.yVal == yval) {
                if (wildAnimal.health == 1) index = wildAnimalsList.indexOf(wildAnimal);
                else {
                    wildAnimal.health -= 1;
                    wildAnimal.lastCagedTurn = currentTurn;
                }
                break;
            }
        }
        if (index != -1) {
            if (wildAnimalsList.get(index).getName().equalsIgnoreCase("tiger")) {
                onMapProduct.add(maketigerProduct((Tiger) wildAnimalsList.get(index)));
                tigerslist.remove(wildAnimalsList.get(index));
            } else if (wildAnimalsList.get(index).getName().equalsIgnoreCase("lion")) {
                onMapProduct.add(makelionProduct((Lion) wildAnimalsList.get(index)));
                lionslist.remove(wildAnimalsList.get(index));
            } else {
                onMapProduct.add(makebearProduct((Bear) wildAnimalsList.get(index)));
                bearslist.remove(wildAnimalsList.get(index));
            }

            wildAnimalsList.remove(index);
        }
       /* for (Bear bear : bearslist) {
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
*/
    }

    private void destroyDomesticAnimals() {
        ArrayList<Integer> indexes = new ArrayList();
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            for (WildAnimal wildAnimal : wildAnimalsList) {
                if (domesticAnimal.xVal == wildAnimal.xVal && domesticAnimal.yVal == wildAnimal.yVal) {
                    indexes.add(domesticAnimalsList.indexOf(domesticAnimal));
                    break;
                }
            }
        }
        for (int i = 0; i < indexes.size(); i++) {
            domesticAnimalsList.remove(indexes.get(i));
        }
    }


    private void dogVsWilds() {
        int indexOfDogs ;
        int indexOfWild;


        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            indexOfWild = -1;
            indexOfDogs=-1;
            if (defenderAnimal instanceof Dog&&((Dog) defenderAnimal).alive) {
                if(((Dog) defenderAnimal).alive){



                    for (WildAnimal wildAnimal : wildAnimalsList) {
                        if (wildAnimal.xVal == defenderAnimal.xVal && wildAnimal.yVal == defenderAnimal.yVal) {
                            indexOfWild = wildAnimalsList.indexOf(wildAnimal);
                            indexOfDogs=defenderAnimalslist.indexOf(defenderAnimal);
                            break;
                        }
                    }
                    if (indexOfWild != -1) {
                        if (wildAnimalsList.get(indexOfWild).getName().equalsIgnoreCase("tiger"))
                            tigerslist.remove(wildAnimalsList.get(indexOfWild));
                        else if (wildAnimalsList.get(indexOfWild).getName().equalsIgnoreCase("lion"))
                            lionslist.remove(wildAnimalsList.get(indexOfWild));
                        else bearslist.remove(wildAnimalsList.get(indexOfWild));
                        ((Dog) defenderAnimal).alive=false;
                    }
                }}
        }



    }
    private void destroyingProducts(){
        ArrayList<Integer> indexes=new ArrayList<>();
        for (WildAnimal wildAnimal : wildAnimalsList) {
            for (Product product : onMapProduct) {
                if(wildAnimal.xVal==product.xVal&&wildAnimal.yVal==product.yVal) indexes.add(onMapProduct.indexOf(product));
            }
        }
        for (int i = 0; i <indexes.size() ; i++) {
            onMapProduct.remove(i);
        }
    }
    private void destroyingfunction() {
        destroyDomesticAnimals();
        dogVsWilds();
        destroyingProducts();
       /* int tem = 0;
        while (tem == 0) {
            tem = 1;
            int count = 0;
            for (DomesticAnimal animal : domesticAnimalsList) {
                for (Bear bear : bearslist) {
                    System.out.println("beer");
                    if (animal.xVal == bear.xVal && animal.yVal == bear.yVal) {
                        bearslist.remove(bear);
                        tem = 0;
                        break;
                    }
                }
                if (tem == 0) break;
                //    System.out.println("duuuuude");
                count++;
            }
            if (tem == 0)
                domesticAnimalsList.remove(count);
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
*/

    }

    private void animalMakingProduct(DomesticAnimal domesticAnimal){
        if (domesticAnimal.getName().equals("chicken")) {// TODO: 6/16/2021
            Egg egg = new Egg(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "egg");
            eggslist.add(egg);
            onMapProduct.add(egg);
        } else if (domesticAnimal.getName().equals("turkey")) {
            Feather feather = new Feather(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "feather");
            featherslist.add(feather);
            onMapProduct.add(feather);
        } else if (domesticAnimal.getName().equals("buffalo")) {
            Milk milk = new Milk(domesticAnimal.getxVal(), domesticAnimal.getyVal(), "milk");
            milkslist.add(milk);
            onMapProduct.add(milk);
        }
    }


    private void movingWildAndDog() {
        int tem;
        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            tem = random.nextInt(4);
            random.nextInt();
            if (defenderAnimal instanceof Dog) {






                if(tem==0) {

                    if (defenderAnimal.xVal < 6)
                        defenderAnimal.xVal += 1;
                    else defenderAnimal.xVal -= 1;
                }else if(tem==1) {
                    if (defenderAnimal.xVal > 1)
                        defenderAnimal.xVal -= 1;
                    else defenderAnimal.xVal += 1;
                }else if(tem==2) {
                    if (defenderAnimal.yVal < 6)
                        defenderAnimal.yVal += 1;
                    else defenderAnimal.yVal -= 1;
                }else if(tem==3) {
                    if (defenderAnimal.yVal > 1)
                        defenderAnimal.yVal -= 1;
                    else defenderAnimal.yVal += 1;
                }
            }
        }


        for (WildAnimal wildAnimal : wildAnimalsList) {

            tem=random.nextInt(4);

            if(tem==0) {
                //  System.out.println(0);
                if (wildAnimal.xVal < 6)
                    wildAnimal.xVal += 1;
                else wildAnimal.xVal -= 1;
            }else if(tem==1) {
                //System.out.println(1);
                if (wildAnimal.xVal > 1)
                    wildAnimal.xVal -= 1;
                else wildAnimal.xVal += 1;
            }else if(tem==2) {
                // System.out.println(2);
                if (wildAnimal.yVal < 6)
                    wildAnimal.yVal += 1;
                else wildAnimal.yVal -= 1;
            }else if(tem==3) {
                //System.out.println(3);
                if (wildAnimal.yVal > 1)
                    wildAnimal.yVal -= 1;
                else wildAnimal.yVal += 1;
            }

        }


    }

    private Product makebearProduct(Bear bear) {
        BearProduct bearProduct = new BearProduct(bear.xVal, bear.yVal, "bearProduct");

        bearProductslist.add(bearProduct);
        return bearProduct;
    }

    private Product makelionProduct(Lion lion) {
        LionProduct lionProduct = new LionProduct(lion.xVal, lion.yVal, "lionProduct");

        lionProductslist.add(lionProduct);
        return lionProduct;
    }

    private Product maketigerProduct(Tiger tiger) {
        TigerProduct tigerProduct = new TigerProduct(tiger.xVal, tiger.yVal, "tigerProduct");

        tigerProductslist.add(tigerProduct);
        return tigerProduct;
    }

    private void putBear() {
        for (Integer integer : bearArrivalTime) {
            if (integer == currentTurn)
            {
                Bear temp=new Bear("bear");
                bearslist.add(temp);
                wildAnimalsList.add(temp);
            }}

        for (Integer integer : lionArrivalTime) {
            if (integer == currentTurn)
            {Lion veryTemp=new Lion("lion");
                lionslist.add(veryTemp);
                wildAnimalsList.add(veryTemp);
            }
        }
        for (Integer integer : tigerArrivalTime) {
            if (integer == currentTurn)
            {   Tiger veryVeryTemp=new Tiger("tiger");
                tigerslist.add(veryVeryTemp);
                wildAnimalsList.add(veryVeryTemp);
            }
        }
    }

    private void productTimePass() {
        for (Product product : onMapProduct) {
            product.setHealth(product.getHealth() - 1);

        }
    }

    private void removingDecayedItems() {
        boolean temp = false;
        int index = -1;
        while (!temp) {
            for (Product product : onMapProduct) {
                if (product.getHealth() == 0) {
                    index = onMapProduct.indexOf(product);
                    break;
                }
            }
            if (index == -1) temp = true;
            else {
                onMapProduct.remove(index);
                index = -1;
            }
        }
    }

    private void catCollectingItems(Cat cat) {

        String[] split=new String[4];
        split[1]="no";
        split[2]=String.valueOf(cat.xVal);
        split[3]=String.valueOf(cat.yVal);
        pickUpProperty(split);
        // removingItemsOnTheSpot(cat.xVal, cat.yVal);
    }

    private void catsCollecting() {
        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            if (defenderAnimal instanceof Cat) catCollectingItems((Cat) defenderAnimal);
        }
    }

   /* private void removingItemsOnTheSpot(int x, int y) {
        boolean temp = false;
        int index = -1;
        while (!temp) {
            for (Product product : onMapProduct) {
                if (product.xVal == x && product.yVal == y && product.size < gainedProductFreeSpace()) {
                    index = onMapProduct.indexOf(product);
                    break;
                }
            }
            if (index == -1) temp = true;
            else {
                onMapProduct.remove(index);
                index = -1;
            }
        }
    }*/

    private void domesticsMoveWisely() {
        for (DomesticAnimal domesticAnimal : domesticAnimalsList) {
            wiseMovementForDomestic(domesticAnimal);
        }
    }
    private void dogsMoveWisely(){
        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            if (defenderAnimal instanceof Dog) {
                if(((Dog) defenderAnimal).alive)
                    wiseMovementForDog((Dog) defenderAnimal);
            }
        }
    }
    private void wiseMovementForDomestic(DomesticAnimal domesticAnimal) {
        int x = domesticAnimal.xVal;
        int y = domesticAnimal.yVal;
        int minX = -1;
        int minY = -1;
        int minDistance = 64;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (grassMap[i][j] > 0) {
                    if (((i - x) * (i - x)) + ((j - y) * (j - y)) < minDistance&&(((i - x) * (i - x)) + ((j - y) * (j - y)) >0)) {
                        minX = i;
                        minY = j;
                        minDistance = ((i - x) * (i - x)) + ((j - y) * (j - y));
                    }
                }
            }



        }
        if (minX != -1) {

            if ((Math.abs(minX - x) > Math.abs(minY - y))&&(minY!=y)) {
                if (minY > y) y += 1;
                else  y -= 1;

            }
            else if ((Math.abs(minX - x) < Math.abs(minY - y))&&(minX!=x)) {
                if (minX > x) x += 1;
                else  x -= 1;

            }
            else if((minY!=y)&&(minX==x)){
                if (minY > y) y += 1;
                else  y -= 1;
            }
            else if((minY==y)&&(minX!=x)){
                if (minX > x) x += 1;
                else  x -= 1;
            }
            domesticAnimal.yVal = y;
            domesticAnimal.xVal = x;

        } else randomMove(domesticAnimal);

    }

    private void wiseMovementForCat(Cat cat) {
        int x = cat.xVal;
        int y = cat.yVal;
        int minX = -1;
        int minY = -1;
        int minDistance = 64;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (isAnItemInThisSpot(i, j)) {
                    if (((i - x) * (i - x)) + ((j - y) * (j - y)) < minDistance) {
                        minX = i;
                        minY = j;
                        minDistance = ((i - x) * (i - x)) + ((j - y) * (j - y));
                    }
                }
            }

        }
        if (minX != -1) {

            if ((Math.abs(minX - x) > Math.abs(minY - y))&&(minY!=y)) {
                if (minY > y) y += 1;
                else  y -= 1;

            }
            else if ((Math.abs(minX - x) < Math.abs(minY - y))&&(minX!=x)) {
                if (minX > x) x += 1;
                else  x -= 1;

            }
            else if((minY!=y)&&(minX==x)){
                if (minY > y) y += 1;
                else  y -= 1;
            }
            else if((minY==y)&&(minX!=x)){
                if (minX > x) x += 1;
                else  x -= 1;
            }
            cat.yVal = y;
            cat.xVal = x;

        } else {

            randomMove(cat);
        }



    }
    private void wiseMovementForDog(Dog dog) {
        int x = dog.xVal;
        int y = dog.yVal;
        int minX = -1;
        int minY = -1;
        int minDistance = 64;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (isAWildInThisSpot(i, j)) {
                    if (((i - x) * (i - x)) + ((j - y) * (j - y)) < minDistance) {
                        minX = i;
                        minY = j;
                        minDistance = ((i - x) * (i - x)) + ((j - y) * (j - y));
                    }
                }
            }

        }
        if (minX != -1) {

            if ((Math.abs(minX - x) > Math.abs(minY - y))&&(minY!=y)) {
                if (minY > y) y += 1;
                else  y -= 1;

            }
            else if ((Math.abs(minX - x) < Math.abs(minY - y))&&(minX!=x)) {
                if (minX > x) x += 1;
                else  x -= 1;

            }
            else if((minY!=y)&&(minX==x)){
                if (minY > y) y += 1;
                else  y -= 1;
            }
            else if((minY==y)&&(minX!=x)){
                if (minX > x) x += 1;
                else  x -= 1;
            }
            dog.yVal = y;
            dog.xVal = x;

        } else {

            randomMove(dog);
        }



    }

    private void catsMoveWisely() {
        for (DefenderAnimal defenderAnimal : defenderAnimalslist) {
            if (defenderAnimal instanceof Cat) wiseMovementForCat((Cat) defenderAnimal);
        }
    }

    private void randomMove(Animal animal) {
        int tem = random.nextInt(4);


        if(tem==0) {
            if (animal.xVal < 5)
                animal.xVal += 1;
            else animal.xVal -= 1;
        }else if(tem==1) {
            if (animal.xVal > 0)
                animal.xVal -= 1;
            else animal.xVal += 1;
        }else if(tem==2) {
            if (animal.yVal < 5)
                animal.yVal += 1;
            else animal.yVal -= 1;
        }else if(tem==3) {
            if (animal.yVal > 0)
                animal.yVal -= 1;
            else animal.yVal += 1;
        }
    }

    private boolean isAnItemInThisSpot(int x, int y) {
        for (Product product : onMapProduct) {
            if (product.xVal == x && product.yVal == y) return true;
        }
        return false;
    }
    private boolean isAWildInThisSpot(int x, int y) {
        for (WildAnimal wildAnimal : wildAnimalsList) {
            if(wildAnimal.yVal==y&&wildAnimal.xVal==x) return true;
        }
        return false;
    }
    private void justTigersMove() {
        for (Tiger tiger : tigerslist) {
            randomMove(tiger);
        }
    }

    public void inquiry(String[] split) {
        System.out.println("***********************");
        System.out.println("turns passed :  " + currentTurn);
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 6; j++) {
                System.out.print(grassMap[i][j]);
            }
            System.out.println();
        }
        System.out.println("GOALS :");
        if (chickenGoal != -1)
            System.out.println("chicken :     catched  :  " + chickencount + "  goal  :  " + chickenGoal);
        if (tigerGoal != -1)
            System.out.println("tiger :     catched  :  " + tigercount + "  goal  :  " + tigerGoal);

        if (turkeyGoal != -1)
            System.out.println("turkey :     catched  :  " + turkeycount + "  goal  :  " + turkeyGoal);
        if (buffaloGoal != -1)
            System.out.println("buffalo :     catched  :  " + buffalocount + "  goal  :  " + buffaloGoal);
        if (coinGoal != -1)
            System.out.println("CoinGoal :     catched  :  " + coinGoal + "  goal  :  " + currentPlayer.getMoney());
        System.out.println("animals : ");
        for (DomesticAnimal animal : domesticAnimalsList) {
            System.out.println(animal.getName());
            System.out.println(animal.getxVal());
            System.out.println(animal.getyVal());
            System.out.println(animal.getHealth());

        }
       /* System.out.println("eggs");
        for (Egg egg : eggslist) {
            System.out.print(egg.xVal + "  ");
            System.out.println(egg.yVal);

        }
        System.out.println("feathers");
        for (Feather feather : featherslist) {
            System.out.print(feather.xVal);
            System.out.println(feather.yVal);

        }

        System.out.println("milks");
        for (Milk milk : milkslist) {
            System.out.print(milk.xVal);
            System.out.println(milk.yVal);

        }
        for (Bread bread : breadslist) {
            System.out.println("bread");
            System.out.print(bread.xVal + "  ");
            System.out.println(bread.yVal);
        }

        System.out.println("cartoonMilks");
        for (CartoonMilk cartoonMilk : cartoonMilkList) {
            System.out.print(cartoonMilk.xVal);
            System.out.println(cartoonMilk.yVal);

        }
        System.out.println("flours");
        for (Flour flour : flourslist) {
            System.out.print(flour.xVal);
            System.out.println(flour.yVal);

        }


        System.out.println("fabrics");
        for (Fabric fabric : fabriclist) {
            System.out.print(fabric.xVal);
            System.out.println(fabric.yVal);

        }

        System.out.println("clothes");
        for (Clothe clothe : clotheslist) {
            System.out.print(clothe.xVal);
            System.out.println(clothe.yVal);

        }

        System.out.println("iceCreams");
        for (IceCream iceCream : icecreamslist) {
            System.out.print(iceCream.xVal);
            System.out.println(iceCream.yVal);

        }

*/
        animalsEatingPlant();
        System.out.println("bearlists : ");
        for (Bear bear : bearslist) {
            System.out.println(bear.getxVal() + "  " + bear.getyVal() + "  " + bear.getName() + "  " + bear.health);
        }
        System.out.println("lionslists : ");
        for (Lion lion : lionslist) {
            System.out.println(lion.getxVal() + "  " + lion.getyVal() + "  " + lion.getName() + "  " + lion.health);
        }
        System.out.println("***********************");
    }

    public void showgainedP() {
        for (Product product : gaindproductslist) {
            System.out.println(product.getName());
        }
    }
}
