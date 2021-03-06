package Business;

import Persistance.CSVManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @version 1.0, 11/3/2022
 * Classe encarregada de gestionar les trials
 */
public class TrialsManager {

    private ArrayList<Trial> trials;

    CSVManager csvManager = new CSVManager();

    public TrialsManager() {
        this.trials = new ArrayList<>();
        csvManager.loadTrialsListFromCSV(trials);
        //trials.add(new Trial("Submitting to OOPD", "Observatory Of Programming Developments", 2, 20, 50, 30));
        //trials.add(new Trial("Publishing to APDS", "Observatory Of Programming Developments", 4, 40, 40, 20));
        //trials.add(new Trial("Submitting to LSJournal", "Observatory Of Programming Developments", 3, 35, 25, 40));
    }

    public ArrayList<Trial> getTrialList() {
        return trials;
    }

    public void setTrialList(ArrayList<Trial> trialList) {
        this.trials = trialList;
    }

    /**
     * Mètode encarregat de crear una trial
     */
    public void createTrial(){

        Trial trial = new Trial();
        Scanner scanner = new Scanner(System.in);

        boolean trial_exists, check=false;
        int revision, rejection, acceptance,quartile;
        String journal_name, trial_name, sc;

        System.out.println("    --- Trial types ---\n");
        System.out.println("    1) Paper publication\n");

        // TRIAL NAME
        do {
            trial_exists = false;
            System.out.print("Enter the trial's name: ");
            trial_name = scanner.nextLine();
            for (int i = 0; i < trials.size(); i++) {
                if (trials.get(i).getTrialName().equals(trial_name)){
                    System.out.println("\nTrial already exists, try again. \n");
                    trial_exists = true;
                }
            }
        }while (trial_exists);
        trial.setTrialName(trial_name);

        // JOURNAL NAME
        do {
            System.out.print("Enter the journal’s name: ");
            journal_name = scanner.nextLine();
            if (journal_name == null || journal_name.isEmpty()) {
                System.out.println("\nJournal's name is wrong, try again.\n");
            }
        }while(journal_name == null || journal_name.isEmpty());
        trial.setPublicationName(journal_name);

        // JOURNAL QUARTILE
        do {
            System.out.print("Enter the journal’s quartile: ");
            sc = scanner.nextLine();

            if (!sc.equals("Q1") && !sc.equals("Q2") && !sc.equals("Q3") && !sc.equals("Q4")){
                System.out.println("\nWrong quartile, try again.\n");
                check=false;
            } else  {
                check =true;
            }

        }while (!check);

        trial.setQuartile(getValueFromQuartile(sc));
        //System.out.println(trial.getQuartile());

        // PERCENTAGES
        do {
            acceptance = askUserOptionBetweenNumbers("Enter the acceptance probability: ", 0, 100);
            revision = askUserOptionBetweenNumbers("Enter the revision probability: ", 0, 100);
            rejection = askUserOptionBetweenNumbers("Enter the rejection probability: ", 0, 100);

            if (acceptance + revision + rejection != 100){
                System.out.println("\nThe percentages aren't correct, try again.\n");
            }

        } while (acceptance + revision + rejection != 100);
        trial.setAcceptanceProbability(acceptance);
        trial.setRevisionProbability(revision);
        trial.setRejectionProbability(rejection);

        trials.add(trial);
    }

    /**
     * Mètode encarregat de generar un valor des del quartile
     * @param sc quartile
     * @return valor
     */
    private int getValueFromQuartile(String sc) {
        return switch (sc) {
            case "Q1" -> 1;
            case "Q2" -> 2;
            case "Q3" -> 3;
            case "Q4" -> 4;
            default -> -1;
        };
    }

    /**
     * Mètode que s'encarrega de llistar les trials creades
     */
    public void listTrials (){

        int option;

        if (trials.isEmpty()){
            System.out.println("\nThere are no trials currently.");
        }else {
            System.out.println("\nHere are the current trials, do you want to see more details or go back?\n");

            option = getOption();

            System.out.println(option);
            System.out.println("Trial size: " + trials.size());

            if (option >= 1 && option <= trials.size()){
                System.out.println("\nTrial: " + trials.get(option - 1).getTrialName());
                System.out.println("Journal: " + trials.get(option - 1).getPublicationName() + " (" + trials.get(option - 1).getQuartile() + ")");
                System.out.println("Chances: " + trials.get(option - 1).getAcceptanceProbability() + "% acceptance, " + trials.get(option - 1).getRevisionProbability() + "% revision, " + trials.get(option - 1).getRejectionProbability() + "% rejection\n");
            }

        }
    }

    /**
     * Mètode que s'encarrega de preguntar la opció que vol fer l'usuari
     * @return opció
     */
    private int getOption() {
        int option;
        for (int i = 0; i < trials.size(); i++) {
            System.out.println(i + 1 + ") " + trials.get(i).getTrialName());
        }
        System.out.println();
        System.out.println(trials.size()+1 + ") Back");
        System.out.println();

        option = askUserOptionBetweenNumbers("Enter an option: ", 1, trials.size() + 1);
        return option;
    }

    /**
     * Mètode encarregat de borrar una trial creada
     */
    public void deleteTrials () {
        int option;
        String trial_deleted;
        Scanner scanner = new Scanner(System.in);

        if (trials.isEmpty()) {
            System.out.println("\nThere are no trials currently.");
        } else {
            System.out.println("\nWhich trial do you want to delete?\n");

            option = getOption();

            if (option >= 1 && option <= trials.size()) {
                System.out.println("Enter the trial’s name for confirmation: ");
                trial_deleted = scanner.nextLine();
                //trial_deleted_int = Integer.parseInt(trial_deleted);
                if (trial_deleted.equals(trials.get(option-1).getTrialName())){
                    trials.remove(option-1);
                    System.out.println("The trial was successfully deleted.\n");
                }else{
                    System.out.println("The trial wasn't deleted, try again.\n");
                }
            }
        }
    }

    /**
     * Mètode que controla si un valor està dins d'un rang
     * @param text valor en string
     * @param min mínim rang
     * @param max màxim rang
     * @return valor
     */
    private int askUserOptionBetweenNumbers(String text, int min, int max){
        int option;
        String input;

        Scanner scanner = new Scanner(System.in);

        do{
            System.out.print(text);
            input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                option = -1;
            }

            if (option<min || option>max){
                System.out.println("\nWrong input! Try again.");
            }

        } while (option<min || option>max);
        return option;
    }

    public void copyTrialsListToCSV() {
        csvManager.copyTrialsListToCSV(trials);
    }
}
