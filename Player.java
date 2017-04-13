import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Rex on 4/13/17.
 */
public class Player {

    public String name;
    private int score;
    ArrayList<Piece> possibleMoves;

    protected Hand hand;

    protected void sortHand() {
        hand.sort();
    }

    public void printMoves() {
        for( Piece piece: possibleMoves) {
            piece.print();
        }
    }

    protected Piece highestPiece() {
        //HAND SHOULD ALREADY BE SORTED in ASC
        return hand.get(hand.size()-1);
    }

    public Player() {
        this.name = "No Name";
        this.score = 0;
        this.hand = new Hand();
        this.possibleMoves = new ArrayList<Piece>();
        return;
    }

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.hand = new Hand();
        return;
    }

    public class Hand extends ArrayList<Piece> {

        final static short HAND_SIZE = 7;
            //Collections.sort(this, new PieceComparator());

        public void print() {
            if (this == null) return;

            for(Piece piece : this) {
                piece.print();
            }

            return;
        }
        private void sort() {
            Collections.sort(this, new PieceComparator());
        }

        public Hand() {
            super();
            return;
        }
    }
}
