package TicketVending;


import java.awt.*;
import javax.swing.*;
// Progressbar
public class Splash extends JWindow {
    //instance variables  
    private JProgressBar loading = new JProgressBar();
    private int duration;
    private int progress;

    //Constructor
    public Splash(int dur) {
        this.duration = dur;
    }

    public void showSplash() {
        // set counting % to bar
        loading.setStringPainted(true);
        //Cast the contenter to JPanel
        JPanel content = (JPanel) getContentPane();
        content.setBackground(new Color(0,204,153));
        //Set the windows size and location
        int width = 500, height = 220;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        //x,y indicate the location of the start point of the content JPanel.
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);

        //build the splash screen
        JLabel image = new JLabel(new ImageIcon("src/images/slashscreen.gif"));
        content.add(image, BorderLayout.CENTER);
        content.add(loading, BorderLayout.SOUTH);

        //Select border color and size
        Color border = new Color(240,240,240,55);
        content.setBorder(BorderFactory.createLineBorder(border));

        //Display the JWindow
        this.setVisible(true);

        //Wait a little, while loading resources(fake)
        try {
            incProgress(100); //call a method to increment progress bar
            Thread.sleep(duration);
            this.dispose();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void incProgress(int value) {
        //Instantiate an object from the inner class IncProgress and start the thread
        IncProgress up = new IncProgress(value);
        up.myThread.start();
       
    }

    //Inner class IncProgress
    class IncProgress {

        private int howFar;

        public IncProgress(int value) {
            howFar = value;
        }
        private Thread myThread = new Thread(new Runnable() {
            public void run() {
                //Increment the progress bar
                while (progress < 2 * howFar) {
                    progress++;
                    try {
                        Thread.sleep(45);
                    } catch (InterruptedException exp) {
                        exp.printStackTrace();
                    }
                    loading.setValue(progress);
                }
            }
        });
    }
}
