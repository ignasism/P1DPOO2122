package Persistance;

import Business.Trial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVManager {

    File file = new File("TrialList.csv");

    public CSVManager() {
    }

    public void loadTrialsListFromCSV(ArrayList<Trial> trials){

        try{

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                Trial trial = new Trial();
                trial.setValuesFromCSV(line);
                trials.add(trial);
            }
            System.out.println("Trials loaded successfully");
        } catch (FileNotFoundException e){
            System.out.println("Error with file, couldn't load trials");
        }
    }


    public void copyTrialsListToCSV(ArrayList<Trial> trials){
        try{
            FileWriter fw = new FileWriter(file, false);
            for(Trial trial: trials){
                fw.write(trial.toCSV());
                fw.write(System.lineSeparator());
            }
            fw.close();
        }catch (IOException e){
            System.out.println("Error is: " + e);
        }
    }


}
