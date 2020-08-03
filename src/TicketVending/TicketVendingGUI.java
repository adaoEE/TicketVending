package TicketVending;

import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;

/**
 *******************************************************************************
 * File name - TicketVending.java
 * <br>Description This is the Ticket Vending GUI for choices, selections, and
 * output of the bill for purchasing movie tickets. The application simulates a
 * ticket vending operation at a theater. Required movie input from a JComboBox
 * and number of adults or children/seniors to calculate bill. The Paradise
 * theater have two ticket prices – a regular price $9.00 and a matinee discount
 * price $5.50 for early show times. Children under 10 and seniors receive the
 * matinee price for all show times. All tickets are taxed at a current rate of
 * 8.9% sales tax.
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
 * @see java.awt.Toolkit
 * @see java.awt.print.PrinterException
 * @see java.io.BufferedReader
 * @see java.io.BufferedWriter
 * @see java.io.File
 * @see java.io.FileNotFoundException
 * @see java.io.FileReader
 * @see java.io.FileWriter
 * @see java.io.IOException
 * @see java.text.DateFormat
 * @see java.text.NumberFormat
 * @see java.text.SimpleDateFormat
 * @see java.time.LocalDateTime
 * @see java.util.ArrayList
 * @see java.util.Date
 * @see javax.swing.JFileChooser
 * @see javax.swing.JOptionPane
 * @see javax.swing.JSpinner
 * @see java.awt.Color
 * @see java.awt.Dimension
 * @see java.net.MalformedURLException
 * @see java.util.logging.Level
 * @see java.util.logging.Logger
 * @see javax.swing.Box
 * ****************************************************************************
 */
public class TicketVendingGUI extends javax.swing.JFrame {

    // Declare class varialbles for methods.
    private double totalSales = 0;
    private int discountTime = 3;
    private Color myColor = new Color(0, 204, 153);
    // create an ArrayList named a movieList to store information of a movie
    ArrayList<MovieInfor> movieList = new ArrayList<>();
    

    /**
     * Constructor creates a new TicketVendingGUI object that represents a GUI
     * for calculating the movie bill and display it on an text area.
     */
    public TicketVendingGUI() {
        initComponents();

        //set the Enter button as default
        this.getRootPane().setDefaultButton(enterJButton);
        //center the form on startup
        this.setLocationRelativeTo(null);
        //set icon for the form
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/MovieIcon.png"));
        //set background color
        this.getContentPane().setBackground(myColor);
        //set header -- the title for the form
        setTitle();
        //test for discount time and show discount message
        isDiscount();
        //set background color for JMenu bar 
        ticketVendingJMenuBar.setBackground(myColor);

        //add a date and clock to the extended JMenu
        DateAndClock timer = new DateAndClock("time");
        DateAndClock date = new DateAndClock("date");
        DateAndClock dayOfWeek = new DateAndClock("dayOfWeek");
        ticketVendingJMenuBar.add(Box.createHorizontalGlue());
        ticketVendingJMenuBar.add(dayOfWeek);
        ticketVendingJMenuBar.add(date);
        ticketVendingJMenuBar.add(timer);
        timer.setEnabled(false);
        date.setEnabled(false);
        dayOfWeek.setEnabled(false);
        //request focus to movie selections
        movieJComboBox.requestFocus();

        //disable spinners editors
        ((JSpinner.DefaultEditor) regularTicketsJSpinner.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) discountTicketsJSpinner.getEditor()).getTextField().setEditable(false);
        //read the external file and add it to JComboBox.
        String fileName = "src/TicketVending/Movies.txt"; // file Name
        readFromFile(fileName);
    }

    /**
     * Read the external text file containing information of movies, analyze it
     * and add the movie name to the JComboBox for user's choices.
     *
     * @see java.io.FileReader
     * @see java.io.BufferedReader
     */
    private void readFromFile(String fileName) {
        try {
            //Read from the external file. The address is saved at initComponents           
            FileReader freader = new FileReader(fileName);
            //Buffer the file
            BufferedReader inputFile = new BufferedReader(freader);
            //Read the first line from the external file.
            String movieEntry = inputFile.readLine();

            //test data and add movie name to Jcombobox
            while (movieEntry != null) {
                // create an array for each movie. array is seperated by ";"
                String[] movieStr = movieEntry.split(";");

                // Check movie data entry is valid
                if (checkMovieData(movieStr)) {
                    //declar subClass of MovieInfor
                    MovieInfor movieInfor = new MovieInfor();
                    //add to an array -- the array could be extended for more information storages or updates                   
                    movieInfor.Title = movieStr[0];
                    movieInfor.Score = movieStr[1];
                    movieInfor.Runtime = Double.parseDouble(movieStr[2]);  //for calculation to hour or second if needed
                    movieInfor.Preview = movieStr[3];
                    movieInfor.ImageUrl = movieStr[4];
                    movieInfor.Rating = movieStr[5];
                    movieInfor.Showtime = showtimeJComboBox.getSelectedItem().toString();
                    movieInfor.VideoUrl = movieStr[6];

                    // Add to movieList (the ArrayList)
                    movieList.add(movieInfor);
                    //set movie name to the movieJComboBox for user's selections
                    movieJComboBox.addItem(movieInfor.Title);

                }// end if
                //catch exceoption -- assum all input is valid
                else {

                }
                movieEntry = inputFile.readLine();
            }// end while
        } catch (FileNotFoundException exp) // file not found - bring JFileChooser
        {
            // exp.printStackTrace();
            JFileChooser chooser = new JFileChooser("src/TicketVending/");
            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File chosenFile = chooser.getSelectedFile();
                readFromFile("src/TicketVending/" + chosenFile.getName());
            }
        } catch (IOException exp) //catch reading error
        {
            JOptionPane.showMessageDialog(null, "Cannot find or open file", "File Read Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * Sets the title of the form
     */
    private void setTitle() {
        this.setTitle("Project #3 ★★ Box Office Ticket Vending Technology ★★ ");
    }

    /**
     * the method update the total price, displays the amount due dynamically
     * when the ticket numbers are changed
     *
     * @see java.text.NumberFormat
     */
    private void updateAmountDue() {
        //declare variable
        double total = 0.0;

        // declare subClass and variables
        NumberFormat dollars = NumberFormat.getCurrencyInstance();
        //get value for input
        int regularTickets = Integer.parseInt(regularTicketsJSpinner.getValue().toString());
        int discountTickets = Integer.parseInt(discountTicketsJSpinner.getValue().toString());
        String selectedMovie = (String) movieJComboBox.getSelectedItem();
        String showtime = (String) showtimeJComboBox.getSelectedItem();

        //call MovieBill class to do the calculation and display on the text area
        MovieBill myBill = new MovieBill(regularTickets, discountTickets, selectedMovie, showtime, isDiscount());
        total = myBill.calculateMovieBill();

        //display Textfield
        amountJTextField.setText(dollars.format(total));
    }

    /**
     * check if it is discount time, and show the price for user
     *
     * @return true
     */
    public boolean isDiscount() {
        int showtime = (int) showtimeJComboBox.getSelectedIndex();

        if (showtime < discountTime) {
            //display the message for discount and explain it 
            messageJLabel.setText("Discount Time!");
            messageJLabel.setToolTipText("Discount for early shows from 12am to 5pm");
            //display the price for discount and explain it
            priceJLabel.setText("Matinee price: $5.50 for all");
            priceJLabel.setToolTipText("for all people");
            //return value
            return true;
        } else {
            messageJLabel.setText("No discount at the time.");
            messageJLabel.setToolTipText("do not qualify for discount at the momment");
            priceJLabel.setText("Adult: $9.00 || Kid/Senior: $5.50");
            priceJLabel.setToolTipText("before sales tax");
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerJPanel = new javax.swing.JPanel();
        logo1JLabel = new javax.swing.JLabel();
        logo2JLabel = new javax.swing.JLabel();
        selectionJPanel = new javax.swing.JPanel();
        movieJComboBox = new javax.swing.JComboBox<>();
        showtimeJComboBox = new javax.swing.JComboBox<>();
        infoJButton = new javax.swing.JButton();
        ticketJFormattedTextField = new javax.swing.JPanel();
        regularTicketsJSpinner = new javax.swing.JSpinner();
        regularTicketsJLabel = new javax.swing.JLabel();
        discountTicketsJSpinner = new javax.swing.JSpinner();
        iscountTicketsJLabel = new javax.swing.JLabel();
        amountJPanel = new javax.swing.JPanel();
        clearJButton = new javax.swing.JButton();
        enterJButton = new javax.swing.JButton();
        amountJTextField = new javax.swing.JTextField();
        discountJPanel = new javax.swing.JPanel();
        messageJLabel = new javax.swing.JLabel();
        priceJLabel = new javax.swing.JLabel();
        transactionJTabbedPane = new javax.swing.JTabbedPane();
        receiptJPanel = new javax.swing.JPanel();
        displayJScrollPane = new javax.swing.JScrollPane();
        outJTextArea = new javax.swing.JTextArea();
        printReceiptJButton = new javax.swing.JButton();
        totalSalesJTextField = new javax.swing.JTextField();
        totalSalesJLabel = new javax.swing.JLabel();
        transactionHistoryJPanel = new javax.swing.JPanel();
        resetJButton = new javax.swing.JButton();
        printTransactionHistorytJButton = new javax.swing.JButton();
        updateMoviesBoxWeeklyJButton = new javax.swing.JButton();
        transactionHistoryJScrollPane = new javax.swing.JScrollPane();
        saleRecordsJTextArea = new javax.swing.JTextArea();
        ticketVendingJMenuBar = new javax.swing.JMenuBar();
        fileJMenu = new javax.swing.JMenu();
        clearJMenuItem = new javax.swing.JMenuItem();
        printJMenuItem = new javax.swing.JMenuItem();
        printReceiptJMenuItem = new javax.swing.JMenuItem();
        fileJSeparator = new javax.swing.JPopupMenu.Separator();
        exitJMenuItem = new javax.swing.JMenuItem();
        actionJMenu = new javax.swing.JMenu();
        receiptJMenuItem = new javax.swing.JMenuItem();
        openTransactionHistoryJMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        printTransactionHistoryJMenuItem = new javax.swing.JMenuItem();
        resetJMenuItem = new javax.swing.JMenuItem();
        updateMoviesJMenuItem = new javax.swing.JMenuItem();
        helpJMenu = new javax.swing.JMenu();
        aboutJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        setResizable(false);

        headerJPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo1JLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MovieRoll.png"))); // NOI18N
        headerJPanel.add(logo1JLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 0, 700, 150));

        logo2JLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Picture2.png"))); // NOI18N
        headerJPanel.add(logo2JLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 260, 140));

        selectionJPanel.setBackground(new java.awt.Color(0, 204, 153));
        selectionJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Movie Selection", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N
        selectionJPanel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        movieJComboBox.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        movieJComboBox.setForeground(new java.awt.Color(255, 255, 255));
        movieJComboBox.setToolTipText("Moive name");
        movieJComboBox.setPreferredSize(new java.awt.Dimension(100, 20));

        showtimeJComboBox.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        showtimeJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00 AM", "12:30 PM", "03:30 PM", "06:00 PM", "09:00 PM", "11:00 PM" }));
        showtimeJComboBox.setSelectedIndex(3);
        showtimeJComboBox.setToolTipText("Showtime");
        showtimeJComboBox.setPreferredSize(new java.awt.Dimension(100, 20));
        showtimeJComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showtimeJComboBoxItemStateChanged(evt);
            }
        });

        infoJButton.setBackground(new java.awt.Color(0, 153, 0));
        infoJButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        infoJButton.setText("Movie Details");
        infoJButton.setToolTipText("Preview, Rating, Poster, Runtime, etc..");
        infoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selectionJPanelLayout = new javax.swing.GroupLayout(selectionJPanel);
        selectionJPanel.setLayout(selectionJPanelLayout);
        selectionJPanelLayout.setHorizontalGroup(
            selectionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionJPanelLayout.createSequentialGroup()
                .addComponent(movieJComboBox, 0, 290, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(infoJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showtimeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        selectionJPanelLayout.setVerticalGroup(
            selectionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectionJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(movieJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, selectionJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(showtimeJComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(infoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ticketJFormattedTextField.setBackground(new java.awt.Color(0, 204, 153));
        ticketJFormattedTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numer of Tickets", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N
        ticketJFormattedTextField.setName(""); // NOI18N
        ticketJFormattedTextField.setLayout(new java.awt.GridLayout(2, 2));

        regularTicketsJSpinner.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        regularTicketsJSpinner.setModel(new javax.swing.SpinnerNumberModel(Byte.valueOf((byte)0), Byte.valueOf((byte)0), Byte.valueOf((byte)50), Byte.valueOf((byte)1)));
        regularTicketsJSpinner.setEditor(new javax.swing.JSpinner.NumberEditor(regularTicketsJSpinner, ""));
        regularTicketsJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                regularTicketsJSpinnerStateChanged(evt);
            }
        });
        ticketJFormattedTextField.add(regularTicketsJSpinner);

        regularTicketsJLabel.setBackground(new java.awt.Color(189, 213, 252));
        regularTicketsJLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        regularTicketsJLabel.setText("Adult(s)");
        regularTicketsJLabel.setToolTipText("Regular price (if at discount time)");
        ticketJFormattedTextField.add(regularTicketsJLabel);

        discountTicketsJSpinner.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        discountTicketsJSpinner.setModel(new javax.swing.SpinnerNumberModel(Byte.valueOf((byte)0), Byte.valueOf((byte)0), Byte.valueOf((byte)50), Byte.valueOf((byte)1)));
        discountTicketsJSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                discountTicketsJSpinnerStateChanged(evt);
            }
        });
        ticketJFormattedTextField.add(discountTicketsJSpinner);

        iscountTicketsJLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        iscountTicketsJLabel.setText("Child/Senior(s)");
        iscountTicketsJLabel.setToolTipText("matinee price for all show times.");
        ticketJFormattedTextField.add(iscountTicketsJLabel);

        amountJPanel.setBackground(new java.awt.Color(0, 204, 153));
        amountJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Amount Due (Including Tax):", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N
        amountJPanel.setForeground(new java.awt.Color(36, 48, 64));
        amountJPanel.setToolTipText("8.9% sales tax");

        clearJButton.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        clearJButton.setForeground(new java.awt.Color(36, 48, 64));
        clearJButton.setMnemonic('r');
        clearJButton.setText("Clear");
        clearJButton.setToolTipText("Clear this transaction");
        clearJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJButtonActionPerformed(evt);
            }
        });

        enterJButton.setBackground(new java.awt.Color(189, 213, 252));
        enterJButton.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        enterJButton.setForeground(new java.awt.Color(36, 48, 64));
        enterJButton.setText("Enter");
        enterJButton.setToolTipText("");
        enterJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterJButtonActionPerformed(evt);
            }
        });

        amountJTextField.setEditable(false);
        amountJTextField.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        amountJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amountJTextField.setText("$0.00");
        amountJTextField.setToolTipText("Of one transaction");

        javax.swing.GroupLayout amountJPanelLayout = new javax.swing.GroupLayout(amountJPanel);
        amountJPanel.setLayout(amountJPanelLayout);
        amountJPanelLayout.setHorizontalGroup(
            amountJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountJPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(amountJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(amountJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        amountJPanelLayout.setVerticalGroup(
            amountJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountJPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(amountJTextField)
                .addContainerGap())
            .addGroup(amountJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(enterJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(clearJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        discountJPanel.setBackground(new java.awt.Color(0, 204, 153));
        discountJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Discount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N
        discountJPanel.setToolTipText("You qualify for discount or not");
        discountJPanel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N

        messageJLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        messageJLabel.setText("Discount message");
        messageJLabel.setToolTipText("");

        priceJLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        priceJLabel.setText("Price");
        priceJLabel.setToolTipText("");

        javax.swing.GroupLayout discountJPanelLayout = new javax.swing.GroupLayout(discountJPanel);
        discountJPanel.setLayout(discountJPanelLayout);
        discountJPanelLayout.setHorizontalGroup(
            discountJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(messageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(priceJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        discountJPanelLayout.setVerticalGroup(
            discountJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountJPanelLayout.createSequentialGroup()
                .addComponent(messageJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(priceJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        transactionJTabbedPane.setBackground(new java.awt.Color(0, 204, 153));
        transactionJTabbedPane.setToolTipText("");
        transactionJTabbedPane.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N

        receiptJPanel.setBackground(new java.awt.Color(0, 204, 153));

        displayJScrollPane.setBackground(new java.awt.Color(51, 51, 51));
        displayJScrollPane.setBorder(null);
        displayJScrollPane.setForeground(new java.awt.Color(255, 255, 255));

        outJTextArea.setEditable(false);
        outJTextArea.setColumns(20);
        outJTextArea.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        outJTextArea.setLineWrap(true);
        outJTextArea.setRows(5);
        outJTextArea.setToolTipText("Receipt of this transaction");
        outJTextArea.setCaretColor(new java.awt.Color(1, 108, 28));
        outJTextArea.setPreferredSize(new java.awt.Dimension(170, 80));
        outJTextArea.setSelectionColor(new java.awt.Color(1, 108, 28));
        displayJScrollPane.setViewportView(outJTextArea);

        printReceiptJButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        printReceiptJButton.setText("Print Receipt");
        printReceiptJButton.setToolTipText("Print out the receipt of this transaction");
        printReceiptJButton.setEnabled(false);
        printReceiptJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptJButtonActionPerformed(evt);
            }
        });

        totalSalesJTextField.setEditable(false);
        totalSalesJTextField.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        totalSalesJTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalSalesJTextField.setText("$0.00");
        totalSalesJTextField.setToolTipText("total sales of the shift");

        totalSalesJLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        totalSalesJLabel.setText("Total Sales Amount:");
        totalSalesJLabel.setToolTipText("Of this shift");

        javax.swing.GroupLayout receiptJPanelLayout = new javax.swing.GroupLayout(receiptJPanel);
        receiptJPanel.setLayout(receiptJPanelLayout);
        receiptJPanelLayout.setHorizontalGroup(
            receiptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(displayJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(receiptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, receiptJPanelLayout.createSequentialGroup()
                        .addGroup(receiptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(printReceiptJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(totalSalesJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, receiptJPanelLayout.createSequentialGroup()
                        .addComponent(totalSalesJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        receiptJPanelLayout.setVerticalGroup(
            receiptJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptJPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(totalSalesJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalSalesJTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(printReceiptJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(receiptJPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(displayJScrollPane)
                .addGap(10, 10, 10))
        );

        transactionJTabbedPane.addTab("Receipt", receiptJPanel);

        transactionHistoryJPanel.setBackground(new java.awt.Color(0, 204, 153));

        resetJButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        resetJButton.setText("Reset");
        resetJButton.setToolTipText("Reset everything! Ready for a new shift.");
        resetJButton.setEnabled(false);
        resetJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetJButtonActionPerformed(evt);
            }
        });

        printTransactionHistorytJButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        printTransactionHistorytJButton.setText("Print Sales Records");
        printTransactionHistorytJButton.setToolTipText("Print sales records at the end of the shift");
        printTransactionHistorytJButton.setActionCommand("Print Sales Records at the end of the shift");
        printTransactionHistorytJButton.setEnabled(false);
        printTransactionHistorytJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTransactionHistorytJButtonActionPerformed(evt);
            }
        });

        updateMoviesBoxWeeklyJButton.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        updateMoviesBoxWeeklyJButton.setText("Update Movies");
        updateMoviesBoxWeeklyJButton.setToolTipText("Update movies weekly from the corporate");
        updateMoviesBoxWeeklyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMoviesBoxWeeklyJButtonActionPerformed(evt);
            }
        });

        transactionHistoryJScrollPane.setBorder(null);

        saleRecordsJTextArea.setEditable(false);
        saleRecordsJTextArea.setColumns(20);
        saleRecordsJTextArea.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        saleRecordsJTextArea.setLineWrap(true);
        saleRecordsJTextArea.setRows(5);
        saleRecordsJTextArea.setToolTipText("HIstory of all transactions");
        saleRecordsJTextArea.setWrapStyleWord(true);
        saleRecordsJTextArea.setBorder(null);
        saleRecordsJTextArea.setMargin(new java.awt.Insets(20, 20, 20, 20));
        transactionHistoryJScrollPane.setViewportView(saleRecordsJTextArea);

        javax.swing.GroupLayout transactionHistoryJPanelLayout = new javax.swing.GroupLayout(transactionHistoryJPanel);
        transactionHistoryJPanel.setLayout(transactionHistoryJPanelLayout);
        transactionHistoryJPanelLayout.setHorizontalGroup(
            transactionHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transactionHistoryJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(transactionHistoryJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(transactionHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(printTransactionHistorytJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateMoviesBoxWeeklyJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        transactionHistoryJPanelLayout.setVerticalGroup(
            transactionHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionHistoryJPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(transactionHistoryJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transactionHistoryJPanelLayout.createSequentialGroup()
                        .addGap(0, 53, Short.MAX_VALUE)
                        .addComponent(resetJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(printTransactionHistorytJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(updateMoviesBoxWeeklyJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(transactionHistoryJScrollPane))
                .addGap(10, 10, 10))
        );

        transactionJTabbedPane.addTab("Transaction History", transactionHistoryJPanel);

        ticketVendingJMenuBar.setBackground(new java.awt.Color(0, 204, 153));
        ticketVendingJMenuBar.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        ticketVendingJMenuBar.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        ticketVendingJMenuBar.setMaximumSize(new java.awt.Dimension(173, 32769));

        fileJMenu.setText("File");
        fileJMenu.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N

        clearJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        clearJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        clearJMenuItem.setMnemonic('C');
        clearJMenuItem.setText("Clear");
        clearJMenuItem.setToolTipText("Clear the receipt and amount due");
        clearJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(clearJMenuItem);

        printJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        printJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        printJMenuItem.setMnemonic('P');
        printJMenuItem.setText("Print");
        printJMenuItem.setToolTipText("Print GUI");
        printJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printJMenuItem);

        printReceiptJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        printReceiptJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        printReceiptJMenuItem.setMnemonic('t');
        printReceiptJMenuItem.setText("Print Receipt");
        printReceiptJMenuItem.setToolTipText("Print out receipt");
        printReceiptJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(printReceiptJMenuItem);
        fileJMenu.add(fileJSeparator);

        exitJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        exitJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        exitJMenuItem.setMnemonic('x');
        exitJMenuItem.setText("Exit");
        exitJMenuItem.setToolTipText("Exit the application");
        exitJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJMenuItemActionPerformed(evt);
            }
        });
        fileJMenu.add(exitJMenuItem);

        ticketVendingJMenuBar.add(fileJMenu);

        actionJMenu.setText("Transaction");
        actionJMenu.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N

        receiptJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        receiptJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        receiptJMenuItem.setMnemonic('e');
        receiptJMenuItem.setText("Receipt");
        receiptJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(receiptJMenuItem);

        openTransactionHistoryJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        openTransactionHistoryJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        openTransactionHistoryJMenuItem.setMnemonic('h');
        openTransactionHistoryJMenuItem.setText("Transaction History");
        openTransactionHistoryJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openTransactionHistoryJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(openTransactionHistoryJMenuItem);
        actionJMenu.add(jSeparator1);

        printTransactionHistoryJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        printTransactionHistoryJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        printTransactionHistoryJMenuItem.setMnemonic('n');
        printTransactionHistoryJMenuItem.setText("Print Transaction History");
        printTransactionHistoryJMenuItem.setToolTipText("Print out the Tranaction records of this shift");
        printTransactionHistoryJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTransactionHistoryJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(printTransactionHistoryJMenuItem);

        resetJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        resetJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        resetJMenuItem.setMnemonic('r');
        resetJMenuItem.setText("Reset");
        resetJMenuItem.setToolTipText("Reset at tge end of the day");
        resetJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(resetJMenuItem);

        updateMoviesJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.ALT_MASK));
        updateMoviesJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        updateMoviesJMenuItem.setMnemonic('k');
        updateMoviesJMenuItem.setText("Update Movies Weekly");
        updateMoviesJMenuItem.setToolTipText("Reset at tge end of the day");
        updateMoviesJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateMoviesJMenuItemActionPerformed(evt);
            }
        });
        actionJMenu.add(updateMoviesJMenuItem);

        ticketVendingJMenuBar.add(actionJMenu);

        helpJMenu.setMnemonic('H');
        helpJMenu.setText("Help");
        helpJMenu.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N

        aboutJMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        aboutJMenuItem.setFont(new java.awt.Font("Franklin Gothic Book", 0, 18)); // NOI18N
        aboutJMenuItem.setMnemonic('A');
        aboutJMenuItem.setText("About");
        aboutJMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutJMenuItemActionPerformed(evt);
            }
        });
        helpJMenu.add(aboutJMenuItem);

        ticketVendingJMenuBar.add(helpJMenu);

        setJMenuBar(ticketVendingJMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(selectionJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(discountJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ticketJFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(amountJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(transactionJTabbedPane))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(headerJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(discountJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selectionJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ticketJFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amountJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(transactionJTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Clear the form by passing it to clearJButtonActionPerformed
     *
     * @param evt ActionEvent performed by clearJButton
     */
    private void clearJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJMenuItemActionPerformed
        // TODO add your handling code here:
        clearJButtonActionPerformed(evt);
    }//GEN-LAST:event_clearJMenuItemActionPerformed

    /**
     * Print out the form using PrintUtilities class by passing form.
     *
     * @param evt ActionEvent performed by printJMenuItem
     */
    private void printJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printJMenuItemActionPerformed
        PrintUtilities.printComponent(this);
    }//GEN-LAST:event_printJMenuItemActionPerformed

    /**
     * Print out the text area by passing it to printReceiptJButton.
     *
     * @param evt ActionEvent performed by printReceiptJButton
     */
    private void printReceiptJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceiptJMenuItemActionPerformed
        printReceiptJButtonActionPerformed(evt);
    }//GEN-LAST:event_printReceiptJMenuItemActionPerformed

    /**
     * Exits the application.
     *
     * @param evt ActionEvent performed by exitJMenuItem.
     */
    private void exitJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJMenuItemActionPerformed
        
        String[] objectButtons = {"Yes","No"};
    int promptResult = JOptionPane.showOptionDialog(null, 
        "Did you save everything? \nAre you sure you want to exit?", "Warning Message", 
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
        objectButtons,objectButtons[1]);
    if(promptResult==0)
    {
      System.exit(0);          
    }
//        System.exit(0);
    }//GEN-LAST:event_exitJMenuItemActionPerformed

    /**
     * Creates a new AboutGUI object passing this form and setting it visible.
     *
     * @param evt ActionEvent performed by aboutJMenuItem
     */
    private void aboutJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutJMenuItemActionPerformed
        AboutJDialog myAbout = new AboutJDialog(this, true);
        myAbout.movieInfo = "test movie info";
        myAbout.setVisible(true);
    }//GEN-LAST:event_aboutJMenuItemActionPerformed

    /**
     * Print out the form using print method .
     *
     * @param evt ActionEvent performed by printReceiptJButton
     */
    private void printReceiptJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printReceiptJButtonActionPerformed
        try {
            outJTextArea.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_printReceiptJButtonActionPerformed

    /**
     * <h1>
     * Calculates movie bill by passing data to MovieBill class, display to the
     * text area.
     * <p>
     * It first completes the validation process, changing the error message if
     * there is no ticket selected. A called method passing data to MovieBill
     * class is then initialized calculations and fed a boolean if it is
     * discount time It start setting text to the receipt form, and append to
     * the transaction history and save the all transactions to an external
     * file, which can access later.
     *
     * @param evt ActionEvent preformed by enterJButton
     */
    private void enterJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterJButtonActionPerformed
        // declare variables
        double total = 0.0;
        //get the current currency
        NumberFormat dollars = NumberFormat.getCurrencyInstance();

        //get input
        int regularTickets = Integer.parseInt(regularTicketsJSpinner.getValue().toString());
        int discountTickets = Integer.parseInt(discountTicketsJSpinner.getValue().toString());
        String selectedMovie = (String) movieJComboBox.getSelectedItem();
        String showtime = (String) showtimeJComboBox.getSelectedItem();

        //check if input is valid and display a missing info message
        if (regularTickets == 0 & discountTickets == 0) {
            JOptionPane.showMessageDialog(null, "Choose a movie and buy at leat one ticket ",
                    "Missing information", JOptionPane.WARNING_MESSAGE);
        } else {
            //Creates a new MovieBill object passing this form, and and call the methods to calculate and display.
            MovieBill myBill = new MovieBill(regularTickets, discountTickets, selectedMovie, showtime, isDiscount());
            total = myBill.calculateMovieBill();
            //add the total to totalSales
            totalSales += total;

            //display amount due text field
            amountJTextField.setText(dollars.format(total));
            //display one transaction receipt
            outJTextArea.setText(myBill.displayBill().toString());
            //display total sales
            totalSalesJTextField.setText(dollars.format(totalSales));

            //save all transactions to the text area
            saleRecordsJTextArea.append(outJTextArea.getText());
            
            //set the print buttons enalbes after enter
            printReceiptJButton.setEnabled(true);
            printTransactionHistorytJButton.setEnabled(true);
            resetJButton.setEnabled(true);
            
            //display total sales amount after each transaction
            saleRecordsJTextArea.append("Total Sales Amount(until now): " + dollars.format(totalSales) + "\n\n\n");
        }

        //write all transactions to an external file
        try {
            FileWriter myFileWriter = new FileWriter("src/TicketVending/SalesRecords.txt", true);
            try (BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter)) {
                outJTextArea.write(myBufferedWriter);
            }
            outJTextArea.requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_enterJButtonActionPerformed

    /**
     * Clears only amount due field, reset Jspinners and select the first movie.
     *
     * @param evt ActionEvent preformed by clearJButton
     */
    private void clearJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJButtonActionPerformed
        // TODO add your handling code here:
        outJTextArea.setText("");
        amountJTextField.setText("$0.00");
        movieJComboBox.setSelectedIndex(0);
        regularTicketsJSpinner.setValue(0);
        discountTicketsJSpinner.setValue(0);

        enterJButton.requestFocus();
    }//GEN-LAST:event_clearJButtonActionPerformed

    /**
     * if the tickets spinners box change, change the amount due dynamically .
     *
     * @param evt ChangeEvent performed by discountTicketsJSpinner
     */
    private void discountTicketsJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_discountTicketsJSpinnerStateChanged
        updateAmountDue();
    }//GEN-LAST:event_discountTicketsJSpinnerStateChanged

    /**
     * if the tickets spinners box change, change the amount due dynamically .
     *
     * @param evt ChangeEvent performed by discountTicketsJSpinner
     */
    private void regularTicketsJSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_regularTicketsJSpinnerStateChanged
        updateAmountDue();
    }//GEN-LAST:event_regularTicketsJSpinnerStateChanged

    /**
     * Creates a new MovieDetailJDialog GUI object passing this form and setting
     * it visible.
     *
     * @param evt ActionEvent performed by infoJButtonAction
     */
    private void infoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoJButtonActionPerformed
        String seletecMovie = (String) movieJComboBox.getSelectedItem();
        MovieInfor movie = FindMovieByTitle(seletecMovie);

        MovieDetailJDialog detailDialog;
        try {
            detailDialog = new MovieDetailJDialog(this, rootPaneCheckingEnabled, movie);

            detailDialog.setVisible(true);

        } catch (MalformedURLException ex) {
            Logger.getLogger(TicketVendingGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_infoJButtonActionPerformed

    /**
     * Print out the form using print method .
     *
     * @param evt ActionEvent performed by printTransactionHistory
     */
    private void printTransactionHistorytJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTransactionHistorytJButtonActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            saleRecordsJTextArea.print();
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_printTransactionHistorytJButtonActionPerformed

    /**
     * Print out the form by passing to printTransactionHistoryActionItem evt.
     *
     * @param evt ActionEvent performed by printTransactionHistory
     */
    private void printTransactionHistoryJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printTransactionHistoryJMenuItemActionPerformed
        printTransactionHistorytJButtonActionPerformed(evt);
    }//GEN-LAST:event_printTransactionHistoryJMenuItemActionPerformed

    /**
     * Reset every to the original state. Clear all text area and text fields
     * including total sale amount and total sales text area
     *
     * @param evt ActionEvent performed by resetJButtonAction
     */
    private void resetJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetJButtonActionPerformed
            String[] objectButtons = {"Yes","No"};
    int promptResult = JOptionPane.showOptionDialog(null, 
        "Did you save everything? \nAre you sure you want to reset everthing?", "Warning Message", 
        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
        objectButtons,objectButtons[1]);
    if(promptResult==0)
    {
      outJTextArea.setText("");
        saleRecordsJTextArea.setText("");
        totalSales = 0.00;
        totalSalesJTextField.setText("$0.00");

        clearJButtonActionPerformed(evt);

        enterJButton.requestFocus();       
    }
    
    }//GEN-LAST:event_resetJButtonActionPerformed

    /**
     * check if it is a discount time for early shows.
     *
     * @param evt ItemEvent performed by showtimeJComboBoxItem
     */
    private void showtimeJComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_showtimeJComboBoxItemStateChanged
        isDiscount();
        updateAmountDue();
    }//GEN-LAST:event_showtimeJComboBoxItemStateChanged

    /**
     * focus on transactions History tab
     *
     * @param evt ActionEvent performed by openTransactionHistoryJMenuItem
     */
    private void openTransactionHistoryJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openTransactionHistoryJMenuItemActionPerformed
        transactionJTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_openTransactionHistoryJMenuItemActionPerformed

    /**
     * reset everything to the original state by passing it to resetJButton
     *
     * @param evt ActionEvent performed by resetJButton
     */
    private void resetJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetJMenuItemActionPerformed
        resetJButtonActionPerformed(evt);
    }//GEN-LAST:event_resetJMenuItemActionPerformed

    /**
     * focus on receipt tab
     *
     * @param evt ActionEvent performed by receiptJMenuItem
     */
    private void receiptJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptJMenuItemActionPerformed
        transactionJTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_receiptJMenuItemActionPerformed

    /**
     * Open the saved sales records -- all transactions history
     *
     * @param evt ActionEvent performed by openSalesRecordsJButton
     */
    private void updateMoviesBoxWeeklyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMoviesBoxWeeklyJButtonActionPerformed

         JFileChooser chooser = new JFileChooser("src/TicketVending/");
            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File chosenFile = chooser.getSelectedFile();
                readFromFile("src/TicketVending/" + chosenFile.getName());
            }
    }//GEN-LAST:event_updateMoviesBoxWeeklyJButtonActionPerformed

    private void updateMoviesJMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateMoviesJMenuItemActionPerformed
        updateMoviesBoxWeeklyJButtonActionPerformed(evt);
    }//GEN-LAST:event_updateMoviesJMenuItemActionPerformed

    /**
     * Main method, set the Nimbus look and feel running the program along with
     * a 4.7 second sleep for Splash Screen to load.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicketVendingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicketVendingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicketVendingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicketVendingGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Splash mySplash = new Splash(4700);
        mySplash.showSplash();
        // Show main form centered 
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        TicketVendingGUI myTicketVendingGUI = new TicketVendingGUI();
        int x = (screen.width - myTicketVendingGUI.getWidth()) / 2;
        int y = (screen.height - myTicketVendingGUI.getHeight()) / 2;
        myTicketVendingGUI.setBounds(x, y, myTicketVendingGUI.getWidth(), myTicketVendingGUI.getHeight());

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicketVendingGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutJMenuItem;
    private javax.swing.JMenu actionJMenu;
    private javax.swing.JPanel amountJPanel;
    private javax.swing.JTextField amountJTextField;
    private javax.swing.JButton clearJButton;
    private javax.swing.JMenuItem clearJMenuItem;
    private javax.swing.JPanel discountJPanel;
    private javax.swing.JSpinner discountTicketsJSpinner;
    private javax.swing.JScrollPane displayJScrollPane;
    private javax.swing.JButton enterJButton;
    private javax.swing.JMenuItem exitJMenuItem;
    private javax.swing.JMenu fileJMenu;
    private javax.swing.JPopupMenu.Separator fileJSeparator;
    private javax.swing.JPanel headerJPanel;
    private javax.swing.JMenu helpJMenu;
    private javax.swing.JButton infoJButton;
    private javax.swing.JLabel iscountTicketsJLabel;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel logo1JLabel;
    private javax.swing.JLabel logo2JLabel;
    private javax.swing.JLabel messageJLabel;
    private javax.swing.JComboBox<String> movieJComboBox;
    private javax.swing.JMenuItem openTransactionHistoryJMenuItem;
    private javax.swing.JTextArea outJTextArea;
    private javax.swing.JLabel priceJLabel;
    private javax.swing.JMenuItem printJMenuItem;
    private javax.swing.JButton printReceiptJButton;
    private javax.swing.JMenuItem printReceiptJMenuItem;
    private javax.swing.JMenuItem printTransactionHistoryJMenuItem;
    private javax.swing.JButton printTransactionHistorytJButton;
    private javax.swing.JMenuItem receiptJMenuItem;
    private javax.swing.JPanel receiptJPanel;
    private javax.swing.JLabel regularTicketsJLabel;
    private javax.swing.JSpinner regularTicketsJSpinner;
    private javax.swing.JButton resetJButton;
    private javax.swing.JMenuItem resetJMenuItem;
    private javax.swing.JTextArea saleRecordsJTextArea;
    private javax.swing.JPanel selectionJPanel;
    private javax.swing.JComboBox<String> showtimeJComboBox;
    private javax.swing.JPanel ticketJFormattedTextField;
    private javax.swing.JMenuBar ticketVendingJMenuBar;
    private javax.swing.JLabel totalSalesJLabel;
    private javax.swing.JTextField totalSalesJTextField;
    private javax.swing.JPanel transactionHistoryJPanel;
    private javax.swing.JScrollPane transactionHistoryJScrollPane;
    private javax.swing.JTabbedPane transactionJTabbedPane;
    private javax.swing.JButton updateMoviesBoxWeeklyJButton;
    private javax.swing.JMenuItem updateMoviesJMenuItem;
    // End of variables declaration//GEN-END:variables

    private boolean checkMovieData(String[] movieStr) {
        return true;
    }

    private MovieInfor FindMovieByTitle(String seletecMovieTitle) {

        for (int i = 0; i < movieList.size(); i++) {
            MovieInfor movie = movieList.get(i);

            if (movie.Title.equals(seletecMovieTitle)) {
                return movie;
            }
        }

        return null;
    }
}
