package Business;

import Presentation.MenuView;
import Presentation.*;

public class MenuController {

    void manageTrials(){
        int option1;
        MenuView menuView = new MenuView();

        do {
            option1 = menuView.subManageTrials();

            if (option1 == 'a') {
                // Create trial

            } else if (option1 == 'b') {
                // List trial

            } else if (option1 == 'c') {
                // Delete trial

            }

        } while (option1 != 'd');

    }

    void manageEditions(){
        int option2;
        MenuView menuView = new MenuView();

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

    public MenuController() {

        MenuView menuView = new MenuView();

        int role = menuView.getRole();

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








}
