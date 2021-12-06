package Presentation;

import java.util.Locale;
import java.util.Scanner;

public class MenuView {


    public int getRole() {

        System.out.println("THE TRIALS");
        System.out.println("\nWelcome to The Trials. Who are you?\n");
        System.out.println("     1) The Composer");
        System.out.println("     2) This year's Conductor\n");

        return askUserOptionBetweenNumbers("Enter role: ", 1,2);
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
