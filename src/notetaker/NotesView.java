package notetaker;

/**
 * 
 * @author kireh
 *
 */

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener; 

public class NotesView extends JFrame
{
	// Declare and set properties for attributes used in this class
	private HashSet<String> classes = new HashSet<>();
	private final ImageIcon folderIcon = new ImageIcon("images/folder.jpg");
	private String path = System.getProperty("user.dir") + "\\notes";
	
	//GUI instance variables
	private ClassListPanel listView = null;
	private NotesListPanel fileView = null;
	
	public NotesView()
	{
		super("Notes");
		this.setLayout(new BorderLayout());
		getClasses(path);
		listView = new ClassListPanel();
		listView.setSize(330, 590);
		fileView = new NotesListPanel();
		this.add(listView, BorderLayout.WEST);
		this.add(fileView, BorderLayout.CENTER);
		this.setSize(990, 590);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listView.setVisible(true);
		this.setVisible(true);
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

    public String[] classArr()
    {
    	int arrSize = classes.size();
    	Object[] temp = classes.toArray();
    	String[] ans = new String[arrSize];
    	for (int i = 0; i<classes.size(); i++)
    	{
    		ans[i] = (String)temp[i];
    	}
    	return ans;
    }
    
    public String getNotes(String className)
    {
    	return "";
    }
	
	
	class UtilityBar extends JToolBar
	{
		
	}
	
	class ClassListPanel extends JPanel
	{
		
		public ClassListPanel()
		{
			super();
			String[] l=classArr(); 
			JList<String> classList = new JList<>(l);
			classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			classList.setLayoutOrientation(JList.VERTICAL);
			classList.setVisibleRowCount(10);
			classList.addListSelectionListener(new ClassListener());
			classList.setSize(330, 590);
			JScrollPane scroll = new JScrollPane(classList);
			scroll.setSize(new Dimension(330, 590));
			scroll.setMinimumSize(new Dimension (330, 590));
			this.add(scroll);
			classList.setVisible(true);
			scroll.setVisible(true);
		}
		
		class ClassListener implements ListSelectionListener
		{

			@Override
			public void valueChanged(ListSelectionEvent e) 
			{
				JList list = (JList)(e.getSource());
				int i = list.getSelectedIndex();
				if (i>=0)
				{
					getNotes((String)list.getSelectedValue());
				}
			}
			
		}
	}
	
	class NotesListPanel extends JPanel
	{
		
	}
}
