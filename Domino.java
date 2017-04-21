//********************************************
//Name:     Dominoes
//Author:   Rex Xu
//Date:     Apr 12, 2017
//Descr:    Basic dominoes game with smart AI
//@author   Rex XU
//********************************************

import java.util.Scanner;

public class Domino
{

  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {
    Game game = new Game();

    game.start();
    Scanner reader = new Scanner(System.in);

    while(game.isNotOver()) {

        game.print();
        System.out.println();

        Player player = game.getCurrentPlayer();
        Player other = game.getOtherPlayer();

        System.out.println("Moves for "+player.name);
        player.printMoves();

        if (player.hasNoMoves()) {
            game.draw(player);
            System.out.println("NO LEGAL MOVES");
            String random = reader.next();
            game.updatePossibleMoves(player);
        }
        else {
            System.out.println("\n\n"+"Same piece moves orderd by LRUD");
            System.out.print("Enter move (1 to end): ");
            int n = reader.nextInt();

            //TODO: if there are two spinner openings, L and U moves are played first because of checking order

            System.out.println();
            game.makeMove(n-1);

            game.updatePossibleMoves(other);
            game.changePlayer();
        }
    }


    return;
  }

}



