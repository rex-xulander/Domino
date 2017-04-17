import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Rex on 4/13/17.
 */

//TO DO , CLARIFY BETWEEN OPENING SCORE AND VALUE.

public class Board {

    boolean hasSpinner;
    LinkedBlockingDeque<Piece> playedPieces;

    Opening leftOpening;
    Opening rightOpening;

    public int getScore() {
        return leftOpening.score()+rightOpening.score();
    }

    public void placePiece(Game.Move move) {
        Piece playedPiece = move.piece;

        if(move instanceof Game.FirstMove) {
            leftOpening = new Opening(playedPiece.left, playedPiece.isDoublet());
            rightOpening = new Opening(playedPiece.right, playedPiece.isDoublet());
            playedPieces.add(playedPiece);
        }
        else {
            if (move.opening == leftOpening.value()) {

                //MAKE THIS CLEANER
                if(playedPiece instanceof Piece.Normal && move.opening == playedPiece.left){
                    playedPiece.flip();
                }

                leftOpening.set(move.newOpening(), move.piece.isDoublet());
                playedPieces.addFirst(playedPiece);
            } else if (move.opening == rightOpening.value()) {

                if(playedPiece instanceof Piece.Normal && move.opening == playedPiece.right){
                    playedPiece.flip();
                }

                rightOpening.set(move.newOpening(), move.piece.isDoublet());
                playedPieces.addLast(playedPiece);
            }
        }

    }

    public class Opening {
        private int value;
        private boolean isDoublet;

        public Opening (int value, boolean isDoublet) {
            this.value = value;
            this.isDoublet = isDoublet;
            return;
        }

        public int value() {
            return value;
        }

        public int score() {
            if (isDoublet) return 2*value;
            else return value;
        }

        public boolean isDoublet() {
            return isDoublet;
        }

        public void set(int value, boolean isDoublet) {
            this. value = value;
            this.isDoublet = isDoublet;
            return;
        }
    }

    public Board() {
        this.hasSpinner = false;
        playedPieces = new LinkedBlockingDeque<Piece>();
    }
}
