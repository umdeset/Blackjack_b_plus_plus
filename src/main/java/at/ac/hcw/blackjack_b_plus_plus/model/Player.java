package at.ac.hcw.blackjack_b_plus_plus.model;

public class Player extends Partaker {
    private int balance;
    private int bet;

    public Player(String name, int startBalance) {
        super(name); //ruft Konstruktor von Partaker auf
        this.balance = startBalance;
    }

    public void setBet(int placedBet) {
        bet = placedBet;
        balance -= placedBet;
    }

    public void win() {
        balance += bet * 2;
        bet = 0;
    }

    public void push() {
        balance += bet;
        bet = 0;
    }

    public void loose() {
        bet = 0;
    }


}
