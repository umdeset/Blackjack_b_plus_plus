package at.ac.hcw.blackjack_b_plus_plus.model;

public class Dealer extends Partaker{

    public Dealer(String name) {
        super("Dealer");
    } //name mitgegeben damit wir später keine probleme haben beim adden von anderen dealer

    //Dealer logik umut kennt sich besser aus
    private double badLuckFactor = 0.0; // Beeinflusst durch Trinkgeld

    public void giveTip() {
        // Erhöht die Chance auf eine schlechte Hand des Dealers
        this.badLuckFactor += 0.05;
    }

    public boolean mustHit() {
        return hand.calculateHand() < 17;
    }
}
