import java.util.Scanner; 
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintWriter;
public class censorship
{
public static ArrayList<String> wordList = new ArrayList<String>();
public static String result = "";
    static String censor(String word) throws FileNotFoundException
    {
        word = word.toLowerCase().trim();
        Scanner s = new Scanner(new File("input.txt"));//txt file for inputting what user wants to read
        while (s.hasNext())
        {
            wordList.add(s.next());
        }
        for (int i = 0; i < wordList.size(); i++)
        {
            wordList.set(i, wordList.get(i).trim());
            if (wordList.get(i).contains("’")){
                int x = wordList.get(i).indexOf("’");
                wordList.set(i, wordList.get(i).substring(0, x) + "'" + wordList.get(i).substring(x+1));
            }
        }
        s.close();
        String asterisk = "";
        for (int i = 0; i < word.length(); i++)
        {
            asterisk += '*';
        }
        int index = 0;
        for (String i : wordList) 
        {
            if (i.toLowerCase().compareTo(word) == 0){
            wordList.set(index, asterisk);
           }
            else if ((word+".").compareTo(i.toLowerCase().trim())==0){
            wordList.set(index, asterisk + "."); 
           }
            else if ((word+"'s").compareTo(i.toLowerCase().trim())==0){
            wordList.set(index, asterisk + "'s"); 
           }
            else if ((word+"s'").compareTo(i.toLowerCase().trim())==0){
            wordList.set(index, asterisk + "s'"); 
           }
            else if ((word+"'re").compareTo(i.toLowerCase().trim())==0){
            wordList.set(index, asterisk + "'re"); 
           }
            else if ((word+"s").compareTo(i.toLowerCase().trim())==0){
            wordList.set(index, asterisk + "s"); 
           }
            index++;
        }
        for (String i : wordList)
        {
            result += i + " ";
        }
       return result;
}
  
public static void main(String[] args) throws FileNotFoundException
{
    String cen = "They";//User picks censored word
    censor(cen);
    System.out.println(result);
    String fileOutput = "output.txt";//Name of the output file
    try {
        PrintWriter outputStream = new PrintWriter(fileOutput);
        outputStream.println(result);
        outputStream.close();
    }catch (FileNotFoundException e){
        e.printStackTrace();
    }
}
}
