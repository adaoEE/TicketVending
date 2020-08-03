package TicketVending;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javax.swing.ImageIcon;

/**
 *******************************************************************************
 * File name - MovieDetailJDialog.java
 * <br>Description This is the Movie Detail JDialog for showing title, runtime,
 * rating, preview, audience scores of the selected movie. The application
 * simulates a poster movie at the theater. Require the MovieList(an ArrayList)
 * to display the information.
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
 * @see java.awt.Color
 * @see java.net.MalformedURLException
 * @see java.net.URL
 * @see java.text.DecimalFormat
 * @see java.util.logging.Level
 * @see java.util.logging.Logger
 * @see javax.swing.ImageIcon
 * ****************************************************************************
 */
public class MovieDetailJDialog extends javax.swing.JDialog {

    public MovieInfor movieInfo = new MovieInfor();

    /**
     * Constructor creates a new Movie Detail JDialog object that contains all
     * the needed information of the selected movie, and display preview, show
     * time, rating, explain the rating
     *
     * @param parent frame that JDialog uses to show
     * @param modal frame uses modal of the movie
     * @param movie derives from MovieInfor to set information
     * @throws MalformedURLException to prevent errors from malformed URL
     */
    public MovieDetailJDialog(java.awt.Frame parent, boolean modal, MovieInfor movie) throws MalformedURLException {
        super(parent, modal);
        initComponents();
        //set color
        this.getContentPane().setBackground(new Color(0, 153, 51));
        //center the form on startup
        this.setLocationRelativeTo(null);
        //set the close button as default
        this.getRootPane().setDefaultButton(closeJButton);

        DecimalFormat duration = new DecimalFormat("###");
        movieInfo = movie;
        //check input
        if (movieInfo != null) {

            detailJTextArea.setText("The Paradise Theater represents \n **" + movieInfo.Title + "**\n");
            detailJTextArea.append("\n");
            detailJTextArea.append("Movie Infomation:\n\n" + movieInfo.Preview);

            logoLabel2.setText(movieInfo.Score);
            titleJLabel.setText(movieInfo.Title);
            ratingJLabel.setText(movieInfo.Rating);
            durationJLabel.setText(duration.format(movieInfo.Runtime));

            //set poster from an external url
            URL url = new URL(movieInfo.ImageUrl);
            ImageIcon imgThisImg = new ImageIcon(url);

            moviePosterJLabel.setIcon(imgThisImg);

            //set ToolTips to explain what rating means
            if (movieInfo.Rating.endsWith("R")) {
                ratingJTextArea.setText("No one under 17 admitted without an accompanying parent or guardian.");
                ratingJLabel.setToolTipText("No one under 17 admitted without an accompanying parent or guardian.");
            } else if (movieInfo.Rating.endsWith("G")) {
                ratingJTextArea.setText("General audiences: No substance abuse, or realistic/noncartoon violence.");
                ratingJLabel.setToolTipText("General audiences: No substance abuse, or realistic/noncartoon violence.");
            } else if (movieInfo.Rating.endsWith("PG")) {
                ratingJTextArea.setText("Parental guidance: Some material may not be suitable for children");
                ratingJLabel.setToolTipText("Parental guidance: Some material may not be suitable for children");
            } else if (movieInfo.Rating.endsWith("13")) {
                ratingJTextArea.setText("Any nudity has to be nonsexual, and any swear words have to be used sparingly. Violence in PG-13 films may be intense, but must be bloodless");
                ratingJLabel.setToolTipText("Any nudity has to be nonsexual, and any swear words have to be used sparingly. Violence in PG-13 films may be intense, but must be bloodless");
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeJButton = new javax.swing.JButton();
        warningJLabel = new javax.swing.JLabel();
        googleLogoJLabel = new javax.swing.JLabel();
        detailJScrollPane = new javax.swing.JScrollPane();
        detailJTextArea = new javax.swing.JTextArea();
        moviePosterJLabel = new javax.swing.JLabel();
        headerJPanel = new javax.swing.JPanel();
        titleJLabel = new javax.swing.JLabel();
        exitJLabel = new javax.swing.JLabel();
        logoLabel2 = new javax.swing.JLabel();
        logoLabel4 = new javax.swing.JLabel();
        authorJLabel = new javax.swing.JLabel();
        authorJLabel2 = new javax.swing.JLabel();
        logoLabel5 = new javax.swing.JLabel();
        getinTouchJLabel = new javax.swing.JLabel();
        twitterLogoJLabel = new javax.swing.JLabel();
        facebookLogoJLabel = new javax.swing.JLabel();
        logoLabel3 = new javax.swing.JLabel();
        logoLabel6 = new javax.swing.JLabel();
        logoLabel7 = new javax.swing.JLabel();
        companyNameJLabel = new javax.swing.JLabel();
        ratingJLabel = new javax.swing.JLabel();
        rateLabel = new javax.swing.JLabel();
        showingRatingJLabel = new javax.swing.JLabel();
        minutesJLabel = new javax.swing.JLabel();
        durationJLabel = new javax.swing.JLabel();
        runtimeJLabel = new javax.swing.JLabel();
        trailerJLabel = new javax.swing.JLabel();
        ratingJScrollPane = new javax.swing.JScrollPane();
        ratingJTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Regular Polygons 1.0 About");
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        closeJButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        closeJButton.setForeground(new java.awt.Color(0, 102, 51));
        closeJButton.setText("Close");
        closeJButton.setSelected(true);
        closeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeJButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 620, 96, -1));

        warningJLabel.setBackground(new java.awt.Color(102, 102, 102));
        warningJLabel.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        warningJLabel.setForeground(new java.awt.Color(204, 204, 204));
        warningJLabel.setText("Experience The Difference");
        getContentPane().add(warningJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 640, 240, -1));

        googleLogoJLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        googleLogoJLabel.setForeground(new java.awt.Color(0, 102, 51));
        googleLogoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Google_Plus_30px.png"))); // NOI18N
        googleLogoJLabel.setText("f");
        googleLogoJLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(googleLogoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 620, 30, 30));

        detailJScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 3));
        detailJScrollPane.setToolTipText("");
        detailJScrollPane.setViewportBorder(new javax.swing.border.MatteBorder(null));
        detailJScrollPane.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        detailJTextArea.setEditable(false);
        detailJTextArea.setColumns(20);
        detailJTextArea.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        detailJTextArea.setLineWrap(true);
        detailJTextArea.setRows(5);
        detailJTextArea.setWrapStyleWord(true);
        detailJTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 48, 64)));
        detailJTextArea.setMargin(new java.awt.Insets(100, 100, 100, 100));
        detailJTextArea.setSelectedTextColor(new java.awt.Color(51, 157, 107));
        detailJTextArea.setSelectionColor(new java.awt.Color(153, 153, 153));
        detailJScrollPane.setViewportView(detailJTextArea);

        getContentPane().add(detailJScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 420, 460));

        moviePosterJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Movie_100px.png"))); // NOI18N
        moviePosterJLabel.setPreferredSize(new java.awt.Dimension(300, 450));
        getContentPane().add(moviePosterJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 300, 450));

        headerJPanel.setBackground(new java.awt.Color(36, 48, 64));
        headerJPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerJPanelMouseDragged(evt);
            }
        });
        headerJPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerJPanelMousePressed(evt);
            }
        });
        headerJPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleJLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        titleJLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleJLabel.setText("Movie Details Title:");
        headerJPanel.add(titleJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 750, 80));

        exitJLabel.setBackground(new java.awt.Color(255, 51, 51));
        exitJLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        exitJLabel.setForeground(new java.awt.Color(255, 0, 51));
        exitJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/closeIcon.png"))); // NOI18N
        exitJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitJLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitJLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitJLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitJLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitJLabelMouseReleased(evt);
            }
        });
        headerJPanel.add(exitJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 50, 40));

        logoLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        logoLabel2.setForeground(new java.awt.Color(255, 255, 255));
        logoLabel2.setToolTipText("The percentage of users who have rated this movie 3.5 stars or higher");
        headerJPanel.add(logoLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, 120, 80));

        logoLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 16)); // NOI18N
        logoLabel4.setForeground(new java.awt.Color(255, 255, 255));
        logoLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Help_52px.png"))); // NOI18N
        logoLabel4.setText("Audience Score: ");
        headerJPanel.add(logoLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 200, 60));

        authorJLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        authorJLabel.setForeground(new java.awt.Color(204, 204, 204));
        authorJLabel.setText("The Paradise Theater represents");
        headerJPanel.add(authorJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 250, 20));

        authorJLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        authorJLabel2.setForeground(new java.awt.Color(204, 204, 204));
        authorJLabel2.setText("liked it!");
        headerJPanel.add(authorJLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 230, 80, 30));

        logoLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Popcorn_100px.png"))); // NOI18N
        headerJPanel.add(logoLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, 110, 120));

        getContentPane().add(headerJPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 291));

        getinTouchJLabel.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        getinTouchJLabel.setForeground(new java.awt.Color(36, 48, 64));
        getinTouchJLabel.setText("Enjoy your movie!");
        getContentPane().add(getinTouchJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 590, 170, 30));

        twitterLogoJLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        twitterLogoJLabel.setForeground(new java.awt.Color(0, 102, 51));
        twitterLogoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Twitter_30px.png"))); // NOI18N
        twitterLogoJLabel.setText("f");
        twitterLogoJLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(twitterLogoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 620, 30, 30));

        facebookLogoJLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        facebookLogoJLabel.setForeground(new java.awt.Color(0, 102, 51));
        facebookLogoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Facebook_30px.png"))); // NOI18N
        facebookLogoJLabel.setText("f");
        facebookLogoJLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(facebookLogoJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 620, 30, 30));

        logoLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_3D_Glasses_30px.png"))); // NOI18N
        logoLabel3.setToolTipText("A three-dimensional stereoscopic film");
        getContentPane().add(logoLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 660, -1, 40));

        logoLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_4k_30px_3.png"))); // NOI18N
        logoLabel6.setToolTipText("a resolution of 3840 pixels × 2160 lines");
        getContentPane().add(logoLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, -1, 40));

        logoLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Dolby_Digital_30px.png"))); // NOI18N
        logoLabel7.setToolTipText("Dolby Digital provides five full-bandwidth channels, front left, front right, center, surround left, and surround right, for true surround sound quality.");
        getContentPane().add(logoLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 660, -1, 40));

        companyNameJLabel.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        companyNameJLabel.setForeground(new java.awt.Color(204, 204, 204));
        companyNameJLabel.setText("The Paradise Theater");
        getContentPane().add(companyNameJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 650, 190, 30));

        ratingJLabel.setBackground(new java.awt.Color(255, 255, 255));
        ratingJLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        ratingJLabel.setForeground(new java.awt.Color(36, 48, 64));
        ratingJLabel.setToolTipText("");
        getContentPane().add(ratingJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 290, 90, 70));

        rateLabel.setBackground(new java.awt.Color(255, 255, 255));
        rateLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        rateLabel.setForeground(new java.awt.Color(36, 48, 64));
        rateLabel.setText("Rating:");
        rateLabel.setToolTipText("Rating included: G, PG, PG-13, R (restricted), NC-17 (no one under 17), Unrated");
        getContentPane().add(rateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 290, 130, 70));

        showingRatingJLabel.setBackground(new java.awt.Color(255, 255, 255));
        showingRatingJLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        showingRatingJLabel.setForeground(new java.awt.Color(36, 48, 64));
        showingRatingJLabel.setToolTipText("");
        getContentPane().add(showingRatingJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 320, 90, 70));

        minutesJLabel.setBackground(new java.awt.Color(255, 255, 255));
        minutesJLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        minutesJLabel.setForeground(new java.awt.Color(36, 48, 64));
        minutesJLabel.setText("minutes");
        minutesJLabel.setToolTipText("");
        getContentPane().add(minutesJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 490, 70, 40));

        durationJLabel.setBackground(new java.awt.Color(255, 255, 255));
        durationJLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        durationJLabel.setForeground(new java.awt.Color(36, 48, 64));
        durationJLabel.setToolTipText("");
        getContentPane().add(durationJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 440, 90, 70));

        runtimeJLabel.setBackground(new java.awt.Color(255, 255, 255));
        runtimeJLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        runtimeJLabel.setForeground(new java.awt.Color(36, 48, 64));
        runtimeJLabel.setText("Runtime:");
        runtimeJLabel.setToolTipText("duration");
        getContentPane().add(runtimeJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 440, 110, 70));

        trailerJLabel.setBackground(new java.awt.Color(255, 51, 51));
        trailerJLabel.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        trailerJLabel.setForeground(new java.awt.Color(255, 255, 0));
        trailerJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_Play_100px_1.png"))); // NOI18N
        trailerJLabel.setToolTipText("PLAY THE TRAILER");
        trailerJLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trailerJLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                trailerJLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                trailerJLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                trailerJLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                trailerJLabelMouseReleased(evt);
            }
        });
        getContentPane().add(trailerJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, 100, 90));

        ratingJScrollPane.setBackground(new java.awt.Color(0, 153, 51));
        ratingJScrollPane.setBorder(null);
        ratingJScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ratingJScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        ratingJScrollPane.setEnabled(false);

        ratingJTextArea.setEditable(false);
        ratingJTextArea.setBackground(new java.awt.Color(0, 153, 51));
        ratingJTextArea.setColumns(20);
        ratingJTextArea.setFont(new java.awt.Font("Franklin Gothic Book", 0, 12)); // NOI18N
        ratingJTextArea.setForeground(new java.awt.Color(36, 48, 64));
        ratingJTextArea.setLineWrap(true);
        ratingJTextArea.setRows(5);
        ratingJTextArea.setText("Explains what rating means");
        ratingJTextArea.setWrapStyleWord(true);
        ratingJScrollPane.setViewportView(ratingJTextArea);

        getContentPane().add(ratingJScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 350, 200, 70));

        getAccessibleContext().setAccessibleName("About Form");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    Color myColor1 = new Color(0, 102, 51);
    Color myColor2 = new Color(51, 157, 107);
    Color myColor3 = new Color(255,255,0);
    
    /**
     * Exits the JDialog
     *
     * @param evt ActionEvent performed by exitJMenuItem
     */
    private void closeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeJButtonActionPerformed
        // Dispose the JDialog from only
        this.dispose();
    }//GEN-LAST:event_closeJButtonActionPerformed

    /**
     * drag the form by pressing the top panel
     */
    int xy;
    int xx;

    /**
     * drag the form by pressing the top panel
     *
     * @param evt MouseDragged performed by headerJPanel
     */
    private void headerJPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerJPanelMouseDragged

        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_headerJPanelMouseDragged

    /**
     * get the location of the mouse
     *
     * @param evt MousePressed performed by headerJPanelMouse
     */
    private void headerJPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerJPanelMousePressed
        // TODO add your handling code here:
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_headerJPanelMousePressed

    /**
     * customizes the exit JButton -- spice the 2D design
     *
     * @param evt MouseExited performed by closeJLabel
     */
    private void exitJLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitJLabelMouseExited
        exitJLabel.setBackground(myColor1);
        exitJLabel.setOpaque(false);
    }//GEN-LAST:event_exitJLabelMouseExited

    /**
     * set color when mouse entered
     */
   

    /**
     * highlight color when mouse entered
     *
     * @param evt MouseEvent performed by closeJLabelMouseEntered
     */
    private void exitJLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitJLabelMouseEntered
        exitJLabel.setOpaque(true);
        exitJLabel.setBackground(myColor2);
    }//GEN-LAST:event_exitJLabelMouseEntered

    /**
     * Exits the application
     *
     * @param evt MouseEvent performed by closeJLabel
     */
    private void exitJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitJLabelMouseClicked
        this.dispose();
    }//GEN-LAST:event_exitJLabelMouseClicked

    /**
     * set color back to normal when release mouse
     *
     * @param evt MouseEvent performed by closeJLabelMouseReleased
     */
    private void exitJLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitJLabelMouseReleased
        exitJLabel.setBackground(myColor1);
        exitJLabel.setOpaque(false);
    }//GEN-LAST:event_exitJLabelMouseReleased

    /**
     * change color when mouse pressed
     *
     * @param evt MouseEvent performed by exitJLabelMousePressed
     */
    private void exitJLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitJLabelMousePressed
        exitJLabel.setOpaque(true);
        exitJLabel.setBackground(Color.RED);
    }//GEN-LAST:event_exitJLabelMousePressed

    /**
     * play the trailer from the external file
     * @param evt MouseEvent perform by MouseClicked
     */
    private void trailerJLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trailerJLabelMouseClicked
         try {
            String url = String.valueOf(movieInfo.VideoUrl);
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            System.out.println(url);
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_trailerJLabelMouseClicked

    /**
     * customizes the exit JButton -- spice the 2D design
     *
     * @param evt MouseExited performed by trailerJLabel
     */
    private void trailerJLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trailerJLabelMouseEntered
         trailerJLabel.setOpaque(true);
        trailerJLabel.setBackground(myColor3);
    }//GEN-LAST:event_trailerJLabelMouseEntered
    /**
     * customizes the exit JButton -- spice the 2D design
     *
     * @param evt MouseExited performed by trailerJLabel
     */
    private void trailerJLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trailerJLabelMouseExited
        // TODO add your handling code here:
        trailerJLabel.setBackground(myColor1);
        trailerJLabel.setOpaque(false);
    }//GEN-LAST:event_trailerJLabelMouseExited
/**
     * customizes the exit JButton -- spice the 2D design
     *
     * @param evt MouseExited performed by trailerJLabel
     */
    private void trailerJLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trailerJLabelMousePressed
         trailerJLabel.setOpaque(true);
        trailerJLabel.setBackground(Color.RED);
    }//GEN-LAST:event_trailerJLabelMousePressed
/**
     * customizes the exit JButton -- spice the 2D design
     *
     * @param evt MouseExited performed by trailerJLabel
     */
    private void trailerJLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trailerJLabelMouseReleased
      trailerJLabel.setBackground(myColor1);
        trailerJLabel.setOpaque(false);
    }//GEN-LAST:event_trailerJLabelMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(MovieDetailJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovieDetailJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovieDetailJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovieDetailJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MovieDetailJDialog dialog;
                    dialog = new MovieDetailJDialog(new javax.swing.JFrame(), true, null);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(MovieDetailJDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorJLabel;
    private javax.swing.JLabel authorJLabel2;
    private javax.swing.JButton closeJButton;
    private javax.swing.JLabel companyNameJLabel;
    private javax.swing.JScrollPane detailJScrollPane;
    private javax.swing.JTextArea detailJTextArea;
    private javax.swing.JLabel durationJLabel;
    private javax.swing.JLabel exitJLabel;
    private javax.swing.JLabel facebookLogoJLabel;
    private javax.swing.JLabel getinTouchJLabel;
    private javax.swing.JLabel googleLogoJLabel;
    private javax.swing.JPanel headerJPanel;
    private javax.swing.JLabel logoLabel2;
    private javax.swing.JLabel logoLabel3;
    private javax.swing.JLabel logoLabel4;
    private javax.swing.JLabel logoLabel5;
    private javax.swing.JLabel logoLabel6;
    private javax.swing.JLabel logoLabel7;
    private javax.swing.JLabel minutesJLabel;
    private javax.swing.JLabel moviePosterJLabel;
    private javax.swing.JLabel rateLabel;
    private javax.swing.JLabel ratingJLabel;
    private javax.swing.JScrollPane ratingJScrollPane;
    private javax.swing.JTextArea ratingJTextArea;
    private javax.swing.JLabel runtimeJLabel;
    private javax.swing.JLabel showingRatingJLabel;
    private javax.swing.JLabel titleJLabel;
    private javax.swing.JLabel trailerJLabel;
    private javax.swing.JLabel twitterLogoJLabel;
    private javax.swing.JLabel warningJLabel;
    // End of variables declaration//GEN-END:variables
}
