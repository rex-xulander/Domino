

public class Game {
    Player p1;
    Player p2;
    Dealer dealer;

    Board board;

    public enum Turn { P1TURN, P2TURN }
    public enum State { Play, Pass, Blocked, P1Finish, P2Finish, GameOver }

    public Turn turn;
    public State state;


    //Constructors
    public Game() {
        //Create all person agents
        this.p1 = new Player("REX");
        this.p2 = new Player("MER");
        this.dealer = new Dealer();

        //Generate playing board
        this.board = new Board();
    }
    public Game(Player one, Player two) {
        this.p1 = one;
        this.p2 = two;
        this.dealer = new Dealer();

        this.board = new Board();
    }
    public void newRound() {

        p1.clearHand(); p2.clearHand();
        this.dealer = new Dealer();
        this.board = new Board();
        dealer.dealFullHand(p1.hand);
        dealer.dealFullHand(p2.hand);

        p1.sortHand();
        p2.sortHand();

        if (state == State.P1Finish) {
            turn = Turn.P1TURN;
            for(Piece piece: p1.hand) {
                p1.possibleMoves.add(new FirstMove(piece));
            }
        } else if (state == State.P2Finish) {
            turn = Turn.P2TURN;
            for(Piece piece: p2.hand) {
                p2.possibleMoves.add(new FirstMove(piece));
            }
        } else if (state == State.Blocked) {
            Piece p1Highest = p1.highestPiece();
            Piece p2Highest = p2.highestPiece();

            if (p1Highest.greaterThan(p2Highest)) {
                p1.possibleMoves.add(new FirstMove(p1Highest));
                turn = Turn.P1TURN;
            } else {
                p2.possibleMoves.add(new FirstMove(p2Highest));
                turn = Turn.P2TURN;
            }
        }
    }

    //Game Status
    public boolean isNotOver() {
        return (state != State.GameOver);
    }
    public boolean isPlayer1Turn() { return (turn == Turn.P1TURN); }
    public Player getCurrentPlayer() {
        return isPlayer1Turn() ? p1:p2;
    }
    public Player getOtherPlayer() {
        return isPlayer1Turn() ? p2:p1;
    }


    //Deals hands to both players and sets turn to player with highest piece
    public void start() {
        dealer.dealFullHand(p1.hand);
        dealer.dealFullHand(p2.hand);

        p1.sortHand();
        p2.sortHand();

        Piece p1Highest = p1.highestPiece();
        Piece p2Highest = p2.highestPiece();

        if (p1Highest.greaterThan(p2Highest)) {
            p1.possibleMoves.add(new FirstMove(p1Highest));
            turn = Turn.P1TURN;
        } else {
            p2.possibleMoves.add(new FirstMove(p2Highest));
            turn = Turn.P2TURN;
        }


        return;
    }
    public void makeMove(int moveIndex){
        //TODO: validate move with GUI
        state = State.Play;

        if(turn == Turn.P1TURN) {
            makeMoveOnBoard(p1, p1.possibleMoves.get(moveIndex));
            return;
        } else if (turn == Turn.P2TURN) {
            makeMoveOnBoard(p2, p2.possibleMoves.get(moveIndex));
            return;
        }
    }
    private void makeMoveOnBoard(Player p, Move m) {
        board.placePiece(m);
        p.hand.remove(m.playerPiece);

        updatePlayerScore(p);
    }
    public void draw(Player player) {
        dealer.dealSingleTile(player.hand);
        return;
    }
    public boolean boneyardNotEmpty() {
        return (!dealer.deck.isEmpty());
    }
    public void pass() {
        state = State.Pass;
    }

    public void updatePlayerScore(Player p) {
        int score = board.boardScore();
        if (score%5 == 0) {
            p.addScore(score);
        }
    }
    public void updatePossibleMoves (Player player) {
        player.possibleMoves.clear();
        for(Piece pieceInHand : player.hand) {
            for(Board.Opening opening: board.openings) {
                if(pieceInHand.hasValue(opening.connectingValue())) {
                    player.possibleMoves.add(new Move(opening.connectingValue(), opening.piece(), pieceInHand));
                }
            }
        }
    }

    public void checkGameStatus() {
        if (p1.finishedHand()) {
            state = State.P1Finish; return;
        }
        if (p2.finishedHand()) {
            state = State.P2Finish; return;
        }
        if (p1.score() >= Domino.WINNING_SCORE || p2.score() >= Domino.WINNING_SCORE) {
            Player winningPlayer = p1.score() > p2.score() ? p1:p2;
            System.out.println("CONGRATULATIONS "+winningPlayer.name);
            state = State.GameOver;
        }
        return;
    }
    public void changePlayer() {
        if(turn == Turn.P1TURN) {
            turn = Turn.P2TURN;
        } else {
            turn = Turn.P1TURN;
        }
    }

    public void print() {
        for(int i=1; i<60; i++){
            System.out.print("-");
        }
        System.out.println("");
        p1.print();
        System.out.println();
        p2.print();
        System.out.println();
        System.out.println("Remaining tiles from Boneyard: "+dealer.deck.size());

        //BOARD SPACE OF 6 LINES
        System.out.println();
        printBoard();
        System.out.println();
        System.out.println();
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

    public boolean timeForNextRound() {
        return (state == State.P2Finish || state == State.P1Finish || state == State.Blocked);
    }

    //Inner Classes
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
