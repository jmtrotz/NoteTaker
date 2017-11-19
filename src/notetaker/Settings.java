package notetaker;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

/**
 * Settings panel for the application
 * @author Jeffrey Trotz
 * @version 1.1.2
 */
public class Settings extends JFrame
{    
    private HashMap<String, String> oldSettings = new HashMap<>();
    private final ArrayList<JTextField> classNameList = new ArrayList<>();
    private final ArrayList<JTextField> classPathList = new ArrayList<>();
    
    /**
     * Constructor
     */
    public Settings()
    {
        oldSettings = this.getSettings();
        JButton save = new JButton("Save");
        save.addActionListener(new SaveButtonListener());        
        this.setLayout(new FlowLayout());        
        Iterator iterator = oldSettings.keySet().iterator();
        int index = 1;
        
        while(iterator.hasNext()) 
        {
            String classNameText = iterator.next().toString();
            String classPathText = oldSettings.get(classNameText);
            
            JTextField className = new JTextField(20);
            className.setBorder(new TitledBorder("Name for class " + index + ":"));          
            className.setText(classNameText);
            classNameList.add(className);
            
            JTextField classPath = new JTextField(20);
            classPath.setBorder(new TitledBorder("Choose folder location for class " + index + ":"));
            classPath.addMouseListener(new PathButtonListener());
            classPath.setText(classPathText);
            classPathList.add(classPath);
            
            this.add(className);
            this.add(classPath);
            index ++;
        }
        
        this.add(save);
    }
    
    /**
     * Method to get class names and file paths from the database
     * @return - Returns a hash map containing these settings
     */
    public HashMap getSettings()
    {
        DatabaseMethods database = new DatabaseMethods();
        oldSettings = database.getSettings();
        
        return oldSettings;
    }
    
    /**
     * Listener class for the text areas to choose the location of class notes.
     * Opens a file chooser in the user's documents folder when they click 
     * in the text area
     */
    public class PathButtonListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent mouseEvent)
        {
            JFileChooser fileChooser = new JFileChooser(new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath()));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int selection = fileChooser.showOpenDialog(null);

            if (selection == JFileChooser.APPROVE_OPTION) 
            {
                if (mouseEvent.getSource() == classPathList.get(0))
                {
                    classPathList.get(0).setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == classPathList.get(1))
                {
                    classPathList.get(1).setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == classPathList.get(2))
                {
                    classPathList.get(2).setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == classPathList.get(3))
                {
                    classPathList.get(3).setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == classPathList.get(4))
                {
                    classPathList.get(4).setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == classPathList.get(5))
                {
                    classPathList.get(5).setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        }
    }
    
    /**
     * Listener class for the save button. Gets all data from the text areas and
     * stores it in the database
     */
    public class SaveButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent) 
        {
            HashMap<String, String> newSettings = new HashMap<>();
            newSettings.put(classNameList.get(0).getText(), classPathList.get(0).getText());
            newSettings.put(classNameList.get(1).getText(), classPathList.get(1).getText());
            newSettings.put(classNameList.get(2).getText(), classPathList.get(2).getText());
            newSettings.put(classNameList.get(3).getText(), classPathList.get(3).getText());
            newSettings.put(classNameList.get(4).getText(), classPathList.get(4).getText());
            newSettings.put(classNameList.get(5).getText(), classPathList.get(5).getText());           
            
            DatabaseMethods database = new DatabaseMethods();            
            Iterator iterator = newSettings.keySet().iterator();
            
            while(iterator.hasNext()) 
            {
                String className = iterator.next().toString();
                String classPath = newSettings.get(className);
                
                database.setSettings(className, classPath);
            }
            
            JFrame parent = new JFrame();
            JOptionPane.showMessageDialog(parent, "Settings Saved!");
        }
    }
}