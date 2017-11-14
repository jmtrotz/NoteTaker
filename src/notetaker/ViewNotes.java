package notetaker;

// Import utilities
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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
	private HashSet<String> classes = new HashSet<>();
    private final ImageIcon folderIcon = new ImageIcon("images/folder.jpg");
    private String path = "C:\\notes"; 
   
    
    /**
     * Constructor
     */
    public ViewNotes() 
    {
    	classes.add("CS 305");
    	classes.add("CS 405");
    	classes.add("CT 206");
    	classes.add("CT 406");
    	classes.add("SE 321");
    	classes.add("SE 451");
    	classes.add("MA 124");
    	
    	this.setLayout(new GridLayout(getRows(), 2));
    	for (String name : classes)
    	{
    		JButton newClassButton = new JButton(name, folderIcon);
    		newClassButton.setHorizontalTextPosition(SwingConstants.CENTER);
    		newClassButton.addActionListener(new ClassListener());
    		this.add(newClassButton);
    	}
    }
    
    public int getRows()
    {
    	int items = classes.size();
    	if (items%2==0)
    	{
    		return items/2;
    	}
    	else
    	{
    		return items/2+1;
    	}
    }
    /**
     * Attempts to set a new path for the ViewNotes window, changing the directory for the NoteTaker program.
     * @param newPath the new path to be set as a String
     * @return successful completion as a boolean
     */
    public boolean setPath(String newPath)
    {
    	boolean success = false;
    	
    	try
    	{
    		File testPath = new File(newPath);
    		if (testPath.isDirectory()) //Verify that the new Path is a directory since we can't base our tree in a file.
    		{
    			success = true;
    			this.path = newPath;
    		}
    	}
    	catch(NullPointerException npe)
    	{
    		System.out.println("Line 82: " + npe.getLocalizedMessage());
    	}
    	catch(SecurityException se)
    	{
    		System.out.println("Line 91: " +se.getLocalizedMessage());
    	}
    	finally
    	{
    		return success;
    	}
    }
    
    /**
     * Listener class for the class buttons
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

/*    
*     * Listener class for the class1 button
*     
*    public class Class1Listener implements ActionListener
*    {
*        @Override
*        public void actionPerformed(ActionEvent e)
*        {
*            // If this button is selected, call chooseFile() and feed it the file path
*            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CS405";
*            chooseFile(filePath);
*        }
*    }
*
*    
*     * Listener class for the class2 button
*    
*    public class Class2Listener implements ActionListener
*    {
*        @Override
*        public void actionPerformed(ActionEvent e)
*        {
*            // If this button is selected, call chooseFile() and feed it the file path
*            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CT206";
*            chooseFile(filePath);
*        }
*    }
*
*    
*     * Listener class for the class3 button
*     
*    public class Class3Listener implements ActionListener
*    {
*        @Override
*        public void actionPerformed(ActionEvent e)
*        {
*            // If this button is selected, call chooseFile() and feed it the file path
*            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\CT406";
*            chooseFile(filePath);
*        }
*    }
*
*    
*     * Listener class for the class4 button
*     
*    public class Class4Listener implements ActionListener
*    {
*        @Override
*        public void actionPerformed(ActionEvent e)
*        {
*            // If this button is selected, call chooseFile() and feed it the file path
*            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\SE321";
*            chooseFile(filePath);
*        }
*    }
*    
*    
*     * Listener class for the class5 button
*     
*    public class Class5Listener implements ActionListener
*    {
*        @Override
*        public void actionPerformed(ActionEvent e)
*        {
*            // If this button is selected, call chooseFile() and feed it the file path
*            String filePath = "C:\\Users\\jmtro\\Documents\\SE321\\demo\\SE451";
*            chooseFile(filePath);
*        }
/*    }
    
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
    
    /**
     * Encompasses the settings panel including all options available to the ViewNotes panel
     * @author kireh
     *
     */
    class SettingsFrame extends JFrame
    {
    	String path = "C:\\";
    	
    	public SettingsFrame(ViewNotes parent)
    	{
    		super("Settings");
    		this.path = parent.path;
    		
    	}
    }
}