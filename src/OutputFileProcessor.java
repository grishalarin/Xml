import java.io.*;
import java.util.Vector;

public class OutputFileProcessor {
        PrintWriter outfile;
        String fileName;

        public OutputFileProcessor(String output_file){

            fileName = output_file;
            try {
                outfile = new PrintWriter(output_file);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        public void writeLineToFile(String line){
             outfile.println(line);
        }

        public String toString(){
            return fileName;
        }
    }