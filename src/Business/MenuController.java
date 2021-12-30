package Business;

import Presentation.MenuView;
import Presentation.*;

import java.util.ArrayList;
import java.util.List;

public class MenuController {

    private final MenuView menuView;
    private final TrialsManager trialsManager;
    private final EditionsManager editionsManager;

    public MenuController() {
        menuView = new MenuView();
        trialsManager = new TrialsManager();
        editionsManager = new EditionsManager(trialsManager.getTrialList());
    }

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

            System.out.println("\nShutting down...");

        }else if (role == 'B'){
            // Conductor


        }
    }

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

    public void manageEditions(){

        int option2;

        do {
            option2 = menuView.subManageEditions();

            if (option2 == 'a') {
                // Create edition

            } else if (option2 == 'b') {
                // List edition

            } else if (option2 == 'c') {
                // Duplicate edition

            } else if (option2 == 'd'){
                // Delete edition
            }

        } while (option2 != 'e');
    }

}
