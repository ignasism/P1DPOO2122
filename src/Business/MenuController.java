package Business;

import Presentation.MenuView;
import Presentation.*;

import java.util.ArrayList;
import java.util.List;

public class MenuController {

    public void manageTrials(ArrayList<Trial> trials){

        int option1;
        MenuView menuView = new MenuView();
        Trial trial = new Trial();

        do {
            option1 = menuView.subManageTrials();

            if (option1 == 'a') {
                // Create trial
                trial = menuView.createTrial(trials);
                trials.add(trial);

            } else if (option1 == 'b') {
                // List trial
                menuView.listTrials(trials);

            } else if (option1 == 'c') {
                // Delete trial
                trials = menuView.deleteTrials(trials);

            }

        } while (option1 != 'd');

    }

    public void manageEditions(){

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
        ArrayList<Trial> trials = new ArrayList<>();


        int role = menuView.getRole();

        if (role == 'A'){
            // COMPOSER

            int option;

            do {
                option = menuView.getManagmentMode();

                if (option == 1) {
                    // MANAGE TRIALS
                    manageTrials(trials);

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
