package at.ac.hcw.blackjack_b_plus_plus.model;

public class Blackjack {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    public Blackjack (String playerName, int startBalance){
        this.deck = new Deck(6); // creating a big deck with 6 decks.
        this.player = new Player(playerName, startBalance);
        this.dealer = new Dealer("Dealer");
    }

    //start of the game
    public void startRound(int betAmount){
        player.reset();
        dealer.reset();
        //reseting the hand form the previous round

        player.setBet(betAmount); //bet amount nehmen


        //karten verteilen
        player.addCardToHand(deck.dealCards());
        dealer.addCardToHand(deck.dealCards());
        player.addCardToHand(deck.dealCards());
        dealer.addCardToHand(deck.dealCards()); //das hier wird in gui versteckt sein
    }

    //ab hier haben wir 2 möglichkeiten entweder stand oder hit

    //falls hit
    public boolean playerHit(){
        //neue karten von deck in hand von player geben
        Card newCard = deck.dealCards();
        player.addCardToHand(newCard);

        //jetzt müssen wir schauen ob der player mit der neuen karte value über 21 hat also bust
        if (player.isOver21()){
            player.loose();
            return false;
        }
        return true;
    }

    private void determineWinner(){
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        //case 1: dealer busted also >21
        if (dealerScore>21){
            player.win();
        }
        //case 2: player value mehr als dealer value -> player wins
        else if (playerScore>dealerScore){
            player.win();
        }
        //gleich ist push passiert ziemlich nichts
        else if (playerScore==dealerScore){
            player.push();
        }
        //ansonsten verliert der player
        else {
            player.loose();
        }
        //wir haben in dieser methode nirgendswo danach geschaut ob evtl player busted ist weil wir das schon in
        // playerHit methode überprüfen wenn er soweit kommt ist er automatisch nicht über 21
    }


    //falls stand
    public void playerStand(){
        //dealers turn, if value kleiner 17 ist muss er ziehen bis er über oder gleich 17 ist.
        while(dealer.mustHit()){
            dealer.addCardToHand(deck.dealCards());
        }
        //after the loop dealer muss größer gleich 17 value haben, jetzt müssen wir
        // schauen ob er über 21 hat und wenn nicht ob der dealer oder player mehr value haben
        determineWinner();
    }

    public Player getPlayer(){
        return player;
    }

    public Dealer getDealer(){
        return dealer;
    }
}
