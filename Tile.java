/**
 * Created by Rex on 4/12/17.
 */

import java.util.*;

public class Tile
{
  final static short MIN_TILE = 0;
  final static short MAX_TILE = 6;

  short end1;
  short end2;
  boolean isDoublet;

  public Tile (int a, int b) {
    end1 = (short) a;
    end2 = (short) b;
    isDoublet = false;
  }

  public Tile (int a, int b, boolean doublet) {
    end1 = (short) a;
    end2 = (short) b;
    isDoublet = doublet;
  }

    protected void print() {
        System.out.print("[" +end1+ "|" +end2+ "]");
    }

    private class DoubleComparator implements Comparator<Tile> {

      //TASK: CHECK TO SEE THAT DOUBLES COMPARATOR ONLY COMPARES DOUBLES.. NOT POSSIBLE TO HAVE EQUAL
      public int compare (Tile t1, Tile t2) {
        if(t1.end1 > t2.end2) return 1;
        else if (t1.end1 < t2.end2) return -1;
        else return 0;
      }

      public int compare (Tile t1, short t2) {
          if(t1.end1 > t2) return 1;
          else if (t1.end1 < t2) return -1;
          else return 0;
      }
    }
}

