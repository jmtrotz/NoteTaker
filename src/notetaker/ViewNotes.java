package notetaker;

// Import utilities
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 * Window for viewing enrolled classes/saved notes
 * @author: Jeffrey Trotz
 * @version: 0.1
 */
public class ViewNotes extends JFrame 
{
    // Declare and set properties for attributes used in this class
    private final ImageIcon folderIcon = new ImageIcon("images/folder.jpg");
    private final JButton class0 = new JButton("CS 305", folderIcon);
    private final JButton class1 = new JButton("CS 405", folderIcon);
    private final JButton class2 = new JButton("CT 206", folderIcon);
    private final JButton class3 = new JButton("CT 406", folderIcon);
    private final JButton class4 = new JButton("SE 321", folderIcon);
    private final JButton class5 = new JButton("SE 451", folderIcon);
   
    
    /**
     * Constructor
     */
    public ViewNotes() 
    {
        // Set position for the buttons
        class0.setHorizontalTextPosition(SwingConstants.CENTER);
        class1.setHorizontalTextPosition(SwingConstants.CENTER);
        class2.setHorizontalTextPosition(SwingConstants.CENTER);
        class3.setHorizontalTextPosition(SwingConstants.CENTER);
        class4.setHorizontalTextPosition(SwingConstants.CENTER);
        class5.setHorizontalTextPosition(SwingConstants.CENTER);
        
        // Add listeners to the buttons
        class0.addActionListener(new ClassListener());
        class1.addActionListener(new ClassListener());
        class2.addActionListener(new ClassListener());
        class3.addActionListener(new ClassListener());
        class4.addActionListener(new ClassListener());
        class5.addActionListener(new ClassListener());
        
        // Set the layout and add the components
        this.setLayout(new GridLayout(3, 2));
        this.add(class0);
        this.add(class1);
        this.add(class2);
        this.add(class3);
        this.add(class4);
        this.add(class5);
    }
    
    /**
     * Listener class for the class0 button
     */
    public class ClassListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	JButton sender = (JButton) e.getSource();
            // If this button is selected, call chooseFile() and feed it the file path
            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\" +sender.getText();
            chooseFile(filePath);
        }
    }

//    /**
//     * Listener class for the class1 button
//     */
//    public class Class1Listener implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e)
//        {
//            // If this button is selected, call chooseFile() and feed it the file path
//            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CS405";
//            chooseFile(filePath);
//        }
//    }
//
//    /**
//     * Listener class for the class2 button
//     */
//    public class Class2Listener implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e)
//        {
//            // If this button is selected, call chooseFile() and feed it the file path
//            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CT206";
//            chooseFile(filePath);
//        }
//    }
//
//    /**
//     * Listener class for the class3 button
//     */
//    public class Class3Listener implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e)
//        {
//            // If this button is selected, call chooseFile() and feed it the file path
//            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CT406";
//            chooseFile(filePath);
//        }
//    }
//
//    /**
//     * Listener class for the class4 button
//     */
//    public class Class4Listener implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e)
//        {
//            // If this button is selected, call chooseFile() and feed it the file path
//            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\SE321";
//            chooseFile(filePath);
//        }
//    }
//    
//    /**
//     * Listener class for the class5 button
//     */
//    public class Class5Listener implements ActionListener
//    {
//        @Override
//        public void actionPerformed(ActionEvent e)
//        {
//            // If this button is selected, call chooseFile() and feed it the file path
//            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\SE451";
//            chooseFile(filePath);
//        }
//    }
    
    /**
     * Method to open the file explorer for viewing saved notes
     * @param filePath - Path to the folder where the file explorer will open
     */
    private void chooseFile(String filePath)
    {
        // Create JFileChooser object with path to the folder where it will open
        JFileChooser fileChooser = new JFileChooser(new File(filePath));
        
        // Store the option selected by the user (open, cancel, etc)
        int selection = fileChooser.showOpenDialog(null);

        // If the user selects "open", open the file
        if (selection == JFileChooser.APPROVE_OPTION) 
        {
            File fileToOpen = fileChooser.getSelectedFile();
            
            // Try to open the file
            try 
            {
                Desktop.getDesktop().open(fileToOpen);
            } 
                
            // Catch any IO exceptions
            catch (IOException ex) 
            {
                Logger.getLogger(ViewNotes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}