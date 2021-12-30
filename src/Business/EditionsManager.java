package Business;

import java.util.ArrayList;
import java.util.Scanner;

public class EditionsManager {

    private final ArrayList<Trial> trialsList;
    private final ArrayList<Edition> editionsList;

    public EditionsManager(ArrayList<Trial> trialsList) {
        this.trialsList = trialsList;
        this.editionsList = new ArrayList<>();
    }

    public ArrayList<Trial> getTrialsList() {
        return trialsList;
    }

    public ArrayList<Edition> getEditionsList() {
        return editionsList;
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
