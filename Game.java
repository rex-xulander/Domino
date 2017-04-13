/**
 * Created by Rex on 4/13/17.
 */
public class Game {
    Player p1;
    Player p2;

    Deck deck;
    PlayedSet playedSet;

    short spinner;

    public enum State { START, P1TURN, P2TURN, END }

    public State state;

    public Game() {
        this.deck = new Deck();
        deck.shuffle();

        this.playedSet = new PlayedSet();

        this.p1 = new Player();
        this.p2 = new Player();

        dealHand(deck, p1.hand);
        dealHand(deck, p2.hand);

        //TASK: CREATE METHOD FOR HIGHEST DOMINO!

    }

    public void print() {
        System.out.print("Player 1 Hand: "); p1.hand.print(); System.out.println();
        System.out.print("Player 2 Hand: "); p2.hand.print(); System.out.println();

        //BOARD SPACE OF 6 LINES
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Remaining tiles from Boneyard:");
        deck.prettyPrint();

    }

    //Deals from the deck to a hand by popping off top Tile from deck
    //Because deck is already shuffled, these tiles are essentially random
    private void deal (Deck deck, Player.Hand hand, int quantity) {
        if(quantity < 1) return;
        if(deck == null || hand == null) return;

        for(int i=0; i<quantity; i++){
            hand.add(deck.pop());
        }
        return;
    }

    private void dealHand (Deck deck, Player.Hand hand) {
        deal(deck, hand, Player.Hand.HAND_SIZE);
        return;
    }

    private void draw (Deck deck, Player.Hand hand) {
        deal(deck, hand, 1);
        return;
    }
}
