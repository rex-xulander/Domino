import java.util.ArrayList;

/**
 * Created by Rex on 4/13/17.
 */
public class Player {

    public String name;
    private int score;

    protected Hand hand;

    //Get value of highest double tile in hand, or return -1 if none exist
    protected short highestDoublet() {
        short highestDoublet = -1;

        for (Tile tile : hand) {
            if (tile.isDoublet && tile.end1 > highestDoublet) {
                highestDoublet = tile.end1;
            }
        }

        return highestDoublet;
    }

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

    public class Hand extends ArrayList<Tile> {

        final static short HAND_SIZE = 7;

        public void print() {
            if (this == null) return;

            for(Tile tile : this) {
                tile.print();
            }

            return;
        }

        public Hand() {
            super();
            return;
        }
    }
}
