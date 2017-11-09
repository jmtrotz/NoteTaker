package notetaker;

// Import utilities
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Welcome window for the note taker
 * @author: Jeffrey Trotz
 * @version: 0.1
 */
public class NoteTaker extends JFrame 
{
    // Declare and set properties for attributes used in this class
    private final String logo = "images/logo.png";
    private final JButton viewNotes = new JButton("View Saved Notes");
    private final JButton takeNotes = new JButton("Take a New Note");
    private final ImageIcon image = new ImageIcon(logo);
    private final JLabel imagePanel = new JLabel(image, JLabel.CENTER);

    /**
     * Constructor
     */
    public NoteTaker() 
    {
        // Add listeners for the buttons
        viewNotes.addActionListener(new ViewNotesListener());
        takeNotes.addActionListener(new TakeNotesListener());

        // Set the layout and add the components
        this.setLayout(new FlowLayout());
        this.add(imagePanel);
        this.add(viewNotes);
        this.add(takeNotes);
    }

    /**
     * Main method
     * @param args: Command line arguments
     */
    public static void main(String[] args)
    {
        NoteTaker noteTaker = new NoteTaker();
        noteTaker.setTitle("Note Taker");
        noteTaker.setSize(500, 600);
        noteTaker.setLocationRelativeTo(null);
        noteTaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        noteTaker.setVisible(true);
    }

    /**
     * Listener for the view notes button. Launches a new window for 
     * viewing enrolled classes/saved notes
     */
    public class ViewNotesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ViewNotes viewNotes = new ViewNotes();
            viewNotes.setTitle("View Notes");
            viewNotes.setSize(700, 700);
            viewNotes.setLocationRelativeTo(null);
            viewNotes.setVisible(true);
        }
    }

    /**
     * Listener for the take notes button. Launches Notepad for 
     * the text editor since there isn't enough time to code our
     * own text editor...
     */
    public class TakeNotesListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try 
            {
                Desktop.getDesktop().open(new File("C:\\Windows\\notepad.exe"));
            } 
            
            catch (IOException ex) 
            {
                Logger.getLogger(NoteTaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}