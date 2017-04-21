//********************************************
//Name:     Dominoes
//Author:   Rex Xu
//Date:     Apr 12, 2017
//Descr:    Basic dominoes game with smart AI
//@author   Rex XU
//********************************************

//TODO: allow for multiple rounds within a game
//TODO: create AI for game

import java.util.Scanner;


public class Domino
{

    static final int WINNING_SCORE = 50;

  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {

    Game game = new Game();

    game.start();
    Scanner reader = new Scanner(System.in);

    while(game.isNotOver()) {

        game.print();
        System.out.println();

        Player currentPlayer = game.getCurrentPlayer();
        Player otherPlayer = game.getOtherPlayer();

        currentPlayer.printMoves();

        if (currentPlayer.hasNoMoves()) {
            System.out.println("No legal moves for " + currentPlayer.name);
            if(game.boneyardNotEmpty()) {
                game.draw(currentPlayer);
                System.out.println("Draw? (Y/N)");
                String random = reader.next();
                game.updatePossibleMoves(currentPlayer);
            }
            else //if boneyard is empty and player has no moves
            {
                if (game.state == Game.State.Pass) {
                    System.out.println("GAME OVER TWO PASSES = A BLOCKED GAME");
                } else {
                    System.out.println("PASS");
                    game.pass();
                    game.updatePossibleMoves(otherPlayer);
                    game.changePlayer();
                }
            }
        }
        else {
            System.out.println("\n"+"Enter move "+currentPlayer.name+":");
            int n = reader.nextInt();

            //TODO: if there are two spinner openings, L and U moves are played first because of checking order

            System.out.println();
            game.makeMove(n-1);

            game.checkForVictor();

            //TODO: no need to do this if Game is OVER
            game.updatePossibleMoves(otherPlayer);
            game.changePlayer();
        }
    }


    return;
  }

}



