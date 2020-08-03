package TicketVending;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JMenu;
import javax.swing.Timer;

/**
 *******************************************************************************
 * File name - DateAndClock.java
 * <br>Description This is the Date And Clock to create a Date box, time box and
 * basically blue the time and clock to the extend menu.
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
 * @see java.awt.event.ActionEvent
 * @see java.awt.event.ActionListener
 * @see java.text.SimpleDateFormat
 * @see java.util.Date
 * @see javax.swing.JMenu
 * @see javax.swing.Timer
 * ****************************************************************************
 */
public class DateAndClock extends JMenu implements ActionListener {

    SimpleDateFormat myDateFormat;
    private int hour;
    private int minute;
    private int second;

    long count = 0;

    /**
     * Clock and time to set up clock and time after every second
     *
     * @param whichOne return whatever the other class asked
     */
    public DateAndClock(String whichOne) {
        if (whichOne.equals("time")) {
            myDateFormat = new SimpleDateFormat("hh:mm:ss a");
        }
        if (whichOne.equals("date")) {
            myDateFormat = new SimpleDateFormat("MMM-dd-yyyy");
        }
        if (whichOne.equals("dayOfWeek")) {
            myDateFormat = new SimpleDateFormat("EEE,");
        }
        
        setForeground(Color.black);
        Timer oneSec = new Timer(1000, this);
        oneSec.start();
    }

    /**
     *
     * @param e ActionEvent by override
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Date date = new Date();
        setText(myDateFormat.format(date));

    }
}
