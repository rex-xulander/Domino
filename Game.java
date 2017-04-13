/**
 * Created by Rex on 4/13/17.
 */

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
    public boolean player1toMove() {
        return (state == State.P1TURN);
    }
    public boolean player2toMove() { return (state == State.P2TURN); }
    private Player currentPlayer() {
        if (this.state == State.P1TURN) return p1;
        if (this.state == State.P2TURN) return p2;
        return null;
    }

    /* GAME ACTIONS */
    public void start() {
        dealer.dealFullHand(p1.hand);
        dealer.dealFullHand(p2.hand);

        p1.sortHand();
        p2.sortHand();

        //SET STATE TURN to Player with highest piece
        //TASK - CHANGE THIS TO A LAMBDA FUNCTION
        if (p1.highestPiece().greaterThan(p2.highestPiece())){
            p1.possibleMoves.add(p1.highestPiece());
            state = State.P1TURN;
        }
        else{
            p2.possibleMoves.add(p2.highestPiece());
            state = State.P2TURN;
        }
        return;
    }
    //TASK: USE TRY CATCH FOR THIS FUNCTIONALITY
    public void play(int handIndex){
        //NEED TO CHECK FOR INVALID MOVES
        Player current = currentPlayer();
        Piece piece = current.hand.get(handIndex);

        //if it is a valid move
        board.playedPieces.add(piece);
        current.hand.remove(piece);


        board.updateOpenings();
        int score = board.getScore();
        if (score%5 == 0) {
            current.addScore(score);
        }

        return;
    }
    public void changePlayer() {
        state = (state == State.P1TURN) ? State.P2TURN : State.P1TURN;
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

        System.out.println("Remaining tiles from Boneyard:");
        dealer.deck.prettyPrint();
    }
    public void printBoard() {
        for (Piece piece: board.playedPieces) {
            piece.print();
        }
    }
}
