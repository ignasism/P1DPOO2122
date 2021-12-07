package Business;

import java.util.ArrayList;
import java.util.Scanner;

public class TrialsManager {

    public TrialsManager() {
        ArrayList<Trial> trialList;
    }





    public void createTrial(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("   --- Trial types ---   ");
        System.out.println("\n   1) Paper publication");

        switch (askUserOptionBetweenNumbers("\nEnter the trial's type: ",1,1)){
            case 1:
                //Trials de tipo Paper publication
                int error = 0;
                do {
                    System.out.println("Enter the trial's name: ");
                    String trialName = scanner.nextLine();
                    if (trialName==null){
                        error=1;
                    }

                    System.out.println("Enter the journal's name: ");
                    String journalName = scanner.nextLine();
                    if (journalName==null){
                        error=1;
                    }

                    System.out.println("Enter the journal's quartile: ");
                    char[] line = scanner.next().toCharArray();
                    if (line.length==2){
                        if (line[0]=='Q'){
                            if (line[1]>=0 && line[1]<=4){
                                int quartile = line[1];
                            } else{ error=1; }
                        } else { error=1; }
                    } else { error=1; }

                    System.out.println("Enter the acceptance probability");





                } while (error == 0);













                break;
            default:
                System.out.println("Nunca deberia entrar aqui, pero bueno, lo pongo por si acaso");
                break;



        }

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
