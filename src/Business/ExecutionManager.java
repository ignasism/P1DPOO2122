package Business;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


/**
 * @version 1.0, 11/3/2022
 * Classe que manega la funció d'executor
 */

public class ExecutionManager {

    public ExecutionManager() {
    }

    /**
     * Mètode per executar una edició
     * @param editionToExecute edició la qual volem executar
     */
    public void executeEdition(Edition editionToExecute){

        String continueExecution = "yes";

        System.out.println("\n--- The Trials " + editionToExecute.getEditionYear() + " ---");

        if (editionToExecute.getPlayerList().isEmpty()) {   //if empty --> first time executing
            for (int i = 0; i < editionToExecute.getNumPlayers(); i++) {
                Player auxPlayer = new Player();
                auxPlayer.setAlive(true);
                auxPlayer.setInvestigationPoints(5);
                auxPlayer.setName(askUserNonEmptyString("Enter the player’s name (" + (i + 1) + "/" + editionToExecute.getNumPlayers() + "): "));
                editionToExecute.getPlayerList().add(auxPlayer);

            }
        }
        for (int i = 0; i < editionToExecute.getNumPlayers(); i++) {
            printPlayer(editionToExecute.getPlayerList().get(i));
        }

        while (editionToExecute.getNumTrials()>editionToExecute.getTrialExecuting() && continueExecution.equals("yes") && editionToExecute.isAnyoneAlive()){

            System.out.println("\nTrial #" + (editionToExecute.getTrialExecuting()+1) + " - " + editionToExecute.getTrials().get(editionToExecute.getTrialExecuting()).getTrialName() + "\n");

            for (int i = 0; i < editionToExecute.getNumPlayers(); i++) {
                if (editionToExecute.getPlayerList().get(i).isAlive()) {
                    submitPlayer(editionToExecute, i, editionToExecute.getTrialExecuting());
                }
            }

            editionToExecute.setTrialExecuting(editionToExecute.getTrialExecuting()+1);

            if ((editionToExecute.getNumTrials()==editionToExecute.getTrialExecuting())) {
                System.out.println("\nTHE TRIALS 2021 HAVE ENDED " + editionToExecute.howManyFinishers() + " PLAYERS WON");

            } else {
                continueExecution = askUserYesNo();
            }
        }

        if (!editionToExecute.isAnyoneAlive()){
            System.out.println("\nTHE TRIALS 2021 HAVE ENDED IN FAILURE - 0 PLAYERS ENDED");
        }

    }

    /**
     * Mètode que s'encarrega de entrar un jugador
     * @param edition edició a la qual ho volem fer
     * @param i numero de la llista
     * @param trial trial la qual ho volem fer
     */
    public void submitPlayer(Edition edition, int i, int trial){
        int acc = edition.getTrials().get(edition.getTrialExecuting()).getAcceptanceProbability();
        int rev = edition.getTrials().get(edition.getTrialExecuting()).getRevisionProbability();
        String result;

        System.out.print(edition.getPlayerList().get(i).getName() + " is submitting... ");

        do{
            result = getRandomDecision(acc,rev);

            switch (result) {
                case "Accepted" -> {
                    edition.getPlayerList().get(i).setInvestigationPoints(edition.getPlayerList().get(i).getInvestigationPoints() + getReward(edition, trial));
                    System.out.println("Accepted! PI count: " + edition.getPlayerList().get(i).getInvestigationPoints());
                }
                case "Revisions" -> System.out.print("Revisions... ");
                case "Rejected" -> {
                    edition.getPlayerList().get(i).setInvestigationPoints(edition.getPlayerList().get(i).getInvestigationPoints() - getPenalization(edition, trial));
                    if (edition.getPlayerList().get(i).getInvestigationPoints() <= 0) {
                        edition.getPlayerList().get(i).setAlive(false);
                        edition.getPlayerList().get(i).setInvestigationPoints(0);
                        System.out.println("Rejected. PI count: 0 - Disqualified!");
                    } else {
                        System.out.println("Rejected. PI count: " + edition.getPlayerList().get(i).getInvestigationPoints());
                    }
                }
            }

        } while (result.equals("Revisions"));

    }

    /**
     * Mètode que s'encarrega de que no hi hagi un string buit
     * @param text String
     * @return retorna String
     */
    public String askUserNonEmptyString(String text){

        Scanner scanner = new Scanner(System.in);
        System.out.print(text );

        return scanner.next();
    }

    /**
     * Mètode que s'encarrega de printar la informació d'un jugador
     * @param player jugador
     */
    public void printPlayer(Player player){
        System.out.println("Player name: " + player.getName() + " , Player Investigation Points: " + player.getInvestigationPoints() + " , Is player alive? " + player.isAlive());
    }

    /**
     * Mètode per triar una decisió aleatoria sobre un jugador
     * @param acc acceptat
     * @param rev revisio
     * @return retorna la decisió en string
     */
    public String getRandomDecision(int acc, int rev){
        int decision=0;
        Random rand = new Random();

        decision = rand.nextInt(100);

        if (decision <=acc){
            return "Accepted";
        } else if (decision < (acc + rev)){
            return "Revisions";
        } else if (decision > (acc + rev)){
            return "Rejected";
        } else {
            return "-1";
        }
    }

    /**
     * Mètode que posa la puntuació guanyada
     * @param edition edició feta
     * @param i posició
     * @return retorna la puntuació
     */
    public int getReward(Edition edition, int i){

        return switch (edition.getTrials().get(i).getQuartile()) {
            case 1 -> 4;
            case 2 -> 3;
            case 3 -> 2;
            case 4 -> 1;
            default -> -1;
        };
    }

    /**
     * Mètode que penalitza la puntuació al jugador
     * @param edition edició feta
     * @param i posicicó
     * @return retorna la penalització
     */
    public int getPenalization(Edition edition, int i){

        return switch (edition.getTrials().get(i).getQuartile()) {
            case 1 -> 5;
            case 2 -> 4;
            case 3 -> 3;
            case 4 -> 2;
            default -> -1;
        };
    }

    /**
     * Mètode per preguntar si vol continuar l'execució
     * @return retorna la entrada per comanda yes/no
     */
    public String askUserYesNo(){
        boolean check = false;
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (!check) {

            System.out.print("\nContinue the execution? [yes/no]: ");

            try {
                input = scanner.nextLine().toLowerCase(Locale.ROOT);
                if (input.equals("yes")){
                    check=true;
                } else if (input.equals("no")){
                    check=true;
                } else {
                    System.out.println("The input must be 'yes' or 'no'");
                }
                
            } catch (Exception ex){
                System.out.println("The input must be 'yes' or 'no'");
                check = false;
            }
        }
        return input;
    }
}
