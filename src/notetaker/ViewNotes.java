package notetaker;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 * Window for viewing enrolled classes and notes for these classes
 * @author Jeffrey Trotz
 * @version 1.0.1
 */
public final class ViewNotes extends JFrame 
{
    private HashMap<String, String> settings = new HashMap<>();
    
    /**
     * Constructor
     */
    public ViewNotes() 
    {  	
        settings = this.getSettings();
        this.setLayout(new GridLayout(3, 2));
        Iterator iterator = settings.keySet().iterator();
            
        while(iterator.hasNext()) 
        {
            String className = iterator.next().toString();
            JButton classButton = new JButton(className, new ImageIcon("images/folder.jpg"));
            classButton.setHorizontalTextPosition(SwingConstants.CENTER);
            classButton.addActionListener(new ClassButtonListener());
            this.add(classButton);
        }
    }
    
    /**
     * Method to get class names and file paths from the database
     * @return - Returns a hash map containing these settings
     */
    public HashMap getSettings()
    {
        DatabaseMethods database = new DatabaseMethods();
        settings = database.getSettings();
        
        return settings;
    }
    
    /**
     * Listener class for the class buttons. Opens a file chooser in the folder
     * containing the notes for the selected class
     */
    public class ClassButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            String filePath = settings.get(((JButton) actionEvent.getSource()).getText());
            JFileChooser fileChooser = new JFileChooser(new File(filePath));
            int selection = fileChooser.showOpenDialog(null);
                
            if (selection == JFileChooser.APPROVE_OPTION) 
            {
                File fileToOpen = fileChooser.getSelectedFile();

                try 
                {
                    Desktop.getDesktop().open(fileToOpen);
                } 

                catch (IOException ioException) 
                {
                    System.out.println(ioException.getMessage());
                }
                
            }
        }
    }
}