/**
 * Created by Rex on 4/13/17.
 */
import java.util.Comparator;

public class PieceComparator implements Comparator<Piece> {

    //TASK: make this more elegant / clean it up
    public int compare(Piece p1, Piece p2) {

        if (p1 instanceof Doublet && p2 instanceof Tile) {
            return -1;
        }
        else if (p1 instanceof Tile && p2 instanceof Doublet) {
            return 1;
        }
        else if (p1 instanceof Doublet && p2 instanceof Doublet){
            return p1.right.getValue() > p2.right.getValue() ? -1 : 1;
        }
        else {
            //Case: both pieces are standard tiles
            if (p1.right.getValue() == p2.right.getValue()) return p1.left.getValue() > p2.left.getValue() ? -1 : 1;
            else return p1.right.getValue() > p2.right.getValue() ? -1:1;
        }
    }

}
