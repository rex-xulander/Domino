import java.util.ArrayList;

/**
 * Created by Rex on 4/13/17.
 */
public class Board {

    boolean hasSpinner;
    ArrayList<Piece> playedPieces;

    int leftOpening;
    int rightOpening;

    public int getScore() {
        return leftOpening+rightOpening;
    }

    public void placePiece(Game.Move move) {
        Piece playedPiece = move.piece;
        playedPieces.add(playedPiece);

        if(move instanceof Game.FirstMove) {
            leftOpening = playedPiece.left;
            rightOpening = playedPiece.right;
        }
        else {
            if (move.opening == leftOpening) {
                leftOpening = move.newOpening();
            } else if (move.opening == rightOpening) {
                rightOpening = move.newOpening();
            }
        }

    }

    public Board() {
        this.hasSpinner = false;
        playedPieces = new ArrayList<Piece>();
    }
}
