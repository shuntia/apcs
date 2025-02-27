
import java.io.*;
// https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html

// The purpose of this class is to read in a .txt file named below
// and output it to System.out in all caps.  
// If the named file cannot be found, output the proper message
// If there is an error during the file read, output the proper message
// If everything finished with no problems, output the proper message
// No matter what happened, make sure to close the BufferedReader object
public class FileReaderTester {

    public static void main(String[] args) {
        //*** Member Variables***
        BufferedReader bufferedReader;
        FileReader fileReader;
        // The name of the file to open.
        String fileName = "dialog.txt";
        // This will reference one line at a time
        String line;
        //*** End Member Variables***

        // FileReader reads text files in the default encoding.
        // Always wrap FileReader in BufferedReader.
        //http://stackoverflow.com/questions/9648811/specific-difference-between-bufferedreader-and-filereader
        System.out.println("Starting to read the file...");
        // BufferedReader allows us to read in file input line by line
        // instead of FileReader's inefficient char by char 
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            System.err.println("File '" + fileName + "' not found");
            System.err.println("Unrecoverable Error.");
            return;
        }
        int idx = 0;
        try {
            while (true) {
                try {
                    line = bufferedReader.readLine();
                    idx++;
                    randomChanceEvent(bufferedReader);
                    if (line == null) {
                        break;
                    }
                    System.out.println(line.toUpperCase());
                } catch (IOException e) {
                    System.err.println("Error reading file '" + fileName + "'");
                    System.err.println("Restoring input stream...");
                    try {
                        fileReader = new FileReader(fileName);
                        bufferedReader = new BufferedReader(fileReader);
                        for (int i = 0; i < idx; i++) {
                            line = bufferedReader.readLine();
                        }
                    } catch (Exception ex) {
                        System.out.println("Error reading file '" + fileName + "'");
                        if (ex instanceof FileNotFoundException) {
                            System.err.println("HOW THE HELL DID YOU MANAGE TO DELETE THE FILE BEFORE RECOVERING IT");
                        } else {
                        }
                        System.err.println("Unrecoverable Error.");
                        throw ex;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Unrecoverable Error.");
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing file '" + fileName + "'");
            }
        }
        // Print this after File is done reading
        System.out.println("...Finished reading file");

    }

    private static void randomChanceEvent(BufferedReader br) throws IOException {
        int eventResult = (int) (Math.random() * 100);
        if (eventResult < 4) // simulates a random corruption that might cause the
        {
            br.close(); // stream to be corrupted (e.g. it is closed or disrupted somehow)
        }
    }
}
