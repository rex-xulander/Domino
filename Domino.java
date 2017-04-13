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
            System.out.println("Possible moves for P1: "); game.p1.printMoves();
        }
        if(game.player2toMove()){
            System.out.println("Possible moves for P2: "); game.p2.printMoves();
        }

        Scanner reader = new Scanner(System.in);
        System.out.print("\n\n" + "Enter move: ");
        int n = reader.nextInt();

        game.play(n);
        game.print();

        game.state = Game.State.END;
    }


    return;
  }

}



