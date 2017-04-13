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

