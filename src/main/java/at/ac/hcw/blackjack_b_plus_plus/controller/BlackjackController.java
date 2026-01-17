package at.ac.hcw.blackjack_b_plus_plus.controller;

import at.ac.hcw.blackjack_b_plus_plus.model.Blackjack;
import at.ac.hcw.blackjack_b_plus_plus.model.Card;
import at.ac.hcw.blackjack_b_plus_plus.model.Dealer;
import at.ac.hcw.blackjack_b_plus_plus.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BlackjackController {
    public VBox PreGameMenu;
    public Label betLabel;
    public Label balanceLabel;
    public VBox RulesMenu;
    public VBox StartMenu;
    public VBox GameMenu;
    public HBox dealerHand;
    public HBox playerHand;
    public Label asd;
    public Label playerHandValueLabel;
    public Label betLabel2;
    @FXML
    private Player player;
    private Blackjack blackjack;
    public TextField betInput;
    private final Dealer dealer = new Dealer();

    @FXML
    public void initialize() {
        RulesMenu.setVisible(false);
        StartMenu.setVisible(true);
        PreGameMenu.setVisible(false);
        player = new Player("Umut",1000);
        blackjack = new Blackjack(player, dealer);
        updateBalanceLabel();
        betInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                betInput.setText(oldValue);
            }
        });
    }
    @FXML
    protected void hitButton(){
        blackjack.playerHit();
    }
    @FXML
    protected void standButton(){
        blackjack.playerStand();
    }
    @FXML
    protected void setBet(){
        try{
            player.clearBet();
            String text = betInput.getText();
            if(!text.isEmpty()){
                int amount = Integer.parseInt(text);
                if(player.getBalance() >= amount){
                    player.setBet(amount);
                    updateBet();
                    updateBalanceLabel();
                }else{
                    betLabel.setText("Bet exceeds Savings");
                }
            }
        }catch (NumberFormatException e){
            betLabel.setText("No Valid Number given");
        }
    }
    @FXML
    protected void startRound(){
        PreGameMenu.setVisible(false);
        GameMenu.setVisible(true);
        blackjack.startRound(player.getBet());
        makeHandVisiblePlayer();
        makeHandVisibleDealer();
        updatePlayerHandValue();
        updateBet();

    }
    @FXML
    protected void allIn(){
        if(player != null) {
            int balance = player.getBalance();
            player.setBet(balance);
            updateBet();
            updateBalanceLabel();
        }
    }
    @FXML
    protected void clearBet(){
        player.clearBet();
        updateBet();
        updateBalanceLabel();
    }

    public void updateBalanceLabel(){
        if(player != null && balanceLabel != null){
            balanceLabel.setText("Balance: " + player.getBalance());
        }
    }

    public void updatePlayerHandValue(){
        if(player != null && playerHandValueLabel != null){
            playerHandValueLabel.setText("Value: " + player.getValue());
        }
    }

    public void updateBet(){
        if(player != null && betLabel2 != null && betLabel != null){
            betLabel2.setText("Bet: " + player.getBet());
            betLabel.setText("Bet: " + player.getBet());
        }
    }

    public void switchToStartMenu(){
        RulesMenu.setVisible(false);
        StartMenu.setVisible(true);
        PreGameMenu.setVisible(false);
    }

    public void switchToRulesMenu(){
        RulesMenu.setVisible(true);
        StartMenu.setVisible(false);
        PreGameMenu.setVisible(false);
    }

    public void startPreGameMenu(){
        RulesMenu.setVisible(false);
        StartMenu.setVisible(false);
        PreGameMenu.setVisible(true);
    }

    public void exitGame(){
        Platform.exit();
    }

    private void addCardToPlayer(String imagePath){
        Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(cardImage);

        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        playerHand.getChildren().add(imageView);
    }
    private void addCardToDealer(String imagePath){
        Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(cardImage);

        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        dealerHand.getChildren().add(imageView);
    }

    private void makeHandVisiblePlayer(){
        playerHand.getChildren().clear();
        for (Card card: player.getHand().getCards()){
            String imageName = card.getName() + "_of_" + card.getSymbol() + ".png";
            addCardToPlayer("/Images/Cards/" + imageName);
        }
    }
    private void makeHandVisibleDealer(){
        dealerHand.getChildren().clear();

        var cards = dealer.getHand().getCards();

        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            if(i==0){
                addCardToDealer("/Images/Cards/Back.png");
            }else{
                String imageName = card.getName() + "_of_" + card.getSymbol() + ".png";
                addCardToDealer("/Images/Cards/" + imageName);
            }
        }
    }
}
