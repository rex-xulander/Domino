import java.util.ArrayList;

/**
 * Created by Rex on 4/13/17.
 */
public class Board {

    boolean hasSpinner;
    ArrayList<Piece> playedPieces;

    Opening left;
    Opening right;
    Opening up;
    Opening down;

    public int getScore() {
        int score = 0;
        if (left != null)   score+=left.value;
        if (right != null)  score+=right.value;
        if (up != null)     score+=up.value;
        if (down != null)   score+=down.value;
        return score;
    }

    public void updateOpenings() {
        if (playedPieces.size() == 1) {
            Piece firstPiece = playedPieces.get(0);

            left = new Opening (firstPiece, firstPiece.left.getValue());
            right = new Opening (firstPiece, firstPiece.right.getValue());
        }
    }

    public Board() {
        this.hasSpinner = false;
        playedPieces = new ArrayList<Piece>();
    }

    public class Opening {
        short value;
        Piece open;

        public Opening(Piece open, short value) {
            this.open = open;
            this.value = value;
        }
    }

}
