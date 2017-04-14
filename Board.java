import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Rex on 4/13/17.
 */
public class Board {

    boolean hasSpinner;
    LinkedBlockingDeque<Piece> playedPieces;

    int leftOpening;
    int rightOpening;

    public int getScore() {
        return leftOpening+rightOpening;
    }

    public void placePiece(Game.Move move) {
        Piece playedPiece = move.piece;

        if(move instanceof Game.FirstMove) {
            leftOpening = playedPiece.left;
            rightOpening = playedPiece.right;
            playedPieces.add(playedPiece);
        }
        else {
            if (move.opening == leftOpening) {

                //MAKE THIS CLEANER
                if(playedPiece instanceof Piece.Normal && move.opening == playedPiece.left){
                    playedPiece.flip();
                }

                leftOpening = move.newOpening();
                playedPieces.addFirst(playedPiece);
            } else if (move.opening == rightOpening) {

                if(playedPiece instanceof Piece.Normal && move.opening == playedPiece.right){
                    playedPiece.flip();
                }

                rightOpening = move.newOpening();
                playedPieces.addLast(playedPiece);
            }
        }

    }

    public Board() {
        this.hasSpinner = false;
        playedPieces = new LinkedBlockingDeque<Piece>();
    }
}
