import java.util.Comparator;

public class Piece
{
  final static int MIN_PIECE = 0;
  final static int MAX_PIECE = 6;

  int left;
  int right;

  public Piece () {
      left = -1;
      right = -1;
      return;
  }
  public Piece (int a, int b) {
    this.left = a;
    this.right = b;
  }

  protected void print() {
      System.out.print("[" +left+ "|" +right+ "]");
  }

  public boolean greaterThan (Piece other) {
      if (this instanceof Doublet && other instanceof Normal) {
          return true;
      }
      else if (this instanceof Normal && other instanceof Doublet) {
          return false;
      }
      else if (this instanceof Doublet && other instanceof Doublet){
          return this.right > other.right ? true : false;
      }
      else {
          //Case: both pieces are standard Normals
          if (this.right == other.right) return this.left > other.left ? true : false;
          else return this.right > other.right ? true : false;
      }
  }

  public boolean hasValue (int openingValue) {
      if (left == openingValue || right == openingValue) return true;
      return false;
  }

  public void flip() {
      int temp = left;
      left = right;
      right = temp;
  }


    public static class Normal extends Piece {

        public Normal (int a, int b) {
            left = a < b ? a:b;
            right = a < b ? b:a;
        }

    }

    public static class Doublet extends Piece {

        //Member Variables from Piece
        //End left;
        //End right;

        int up;
        int down;

        public Doublet () {
            return;
        }

        public Doublet (int a, int b) {
            super(a, b);
        }

    }

    public static class PieceComparator implements Comparator<Piece> {

        //TASK: make this more elegant / clean it up
        //also figure out how to use greater than to figure it out!
        public int compare(Piece p1, Piece p2) {

            if (p1 instanceof Doublet && p2 instanceof Normal) {
                return 1;
            }
            else if (p1 instanceof Normal && p2 instanceof Doublet) {
                return -1;
            }
            else if (p1 instanceof Doublet && p2 instanceof Doublet){
                return p1.right > p2.right ? 1 : -1;
            }
            else {
                //Case: both pieces are standard Normals
                if (p1.right == p2.right) return p1.left > p2.left ? 1 : -1;
                else return p1.right > p2.right ? 1:-1;
            }
        }

    }
}

