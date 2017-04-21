import java.util.concurrent.LinkedBlockingDeque;
import java.util.ArrayList;


//TODO: Can consolidate all played pieces into a single deque array with direction

public class Board {

    LinkedBlockingDeque<Piece> playedPieces_horizontal;
    LinkedBlockingDeque<Piece> playedPieces_vertical;

    ArrayList<Opening> openings;

    public enum Direction { LEFT, RIGHT, UP, DOWN };

    Piece spinner;
    boolean canPlayVertical = false;

    public void placePiece(Game.Move move) {
        if(move instanceof Game.FirstMove) {
            placeFirstPiece(move);
        }

        Piece playedPiece = move.playerPiece;

        for (Opening opening: openings) {
            if(move.targetOpening == opening.piece) {
                orientPiece(playedPiece, move.connectingValue, opening.direction);
                addToPlayedPiecesOnBoard(playedPiece, opening.direction);
                opening.update(move.nonConnectingValue(), playedPiece);
                break;
            }
        }

        //Spinner game condition checks
        tryToSetSpinner(playedPiece);
        if(!canPlayVertical) checkForVerticalOpenings();
    }
    private void placeFirstPiece(Game.Move firstMove) {
        Piece playedPiece = firstMove.playerPiece;
        tryToSetSpinner(playedPiece);

        openings.add(new Opening(playedPiece.left, playedPiece, Direction.LEFT));
        openings.add(new Opening(playedPiece.right, playedPiece, Direction.RIGHT));

        playedPieces_horizontal.add(playedPiece);
        return;
    }

    private boolean hasSpinner() {
        return (spinner!=null);
    }
    public int getScore() {
        int score = 0;
        for(Opening opening: openings) {
            //Vertical openings are only counted if a piece has been played on the spinner
            if (!((opening.direction == Direction.UP || opening.direction == Direction.DOWN) && (opening.piece == spinner)))
                score += opening.value();
        }
        return score;
    }

    private void checkForVerticalOpenings() {
        if(hasSpinner() && openings.size() == 2) {
            //Check to see if spinner has left and right connecting tiles
            for (Opening opening: openings) {
                if(opening.piece == spinner) return;
            }
            openings.add(new Opening(spinner.left, spinner, Direction.UP));
            openings.add(new Opening(spinner.right, spinner, Direction.DOWN));

            playedPieces_vertical.add(spinner);
            canPlayVertical = true;
        }
        return;
    }
    private void tryToSetSpinner(Piece playedPiece) {
        if(spinner == null && playedPiece.isDoublet()) {
            spinner = playedPiece;
        }
        return;
    }

    private void addToPlayedPiecesOnBoard(Piece playedPiece, Direction opening) {
        switch (opening) {
            case LEFT:
                playedPieces_horizontal.addFirst(playedPiece);
                break;
            case RIGHT:
                playedPieces_horizontal.addLast(playedPiece);
                break;
            case UP:
                playedPieces_vertical.addFirst(playedPiece);
                break;
            case DOWN:
                playedPieces_vertical.addLast(playedPiece);
                break;
        }
        return;
    }
    private void orientPiece(Piece playedPiece, int connectingValue, Direction openingDirection) {
        if(playedPiece instanceof Piece.Normal) {
            if (connectingValue == playedPiece.left) {
                if (openingDirection == Direction.LEFT || openingDirection == Direction.UP) playedPiece.flip();
            } else if (connectingValue == playedPiece.right) {
                if (openingDirection == Direction.RIGHT || openingDirection == Direction.DOWN) playedPiece.flip();
            }
        }
    }

    public class Opening {
        private int value;
        private Piece piece;
        private Direction direction;

        public Opening(int value, Piece piece, Direction direction) {
            this.value = value;
            this.piece = piece;
            this.direction = direction;
            return;
        }

        public int value() {
            return value;
        }
        public int score() {
            if (piece.isDoublet() && playedPieces_horizontal.size() != 1) return 2*value;
            else return value;
        }
        public Direction direction() {return direction;}

        public Piece piece() {
            return piece;
        }

        private void update(int value, Piece piece) {
            this.value = value;
            this.piece = piece;
            return;
        }
    }

    public Board() {
        playedPieces_horizontal = new LinkedBlockingDeque<Piece>();
        playedPieces_vertical = new LinkedBlockingDeque<Piece>();
        openings = new ArrayList<Opening>();
    }
}
