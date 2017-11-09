package notetaker;

// Import utilities
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
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
        this.setLayout(new GridLayout(3, 3));
        this.add(class0);
        this.add(class1);
        this.add(class2);
        this.add(class3);
        this.add(class4);
        this.add(class5);
    }
    
    /**
     * Listener for the class0 button. Launches a new window for viewing
     * saved notes
     */
    public class Class0Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Class0Notes class0Notes = new Class0Notes();
            class0Notes.setTitle("CS 305");
            class0Notes.setSize(700, 700);
            class0Notes.setLocationRelativeTo(null);
            class0Notes.setVisible(true);
        }
    }
    
    /**
     * Listener for the class1 button. Launches a new window for viewing
     * saved notes
     */
    public class Class1Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Class1Notes class1Notes = new Class1Notes();
            class1Notes.setTitle("CS 405");
            class1Notes.setSize(700, 700);
            class1Notes.setLocationRelativeTo(null);
            class1Notes.setVisible(true);
        }
    }
    
    /**
     * Listener for the class2 button. Launches a new window for viewing
     * saved notes
     */
    public class Class2Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Class2Notes class2Notes = new Class2Notes();
            class2Notes.setTitle("CT 206");
            class2Notes.setSize(700, 700);
            class2Notes.setLocationRelativeTo(null);
            class2Notes.setVisible(true);
        }
    }
    
    /**
     * Listener for the class3 button. Launches a new window for viewing
     * saved notes
     */
    public class Class3Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Class3Notes class3Notes = new Class3Notes();
            class3Notes.setTitle("CT 406");
            class3Notes.setSize(700, 700);
            class3Notes.setLocationRelativeTo(null);
            class3Notes.setVisible(true);
        }
    }
    
    /**
     * Listener for the class4 button. Launches a new window for viewing
     * saved notes
     */
    public class Class4Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Class4Notes class4Notes = new Class4Notes();
            class4Notes.setTitle("SE 321");
            class4Notes.setSize(700, 700);
            class4Notes.setLocationRelativeTo(null);
            class4Notes.setVisible(true);
        }
    }
    
    /**
     * Listener for the class4 button. Launches a new window for viewing
     * saved notes
     */
    public class Class5Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Class5Notes class5Notes = new Class5Notes();
            class5Notes.setTitle("SE 451");
            class5Notes.setSize(700, 700);
            class5Notes.setLocationRelativeTo(null);
            class5Notes.setVisible(true);
        }
    }
}