import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Rex on 4/13/17.
 */

//TO DO , CLARIFY BETWEEN OPENING SCORE AND VALUE.

public class Board {

    LinkedBlockingDeque<Piece> playedPieces;

    Opening leftOpening;
    Opening rightOpening;
    Opening upOpening;
    Opening downOpening;

    Piece spinner;

    public int getScore() {
        int score = leftOpening.score() + rightOpening.score();
        if(upOpening != null && downOpening != null) {
            if(upOpening.piece != spinner)  score += upOpening.score();
            if(downOpening.piece != spinner) score += downOpening.score();
        }
        return score;
    }

    public void placePiece(Game.Move move) {
        Piece playedPiece = move.piece;


        if(move instanceof Game.FirstMove) {
            leftOpening = new Opening(playedPiece.left, playedPiece);
            rightOpening = new Opening(playedPiece.right, playedPiece);
            playedPieces.add(playedPiece);
        }
        else {
            if (move.opening == leftOpening.value()) {

                //MAKE THIS CLEANER
                if(playedPiece instanceof Piece.Normal && move.opening == playedPiece.left){
                    playedPiece.flip();
                }

                leftOpening.set(move.newOpening(), move.piece);
                playedPieces.addFirst(playedPiece);
            } else if (move.opening == rightOpening.value()) {

                if(playedPiece instanceof Piece.Normal && move.opening == playedPiece.right){
                    playedPiece.flip();
                }

                rightOpening.set(move.newOpening(), move.piece);
                playedPieces.addLast(playedPiece);
            }
        }

        //IF IT IS FIRST DOUBLET PLAYED, SET IT AS SPINNER
        if(spinner == null && playedPiece.isDoublet()) {
            spinner = playedPiece;
        }

        if(spinner != null && leftOpening.piece != spinner && rightOpening.piece != spinner && upOpening == null && downOpening == null) {
            upOpening = new Opening(spinner.left, spinner);
            downOpening = new Opening(spinner.right, spinner);
        }

    }


    public class Opening {
        private int value;
        private Piece piece;

        public Opening (int value, Piece piece) {
            this.value = value;
            this.piece = piece;
            return;
        }

        public int value() {
            return value;
        }

        public int score() {
            if (piece.isDoublet() && playedPieces.size() != 1) return 2*value;
            else return value;
        }

        public boolean isDoublet() {
            return piece.isDoublet();
        }

        public void set(int value, Piece piece) {
            this. value = value;
            this.piece = piece;
            return;
        }
    }

    public Board() {
        playedPieces = new LinkedBlockingDeque<Piece>();
    }
}
