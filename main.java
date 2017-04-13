//********************************************
//Name:     Dominoes
//Author:   Rex Xu
//Date:     Apr 12, 2017
//Descr:    Basic dominoes game with smart AI
//********************************************

import java.lang.Math;
import java.util.*;


public class HelloWorld
{
  // arguments are passed using the text field below this editor
  public static void main(String[] args)
  {
    /*Tile tile = new Tile(1,2);
    tile.print();*/
    Sytem.out.println("TEST");
    return;
  }
}


public class Tile
{
  final static short int MIN_TILE = 0;
  final static short int MAX_TILE = 6;
  
  short int end1;
  short int end2;
  boolean isDoublet;
  
  protected print() {
    System.out.print('[ '+end1+' | '+end2' ]');
  }
  
  public Tile (int a, int b) {
    end1 = a;
    end2 = b;
    isDoublet = false;
  }
  
  public Tile (int a, int b, boolean doublet) {
    end1 = a;
    end2 = b;
    isDouble = doublet;
  }
}  



public class Deck 
{
  
  private ArrayList<Tile> deck;
  
  protected void printDeck() {
    if (deck == null) return;
    
    for(Tile tile:deck) {
      tile.print();
    }  
    
    return;
  }
    
    
  public Deck() 
  {
    
    deck = new ArrayList<Tile>();
  	
    //Creates all tiles and puts them into deck
    //28 total tiles with end values from [0,6]
    //6 double tiles, 22 non-double tiles
    for (int i=MIN_TILE; i<=MAX_TILE; i++){
      for (int j=i; j<=MAX_TILE; j++) {
        if (i=j) 	deck.put(new Tile(i, j, true));
        else 		deck.put(new Tile(i, j));
      }
    }
    
  }
  
}
