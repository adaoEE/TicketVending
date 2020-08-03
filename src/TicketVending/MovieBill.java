package TicketVending;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *******************************************************************************
 * File name - MovieBill.java
 * <br>Description This is the MovieBill class handles choices, selections, and
 * output of the bill. It get all passing values from the main GUI. Required
 * movie input from a JComboBox and number of adults or children/seniors to
 * calculate bill. The Paradise theater have two ticket prices – a regular price
 * $9.00 and a matinee discount price $5.50 for early show times. Children under
 * 10 and seniors receive the matinee price for all show times. All tickets are
 * taxed at a current rate of 8.9% sales tax.
 * <br>Created on 11/27/2017 Window 10, NetBeans 8.2, jdk 1.8.0_144
 *
 * @author Anh Dao
 * @version 2.0  <pre> 
 *   project:       Project #3, Box office ticket vending technology.
 *   platform:      JDK 1.8.0_144
 *   IDE:           Netbeans 8.2
 *   course:        CS141, 2810, 2:50PM
 *   hours:         17 hours
 * </pre>
 *
 * @see java.text.DecimalFormat
 * @see java.text.NumberFormat
 * @see java.text.SimpleDateFormat
 * @see java.util.Calendar
 * ****************************************************************************
 */
public class MovieBill {

    // class constants
    static final double REGULAR_PRICE = 9.00,
            DISCOUNT_PRICE = 5.50,
            TAX = 1.089; //SALES TAX 8.9% + 100%

    double total = 0.0;   // sum of all services provided    

    StringBuffer display = new StringBuffer(); // output

    // instance variables
    private int regularTickets;
    private int discountTickets;
    private String selectedMovie;
    private String showtime;
    private boolean discount;

    /**
     * Constructor creates a new MovieBill object, giving initial values
     *
     */
    public MovieBill() {
        regularTickets = 0;
        discountTickets = 0;
        selectedMovie = "";
        showtime = "";
        discount = false;
    }

  /**
   * Overloaded constructor creates a new MovieBill object
   * getting values from the main GUI and process the information 
   * @param rt stands for regular tickets
   * @param dt stands for discount tickets
   * @param sm stands for selectedMovie
   * @param st stands for show time
   * @param dc stands for discount or not
   */
    public MovieBill(int rt, int dt, String sm, String st, boolean dc) {
        this.regularTickets = rt;
        this.discountTickets = dt;
        this.selectedMovie = sm;
        this.showtime = st;
        this.discount = dc;
    }

    /**
     * Calculate the bill regarding to discount, tax, number of tickets
     *
     * @return total amount due when enter is pressed
     */
    public double calculateMovieBill() {
        if (discount) {
            total = TAX * (DISCOUNT_PRICE * (regularTickets + discountTickets));
        } else {
            total = TAX * (REGULAR_PRICE * regularTickets + DISCOUNT_PRICE * discountTickets);
        }
        return total;
    }

    /**
     * method to display the bill be with all services provided
     *
     * @return display -- a Stringbuffer object containing formateed bill
     */
    public StringBuffer displayBill() {
        NumberFormat dollars = NumberFormat.getCurrencyInstance();
        DecimalFormat quantity = new DecimalFormat("#");
        Calendar myCalendar = Calendar.getInstance();
        SimpleDateFormat myTimeFormat = new SimpleDateFormat("hh:mm:ss a 'on'");
        SimpleDateFormat myDateFormat = new SimpleDateFormat("EEE, MMM-dd-yyyy");

        myCalendar.getTime();

        display.append("Transaction Time: " + myTimeFormat.format(myCalendar.getTime())
                + " " + myDateFormat.format(myCalendar.getTime()) + "\n");
        display.append(padSpaces(selectedMovie, "", "Showtime: " + showtime));
        display.append('\n');
        double regularTotal = REGULAR_PRICE * regularTickets;
        double discountTotal = DISCOUNT_PRICE * discountTickets;
        if (discount) {
            display.append("Discount Time." + "\n");
            regularTotal = DISCOUNT_PRICE * regularTickets;
        }
        display.append("Type of Tickets--------------Number of Tickets--------------------Amount" + '\n'); //78 

        if (regularTickets != 0) {
            display.append(padSpaces("Adult(s)", quantity.format(regularTickets), dollars.format(regularTotal) + '\n'));
        }
        if (discountTickets != 0) {
            display.append(padSpaces("Child(ren)/Senior(s)", quantity.format(discountTickets), dollars.format(discountTotal) + '\n'));
        }
        display.append("---------------------------------------------------------------------" + '\n');
        display.append(padSpaces("Total (tax included)", "", dollars.format(total) + '\n'));
        return display;
    }

    /**
     * method to count spaces and pad spaces to a line - basically a left tab
     *
     * @return line -- a StringBuffer object containing formated bill
     */
    private StringBuffer padSpaces(String first, String second, String third) {
        final int MAX_SPACES = 73; // with of the output text area
        StringBuffer line = new StringBuffer(first);

        //find number of spaces needed to pad the string for right - justification
        int numSpaces = MAX_SPACES - first.length() - 43 - second.length();

        for (int i = 0; i < numSpaces; i++) {
            line.append(" ");
        }
        line.append(second);

        int numSpaces2 = MAX_SPACES - numSpaces - second.length() - first.length() - third.length();

        for (int j = 0; j < numSpaces2; j++) {
            line.append(" ");
        }
        line.append(third);
        return line;
    }

}
