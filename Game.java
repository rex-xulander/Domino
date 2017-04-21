/**
 * Created by Rex on 4/13/17.
 */

public class Game {
    Player p1;
    Player p2;
    Dealer dealer;

    Board board;

    public enum State { P1TURN, P2TURN, END }

    public State state;

    public Game() {
        //Create all person agents
        this.dealer = new Dealer();
        this.p1 = new Player("REX");
        this.p2 = new Player("MER");

        //Generate playing board
        this.board = new Board();
    }

    /* Game Status Information */
    public boolean isNotOver() {
        return (state != State.END);
    }
    public boolean isPlayer1Turn() { return (state == State.P1TURN); }
    public Player getCurrentPlayer() {
        return isPlayer1Turn() ? p1:p2;
    }
    public Player getOtherPlayer() {
        return isPlayer1Turn() ? p2:p1;
    }

    /* GAME ACTIONS */
    public void start() {
        dealer.dealFullHand(p1.hand);
        dealer.dealFullHand(p2.hand);

        p1.sortHand();
        p2.sortHand();

        Piece p1Highest = p1.highestPiece();
        Piece p2Highest = p2.highestPiece();

        if (p1Highest.greaterThan(p2Highest)) {
            p1.possibleMoves.add(new FirstMove(p1Highest));
            state = State.P1TURN;
        } else {
            p2.possibleMoves.add(new FirstMove(p2Highest));
            state = State.P2TURN;
        }


        return;
    }
    public void makeMove(int moveIndex){
        //TODO: validate move

        if(state == State.P1TURN) {
            makeMove(p1, p1.possibleMoves.get(moveIndex));
            return;
        } else if (state == State.P2TURN) {
            makeMove(p2, p2.possibleMoves.get(moveIndex));
            return;
        }
    }
    private void makeMove(Player p, Move m) {
        board.placePiece(m);
        p.hand.remove(m.playerPiece);

        updatePlayerScore(p);
    }
    public void draw(Player player) {
        dealer.dealSingleTile(player.hand);
        return;
    }

    public void updatePlayerScore(Player p) {
        int score = board.getScore();
        if (score%5 == 0) {
            p.addScore(score);
        }
    }
    public void changePlayer() {
        if(state == State.P1TURN) {
            state = State.P2TURN;
        } else {
            state = State.P1TURN;
        }
    }

    public void updatePossibleMoves (Player player) {
        player.possibleMoves.clear();
        for(Piece pieceInHand : player.hand) {
            for(Board.Opening opening: board.openings) {
                if(pieceInHand.hasValue(opening.value())) {
                    player.possibleMoves.add(new Move(opening.value(), opening.piece(), pieceInHand));
                }
            }
        }
    }

    /* Basic GUI using System.IO */
    public void print() {
        p1.print();
        System.out.println();
        p2.print();
        System.out.println();

        //BOARD SPACE OF 6 LINES
        System.out.println();
        printBoard();
        System.out.println();
        System.out.println();

        System.out.println("Remaining tiles from Boneyard: "+dealer.deck.size());
    }
    public void printBoard() {

        System.out.print("LEFT AND RIGHT: ");
        for (Piece piece: board.playedPieces_horizontal) {
            piece.print();
        }
        System.out.println();
        System.out.print("UP AND DOWN: ");
        for (Piece piece: board.playedPieces_vertical) {
            piece.print();
        }
    }

    //INNER CLASSES
    public class Move {
        int connectingValue;
        Piece targetOpening; //the opening, played piece on the board
        Piece playerPiece;

        public Move () { return; }

        public Move (int connectingValue, Piece boardPiece, Piece playerPiece) {
            this.connectingValue = connectingValue;
            this.targetOpening = boardPiece;
            this.playerPiece = playerPiece;
        }

        public int nonConnectingValue() {
            //return the side of the tile that is now open
            return (playerPiece.left == connectingValue) ? playerPiece.right:playerPiece.left;
        }
    }
    public class FirstMove extends Move {
        public FirstMove (Piece piece) {
            this.playerPiece = piece;
        }
    }
}

// a move should contain an placed piece and the connectingValue piece you are referring to

//TODO MOVES AND OPENINGS ARE THE SAME THING SO CONSOLIDATE THEM