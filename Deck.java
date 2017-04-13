import java.util.ArrayList;
import java.math.*;

/**
 * Created by Rex on 4/12/17.
 */

//TASK: REFACTOR DECK AS DEALER INNER CLASS
public class Deck extends ArrayList<Piece>
{

    protected void print() {
        if (this == null) return;

        for(Piece piece : this) {
            piece.print();
        }

        return;
    }

    protected void prettyPrint() {
        if (this == null) return;

        for (int i=0; i<this.size(); i++) {
            if(i%7==0 & i!=0) System.out.print("\n");
            this.get(i).print();
        }

        return;
    }

    //Iteratively shuffles deck by swapping with random index
    protected void shuffle() {
        for (int i=0; i<this.size(); i++) {
            int k = rand(0, i);
            //Swap i with random index k
            Piece temp = this.get(i);
            this.set(i, this.get(k));
            this.set(k, temp);
        }
    }

    protected Piece pop() {
        Piece top = this.get(size()-1);
        this.remove(size()-1);
        return top;
    }

    //Returns random integer between lower and higher, inclusive
    private int rand (int lower, int higher) {
        return lower + (int) (Math.random()*(higher-lower)+1);
    }

    public Deck() {
        super();

        //Creates all tiles and puts them into deck
        //28 total tiles with end values from [0,6]
        //6 double tiles, 22 non-double tiles
        for (short i = Tile.MIN_PIECE; i <= Tile.MAX_PIECE; i++){
            for (short j=i; j<= Tile.MAX_PIECE; j++) {
                if (i==j) 	this.add(new Doublet(i, j));
                else 		this.add(new Tile(i, j));
            }
        }

        this.trimToSize();
        return;
    }

}