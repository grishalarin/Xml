import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class InputFileProcessor {
    private String fileName;
    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;


    public InputFileProcessor(String fileName){
        this.fileName = fileName;
        file = new File(fileName);
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (IOException e) {
            System.out.println("File could not be opened.");
            e.printStackTrace();
            System.exit(1);
        } finally {
        }


    }

    public synchronized String readLine(){
        try {
            return bufferedReader.readLine();
        } catch (IOException e){
            System.out.println("File could not be read.");
            e.printStackTrace();
            System.exit(1);
        } finally {
        }
        return null;
    }



    public String toString(){
        return fileName;
    }
}
