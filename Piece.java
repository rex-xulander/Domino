/**
 * Created by Rex on 4/12/17.
 */

import java.util.*;

public class Piece
{
  final static short MIN_PIECE = 0;
  final static short MAX_PIECE = 6;

  End left;
  End right;

  public Piece () {
    return;
  }

  public Piece (int a, int b) {
    left = new End((short) a);
    right = new End((short) b);
  }

  protected void print() {
      System.out.print("[" +left.value+ "|" +right.value+ "]");
  }

  public boolean greaterThan (Piece other) {
      if (this instanceof Doublet && other instanceof Tile) {
          return true;
      }
      else if (this instanceof Tile && other instanceof Doublet) {
          return false;
      }
      else if (this instanceof Doublet && other instanceof Doublet){
          return this.right.getValue() > other.right.getValue() ? true : false;
      }
      else {
          //Case: both pieces are standard tiles
          if (this.right.getValue() == other.right.getValue()) return this.left.getValue() > other.left.getValue() ? true : false;
          else return this.right.getValue() > other.right.getValue() ? true : false;
      }
  }

  protected class End {
    protected Piece next;
    private short value;

    public End() {return;}
    public End(short value) {
      this.value = value;
    }

    public short getValue() {
        return value;
    }
  }
}

