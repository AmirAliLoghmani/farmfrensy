import java.io.*;
import java.util.Scanner;

public class InputProcessor {
    Scanner scanner = new Scanner(System.in);
    private Manager manager;
    public InputProcessor(Manager manager) {
        this.manager = manager;
    }
    private Scanner console = new Scanner(System.in);
    public boolean loginProcess() {
        System.out.println("enter your Username.");
        String username = scanner.nextLine();
        Player p = null;
        String pass = "";
        int tem = 0;
        int lvl = 0;
        for (Player player : manager.playersList) {
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
            manager.currentLevel = lvl;
            manager.currentPlayer = p;
            startPanel();
            return true;
        }
        System.out.println("wrong password");
        return false;


    }
    public void run() {
        manager.updateEveryThing();
        boolean checking = false;

              while (true) {
            System.out.println("1 : Log in");
            System.out.println("2 : Sign Up");
            if (console.nextLine().equals("1"))
                checking = loginProcess();
            else if (console.nextLine().equals("2"))
                checking = signUpProcess();
            //if (checking)
              //  break;

        }

    }
    public boolean signUpProcess() {

        System.out.println("enter your username.");
        int tem = 0;
        String username = scanner.nextLine();
        for (Player player : manager.playersList) {
            if (player.getUserName().equals(username)) {
                System.out.println("already existing!!!");
                tem = 1;
                return false;
                // break;
            }
        }

        System.out.println("enter the password you want to have!");
        String pass = scanner.nextLine();
        manager.playersList.add(manager.currentPlayer = new Player(username, pass, 1));
        // FileWriter fw = new FileWriter("users.txt");
        //BufferedWriter bw = new BufferedWriter(fw);
           /* for (Player player : playersList) {
                bw.append(player.getUserName()+" "+player.getPassWord()+player.getLevel()+"\n");
            }
                    //bw.append(username + " " + pass);
                    bw.close();*/


        for (Player player :manager.playersList) {
            System.out.println(player.getUserName());
        }
        manager.currentLevel = 1;
        startPanel();
        return true;
    }
    public void saveEveryThing() {
        try {
            FileWriter fw = new FileWriter("users.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Player player : manager.playersList) {
                bw.append(player.getUserName() + " " + player.getPassWord() + " " + (player.getLevel()-1) + "\n");
            }
            //bw.append(username + " " + pass);
            bw.close();

            FileWriter fw2 = new FileWriter("animals.txt");
            BufferedWriter bw2 = new BufferedWriter(fw2);
            for (Animal animal : manager.domesticAnimalsList) {
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
            for (Animal animal : manager.domesticAnimalsList) {
                System.out.println(animal.getName());
                System.out.println(animal.getxVal());
                System.out.println(animal.getyVal());

            }
            System.out.println("eggs");
            for (Egg egg : manager.eggslist) {
                System.out.print(egg.xVal + "  ");
                System.out.println(egg.yVal);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void processPutCage(String split[]){
        if(split.length==3) manager.putCage(split);
        else Messages.invalidCommandMessage();
    }
    private void processBuy(String split[]){
        if(split.length==2) manager.buyAnimal(split);
        else Messages.invalidCommandMessage();
    }

    private void processPlant(String split[]){
        if (split.length==3) manager.plant(split);
        else Messages.invalidCommandMessage();
    }
    public void processTurnTime(String split[]){
        if (split.length==2) manager.turnTime(split);
        else Messages.invalidCommandMessage();
    }
    public void processPickUp(String split[]){
        if (split.length==3) manager.pickUpProperty(split);
        else Messages.invalidCommandMessage();
    }
    public void processMakeBuilding(String split[]){
        if (split.length==2) manager.makingBuilding(split);
        else Messages.invalidCommandMessage();
    }
    public void processWorkingBuilding(String split[]){
        if(split.length==2)manager.workingBuilding(split);
    }
    public void processLoadTruck(String split[]){
        if(split.length==3) manager.loadingTruck(split);
        else Messages.invalidCommandMessage();
    }

    public void startPanel() {
        Messages.startMessage();
        try {
            if (manager.status.equals("finished")) {
                Messages.wholeGameFinished();
                return;
            }
            Messages.showCurrentLevelMessage(manager.currentLevel);
            FileReader fileReader3 = new FileReader("missions.txt");
            BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
            manager.numberOfLevels = Integer.parseInt(bufferedReader3.readLine());
            String temp;
            int tt = 0;
            while (tt == 0) {
                if ((temp = bufferedReader3.readLine()).equals("end")) {
                    Messages.fileEndedMessage();
                    manager.status = "finished";
                    // startPanel();
                    tt = 1;
                    return;

                    //return;
                } else if ((temp).startsWith("level")) {
                    if (Integer.parseInt(temp.split("\\s")[1]) == manager.currentLevel)
                        tt = 1;
                }

            }

            //currentLevel = Integer.parseInt(bufferedReader3.readLine().split("\\s")[1]);
            manager.initialCoins = Integer.parseInt(bufferedReader3.readLine());// TODO: 6/13/2021 isThisCorrect?
            manager.numberOfTasks = Integer.parseInt(bufferedReader3.readLine());
            Messages.numberOfTasksMessage(manager.numberOfTasks);
            String goal;
            for (int i = 0; i < manager.numberOfTasks; i++) {
                System.out.println("1");
                goal = bufferedReader3.readLine();
                if (goal.startsWith("chicken")) {
                    System.out.println("2");
                    System.out.println("chicken");
                    manager .chickenGoal = Integer.parseInt(goal.split("\\s")[1]);
                } else if (goal.startsWith("turkey"))
                    manager.turkeyGoal = Integer.parseInt(goal.split("\\s")[1]);
                    //  else if ((goal = bufferedReader3.readLine()).startsWith("buffalo"))
                    //    buffaloGoal = Integer.parseInt(goal.split("\\s")[1]);
                else if (goal.startsWith("coins")) {
                    System.out.println("coin");
                    System.out.println("3");
                    manager.coinGoal = Integer.parseInt(goal.split("\\s")[1]);
                }
            }
            //System.out.println(bufferedReader3.readLine());
            manager.numberOfWildAnimals = Integer.parseInt(bufferedReader3.readLine());
            String tem;
            for (int i = 0; i < manager.numberOfWildAnimals; i++) {
                tem = bufferedReader3.readLine();
                if (tem.startsWith("bear"))
                    manager.bearArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
                if (tem.startsWith("lion"))
                    manager.lionArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
            }

            manager.maxTime = Integer.parseInt(bufferedReader3.readLine());
            manager.prizeEndingQuick = Integer.parseInt(bufferedReader3.readLine());

            manager.currentPlayer.setMoney(manager.initialCoins);
            bufferedReader3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("1 : Start\n2: Log out\n3: Settings");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    manager.map[i][j] = "0";
                }
            }
            manager.waterTank = new WaterTank(0);
            String input;
            //test

            //test end
            String[] split;
            while (!((input = scanner.nextLine()).equals("exit"))) {
                split = input.split("\\s");
                if (input.startsWith("BUY")) {
                    processBuy(split);
                } else if (input.equalsIgnoreCase("WELL"))
                    manager.wellWater();
                else if (input.toUpperCase().startsWith("PLANT"))
                    processPlant(split);
                else if (input.toUpperCase().startsWith("CAGE"))
                    processPutCage(split);
                else if (input.toUpperCase().startsWith("TURN"))
                    processTurnTime(split);
                else if (input.toUpperCase().startsWith("PICK UP"))
                    processPickUp(split);
                else if (input.toUpperCase().startsWith("MAKE"))
                    processMakeBuilding(split);
                else if (input.toUpperCase().startsWith("WORK"))
                    processWorkingBuilding(split);
                else if (input.toUpperCase().startsWith("TRUCK LOAD"))
                    processLoadTruck(split);
                    // TODO: 6/13/2021 unloadTruck
                else if (input.equalsIgnoreCase("TRUCK GO"))
                    manager.sendingTruck();
                if (manager.status.equals("win")||manager.status.equals("finished")) {
                    System.out.println("you finished the level");
                    manager.status = "progress";
                    manager.currentLevel++;
                    readingOrderFile();
                    saveEveryThing();
                    //startPanel();
                }
                if (manager.status.equals("finished"))
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
           manager. deleteEveryThing();

            System.out.println("current level" + manager.currentLevel);
            FileReader fileReader3 = new FileReader("missions.txt");
            BufferedReader bufferedReader3 = new BufferedReader(fileReader3);
            manager.numberOfLevels = Integer.parseInt(bufferedReader3.readLine());
            String temp;
            int tt = 0;
            while (tt == 0) {
                if ((temp = bufferedReader3.readLine()).equals("end")) {
                    System.out.println("file ended");
                    manager.status = "finished";
                    // startPanel();
                    tt = 1;
                    return;

                    //return;
                } else if ((temp).startsWith("level")) {
                    if (Integer.parseInt(temp.split("\\s")[1]) == manager.currentLevel)
                        tt = 1;
                }

            }

            //currentLevel = Integer.parseInt(bufferedReader3.readLine().split("\\s")[1]);
            manager.initialCoins = Integer.parseInt(bufferedReader3.readLine());
            manager.numberOfTasks = Integer.parseInt(bufferedReader3.readLine());
            System.out.println("nom of tasks  " + manager.numberOfTasks);
            String goal;
            for (int i = 0; i < manager.numberOfTasks; i++) {
                System.out.println("1");
                goal = bufferedReader3.readLine();
                if (goal.startsWith("chicken")) {
                    System.out.println("2");
                    System.out.println("chicken");
                    manager.chickenGoal = Integer.parseInt(goal.split("\\s")[1]);
                } else if (goal.startsWith("turkey"))
                    manager.turkeyGoal = Integer.parseInt(goal.split("\\s")[1]);
                    //  else if ((goal = bufferedReader3.readLine()).startsWith("buffalo"))
                    //    buffaloGoal = Integer.parseInt(goal.split("\\s")[1]);
                else if (goal.startsWith("coins")) {
                    System.out.println("coin");
                    System.out.println("3");
                    manager.coinGoal = Integer.parseInt(goal.split("\\s")[1]);
                }
            }
            //System.out.println(bufferedReader3.readLine());
            manager.numberOfWildAnimals = Integer.parseInt(bufferedReader3.readLine());
            String tem;
            for (int i = 0; i < manager.numberOfWildAnimals; i++) {
                tem = bufferedReader3.readLine();
                if (tem.startsWith("bear"))
                    manager.bearArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
                if (tem.startsWith("lion"))
                    manager.lionArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));
            }

            manager.maxTime = Integer.parseInt(bufferedReader3.readLine());
            manager.prizeEndingQuick = Integer.parseInt(bufferedReader3.readLine());

            manager.currentPlayer.setMoney(manager.initialCoins);
            bufferedReader3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void logoutProcess() {
        return;
    }
}
