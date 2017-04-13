/*
 *
 * Created by Rex on 4/13/17.
 */
public class Tile extends Piece {

    public Tile (int a, int b) {
        left = a < b ? new End((short) a) : new End((short) b);
        right = a < b ? new End((short) b) : new End((short) a);
    }

}