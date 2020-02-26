/**
 * @author Reges and Stepp, Building Java Programs
 * modified by W.P. Iverson, Bellevue College,
 * January 2019 for CS211 class
 */
 
// Stuart Reges
// 3/28/07
//
// Class ShoppingFrame provides the user interface for a simple shopping
// program, starting with Building Java Programs, chapter 10, project 1.
//

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

@SuppressWarnings("serial")
public class ShoppingFrame extends JFrame {
	
	// data FIELDS
    private ShoppingCart selections; 	// assigned
    private JTextField total;			// standard Oracle JAVA GUI
    final JCheckBox cb = new JCheckBox("qualify for discount");
    
    // This INNER CLASS is just to make some anonymous objects
    // done this way because nothing outside this class needs it
    private class EventExample implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
            selections.setDiscount(cb.isSelected());
            updateTotal();
        }
    }

    public ShoppingFrame(ArrayList<SKU> products)      {
        // create frame and order list
        setTitle("CS Gift Catalog");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        selections = new ShoppingCart();
        
        // Just making sure we're using the Iverson version:
        if (!(selections instanceof ArrayList))
        	throw new RuntimeException("This quarter I require an ArrayList");
        	
        // set up text field with order total
        total = new JTextField("$0.00", 12);
        total.setEditable(false);
        total.setEnabled(false);
        total.setDisabledTextColor(Color.BLACK);
        JPanel p = new JPanel();
        p.setBackground(Color.blue);
        JLabel l = new JLabel("Order Total");
        l.setForeground(Color.YELLOW);
        p.add(l);
        p.add(total);
        add(p, BorderLayout.NORTH);

        p = new JPanel(new GridLayout(products.size(), 1));
        removeDuplicates(products);
        for (SKU i : products) {
        		addItem(i, p); // add selections to panel
        }
        add(p, BorderLayout.CENTER); // add panel to frame

        p = new JPanel();
       add(makeCheckBoxPanel(), BorderLayout.SOUTH);

        // adjust size to just fit
        pack();
    }

    // Should probably use Set rather than Array List, but this is Chapter 10
    private void removeDuplicates(ArrayList<SKU> products) {
		for (SKU i: products) {
			if (products.indexOf(i) != products.lastIndexOf(i))
				products.remove(i);
		}
	}

	// Sets up the "discount" checkbox for the frame
    private JPanel makeCheckBoxPanel() {
        JPanel p = new JPanel();
        p.setBackground(Color.blue);
        p.add(cb);
        cb.addActionListener(new EventExample());
        return p;
    }

    // adds a product to the panel, including a textfield for user input of
    private void addItem(final SKU product, JPanel p) {
        JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sub.setBackground(new Color(0, 180, 0));
        final JTextField quantity = new JTextField(3);
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Below is a trick to make an anonymous object from an anonymous class
        // The addActionListener needs something that implements ActionListener
        // So we make an object from a class that does not exist as follows:
        quantity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateItem(product, quantity);
                quantity.transferFocus();
            }
        });
        
        // same trick
        quantity.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                updateItem(product, quantity);
            }
        });
        
        sub.add(quantity);
        JLabel l = new JLabel("" + product);
        l.setForeground(Color.white);
        sub.add(l);
        p.add(sub);
    }

    // When the user types a new value into one of the quantity fields,
    // parse the input and update the ShoppingCart.  Display an error
    // message if text is not a number or is negative.
    private void updateItem(SKU product, JTextField quantity) {
        int number;
        String text = quantity.getText().trim();
        try {
            number = Integer.parseInt(text);
        } catch (NumberFormatException error) {
            number = 0;
        }
        if (number <= 0 && text.length() > 0) {
            Toolkit.getDefaultToolkit().beep();
            quantity.setText("");
            number = 0;
        }
        selections.add(new NumSelected(product, number));
        updateTotal();
    }

    // reset the text field for order total
    private void updateTotal() {
        double amount = selections.getTotal();
        total.setText(NumberFormat.getCurrencyInstance().format(amount));
    }
    
    // Below used to be separate ShoppingMain, now an easier entry point:
    public static void main(String[] args) {
    	// the Catalog is a simple Array List of Items:
        ArrayList<SKU> list = new ArrayList<SKU>();
        list.add(new SKU("silly putty", 3.95, 10, 19.99));
        list.add(new SKU("silly string", 3.50, 10, 14.95));
        list.add(new SKU("bottle o bubbles", 0.99));
        list.add(new SKU("Nintendo Wii system", 389.99));
        list.add(new SKU("Mario Computer Science Party 2 (Wii)", 49.99));
        list.add(new SKU("Don Knuth Code Jam Challenge (Wii)", 49.99));
        list.add(new SKU("Computer Science pen", 3.40));
        list.add(new SKU("Rubik's cube", 9.10));
        list.add(new SKU("Computer Science Barbie", 19.99));
        list.add(new SKU("'Java Rules!' button", 0.99, 10, 5.0));
        list.add(new SKU("'Java Rules!' bumper sticker", 0.99, 20, 8.95));

        ShoppingFrame f = new ShoppingFrame(list);
        f.setVisible(true);
    }
    
}