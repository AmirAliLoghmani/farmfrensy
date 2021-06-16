import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Date;

public class Log {
    File adminFile;
    File playerFile;
    Date date;
    public Log() {
        this.adminFile=new File("adminLog.txt");
        this. playerFile=new File("playerLog.txt");
        this.date=new Date();

        try {


            if(!adminFile.exists()) adminFile.createNewFile();
            if(!playerFile.exists()) adminFile.createNewFile();
        }
        catch (Exception e){
            Messages.fileHandlingIssue();
        }
    }




    public void errorLog (String name,String errorText){
        try
        {
            FileWriter fileWriter=new FileWriter(this.adminFile,true);
        BufferedWriter bw=new BufferedWriter(fileWriter);
        bw.append("[error]: player id : "+name+" "+date.toString()+" "+errorText+"\n");
        bw.close();

    }
    catch (Exception e){
            Messages.fileHandlingIssue();
    }
    }
    public  void infoLog (String name,String infoText){
        try
        {
            FileWriter fileWriter=new FileWriter(this.playerFile,true);
            BufferedWriter bw=new BufferedWriter(fileWriter);
            bw.append("[info]: player id :  "+name+" "+date.toString()+" "+infoText+"\n");
            bw.close();

        }
        catch (Exception e){
            Messages.fileHandlingIssue();
        }
    }


}
