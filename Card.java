public class Card {
    private String val; // card value which is 1 to 8
    private boolean reval; // true if card currently revealed
    private boolean matched; // true if card has been matched

    /**
     * Constructor for card
     * 
     * @param val value of the card
     */
    public Card(String val) {
        this.val = val;
        this.reval = false;
        this.matched = false;

    }

    /**
     * @return the cards value
     */
    public String get_value() {
        return val;
    }

    /**
     * @return true if the card is revealed
     */
    public boolean is_Revealed() {
        return reval;
    }

    /**
     * 
     * @param reval sets whether the card is revealed
     */
    public void set_Revaled(boolean reval) {
        this.reval = reval;
    }

    /**
     * @return true if the card has been matched
     */
    public boolean is_matched() {
        return matched;

    }

    /**
     * sets weather the card has been matched
     * 
     * @param matched
     */
    public void set_matched(boolean matched) {
        this.matched = matched;

    }
}
