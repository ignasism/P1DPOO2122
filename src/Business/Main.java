package Business;

public class Main {
    public static void main(String[] args) {

        MenuController menuController = new MenuController();

        int role = menuController.menuView.getRole();

        if (role == 'A'){
            // COMPOSER

            int option;

            do {
                option = menuController.menuView.getManagmentMode();

                if (option == 1) {
                    // MANAGE TRIALS
                    int option1;

                    do {
                        option1 = menuController.menuView.subManageTrials();

                        if (option1 == 'a') {
                            // Create trial
                        } else if (option1 == 'b') {
                            // List trial
                        } else if (option1 == 'c') {
                            // Delete trial
                        } else if (option1 == 'd') {
                            // Back

                        }
                    } while (option1 != 'd');

                } else if (option == 2) {
                    // MANGE EDITIONS

                }
            }while (option != 3);

            System.out.println("Shutting down...");

        }else if (role == 'B'){
            // Conductor
        }


    }
}
