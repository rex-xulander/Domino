/**
 * Created by Rex on 4/13/17.
 */

import java.util.*;

public class PlayedSet {
    ArrayList<Tile> initial;

    public void print() {
        for (Tile tile : initial) {
            tile.print();
        }
    }

    public PlayedSet() {
        this.initial = new ArrayList<Tile>();
    }
}
