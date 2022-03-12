package Business;


import Presentation.MenuView;
import java.util.Calendar;

/**
 * @version 1.0, 11/3/2022
 * Controla les funcións del menu
 */
public class MenuController {

    private final MenuView menuView;
    private final TrialsManager trialsManager;
    private final EditionsManager editionsManager;
    private final ExecutionManager executionManager;

    public MenuController() {
        menuView = new MenuView();
        trialsManager = new TrialsManager();
        editionsManager = new EditionsManager(trialsManager.getTrialList());
        editionsManager.loadEditionsListFromCSV();
        executionManager = new ExecutionManager();
    }

    /**
     * Mètode que s'encarrega de començar el joc
     */
    public void start (){

        char role = menuView.getRole();
        if (role == 'A'){
            // COMPOSER
            int option;
            do {
                option = menuView.getManagmentMode();
                if (option == 1) {
                    // MANAGE TRIALS
                    manageTrials();
                } else if (option == 2) {
                    // MANGE EDITIONS
                    manageEditions();
                }
            }while (option != 3);
            trialsManager.copyTrialsListToCSV();
            editionsManager.copyEditionsListToCSV();

            System.out.println("\nShutting down...");

        }else if (role == 'B'){
            // Conductor
            boolean foundEdition = false;
            int editionId=0;
            System.out.println("Entering execution mode...");
            int year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = 0; i < editionsManager.getEditionsList().size(); i++) {
                if (editionsManager.getEditionsList().get(i).getEditionYear() == year){
                    System.out.println("We got the year");
                    foundEdition = true;
                    editionId = i;
                }
            }
            if (foundEdition) {
                executionManager.executeEdition(editionsManager.getEditionsList().get(editionId));
            } else {
                System.out.println("No edition is defined for the current year (" + year + ").");
            }
            editionsManager.copyEditionsListToCSV();
        }
    }

    /**
     * Mètode que s'encarrega de gestionar les trials
     */
    public void manageTrials(){

        int option1;

        do {
            option1 = menuView.subManageTrials();
            if (option1 == 'a') {
                // Create trial
                trialsManager.createTrial();
            } else if (option1 == 'b') {
                // List trial
                trialsManager.listTrials();
            } else if (option1 == 'c') {
                // Delete trial
                trialsManager.deleteTrials();
            }
        } while (option1 != 'd');
    }

    /**
     * Mètode que s'encarrega de gestionar les edicions
     */
    public void manageEditions(){

        int option2;
        do {
            option2 = menuView.subManageEditions();
            if (option2 == 'a') {
                // Create edition
                editionsManager.createEdition();
            } else if (option2 == 'b') {
                // List edition
                editionsManager.listEditions();
            } else if (option2 == 'c') {
                // Duplicate edition
                editionsManager.duplicateEditions();
            } else if (option2 == 'd'){
                // Delete edition
                editionsManager.deleteEdition();
            }
        } while (option2 != 'e');
    }
}
