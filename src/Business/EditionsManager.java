package Business;

import java.util.ArrayList;
import java.util.Scanner;

public class EditionsManager {

    public EditionsManager() {ArrayList<Edition> editionList;}

    public Trial createEdition(Edition edition){

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
    public void listTrials (ArrayList<Edition> editionList){

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
    public  ArrayList<Trial> deleteTrials (ArrayList<Edition> editionList) {
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
