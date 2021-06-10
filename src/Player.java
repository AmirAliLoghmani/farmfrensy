public class Player {
    private String userName;
    private String passWord;
    private int level;
    private int money;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Player(String userName, String passWord,int level) {
        this.userName = userName;
        this.passWord = passWord;
        this.level = level;
        this.money = 5000;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }
}
