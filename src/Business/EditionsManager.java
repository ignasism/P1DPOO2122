package Business;

import java.util.ArrayList;
import java.util.Scanner;

public class EditionsManager {

    private final ArrayList<Trial> trialsList;
    private final ArrayList<Edition> editionsList;

    public EditionsManager(ArrayList<Trial> trialsList) {
        this.trialsList = trialsList;
        this.editionsList = new ArrayList<>();
        editionsList.add(new Edition("2077", "5", "3", "Submitting to OOPD"));
    }


    public ArrayList<Trial> getTrialsList() {
        return trialsList;
    }

    public ArrayList<Edition> getEditionsList() {
        return editionsList;
    }

    public void createEdition() {

        Edition edition = new Edition();
        ArrayList<Trial> trials = new ArrayList<Trial>();
        Scanner scanner = new Scanner(System.in);
        int year, num_players, num_trials;
        boolean error = false;

        year = askUserOptionBetweenNumbers("\nEnter the edition’s year: " , 2022, 999999999);

        do {
            for (int i = 0; i < editionsList.size(); i++) {
                if (editionsList.get(i).getEditionYear() == year) {
                    System.out.println("This edition already exists!");
                    error = true;
                }else{
                    error = false;
                }
            }
        }while (error);
        edition.setEditionYear(year);

        num_players = askUserOptionBetweenNumbers("Enter the initial number of players: ", 1, 5);
        edition.setNumPlayers(num_players);

        num_trials = askUserOptionBetweenNumbers("Enter the number of trials: ", 3, 12);
        edition.setNumTrials(num_trials);

        System.out.println("\n     --- Trials ---\n");

        for (int i = 0; i < trialsList.size(); i++) {
            System.out.println( (i+1) + ") " + trialsList.get(i).getTrialName());

        }

        System.out.println();

        for (int i = 0; i < num_trials; i++) {
            int trial = askUserOptionBetweenNumbers("Pick a trial (" + (i+1) + "/" + num_trials + "): " , 1, trialsList.size()+1);
            trials.add(trialsList.get(trial-1));
        }
        edition.setTrials(trials);

        System.out.println("\nThe edition was created successfully!");
        editionsList.add(edition);

    }

    public void listEditions() {
        int option;

        System.out.println("Here are the current editions, do you want to see more details or go back? ");

        for (int i = 0; i < editionsList.size(); i++) {
            System.out.println("\n    " + (i+1) + ") The Trials " + editionsList.get(i).getEditionYear());
        }
        System.out.println("\n    " + (editionsList.size()+1) + ") Back\n");

        option = askUserOptionBetweenNumbers("Enter an option: ", 1, editionsList.size()+1) - 1;

        System.out.println("\nYear: " + editionsList.get(option).getEditionYear());
        System.out.println("Players: " + editionsList.get(option).getNumPlayers());

        for (int i = 0; i < editionsList.get(option).getNumTrials(); i++) {
            System.out.println("    " + (i+1) + "- " + editionsList.get(option).getTrials().get(i).getTrialName() + " (Paper publication)");
        }


    }

    public void duplicateEditions() {

    }

    private int askUserOptionBetweenNumbers(String text, int min, int max){
        int option;
        String input;

        Scanner scanner = new Scanner(System.in);

        do{
            System.out.print(text);
            input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException excepcion) {
                option = -1;
            }

            if (option<min || option>max){
                System.out.println("\nWrong input! Try again.");
            }

        } while (option<min || option>max);
        return option;
    }

}
