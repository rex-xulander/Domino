import java.util.ArrayList;

/**
 * Created by Rex on 4/13/17.
 */
public class Board {

    boolean hasSpinner;
    ArrayList<Piece> playedPieces;

    int leftOpening;
    int rightOpening;
//    Opening up;
//    Opening down;

    public int getScore() {
        int score = 0;
//        if (left != null)   score+=left.value;
//        if (right != null)  score+=right.value;
//        if (up != null)     score+=up.value;
//        if (down != null)   score+=down.value;
        return score;
    }

    public void makeMove(Game.Move move) {
        playedPieces.add(move.piece);

        //clean up make move!!
//        int newOpeningValue = (move.piece.left.getValue()==move.opening.open.left.getValue()) ? move.piece.right.getValue() : move.piece.left.getValue();

        //move.opening

    }
//    public void updateOpenings() {
//        if (playedPieces.size() == 1) {
//            Piece firstPiece = playedPieces.get(0);
//
//            left = new Opening (firstPiece, firstPiece.left.getValue());
//            right = new Opening (firstPiece, firstPiece.right.getValue());
//        }
//    }

    public Board() {
        this.hasSpinner = false;
        playedPieces = new ArrayList<Piece>();
    }

//    public class Opening {
//        int value;
//        Piece open;
//
//        public Opening(Piece open, int value) {
//            this.open = open;
//            this.value = value;
//        }
//    }
}
