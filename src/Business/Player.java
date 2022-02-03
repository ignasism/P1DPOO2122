package Business;

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

    public String toCSV(){
        return name + "-" + investigationPoints + "-" + alive;
    }

    public void setPlayerValuesFromCSV(String line){
        String[] values = line.split("-");
        name = values[0];
        investigationPoints = Integer.parseInt(values[1]);
        alive = Boolean.getBoolean(values[2]);
    }
}
