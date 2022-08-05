package main.java;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class RequestDAO {

    private final String INPUT_PATH = "Input/";

    private final String OUTPUT_PATH = "Output/output.csv";

    private final String RESTRICTED_PATH = "Restricted/Restricted.csv";

    private static RequestDAO _dao;

    public RequestDAO() {

    }

    public static RequestDAO getInstance() {

        if (_dao == null) { _dao = new RequestDAO(); }

        return _dao;
    }
    
    public ArrayList<Request> Read() {

        File directory = new File(INPUT_PATH);

        File[] inputFile = directory.listFiles();

        File input = null;

        for (File file : inputFile) {

            if (!file.getName().startsWith(".")) { input = file; }
        }

        ArrayList<Request> output = new ArrayList<>();
        
        try {

            System.out.println(inputFile[0]);

            Scanner readCSV = new Scanner(input);

            for (int i = 0; i < 11; i++) { readCSV.nextLine(); }

            while (readCSV.hasNext()) {

                String result = readCSV.nextLine();

                String[] resultArray = result.split(",");

                Request row = new Request(resultArray[1].substring(5, 10), resultArray[17], resultArray[18], 
                    resultArray[19].toUpperCase().replaceAll("\\s", ""), resultArray[20], resultArray[21], resultArray[24], 
                    resultArray[22], resultArray[23]);

                output.add(row);
            }

            readCSV.close();
        }

        catch (FileNotFoundException fnf) { System.out.println("File not found"); }
        
        return output;
    }

    public void Write(ArrayList<Request> requests) {

        try {

            PrintWriter writer = new PrintWriter(OUTPUT_PATH);

            for (Request line : requests) { writer.println(line); }

            writer.close();
        }

        catch(FileNotFoundException fnf) { System.out.println("File was not found"); }
    }

    public ArrayList<String> ReadRestricted() {

        ArrayList<String> restricted = new ArrayList<>();

        try {

            Scanner read = new Scanner(new File(RESTRICTED_PATH));

            while (read.hasNext()) {

                restricted.add(read.nextLine().toUpperCase().replaceAll("\\s", ""));
            }

            read.close();
        }

        catch (FileNotFoundException fnf) { System.out.println("File not found"); }

        return restricted;
    }
}
