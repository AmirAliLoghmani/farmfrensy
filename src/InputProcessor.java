import javax.swing.*;
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
        manager.numberOfLevels=5;
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
            int tem=1;
            FileWriter fw = new FileWriter("users.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Player player : manager.playersList) {
                if (player.getLevel()>1)
                    tem = player.getLevel()-1;
                else if (player.getLevel()==1)
                    tem=1;
                System.out.println(player.getUserName() + " " + player.getPassWord() + " " + (player.getLevel()) + "\n");
                bw.append(player.getUserName() + " " + player.getPassWord() + " " + player.getLevel()+ "\n");
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

    public void startPanel() {
        manager.deleteEveryThing();

        if(!choosingLevel())
            return;
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
            manager.numberOfLevels=5;
            String temp;
            int tt = 0;
            while (tt == 0) {
                if ((temp = bufferedReader3.readLine()).equals("end")) {
                    Messages.fileEndedMessage();
                    manager.status = "finished";
                    System.out.println("last");
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
                }
                else if (goal.startsWith("tiger")){
                    manager.tigerGoal = Integer.parseInt(goal.split("\\s")[1]);
                }
                else if (goal.startsWith("turkey"))
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
                if (tem.startsWith("tiger"))
                    manager.tigerArrivalTime.add(Integer.parseInt(tem.split("\\s")[1]));

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
        boolean correct=false;
        while (!correct){


            if (choice.equals("1")) {
                correct=true;
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        manager.grassMap[i][j] = 0;
                    }
                }
                manager.waterTank = new WaterTank(0);
                String input;
                //test

                manager.currentPlayer.setMoney(manager.currentPlayer.getMoney()+manager.leftPrize);
                manager.leftPrize=0;
                //test end
                String[] split;
                while (!((input = scanner.nextLine()).equals("exit"))) {
                    try {


                    split = input.split("\\s");
                    if (input.toUpperCase().startsWith("BUY")) {
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
                    else if (input.toUpperCase().startsWith("UPDATE"))
                        processUpdateBuilding(split);
                    else if (input.toUpperCase().startsWith("TRUCK LOAD"))
                        processLoadTruck(split);
                    else if (input.toUpperCase().equals("INQUIRY"))
                        manager.inquiry(split);
                    else if (input.toUpperCase().startsWith("TRUCK UNLOAD"))
                        processUnloadTruck(split);

                    else if (input.equalsIgnoreCase("TRUCK GO"))
                        manager.sendingTruck();
                    else {
                        Messages.invalidCommandMessage();
                        manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid command");
                    }
                    }
                    catch (Exception e){
                        Messages.invalidCommandMessage();
                        manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid command");
                    }
                    if (manager.status.equals("win")||manager.status.equals("finished")) {
                   /* System.out.println("you finished the level");
                    manager.log.infoLog(manager.currentPlayer.getUserName(),"level is finished");
                    manager.status = "progress";
                    //if(manager.currentLevel==manager.lastLevelThatIsFinished) manager.lastLevelThatIsFinished+=1;
                    manager.currentLevel++;
                    readingOrderFile();
                    saveEveryThing();*/
                        if (manager.currentPlayer.getLevel()==manager.currentLevel  &&  manager.currentLevel!=6)
                            manager.currentPlayer.setLevel(manager.currentPlayer.getLevel()+1);
                        //System.out.println("duddd bro");
                        saveEveryThing();
                        if (choosingLevel()){

                            System.out.println("saving...");

                            manager.status = "progress";
                            readingOrderFile();


                        }
                        else{
                            System.out.println("status"+ manager.status);
                            manager.status="progress";
                            return;
                        }
                        //startPanel();
                    }
                    if (manager.status.equals("finished"))
                        // saveEveryThing();
                        return;

                }
            } else if (choice.equals("2")) {
                correct=true;
                saveEveryThing();
                logoutProcess();
            } else if (choice.equals("3")) {
                correct=true;
                saveEveryThing();
            }
            else {
                Messages.invalidCommandMessage();
                manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid command");
            }







        }

    }
    public boolean choosingLevel(){
        System.out.println(manager.numberOfLevels);
        System.out.println("LEVELS : \n (you can log out either by writing 0)");
        for (int i = 1; i <= manager.currentPlayer.getLevel(); i++) {
            if (i== 6)break;                                     //fix it
            System.out.println("level  "+ i);
        }

        String s="";
        //int tem =scanner.nextInt();
        int tem=0;
        while (tem==0){
            s = scanner.nextLine();
            if (Integer.parseInt(s)==0){
                return false;
            }
            if (Integer.parseInt(s)<=manager.currentPlayer.getLevel()) {
                tem=1;
                manager.currentLevel = Integer.parseInt(s);
                manager.currentPlayer.setMoney(manager.currentPlayer.getMoney()+manager.leftPrize);
                manager.leftPrize=0;
                return true;
            }}

        return true;
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
                    System.out.println("creed");
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
                }
                else if (goal.startsWith("tiger")){
                    manager.tigerGoal = Integer.parseInt(goal.split("\\s")[1]);
                }else if (goal.startsWith("turkey"))
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

            manager.currentPlayer.setMoney(manager.initialCoins+ manager.currentPlayer.getMoney());
            bufferedReader3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void logoutProcess() {
        return;
    }




    private void processPutCage(String split[]){
        if(split.length==3) manager.putCage(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    private void processBuy(String split[]){
        if(split.length==2) manager.buyAnimal(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    private void processPlant(String split[]){
        if (split.length==3) manager.plant(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    public void processTurnTime(String split[]){
        String[] s = new String[2];
        if (split.length==2) manager.turnTimes(split);
        else if(split.length==1) {
            s[0]="TURN";
            s[1]="1";
            manager.turnTimes(s);
        }else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    public void processPickUp(String split[]){
        if (split.length==4) manager.pickUpProperty(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");

        }
    }
    public void processMakeBuilding(String split[]){
        if (split.length==2) manager.makingBuilding(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    public void processWorkingBuilding(String split[]){
        if(split.length==2)manager.workingBuilding(split);
        else if (split.length==3) manager.workingBuildingWithUpdate(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    public void processLoadTruck(String split[]){
        if(split.length==3) manager.loadingTruck(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    public void processUnloadTruck(String split[]){
        if(split.length==3) manager.unloadTruck(split);
        else {
            Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");
        }
    }
    public void processUpdateBuilding(String split[]){
        if(split.length==2) manager.updateBuilding(split);
        else {Messages.invalidCommandMessage();
            manager.log.errorLog(manager.currentPlayer.getUserName(),"invalid input");

        }
    }
}


