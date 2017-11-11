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
    private final String folder = "images/folder.jpg";
    private final ImageIcon folderIcon = new ImageIcon(folder);
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
        // Set text position for the buttons
        class0.setHorizontalTextPosition(SwingConstants.CENTER);
        class1.setHorizontalTextPosition(SwingConstants.CENTER);
        class2.setHorizontalTextPosition(SwingConstants.CENTER);
        class3.setHorizontalTextPosition(SwingConstants.CENTER);
        class4.setHorizontalTextPosition(SwingConstants.CENTER);
        class5.setHorizontalTextPosition(SwingConstants.CENTER);
        
        // Add listeners to the buttons
        class0.addActionListener(new Class0Listener());
        class1.addActionListener(new Class1Listener());
        class2.addActionListener(new Class2Listener());
        class3.addActionListener(new Class3Listener());
        class4.addActionListener(new Class4Listener());
        class5.addActionListener(new Class5Listener());
        
        // Set the layout and add the components
        this.setLayout(new GridLayout(3, 2));
        this.add(class0);
        this.add(class1);
        this.add(class2);
        this.add(class3);
        this.add(class4);
        this.add(class5);
    }
    
    public class Class0Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CS305";
            chooseFile(filePath);
        }
    }

    public class Class1Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CS405";
            chooseFile(filePath);
        }
    }

    public class Class2Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CT206";
            chooseFile(filePath);
        }
    }

    public class Class3Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CT406";
            chooseFile(filePath);
        }
    }

    public class Class4Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\SE321";
            chooseFile(filePath);
        }
    }
    
    public class Class5Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\SE451";
            chooseFile(filePath);
        }
    }
    
    private void chooseFile(String filePath)
    {
        JFileChooser fileChooser = new JFileChooser(new File(filePath));
        int selection = fileChooser.showOpenDialog(null);

        if (selection == JFileChooser.APPROVE_OPTION) 
        {
            File fileToOpen = fileChooser.getSelectedFile();
            
            try 
            {
                Desktop.getDesktop().open(fileToOpen);
            } 
                
            catch (IOException ex) 
            {
                Logger.getLogger(ViewNotes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}