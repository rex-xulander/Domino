/**
 * Created by Rex on 4/13/17.
 */
public class Game {
    Player p1;
    Player p2;
    Dealer dealer;

    boolean hasSpinner;

    Opening left;
    Opening right;
    Opening up;
    Opening down;

    public enum State { START, P1TURN, P2TURN, END }

    public State state;

    public Game() {
        this.dealer = new Dealer();

        this.p1 = new Player();
        this.p2 = new Player();

        this.hasSpinner = false;
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
        dealer.deck.prettyPrint();

    }

    public void start() {
        dealer.dealFullHand(p1.hand);
        dealer.dealFullHand(p2.hand);

        p1.sortHand();
        p2.sortHand();

        System.out.print("p1 highest piece is : "); p1.highestPiece().print();
        System.out.print("p2 highest piece is : "); p2.highestPiece().print();
    }

    private class Opening {
        short value;
        Piece open;

        public Opening(Piece open, short value) {
            this.open = open;
            this.value = value;
        }
    }

}
