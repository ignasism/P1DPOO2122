package Business;

/**
 * @version 1.0, 11/3/2022
 * Classe de l'objecte trial
 */

public class Trial {

    private String trialName;
    private String publicationName;
    private int quartile;
    private int acceptanceProbability;
    private int revisionProbability;
    private int rejectionProbability;

    public Trial(String trialName, String publicationName, int quartile, int acceptanceProbability, int revisionProbability, int rejectionProbability) {
        this.trialName = trialName;
        this.publicationName = publicationName;
        this.quartile = quartile;
        this.acceptanceProbability = acceptanceProbability;
        this.revisionProbability = revisionProbability;
        this.rejectionProbability = rejectionProbability;
    }

    public Trial() {
    }

    public String getTrialName() {
        return trialName;
    }

    public String getTrialNameDetail() {
        return trialName + " (Paper publication)";
    }

    public void setTrialName(String trialName) {
        this.trialName = trialName;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public int getQuartile() {
        return quartile;
    }

    public void setQuartile(int quartile) {
        this.quartile = quartile;
    }

    public int getAcceptanceProbability() {
        return acceptanceProbability;
    }

    public void setAcceptanceProbability(int acceptanceProbability) {
        this.acceptanceProbability = acceptanceProbability;
    }

    public int getRevisionProbability() {
        return revisionProbability;
    }

    public void setRevisionProbability(int revisionProbability) {
        this.revisionProbability = revisionProbability;
    }

    public int getRejectionProbability() {
        return rejectionProbability;
    }

    public void setRejectionProbability(int rejectionProbability) {
        this.rejectionProbability = rejectionProbability;
    }

    /**
     * M??tode que passa els valors de CSV a l'objecte trial
     * @param line String del CSV
     */
    public void setValuesFromCSV(String line){
        String[] values = line.split("_");
        System.out.println("line: " + line);
        trialName = values[0];
        publicationName = values[1];
        quartile = Integer.parseInt(values[2]);
        acceptanceProbability = Integer.parseInt(values[3]);
        revisionProbability = Integer.parseInt(values[4]);
        rejectionProbability = Integer.parseInt(values[5]);
    }

    /**
     * M??tode que passa els valors al CSV
     */
    public String toCSV(){
        return trialName + "_" + publicationName + "_" + quartile + "_" + acceptanceProbability + "_" + revisionProbability + "_" + rejectionProbability;
    }
}
