package notetaker;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Main welcome window for the application
 * @author Jeffrey Trotz @ Kireh Wright
 * @version 1.0.1
 */
public class NoteTaker extends JFrame 
{
    /**
     * Constructor
     */
    public NoteTaker() 
    {
        JButton viewNotes = new JButton("View Saved Notes");
        JButton takeNotes = new JButton("Take a New Note");
        JButton settingsButton = new JButton("Settings");
        JLabel imagePanel = new JLabel(new ImageIcon("images/logo.png"), JLabel.CENTER);
        viewNotes.addActionListener(new ViewNotesListener());
        takeNotes.addActionListener(new TakeNotesListener());
        settingsButton.addActionListener(new SettingsListener());

        this.setLayout(new FlowLayout());
        this.add(imagePanel);
        this.add(viewNotes);
        this.add(takeNotes);
        this.add(settingsButton);
    }

    /**
     * Main method
     * @param args - Command line arguments
     */
    public static void main(String[] args)
    {
        NoteTaker noteTaker = new NoteTaker();
        noteTaker.setTitle("Note Taker");
        noteTaker.setSize(450, 590);
        noteTaker.setLocationRelativeTo(null);
        noteTaker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        noteTaker.setVisible(true);
    }

    /**
     * Listener class for the "View Notes" button. Opens a new window for 
     * viewing saved notes
     */
    public class ViewNotesListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            ViewNotes viewNotes = new ViewNotes();
            viewNotes.setTitle("View Notes");
            viewNotes.setSize(600, 650);
            viewNotes.setLocationRelativeTo(null);
            viewNotes.setVisible(true);
        }
    }

    /**
     * Listener class for the "Take Notes" button. Opens NotePad since there 
     * wasn't enough time to code my own text editor
     */
    public class TakeNotesListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            try 
            {
                Desktop.getDesktop().open(new File(System.getenv("SystemRoot") + "\\notepad.exe"));
            } 
            
            catch (IOException ioException) 
            {
               System.out.println(ioException.getMessage());
            }
        }
    }
    
    /**
     * Listener class for the "Settings" button. Opens a new window for changing 
     * class names or changing the location of saved notes
     */
    public class SettingsListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            Settings settings = new Settings();
            settings.setTitle("Settings");
            settings.setSize(300, 610);
            settings.setLocationRelativeTo(null);
            settings.setVisible(true);
        }
    }
}