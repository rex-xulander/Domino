import java.util.ArrayList;

/**
 * Created by Rex on 4/13/17.
 */
public class Player {

    public String name;
    private int score;

    protected Hand hand;

    /*The player with the highest piece plays first in domino when first starting a game.
     *The player with the highest doublet plays first, or if neither player has a doublet, then
     *the piece with the highest point value is played next.
     */
    /*
    protected Piece highestPiece() {
        short highestDoublet = -1;

        for (Piece piece : hand) {
            if (piece instanceof Tile && tile.end1 > highestDoublet) {
                highestDoublet = tile.end1;
            }
        }

        return highestDoublet;
    }*/

    public Player() {
        this.name = "No Name";
        this.score = 0;
        this.hand = new Hand();
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

        public void print() {
            if (this == null) return;

            for(Piece piece : this) {
                piece.print();
            }

            return;
        }

        public Hand() {
            super();
            return;
        }
    }
}
