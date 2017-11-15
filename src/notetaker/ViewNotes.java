package notetaker;

// Import utilities
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private String path = System.getProperty("user.dir") + "\\notes"; 
   
    
    /**
     * Constructs a new ViewNotes frame, populates it with appropriate classes, presents the frame
     */
    public ViewNotes() 
    {
    	System.out.println(path);
    	System.out.println(getClasses(path));
    	//System.out.println(addClass("MA355"));
    	addClass();
    	
    	this.setLayout(new GridLayout(getRows(), 2));
    	for (String name : classes)
    	{
    		JButton newClassButton = new JButton(name, folderIcon);
    		newClassButton.setHorizontalTextPosition(SwingConstants.CENTER);
    		newClassButton.addActionListener(new ClassListener());
    		this.add(newClassButton);
    	}
    }
    
    /**
     * Returns a row calculation for proper display in the grid
     * @return number of rows as an int
     */
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
    		else if (!testPath.exists())
    		{
    			//this block occurs if the path needs to be created
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
            String openFilePath = path +"\\" +sender.getText();
            System.out.println(openFilePath);
            chooseFile(openFilePath);
        }
    }
    
    /**
     * Method to open the file explorer for viewing saved notes
     * @param filePath - Path to the folder where the file explorer will open
     */
    private void chooseFile(String filePath)
    {
        // Create JFileChooser object with path to the folder where it will open
        JFileChooser fileChooser = new JFileChooser(new File(filePath));
        
        // Store the option selected by the user (open, cancel, etc)
        int selection = fileChooser.showOpenDialog(this);

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
     * Prompts the user to enter the class name to be added
     * @return The name of the new class as a String
     */
    public String promptAdd()
    {
    	return JOptionPane.showInputDialog(this, "Enter the name of the class to add:", "Add a class", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Adds a class to the list of viewable folders
     * @param newClassName the name of the new class to be added as a String with no spaces
     * @return success as a boolean
     */
    private String addClass()
    {
    	String newClassName = promptAdd();
    	if (this.isClean(newClassName) && classes.add(newClassName))
    	{
    		String command = "powershell.exe  New-Item -Path" + " \"" +path +"\\" +newClassName +"\"" +" -ItemType \"directory\""; //this command does not work with spaces
        	String line = "";
        	System.out.println(command);
        	try
        	{
        	  // Executing the command
        	  Process powerShellProcess = Runtime.getRuntime().exec(command);
        	  powerShellProcess.getOutputStream().close();
        	  BufferedReader stdout = new BufferedReader(new InputStreamReader(
        			    powerShellProcess.getInputStream()));
        	  while ((line =stdout.readLine()) != null) 
        	  {
        		  System.out.println(line);
        	  }
        	  getClasses(path);
        	  return "line 178: true";
        	  
        	}
        	catch (SecurityException se)
        	{
        		return "false1";
        	}
        	catch(IOException ioe)
        	{
        		return "false2";
        	}
        	catch(NullPointerException npe)
        	{
        		return "false3";
        	}
        	catch(IllegalArgumentException iae)
        	{
        		return "false4";
        	}
    	}
    	else
    	{
    		return "false5";
    	}
    }
    
    /**
     * Method to remove a class from the classes list and 
     * @param className
     * @return
     */
    public boolean removeclass(String className)
    {
    	return false;
    }
    
    /**
     * Will allow for a script to run in order to get and return an array of classes
     * @param path the path to be searched as a String
     * @return classes list as a String
     */
    public String getClasses(String path)
    {
    	String command = "powershell.exe  Get-ChildItem " + path +" -Directory | Select-Object Name | ft -hide" ;
    	String line = "";
    	HashSet<String> ans = new HashSet<>();
    	try
    	{
    	  // Executing the command
    	  Process powerShellProcess = Runtime.getRuntime().exec(command);
    	  // Getting the results
    	  powerShellProcess.getOutputStream().close();
    	  BufferedReader stdout = new BufferedReader(new InputStreamReader(
    			    powerShellProcess.getInputStream()));
    	  while (((line =stdout.readLine()) != null)) 
    	  {
    		  if (!line.isEmpty())
    		   System.out.println(line);
    		  ans.add(line);
    		  classes = ans;
    	  }
    	  return "ViewNotes updated classes successfully";
    	  
    	}
    	catch (SecurityException se)
    	{
    		return("getClasses isn't allowed to run powershell");
    	}
    	catch(IOException ioe)
    	{
    		return("getClasses has caught an IOException");
    	}
    	catch(NullPointerException npe)
    	{
    		return("null pointer in getClasses method");
    	}
    	catch(IllegalArgumentException iae)
    	{
    		return "illegal argument in getClasses";
    	}
    }
    
    private boolean isClean(String s)
    {
    	return (s!=null && !s.equals("") && !s.equals("null"));
    }
    
    /**
     * Encompasses the settings panel including all options available to the ViewNotes panel
     * @author kireh
     *
     */
    class SettingsDialog extends JDialog
    {
    	String path = "C:\\";
    	
    	public SettingsDialog(ViewNotes parent)
    	{
    		super(parent, "Settings", true);
    		
    	}
    }
    
    /**
     * Encapsulates the dialog that allows a user to add a class to the ViewNotes panel
     * @author kireh
     *
     */
    class addClassPane extends JOptionPane
    {
    	public addClassPane(JFrame owner)
    	{
    		
    	}
    }
    
    /**
     * Encapsulates the dialog that allows a user to remove a class from the ViewNotes panel
     * @author kireh
     */
    class removeClassDialog extends JDialog
    {
    	
    }
}