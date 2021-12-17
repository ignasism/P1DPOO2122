package Presentation;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import Business.MenuController;
import Business.Trial;


public class MenuView {
    public MenuView() {
    }


    public int getRole() {

        Scanner scanner = new Scanner(System.in);
        char input;

        System.out.println(" _____ _             _____      _       _");
        System.out.println("/__   \\ |__   ___   /__   \\_ __(_) __ _| |___");
        System.out.println("  / /\\/ '_ \\ / _ \\    / /\\/ '__| |/ _` | / __|");
        System.out.println(" / /  | | | |  __/   / /  | |  | | (_| | \\__ \\");
        System.out.println(" \\/   |_| |_|\\___|   \\/   |_|  |_|\\__,_|_|___/");
        System.out.println("\nWelcome to The Trials. Who are you?\n");
        System.out.println("     A) The Composer");
        System.out.println("     B) This year's Conductor\n");

        do{
            System.out.print("Enter role: ");
            input = scanner.next().charAt(0);

            if (input != 'A' && input != 'B'){
                System.out.println("\nWrong input! Try again.");
            }
        } while (input != 'A' && input != 'B');

        return input;
    }

    public int getManagmentMode(){
        int option;

        System.out.println("\nEntering management mode...\n");
        System.out.println("     1) Manage Trials");
        System.out.println("     2) Manage Editions\n");
        System.out.println("     3) Exit\n");
        option = askUserOptionBetweenNumbers("Enter an option: ", 1,3);

        return option;
    }

    public int subManageTrials(){
        int option;

        System.out.println("\na) Create Trial");
        System.out.println("b) List Trials");
        System.out.println("c) Delete Trial");
        System.out.println("\nd) Back\n");

        option = askUserOptionBetweenLetters("Enter an option: ", 'a', 'd');
        return option;
    }

    public int subManageEditions(){
        int option;

        System.out.println("\na) Create Edition");
        System.out.println("b) List Editions");
        System.out.println("c) Duplicate Editions");
        System.out.println("d) Delete Edition");
        System.out.println("\ne) Back\n");

        option = askUserOptionBetweenLetters("Enter an option: ", 'a', 'e');
        return option;
    }

    public Trial createTrial(ArrayList<Trial> trials){

        Trial trial = new Trial();
        Scanner scanner = new Scanner(System.in);

        boolean trial_exists;
        int revision, rejection, acceptance;
        String quartile, journal_name, trial_name;

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
            quartile = scanner.nextLine();

            if (!quartile.equals("Q1") && !quartile.equals("Q2") && !quartile.equals("Q3") && !quartile.equals("Q4")){
                System.out.println("\nWrong quartile, try again.\n");
            }

        }while (!quartile.equals("Q1") && !quartile.equals("Q2") && !quartile.equals("Q3") && !quartile.equals("Q4"));
        trial.setQuartile(quartile);
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

        return trial;

    }

    public void listTrials (ArrayList<Trial> trials){

        int option;

        if (trials.isEmpty()){
            System.out.println("\nThere are no trials currently.");
        }else {
            System.out.println("\nHere are the current trials, do you want to see more details or go back?\n");

            for (int i = 0; i < trials.size(); i++) {
                System.out.println(i + 1 + ") " + trials.get(i).getTrialName());
            }
            System.out.println();
            System.out.println(trials.size()+1 + ") Back");
            System.out.println();

            option = askUserOptionBetweenNumbers("Enter an option: ", 1, trials.size() + 1);

            System.out.println(option);
            System.out.println("Trial size: " + trials.size());

            if (option >= 1 && option <= trials.size()){
                System.out.println("\nTrial: " + trials.get(option - 1).getTrialName());
                System.out.println("Journal: " + trials.get(option - 1).getPublicationName() + " (" + trials.get(option - 1).getQuartile() + ")");
                System.out.println("Chances: " + trials.get(option - 1).getAcceptanceProbability() + "% acceptance, " + trials.get(option - 1).getRevisionProbability() + "% revision, " + trials.get(option - 1).getRejectionProbability() + "% rejection\n");
            }

        }
    }

    public  ArrayList<Trial> deleteTrials (ArrayList<Trial> trials) {
        int option, trial_deleted_int;
        String trial_deleted;
        Scanner scanner = new Scanner(System.in);

        if (trials.isEmpty()) {
            System.out.println("\nThere are no trials currently.");
        } else {
            System.out.println("\nWhich trial do you want to delete?\n");

            for (int i = 0; i < trials.size(); i++) {
                System.out.println(i + 1 + ") " + trials.get(i).getTrialName());
            }
            System.out.println();
            System.out.println(trials.size() + 1 + ") Back");
            System.out.println();

            option = askUserOptionBetweenNumbers("Enter an option: ", 1, trials.size() + 1);

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
        return trials;
    }

    private char askUserOptionBetweenLetters (String text, char min, char max){

        char input;

        Scanner scanner = new Scanner(System.in);

        do{
            System.out.print(text);
            input = scanner.next().charAt(0);

            if (input<min || input>max){
                System.out.println("\nWrong input! Try again.");
            }

        } while (input<min || input>max);
        return input;
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
