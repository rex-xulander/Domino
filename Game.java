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
        this.p1 = new Player();
        this.p2 = new Player();

        //Generate playing board
        this.board = new Board();
    }

    public boolean isNotOver() {
        return (state != State.END);
    }
    public boolean player1toMove() {
        return (state == State.P1TURN);
    }
    public boolean player2toMove() { return (state == State.P2TURN); }

    //TASK: USE TRY CATCH FOR THIS FUNCTIONALITY
    public void play(int handIndex){
        //NEED TO CHECK FOR INVALID MOVES
        Player current = currentPlayer();
        Piece piece = current.hand.get(handIndex);

        //if it is a valid move
        board.playedPieces.add(piece);
        board.updateOpenings();
        current.hand.remove(piece);

        return;
    }

    public void print() {
        System.out.print("Player 1 Hand: "); p1.hand.print(); System.out.println();
        System.out.print("Player 2 Hand: "); p2.hand.print(); System.out.println();

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

    private Player currentPlayer() {
        if (this.state == State.P1TURN) return p1;
        if (this.state == State.P2TURN) return p2;
        return null;
    }
}
