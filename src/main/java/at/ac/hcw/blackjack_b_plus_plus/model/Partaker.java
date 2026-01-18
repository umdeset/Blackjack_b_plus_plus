package at.ac.hcw.blackjack_b_plus_plus.model;

public abstract class Partaker {
    private String name;
    protected Hand hand;

    public Partaker(String name){
        this.name = name;
        this.hand = new Hand(); //leere Hand ps von umut: this.hand nicht Hand hand. Hand hand ist nur temporary
    }

    public void addCardToHand(Card card){
        hand.addCard(card);
    }

    public int getValue(){
        return hand.calculateHand();
    }

    public int getScore(){
        return hand.calculateHand();
    }

    public boolean isOver21(){
        return getValue() > 21;
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
