/**
 * Created by Rex on 4/13/17.
 */

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;

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
        p.hand.remove(m.piece);

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
        for(Piece piece : player.hand) {
            if (piece.hasValue(board.leftOpening.value()))    player.possibleMoves.add(new Move(board.leftOpening.value(), piece));
            if (piece.hasValue(board.rightOpening.value()))   player.possibleMoves.add(new Move(board.rightOpening.value(), piece));
            if(board.upOpening != null && board.downOpening != null) {
                if (piece.hasValue(board.upOpening.value()))    player.possibleMoves.add(new Move(board.upOpening.value(), piece));
                if (piece.hasValue(board.downOpening.value()))   player.possibleMoves.add(new Move(board.downOpening.value(), piece));
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
        for (Piece piece: board.playedPieces) {
            piece.print();
        }
    }

    //INNER CLASSES
    public class Move {
        int opening;
        Piece piece;

        public Move () { return; }

        public Move (int opening, Piece piece) {
            this.opening = opening;
            this.piece = piece;
        }

        public int newOpening() {
            //return the side of the tile that is now open
            return (piece.left == opening) ? piece.right:piece.left;
        }
    }
    public class FirstMove extends Move {
        public FirstMove (Piece piece) {
            this.piece = piece;
        }
    }
}


//TODO MOVES AND OPENINGS ARE THE SAME THING SO CONSOLIDATE THEM