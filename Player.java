import java.util.Collections;
import java.util.ArrayList;

/**
 * Created by Rex on 4/13/17.
 */
public class Player {

    /* Information variables */
    public String name;
    private int score;

    protected Hand hand;
    ArrayList<Game.Move> possibleMoves;

    protected Piece highestPiece() {
        //HAND SHOULD ALREADY BE SORTED in ASC
        return hand.get(hand.size()-1);
    }
    protected void sortHand() {
        hand.sort();
    }
    public void addScore(int points) {
        this.score += points;
    }
    public boolean hasNoMoves() { return possibleMoves.isEmpty(); }

    public void print() {
        System.out.print(name+": " +score+ "\t\t\t");
        hand.print();
    }
    public void printMoves() {
        for( Game.Move move: possibleMoves) {
            move.playerPiece.print();
        }
    }

    public Player() {
        this.name = "No Name";
        this.score = 0;
        this.hand = new Hand();
        this.possibleMoves = new ArrayList<Game.Move>();
        return;
    }
    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.hand = new Hand();
        this.possibleMoves = new ArrayList<Game.Move>();
        return;
    }

    public class Hand extends ArrayList<Piece> {

        final static int HAND_SIZE = 7;
            //Collections.sort(this, new Piece.PieceComparator());

        public void print() {
            if (this == null) return;

            for(Piece piece : this) {
                piece.print();
            }

            return;
        }
        private void sort() {
            Collections.sort(this, new Piece.PieceComparator());
        }

        public Hand() {
            super();
            return;
        }
    }
}
