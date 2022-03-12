package Business;

/**
 * @version 1.0, 11/3/2022
 * Classe de l'objecte jugador
 */
public class Player {

    private String name;
    private boolean alive;
    private int investigationPoints;

    public Player(String name, boolean alive, int investigationPoints) {
        this.name = name;
        this.alive = alive;
        this.investigationPoints = investigationPoints;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getInvestigationPoints() {
        return investigationPoints;
    }

    public void setInvestigationPoints(int investigationPoints) {
        this.investigationPoints = investigationPoints;
    }

    /**
     * Passa al CSV els valors de jugador
     * @return retorna la informació a CSV
     */
    public String toCSV(){
        return name + "-" + investigationPoints + "-" + alive;
    }

    /**
     * Mètode que posa els valors del CSV a la classe jugador
     * @param line informació del CSV en String
     */
    public void setPlayerValuesFromCSV(String line){
        String[] values = line.split("-");
        name = values[0];
        investigationPoints = Integer.parseInt(values[1]);
        alive = setTrueFalseFromString(values[2]);
    }

    /**
     * Mètode que posa a true o false depenent del string
     * @param line String
     * @return true/false
     */
    public boolean setTrueFalseFromString(String line){
        return switch (line) {
            case "true" -> true;
            default -> false;
        };
    }
}
