package at.ac.hcw.blackjack_b_plus_plus.model;

public abstract class Partaker {
    private String name;
    private Hand hand;

    public Partaker(String name){
        this.name = name;
        Hand hand = new Hand(); //leere Hand
    }

    public void addCardToHand(Card card){
        hand.addCard(card);
    }

    public int getScore(){
        return hand.calculateHand();
    }

    public boolean isOver21(){
        return getScore() > 21;
    }

    public void reset(){
        hand.clearHand();
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
