/**
 * Created by Rex on 4/13/17.
 */

import java.util.ArrayList;

public class Dealer {
    Deck deck;
    public Dealer() {
        this.deck = new Deck();
        this.deck.shuffle();
    }

    /* Methods */
    protected void dealSingleTile (ArrayList<Piece> hand) {
        dealHelper(hand, 1);
        return;
    }
    protected void dealFullHand (ArrayList<Piece> hand) {
        dealHelper(hand, Player.Hand.HAND_SIZE);
        return;
    }
    private void dealHelper (ArrayList<Piece> hand, int quantity) {
        if(quantity < 1) return;
        if(deck == null || hand == null) return;

        for(int i=0; i<quantity; i++){
            hand.add(deck.pop());
        }
        return;
    }

    /* Inner Class Deck */
    public static class Deck extends ArrayList<Piece>
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

            //Creates all Normals and puts them into deck
            //28 total Normals with end values from [0,6]
            //6 double Normals, 22 non-double Normals
            for (int i = Piece.Normal.MIN_PIECE; i <= Piece.Normal.MAX_PIECE; i++){
                for (int j = i; j<= Piece.Normal.MAX_PIECE; j++) {
                    if (i==j) 	this.add(new Piece.Doublet(i, j));
                    else 		this.add(new Piece.Normal(i, j));
                }
            }

            this.trimToSize();
            return;
        }

    }
}
