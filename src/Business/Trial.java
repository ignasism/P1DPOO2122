package Business;

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
}
