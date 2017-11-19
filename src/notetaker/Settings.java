package notetaker;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

/**
 * Settings panel for the application
 * @author Jeffrey Trotz
 * @version 1.0.1
 */
public class Settings extends JFrame
{    
    // These text ares are global so methods like .getTtext() 
    // can be called on them from the listener classes
    private final JTextField class1Name = new JTextField(20);
    private final JTextField class2Name = new JTextField(20);
    private final JTextField class3Name = new JTextField(20);
    private final JTextField class4Name = new JTextField(20);
    private final JTextField class5Name = new JTextField(20);
    private final JTextField class6Name = new JTextField(20);    
    private final JTextField class1PathChooser = new JTextField(20);
    private final JTextField class2PathChooser = new JTextField(20);
    private final JTextField class3PathChooser = new JTextField(20);
    private final JTextField class4PathChooser = new JTextField(20);
    private final JTextField class5PathChooser = new JTextField(20);
    private final JTextField class6PathChooser = new JTextField(20); 
    
    /**
     * Constructor
     */
    public Settings()
    {
        class1Name.setBorder(new TitledBorder("Name for class 1:"));
        class2Name.setBorder(new TitledBorder("Name for class 2:"));
        class3Name.setBorder(new TitledBorder("Name for class 3:"));
        class4Name.setBorder(new TitledBorder("Name for class 4:"));
        class5Name.setBorder(new TitledBorder("Name for class 5:"));
        class6Name.setBorder(new TitledBorder("Name for class 6:"));        
        class1PathChooser.setBorder(new TitledBorder("Choose folder location for class 1:"));
        class2PathChooser.setBorder(new TitledBorder("Choose folder location for class 2:"));
        class3PathChooser.setBorder(new TitledBorder("Choose folder location for class 3:"));
        class4PathChooser.setBorder(new TitledBorder("Choose folder location for class 4:"));
        class5PathChooser.setBorder(new TitledBorder("Choose folder location for class 5:"));
        class6PathChooser.setBorder(new TitledBorder("Choose folder location for class 6:"));        
        class1PathChooser.addMouseListener(new PathButtonListener());
        class2PathChooser.addMouseListener(new PathButtonListener());
        class3PathChooser.addMouseListener(new PathButtonListener());
        class4PathChooser.addMouseListener(new PathButtonListener());
        class5PathChooser.addMouseListener(new PathButtonListener());
        class6PathChooser.addMouseListener(new PathButtonListener());
        
        JButton save = new JButton("Save");
        save.addActionListener(new SaveButtonListener());
        
        this.setLayout(new FlowLayout());
        this.add(class1Name);
        this.add(class2Name);
        this.add(class3Name);
        this.add(class4Name);
        this.add(class5Name);
        this.add(class6Name);
        this.add(class1PathChooser);
        this.add(class2PathChooser);
        this.add(class3PathChooser);
        this.add(class4PathChooser);
        this.add(class5PathChooser);
        this.add(class6PathChooser);
        this.add(save);
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
                if (mouseEvent.getSource() == class1PathChooser)
                {
                    class1PathChooser.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == class2PathChooser)
                {
                    class2PathChooser.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == class3PathChooser)
                {
                    class3PathChooser.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == class4PathChooser)
                {
                    class4PathChooser.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == class5PathChooser)
                {
                    class5PathChooser.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
                
                else if (mouseEvent.getSource() == class6PathChooser)
                {
                    class6PathChooser.setText(fileChooser.getSelectedFile().getAbsolutePath());
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
            HashMap<String, String> settings = new HashMap<>();
            
            settings.put(class1Name.getText(), class1PathChooser.getText());
            settings.put(class2Name.getText(), class2PathChooser.getText());
            settings.put(class3Name.getText(), class3PathChooser.getText());
            settings.put(class4Name.getText(), class4PathChooser.getText());
            settings.put(class5Name.getText(), class5PathChooser.getText());
            settings.put(class6Name.getText(), class6PathChooser.getText());
            
            DatabaseMethods database = new DatabaseMethods();            
            Iterator iterator = settings.keySet().iterator();
            
            while(iterator.hasNext()) 
            {
                String className = iterator.next().toString();
                String classPath = settings.get(className);
                
                database.setSettings(className, classPath);
            }
        }
    }
}