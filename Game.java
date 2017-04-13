/**
 * Created by Rex on 4/13/17.
 */

import java.util.ArrayList;

public class Game {
    Player p1;
    Player p2;
    Dealer dealer;

    boolean hasSpinner;
    ArrayList<Piece> playedPieces;

    Opening left;
    Opening right;
    Opening up;
    Opening down;

    public enum State { P1TURN, P2TURN, END }

    public State state;

    public Game() {
        this.dealer = new Dealer();

        this.p1 = new Player();
        this.p2 = new Player();

        this.hasSpinner = false;
        playedPieces = new ArrayList<Piece>();
    }

    public boolean isNotOver() {
        return (state != State.END);
    }
    public boolean player1toMove() {
        return (state == State.P1TURN);
    }
    public boolean player2toMove() { return (state == State.P2TURN); }

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

        //SET STATE TURN to Player with highest piece
        //TASK - CHANGE THIS TO A LAMBDA FUNCTION
        if (p1.highestPiece().greaterThan(p2.highestPiece())){
            p1.possibleMoves.add(p1.highestPiece());
            state = State.P1TURN;
        }
        else{
            p2.possibleMoves.add(p2.highestPiece());
            state = State.P2TURN;
        }
        return;
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
