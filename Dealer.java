/**
 * Created by Rex on 4/13/17.
 */

import java.util.ArrayList;

public class Dealer {

    Deck deck;

    public Dealer() {
        this.deck = new Deck();
        this.deck.shuffle();
    }

    //Deals from the deck to a hand by popping off top Tile from deck
    //Because deck is already shuffled, these tiles are essentially random
    private void deal (ArrayList<Piece> hand, int quantity) {
        if(quantity < 1) return;
        if(deck == null || hand == null) return;

        for(int i=0; i<quantity; i++){
            hand.add(deck.pop());
        }
        return;
    }

    protected void dealFullHand(ArrayList<Piece> hand) {
        deal(hand, Player.Hand.HAND_SIZE);
        return;
    }

    protected void draw (ArrayList<Piece> hand) {
        deal(hand, 1);
        return;
    }
}
