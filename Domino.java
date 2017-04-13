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
    game.print();

    while(game.isNotOver()) {

        System.out.println();

        if(game.player1toMove()){
            System.out.println("PLAYER 1 TURN");
            System.out.println("Possible moves: "); game.p1.printMoves();
        }
        if(game.player2toMove()){
            System.out.println("PLAYER 2 TURN");
            System.out.println("Possible moves: "); game.p2.printMoves();
        }

        Scanner reader = new Scanner(System.in);
        System.out.print("\n\n" + "Enter move: ");
        int n = reader.nextInt();

        game.state = Game.State.END;
    }


    return;
  }

}



