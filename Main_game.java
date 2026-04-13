//Ai has been used to generate some documentations  and in some part of the logic
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Main class for the Memory Game.
 * The game consists of a 4x4 grid of cards.
 * The player flips two cards at a time to find matching pairs.
 * When all pairs are matched, the game ends.
 */
public class Main_game extends JFrame {
    private JButton[] btn; // arrays of buttons representing cards on GUI
    private Card[] cards; // array of card object containing values and states
    private int first_index = -1; // first flipepd card index
    private int second_index = -1; // secibd flipped card index
    private int moves = 0; // counter for total moves
    private JLabel status_label; // label to display number of moves
    private boolean can_click = true; // flag to prevent clicks while checking matches

    public Main_game() {
        setTitle("Memory game , only the allmighty can win");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Instructions of the main game
        JOptionPane.showMessageDialog(this,
                "Flip two cards to find a match.\nIf you find all, you win!");
        // uppermost Label
        status_label = new JLabel("move: 0");
        add(status_label, BorderLayout.NORTH);

        // panel which holds the card buttons in a 4x4 grid
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(4, 4));
        btn = new JButton[16];
        cards = new Card[16];
        // prepare card values
        ArrayList<String> vals = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            vals.add("" + i);
            vals.add("" + i);
        }
        Collections.shuffle(vals); // shuffle cards

        // objects and buttons are created
        for (int i = 0; i < 16; i++) {
            cards[i] = new Card(vals.get(i));
            btn[i] = new JButton("???"); // text on the hidden cards
            final int idx = i; // for the lambda expression
            btn[i].addActionListener(e -> handle_click(idx));
            pnl.add(btn[i]);

        }
        add(pnl, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Button click handeler at index idx
     * 
     * @param idx index of the clicked card
     */
    private void handle_click(int idx) {
        if (!can_click) // ignores clicks during checing
            return;
        if (cards[idx].is_matched() || cards[idx].is_Revealed()) // ignores alredy matched cards
            return;

        // reveal the card
        btn[idx].setText(cards[idx].get_value());
        cards[idx].set_Revaled(true);
        if (first_index == -1) {
            first_index = idx;

        } else {
            second_index = idx; // second card flipped
            moves++;
            status_label.setText("total Moves:" + moves);
            can_click = false;// more clicks are prevented
            check_match();
        }

    }

    /**
     * matched card checker
     * if the cards are matched marks as matched and checks win
     * if not matched, flips them back
     */
    private void check_match() {
        if (cards[first_index].get_value().equals(cards[second_index].get_value())) {
            // match found
            cards[first_index].set_matched(true);
            cards[second_index].set_matched(true);
            reset_turn();
            can_click = true;
            check_win();
        } else {
            // no mathc, flips card back after a second
            javax.swing.Timer tmr = new javax.swing.Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent a) {
                    btn[first_index].setText("?");
                    btn[second_index].setText("?");
                    cards[first_index].set_Revaled(false);
                    cards[second_index].set_Revaled(false);

                    reset_turn();
                    can_click = true;
                }

            });
            tmr.setRepeats(false);
            tmr.start();
        }
    }

    /**
     * Resets the indiced of the flipped cards for the next turn
     */
    private void reset_turn() {
        first_index = -1;
        second_index = -1;
    }

    /**
     * Checks the win by seeing if all the cards are matched or not
     */
    private void check_win() {
        for (Card k : cards) {
            if (!k.is_matched())
                return; // if any cards is unmatched, stop
        }
        JOptionPane.showMessageDialog(this, "Victory You finished in " + moves + " moves");
        System.exit(0); // closes the game

    }

    public static void main(String[] args) {
        new Main_game();
    }
}
